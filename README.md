# Resend Java SDK

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
![Release](https://img.shields.io/github/release/resendlabs/resend-java.svg?style=flat-square)
---

## Installation

To install the Java SDK add the dependency to your project:

Gradle:

```
implementation 'com.resend-java:sdk:1.0.0'
```

Maven:

```
<dependency>
    <groupId>com.resend-java</groupId>
    <artifactId>resend-java</artifactId>
    <version>1.0.0</version>
</dependency>
```
## Setup

First, you need to get an API key, which is available in the [Resend Dashboard](https://resend.com).

## Example

```java
package com.resend;

import com.resend.core.model.*;
import com.resend.core.provider.AuthenticationProvider;
import com.resend.core.provider.impl.AuthenticationProviderStandard;
import com.resend.core.service.EmailService;

public class Main {
    public static void main(String[] args) {
        String apiKey = "re_123";

        AuthenticationProvider provider = new AuthenticationProviderStandard(apiKey);
        EmailService emailClient = new EmailService(provider);

        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .from("Me <me@exemple.io>")
                .to(Arrays.asList("to@example", "you@example.com"))
                .cc(Arrays.asList("cc@example.com"))
                .bcc(Arrays.asList("bcc@example.com"))
                .replyTo(Arrays.asList("reply@example.com", "to@example.com"))
                .text("Hello, world!")
                .subject("Hello from Java!")
                .build();

        SendEmailResponse ser = emailClient.sendEmail(sendEmailRequest);
        
    }
}


```

You can view all the examples in the [examples folder](https://github.com/resendlabs/resend-java-examples)
