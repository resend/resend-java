package com.resend.services.util;

import com.resend.services.contacts.model.*;

import java.util.ArrayList;
import java.util.List;

public class ContactsUtil {
    public static CreateContactOptions createContactRequest() {
        return CreateContactOptions.builder()
                .email("steve.wozniak@gmail.com")
                .firstName("Steve")
                .lastName("Wozniak")
                .unsubscribed(true)
                .build();
    }
    public static CreateContactResponseSuccess createContactResponseSuccess() {
        return new CreateContactResponseSuccess("123", "contact");
    }

    public static RemoveContactResponseSuccess removeContactResponseSuccess() {
        return new RemoveContactResponseSuccess("123", "contact", true, "contact");
    }

    public static ListContactsResponseSuccess createContactsListResponse() {
        List<Contact> contacts = new ArrayList<>();

        Contact c1 = new Contact("1", "frodo.baggins@shire.com", "Frodo", "Baggins", "2023-04-08T00:11:13.110779+00:00", false);
        Contact c2 = new Contact("2", "aragorn.strider@gondor.com", "Aragorn", "Strider", "2023-04-08T00:11:13.110779+00:00", false);
        Contact c3 = new Contact("3", "legolas.greenleaf@woodland.com", "Legolas", "Greenleaf", "2023-04-08T00:11:13.110779+00:00", false);


        contacts.add(c1);
        contacts.add(c2);
        contacts.add(c3);

        return new ListContactsResponseSuccess(contacts, "list");
    }

    public static UpdateContactOptions createUpdateOptions() {
        return UpdateContactOptions.builder()
                .audienceId("123")
                .id("123")
                .firstName("frodo")
                .lastName("baggins").build();
    }

    public static UpdateContactResponseSuccess updateContactResponseSuccess() {
        return new UpdateContactResponseSuccess("123", "contact");
    }

    public static GetContactResponseSuccess getContactResponseSuccess() {
        return new GetContactResponseSuccess(
                "contacts",
                "123",
                "user@example.com",
                "test",
                "test",
                "2025-04-30T12:00:00Z",
                false
        );
    }
}
