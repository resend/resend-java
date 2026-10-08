package com.resend.services;

import com.resend.Resend;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.core.net.RequestOptions;
import com.resend.core.service.BaseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationOnMock;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;

public class RequestOptionsCoverageTest {

    private static final Set<String> WITHOUT_OPTIONS_VARIANT = new HashSet<>(Arrays.asList(
            "Emails.metrics()",
            "Webhooks.verify(VerifyWebhookOptions)"));

    private static final Set<String> NOT_INVOKED = new HashSet<>(Collections.singletonList(
            "ContactImports.create(CreateContactImportOptions, RequestOptions)"));

    @Test
    public void testEveryServiceMethodHasAnOverloadAcceptingRequestOptions() {
        List<String> missing = new ArrayList<>();

        for (Class<?> service : serviceClasses()) {
            for (Method method : httpMethods(service)) {
                Class<?>[] params = method.getParameterTypes();
                if (params.length > 0 && params[params.length - 1] == RequestOptions.class) {
                    continue;
                }
                if (WITHOUT_OPTIONS_VARIANT.contains(describe(method))) {
                    continue;
                }
                Class<?>[] expected = Arrays.copyOf(params, params.length + 1);
                expected[params.length] = RequestOptions.class;
                try {
                    service.getDeclaredMethod(method.getName(), expected);
                } catch (NoSuchMethodException e) {
                    missing.add(describe(method));
                }
            }
        }

        assertTrue(missing.isEmpty(),
                "These public service methods have no overload with a trailing RequestOptions parameter: " + missing);
    }

    @Test
    public void testEveryRequestOptionsOverloadForwardsTheOptionsToTheHttpClient() throws Exception {
        List<String> failures = new ArrayList<>();
        int invoked = 0;

        for (Class<?> service : serviceClasses()) {
            for (Method method : httpMethods(service)) {
                Class<?>[] params = method.getParameterTypes();
                if (params.length == 0 || params[params.length - 1] != RequestOptions.class) {
                    continue;
                }
                if (NOT_INVOKED.contains(describe(method))) {
                    continue;
                }

                RequestOptions options = RequestOptions.builder().setIdempotencyKey("key-" + method.getName()).build();
                Call call = call(service, method, options);
                if (call.failure != null) {
                    failures.add(describe(method) + " threw " + call.failure);
                    continue;
                }

                invoked++;
                if (!forwarded(call.httpClient, options)) {
                    failures.add(describe(method) + " did not pass the RequestOptions to the HTTP client");
                }
            }
        }

        assertTrue(failures.isEmpty(), failures.size() + " method(s) failed:\n" + String.join("\n", failures));
        assertTrue(invoked > 100, "expected to exercise the whole API surface, only invoked " + invoked);
    }

    @Test
    public void testOverloadsWithoutOptionsKeepSendingRequestsWithoutThem() throws Exception {
        List<String> failures = new ArrayList<>();

        for (Class<?> service : serviceClasses()) {
            for (Method method : httpMethods(service)) {
                Class<?>[] params = method.getParameterTypes();
                if (params.length > 0 && params[params.length - 1] == RequestOptions.class) {
                    continue;
                }
                if (WITHOUT_OPTIONS_VARIANT.contains(describe(method)) && method.getName().equals("verify")) {
                    continue;
                }
                if (describe(method).equals("ContactImports.create(CreateContactImportOptions)")) {
                    continue;
                }

                Call call = call(service, method, null);
                if (call.failure != null) {
                    failures.add(describe(method) + " threw " + call.failure);
                    continue;
                }

                for (Invocation invocation : mockingDetails(call.httpClient).getInvocations()) {
                    for (Object argument : invocation.getArguments()) {
                        if (argument instanceof RequestOptions) {
                            failures.add(describe(method) + " sent a RequestOptions it was never given");
                        }
                    }
                }
            }
        }

        assertTrue(failures.isEmpty(), failures.size() + " method(s) failed:\n" + String.join("\n", failures));
    }

    private static List<Class<?>> serviceClasses() {
        Set<Class<?>> found = new LinkedHashSet<>();
        collect(new Resend("re_test"), found);
        assertTrue(found.size() >= 24, "expected to discover every service from the Resend facade, found " + found);
        return new ArrayList<>(found);
    }

    private static void collect(Object holder, Set<Class<?>> found) {
        for (Method accessor : holder.getClass().getMethods()) {
            if (accessor.getParameterCount() != 0 || Modifier.isStatic(accessor.getModifiers())
                    || !BaseService.class.isAssignableFrom(accessor.getReturnType())) {
                continue;
            }
            try {
                Object service = accessor.invoke(holder);
                if (service != null && found.add(service.getClass())) {
                    collect(service, found);
                }
            } catch (ReflectiveOperationException e) {
                throw new AssertionError("could not call " + accessor, e);
            }
        }
    }

    private static List<Method> httpMethods(Class<?> service) {
        return Arrays.stream(service.getDeclaredMethods())
                .filter(m -> Modifier.isPublic(m.getModifiers()) && !Modifier.isStatic(m.getModifiers()))
                .filter(m -> !m.isSynthetic() && !m.isBridge())
                .filter(m -> !m.isAnnotationPresent(Deprecated.class))
                .filter(m -> Arrays.asList(m.getExceptionTypes()).contains(com.resend.core.exception.ResendException.class))
                .sorted((a, b) -> describe(a).compareTo(describe(b)))
                .collect(Collectors.toList());
    }

    private static String describe(Method method) {
        return method.getDeclaringClass().getSimpleName() + "." + method.getName() + "("
                + Arrays.stream(method.getParameterTypes()).map(Class::getSimpleName).collect(Collectors.joining(", "))
                + ")";
    }

    @SuppressWarnings("unchecked")
    private static IHttpClient<String> httpClientAnsweringOk() {
        return (IHttpClient<String>) mock(IHttpClient.class, invocation ->
                invocation.getMethod().getReturnType() == AbstractHttpResponse.class
                        ? new AbstractHttpResponse<>(200, "{}", true)
                        : null);
    }

    private static Object newService(Class<?> service, IHttpClient<String> httpClient) throws Exception {
        Constructor<?> constructor = service.getConstructor(String.class, IHttpClient.class);
        return constructor.newInstance("re_test", httpClient);
    }

    private static boolean forwarded(IHttpClient<String> httpClient, RequestOptions options) {
        for (Invocation invocation : mockingDetails(httpClient).getInvocations()) {
            for (Object argument : invocation.getArguments()) {
                if (argument == options) {
                    return true;
                }
            }
        }
        return false;
    }

    private static final class Call {

        private final IHttpClient<String> httpClient;
        private final Throwable failure;

        Call(final IHttpClient<String> httpClient, final Throwable failure) {
            this.httpClient = httpClient;
            this.failure = failure;
        }
    }

    private static Call call(Class<?> service, Method method, RequestOptions options) throws Exception {
        Call call = attempt(service, method, options, false);
        if (call.failure instanceof IllegalArgumentException) {
            call = attempt(service, method, options, true);
        }
        return call;
    }

    private static Call attempt(Class<?> service, Method method, RequestOptions options, boolean mocks) throws Exception {
        IHttpClient<String> httpClient = httpClientAnsweringOk();
        Object instance = newService(service, httpClient);
        Class<?>[] params = method.getParameterTypes();
        Object[] args = new Object[params.length];
        int generated = options == null ? params.length : params.length - 1;
        for (int i = 0; i < generated; i++) {
            args[i] = argumentFor(params[i], mocks);
        }
        if (options != null) {
            args[params.length - 1] = options;
        }
        try {
            method.invoke(instance, args);
        } catch (InvocationTargetException e) {
            return new Call(httpClient, e.getCause());
        }
        return new Call(httpClient, null);
    }

    private static Object argumentFor(Class<?> type, boolean mocks) throws Exception {
        if (type == String.class) {
            return "id_123";
        }
        if (type == boolean.class || type == Boolean.class) {
            return false;
        }
        if (type == int.class || type == Integer.class) {
            return 1;
        }
        if (type == long.class || type == Long.class) {
            return 1L;
        }
        if (type == ListParams.class) {
            return ListParams.builder().build();
        }
        if (List.class.isAssignableFrom(type) || type == Iterable.class) {
            return new ArrayList<>();
        }
        if (Map.class.isAssignableFrom(type)) {
            return new HashMap<>();
        }
        if (type.isArray()) {
            return Array.newInstance(type.getComponentType(), 0);
        }
        if (type.isEnum()) {
            return type.getEnumConstants()[0];
        }
        if (mocks) {
            return mock(type, RequestOptionsCoverageTest::answerWithValidIdentifiers);
        }
        try {
            Method builder = type.getMethod("builder");
            Object built = builder.invoke(null);
            return built.getClass().getMethod("build").invoke(built);
        } catch (ReflectiveOperationException | RuntimeException e) {
            return mock(type, RequestOptionsCoverageTest::answerWithValidIdentifiers);
        }
    }

    private static Object answerWithValidIdentifiers(InvocationOnMock invocation) throws Throwable {
        if (invocation.getMethod().getReturnType() == String.class) {
            String name = invocation.getMethod().getName();
            if (name.equals("getEmail")) {
                return null;
            }
            return name.endsWith("Id") ? "id_123" : "value";
        }
        if (invocation.getMethod().getReturnType() == List.class
                && invocation.getMethod().getGenericReturnType() instanceof ParameterizedType) {
            Type element = ((ParameterizedType) invocation.getMethod().getGenericReturnType()).getActualTypeArguments()[0];
            if (element instanceof Class) {
                return new ArrayList<>(Collections.singletonList(argumentFor((Class<?>) element, true)));
            }
        }
        return Mockito.RETURNS_DEFAULTS.answer(invocation);
    }
}
