package com.resend.services.util;

import com.resend.services.templates.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for creating test data for Templates service.
 */
public class TemplatesUtil {

    /**
     * Creates a test Variable.
     *
     * @return A Variable instance for testing.
     */
    public static Variable createVariable() {
        return Variable.builder()
                .key("NAME")
                .type(VariableType.STRING)
                .fallbackValue("user")
                .build();
    }

    /**
     * Creates a test Variable with number type.
     *
     * @return A Variable instance for testing.
     */
    public static Variable createNumberVariable() {
        return Variable.builder()
                .key("AGE")
                .type(VariableType.NUMBER)
                .fallbackValue(25)
                .build();
    }

    /**
     * Creates a test Variable without fallback value.
     *
     * @return A Variable instance for testing.
     */
    public static Variable createVariableWithoutFallback() {
        return Variable.builder()
                .key("OPTIONAL_VARIABLE")
                .type(VariableType.STRING)
                .build();
    }

    /**
     * Creates test CreateTemplateOptions.
     *
     * @return A CreateTemplateOptions instance for testing.
     */
    public static CreateTemplateOptions createTemplateOptions() {
        return CreateTemplateOptions.builder()
                .name("welcome-email")
                .alias("welcome-email-alias")
                .from("John Doe <john.doe@example.com>")
                .subject("Welcome to our service")
                .replyTo("support@example.com")
                .html("<strong>Hey, {{{NAME}}}, you are {{{AGE}}} years old.</strong>")
                .text("Hey, NAME, you are AGE years old.")
                .variables(Arrays.asList(createVariable(), createNumberVariable(), createVariableWithoutFallback()))
                .build();
    }

    /**
     * Creates test UpdateTemplateOptions.
     *
     * @return An UpdateTemplateOptions instance for testing.
     */
    public static UpdateTemplateOptions updateTemplateOptions() {
        return UpdateTemplateOptions.builder()
                .name("updated-welcome-email")
                .html("<strong>Hello, {{{NAME}}}</strong>")
                .build();
    }

    /**
     * Creates a test CreateTemplateResponse.
     *
     * @return A CreateTemplateResponse instance for testing.
     */
    public static CreateTemplateResponseSuccess createTemplateResponse() {
        return new CreateTemplateResponseSuccess("49a3999c-0ce1-4ea6-ab68-afcd6dc2e794", "template");
    }

    /**
     * Creates a test DeleteTemplateResponse.
     *
     * @return A DeleteTemplateResponse instance for testing.
     */
    public static DeleteTemplateResponseSuccess deleteTemplateResponse() {
        return new DeleteTemplateResponseSuccess("template", "34a080c9-b17d-4187-ad80-5af20266e535", true);
    }

    /**
     * Creates a test DuplicateTemplateResponse.
     *
     * @return A DuplicateTemplateResponse instance for testing.
     */
    public static DuplicateTemplateResponseSuccess duplicateTemplateResponse() {
        return new DuplicateTemplateResponseSuccess("template", "e169aa45-1ecf-4183-9955-b1499d5701d3");
    }

    /**
     * Creates a test PublishTemplateResponse.
     *
     * @return A PublishTemplateResponse instance for testing.
     */
    public static PublishTemplateResponseSuccess publishTemplateResponse() {
        return new PublishTemplateResponseSuccess("34a080c9-b17d-4187-ad80-5af20266e535", "template");
    }

    /**
     * Creates a test UpdateTemplateResponse.
     *
     * @return An UpdateTemplateResponse instance for testing.
     */
    public static UpdateTemplateResponseSuccess updateTemplateResponse() {
        return new UpdateTemplateResponseSuccess("34a080c9-b17d-4187-ad80-5af20266e535", "template");
    }

    /**
     * Creates a test Template.
     *
     * @return A Template instance for testing.
     */
    public static GetTemplateResponseSuccess createTemplate() {
        GetTemplateResponseSuccess template = new GetTemplateResponseSuccess();
        template.setObject("template");
        template.setId("34a080c9-b17d-4187-ad80-5af20266e535");
        template.setAlias("reset-password");
        template.setName("reset-password");
        template.setCreatedAt("2023-10-06T23:47:56.678Z");
        template.setUpdatedAt("2023-10-06T23:47:56.678Z");
        template.setStatus("published");
        template.setPublishedAt("2023-10-06T23:47:56.678Z");
        template.setFrom("John Doe <john.doe@example.com>");
        template.setSubject("Hello, world!");
        template.setReplyTo(null);
        template.setHtml("<h1>Hello, world!</h1>");
        template.setText("Hello, world!");

        Variable variable = new Variable();
        variable.setId("e169aa45-1ecf-4183-9955-b1499d5701d3");
        variable.setKey("user_name");
        variable.setType(VariableType.STRING);
        variable.setFallbackValue("John Doe");
        variable.setCreatedAt("2023-10-06T23:47:56.678Z");
        variable.setUpdatedAt("2023-10-06T23:47:56.678Z");

        template.setVariables(Arrays.asList(variable));

        return template;
    }

    /**
     * Creates a test TemplateListItem.
     *
     * @return A TemplateListItem instance for testing.
     */
    public static TemplateListItem createTemplateListItem() {
        TemplateListItem item = new TemplateListItem();
        item.setId("e169aa45-1ecf-4183-9955-b1499d5701d3");
        item.setName("reset-password");
        item.setStatus("draft");
        item.setPublishedAt(null);
        item.setCreatedAt("2023-10-06T23:47:56.678Z");
        item.setUpdatedAt("2023-10-06T23:47:56.678Z");
        item.setAlias("reset-password");
        return item;
    }

    /**
     * Creates a second test TemplateListItem.
     *
     * @return A TemplateListItem instance for testing.
     */
    public static TemplateListItem createTemplateListItem2() {
        TemplateListItem item = new TemplateListItem();
        item.setId("b7f9c2e1-1234-4abc-9def-567890abcdef");
        item.setName("welcome-message");
        item.setStatus("published");
        item.setPublishedAt("2023-10-06T23:47:56.678Z");
        item.setCreatedAt("2023-10-06T23:47:56.678Z");
        item.setUpdatedAt("2023-10-06T23:47:56.678Z");
        item.setAlias("welcome-message");
        return item;
    }

    /**
     * Creates a list of test TemplateListItems.
     *
     * @return A list of TemplateListItem instances for testing.
     */
    public static List<TemplateListItem> createTemplateList() {
        List<TemplateListItem> templates = new ArrayList<>();
        templates.add(createTemplateListItem());
        templates.add(createTemplateListItem2());
        return templates;
    }

    /**
     * Creates a test ListTemplatesResponse.
     *
     * @return A ListTemplatesResponse instance for testing.
     */
    public static ListTemplatesResponseSuccess listTemplatesResponse() {
        return new ListTemplatesResponseSuccess("list", createTemplateList(), false);
    }
}
