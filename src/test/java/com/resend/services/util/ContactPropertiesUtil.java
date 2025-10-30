package com.resend.services.util;

import com.resend.services.contactproperties.model.*;

import java.util.ArrayList;
import java.util.List;

public class ContactPropertiesUtil {

    public static CreateContactPropertyOptions createContactPropertyRequest() {
        return CreateContactPropertyOptions.builder()
                .key("age")
                .type("number")
                .fallbackValue(25)
                .build();
    }

    public static CreateContactPropertyResponseSuccess createContactPropertyResponseSuccess() {
        return new CreateContactPropertyResponseSuccess("123", "contact_property");
    }

    public static UpdateContactPropertyOptions createUpdateOptions() {
        return UpdateContactPropertyOptions.builder()
                .id("123")
                .fallbackValue(30)
                .build();
    }

    public static UpdateContactPropertyResponseSuccess updateContactPropertyResponseSuccess() {
        return new UpdateContactPropertyResponseSuccess("123", "contact_property");
    }

    public static RemoveContactPropertyResponseSuccess removeContactPropertyResponseSuccess() {
        return new RemoveContactPropertyResponseSuccess("123", "contact_property", true);
    }

    public static ContactProperty getContactProperty() {
        return new ContactProperty(
                "123",
                "age",
                "contact_property",
                "2023-04-08T00:11:13.110779+00:00",
                "number",
                25
        );
    }

    public static ListContactPropertiesResponseSuccess createContactPropertiesListResponse() {
        List<ContactProperty> properties = new ArrayList<>();

        ContactProperty p1 = new ContactProperty("1", "age", "contact_property", "2023-04-08T00:11:13.110779+00:00", "number", 25);
        ContactProperty p2 = new ContactProperty("2", "city", "contact_property", "2023-04-08T00:11:13.110779+00:00", "string", "New York");
        ContactProperty p3 = new ContactProperty("3", "subscribed", "contact_property", "2023-04-08T00:11:13.110779+00:00", "boolean", true);

        properties.add(p1);
        properties.add(p2);
        properties.add(p3);

        return new ListContactPropertiesResponseSuccess(properties, "list");
    }
}
