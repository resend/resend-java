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

### Create a domain

```java
import com.resend.services.domains.model.CreateDomainOptions;

resend.domains().create(CreateDomainOptions.builder()
        .name("example.com")
        .build());
```

### Verify a webhook signature

```java
import com.resend.services.webhooks.model.VerifyWebhookOptions;

resend.webhooks().verify(VerifyWebhookOptions.builder()
        .payload(rawBody)
        .addHeaders(requestHeaders)
        .secret(webhookSecret)
        .build());
```

## Available modules

| Method | Purpose |
|--------|---------|
| `emails()` | Send, list, cancel, and inspect emails |
| `batch()` | Send many emails in one request |
| `domains()` | Manage sending domains |
| `contacts()` | Manage contacts, segments membership, topics, imports |
| `segments()` | Manage audience segments |
| `broadcasts()` | Create and send broadcasts |
| `templates()` | Manage email templates |
| `webhooks()` | Manage and verify webhooks |
| `receiving()` | Read inbound emails |
| `topics()` | Manage subscription topics |
| `automations()` | Manage automations and runs |
| `suppressions()` | Manage suppression lists |
| `apiKeys()` | Manage API keys |
| `events()` / `logs()` / `usage()` | Events, logs, and account usage |
| `oauthGrants()` | List and revoke OAuth grants |

More examples live in the [resend-java-example](https://github.com/resendlabs/resend-java-example) repository.

## Documentation

- [Resend API reference](https://resend.com/docs/api-reference/introduction)
- [Contributing guide](CONTRIBUTING.md)

## License

MIT
