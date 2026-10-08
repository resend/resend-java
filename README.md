# Resend Java SDK

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
![Build](https://github.com/resendlabs/resend-java/actions/workflows/ci.yml/badge.svg)
![Release](https://img.shields.io/github/release/resendlabs/resend-java.svg?style=flat-square)
---

## Installation

To install the Java SDK, add the following dependency to your project:

Gradle

```gradle
implementation 'com.resend:resend-java:+'
```

Maven

```Maven
<dependency>
    <groupId>com.resend</groupId>
    <artifactId>resend-java</artifactId>
    <version>LATEST</version>
</dependency>

```
## Setup

First, you need to get an API key, which is available in the [Resend Dashboard](https://resend.com).
## Example

```java
package com.resend;

import com.resend.services.emails.model.*;
import com.resend.core.provider.AuthenticationProvider;
import com.resend.core.provider.impl.AuthenticationProviderStandard;
import com.resend.services.emails.ResendEmails;

public class Main {
    public static void main(String[] args) {
        Resend resend = new Resend("re_123");

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Me <me@exemple.io>")
                .to("to@example", "you@example.com")
                .cc("carbon@example.com", "copy@example.com")
                .bcc("blind@example.com", "carbon.copy@example.com")
                .replyTo("reply@example.com", "to@example.com")
                .text("Hello, world!")
                .subject("Hello from Java!")
                .build();

        try {
            CreateEmailResponse data = resend.emails().send(params);
            System.out.println(data.getId());
        } catch (ResendException e) {
            e.printStackTrace();
        }
    }
}


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

### Retries and timeouts

Retries are off by default. Set `maxRetries` on the builder to retry failed requests for every call:

```java
Resend resend = Resend.builder()
    .apiKey("re_123")
    .maxRetries(3)
    .build();
```

A request is retried when the API answers `429` or any `5xx`, or when the connection fails (refused, reset, or closed
mid-response). The SDK waits between attempts with exponential backoff (starting at 500 ms, capped at 5 s, with
jitter), or for as long as the `Retry-After` header asks for, up to 30 s.

When the last attempt still gets an error response from the API, you get the usual `ResendException`. When the last
attempt fails before any response arrives (a connection failure, a timeout, or a failure that retrying can't fix, such
as an unknown host or a TLS error), the SDK throws a `RuntimeException` wrapping the underlying `IOException`.

A `POST` may already have been processed when a `5xx` or a connection failure happens, so it is retried on `429`
always, but on `5xx` or connection failures only when it carries an idempotency key. Timeouts are never retried.

The waits between attempts block the calling thread and are not covered by the timeout, which applies to each
attempt separately. In the worst case a request takes about `(maxRetries + 1)` attempts plus up to 30 s of waiting per
retry, so keep `maxRetries` small for latency-sensitive code. Interrupting the thread ends the wait immediately.

`RequestOptions` can override both settings for a single request. The timeout covers one whole attempt, from
connecting to reading the full response, and replaces the client's `callTimeout` for that request:

```java
RequestOptions options = RequestOptions.builder()
    .setIdempotencyKey("order-1234")
    .maxRetries(5)
    .timeout(Duration.ofSeconds(15))
    .build();

CreateEmailResponse data = resend.emails().send(params, options);
```

Per-request options are available on `emails().send(...)`, `batch().send(...)` and `contacts().imports().create(...)`.

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

A custom `httpClient` can't be combined with `baseUrl`, the timeouts, `maxRetries` or `proxy`; configure those on your
client. To enable retries on the built-in `HttpClient`, pass the retry count as the third argument:
`new HttpClient(okHttpClient, "https://api.resend.com", 3)`.

You can view all the examples in the [examples folder](https://github.com/resendlabs/resend-java-example)
