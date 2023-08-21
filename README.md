# Resend Java SDK

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
![Build](https://github.com/resendlabs/resend-java/actions/workflows/ci.yml/badge.svg)
![Release](https://img.shields.io/github/release/resendlabs/resend-java.svg?style=flat-square)
---

## Installation

To install the Java SDK add the dependency to your project:

Gradle:

```
implementation 'com.resend:resend-java:1.0.0'
```

Maven:

```
<dependency>
    <groupId>com.resend</groupId>
    <artifactId>resend-java</artifactId>
    <version>1.0.0</version>
</dependency>

```
## Setup

First, you need to get an API key, which is available in the [Resend Dashboard](https://resend.com).

Within the AuthenticationProvider, you need to programmatically pass an API Key. If you wish to utilize an environment variable instead, you can simply omit the parameter from the ResendEmails constructor, and it will automatically search for an environment variable called 'RESEND_API_KEY'.

## Example

```java
package com.resend;

import com.resend.services.emails.model.*;
import com.resend.core.provider.AuthenticationProvider;
import com.resend.core.provider.impl.AuthenticationProviderStandard;
import com.resend.services.emails.ResendEmails;

public class Main {
    public static void main(String[] args) {
        String apiKey = "re_123";

        AuthenticationProvider provider = new AuthenticationProviderStandard(apiKey);
        ResendEmails emailClient = new ResendEmails(provider);

        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .from("Me <me@exemple.io>")
                .to("to@example", "you@example.com")
                .cc("carbon@example.com", "copy@example.com")
                .bcc("blind@example.com", "carbon.copy@example.com")
                .replyTo("reply@example.com", "to@example.com")
                .text("Hello, world!")
                .subject("Hello from Java!")
                .build();

        SendEmailResponse ser = emailClient.sendEmail(sendEmailRequest);
        
    }
}


```

You can view all the examples in the [examples folder](https://github.com/resendlabs/resend-java-examples)
