# Contributing to resend-java

Thanks for helping improve the official Resend Java SDK.

## Development setup

1. Fork and clone the repository.
2. Use JDK **11+** for running tests (the library still targets Java 8 bytecode).
3. From the repo root, run:

```bash
./gradlew test
```

## Branching and pull requests

1. Create a focused branch from `main`, for example:
   - `fix/readme-example`
   - `feat/client-options`
2. Keep changes related to one concern when practical.
3. Make sure `./gradlew test` passes locally.
4. Open a PR against `resendlabs/resend-java` `main`.
5. In the PR description, include:
   - what changed
   - why it changed
   - how you tested it

## Coding guidelines

- Follow existing patterns in `src/main/java/com/resend/services/`.
- When adding an API method:
  - put request/response models under the service `model` package
  - expose the method on the service class
  - wire it through `Resend` if it is a top-level module
  - add unit tests that mock `IHttpClient` (see existing `*Test.java` files)
- Prefer non-breaking changes. If you must break an API, call it out clearly in the PR.
- Do not commit secrets, API keys, or local credential files.

## Useful references

- [Resend API docs](https://resend.com/docs/api-reference/introduction)
- [OpenAPI spec](https://github.com/resendlabs/resend-openapi)

## Questions

Open a GitHub issue if you are unsure whether a change belongs in this SDK.
