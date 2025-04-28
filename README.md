# Resend Java SDK

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
![Build](https://github.com/resendlabs/resend-java/actions/workflows/ci.yml/badge.svg)
![Release](https://img.shields.io/github/release/resendlabs/resend-java.svg?style=flat-square)
---

## Installation

To install the Java SDK, add the following dependency to your project:

Gradle

```gradle
implementation 'com.resend:resend-java:4.2.0'
```

Maven

```Maven
<dependency>
    <groupId>com.resend</groupId>
    <artifactId>resend-java</artifactId>
    <version>4.2.0</version>
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

You can view all the examples in the [examples folder](https://github.com/resendlabs/resend-java-example)
