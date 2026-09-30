# Resend Java SDK

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
![Build](https://github.com/resendlabs/resend-java/actions/workflows/ci.yml/badge.svg)
![Release](https://img.shields.io/github/release/resendlabs/resend-java.svg?style=flat-square)

Official Java SDK for the [Resend](https://resend.com) email API.

## Installation

Add the dependency to your project. Prefer a pinned version (current release: **4.27.0**).

### Gradle

```gradle
implementation 'com.resend:resend-java:4.27.0'
```

### Maven

```xml
<dependency>
    <groupId>com.resend</groupId>
    <artifactId>resend-java</artifactId>
    <version>4.27.0</version>
</dependency>
```

## Setup

Create an API key in the [Resend Dashboard](https://resend.com), then create a client:

```java
import com.resend.Resend;

Resend resend = new Resend("re_123456789");
```

### Optional client configuration

Customize the base URL (useful for tests), User-Agent, and HTTP timeouts:

```java
import com.resend.Resend;
import com.resend.ResendOptions;

Resend resend = new Resend("re_123456789", ResendOptions.builder()
        .baseUrl("https://api.resend.com")
        .connectTimeoutMs(10_000L)
        .readTimeoutMs(30_000L)
        .writeTimeoutMs(30_000L)
        .build());
```

## Examples

### Send an email

```java
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;

public class Main {
    public static void main(String[] args) {
        Resend resend = new Resend("re_123456789");

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Acme <onboarding@resend.dev>")
                .to("delivered@resend.dev")
                .subject("Hello from Java")
                .html("<strong>It works!</strong>")
                .build();

        try {
            CreateEmailResponse data = resend.emails().send(params);
            System.out.println(data.getId());
        } catch (ResendException e) {
            System.err.println(e);
        }
    }
}
```

### Send with an idempotency key

```java
import com.resend.core.net.RequestOptions;
import com.resend.services.emails.model.CreateEmailOptions;

CreateEmailOptions params = CreateEmailOptions.builder()
        .from("Acme <onboarding@resend.dev>")
        .to("delivered@resend.dev")
        .subject("Hello from Java")
        .text("Hello, world!")
        .build();

RequestOptions requestOptions = RequestOptions.builder()
        .setIdempotencyKey("unique-key-123")
        .build();

resend.emails().send(params, requestOptions);
```

## Configuration

`new Resend("re_123")` uses sensible defaults. Use `Resend.builder()` to set a proxy, timeouts or a custom base URL:

```java
import com.resend.Resend;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;

// 1. Configure the proxy server
Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("your.proxy.host", 8080));

// 2. Add the proxy and any other options to the builder, then build the client
Resend resend = Resend.builder()
    .apiKey("re_123")
    .proxy(proxy)
    .connectTimeout(Duration.ofSeconds(5))
    .readTimeout(Duration.ofSeconds(30))
    .build();
```

Create one `Resend` instance and reuse it: every service it returns shares the same HTTP client.

### Custom HTTP client

To take full control of the HTTP layer, pass your own `IHttpClient` with `.httpClient(...)`. For example, to use
your own `OkHttpClient` (interceptors, TLS settings, a shared connection pool), add the
`com.squareup.okhttp3:okhttp-jvm` dependency to your build and wrap it in the built-in `HttpClient`:

```java
import com.resend.Resend;
import com.resend.core.net.impl.HttpClient;
import okhttp3.OkHttpClient;

// 1. Configure your OkHttpClient, e.g. with an interceptor that logs every request
OkHttpClient okHttpClient = new OkHttpClient.Builder()
    .addInterceptor(chain -> {
        System.out.println(chain.request().method() + " " + chain.request().url());
        return chain.proceed(chain.request());
    })
    .build();

// 2. Wrap it in the built-in HttpClient and build the client
Resend resend = Resend.builder()
    .apiKey("re_123")
    .httpClient(new HttpClient(okHttpClient, "https://api.resend.com"))
    .build();
```

A custom `httpClient` can't be combined with `baseUrl`, the timeouts or `proxy`; configure those on your client.

You can view all the examples in the [examples folder](https://github.com/resendlabs/resend-java-example)
