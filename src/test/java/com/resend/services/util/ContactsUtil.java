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

    public static ListContactTopicsResponse createContactTopicsListResponse() {
        List<ContactTopic> topics = new ArrayList<>();

        ContactTopic t1 = new ContactTopic(
                "b6d24b8e-af0b-4c3c-be0c-359bbd97381e",
                "Product Updates",
                "New features, and latest announcements.",
                "opt_in"
        );
        ContactTopic t2 = new ContactTopic(
                "07d84122-7224-4881-9c31-1c048e204602",
                "Weekly Newsletter",
                "Weekly digest of content.",
                "opt_out"
        );

        topics.add(t1);
        topics.add(t2);

        return new ListContactTopicsResponse("list", topics, false);
    }

    public static UpdateContactTopicsOptions createUpdateTopicsOptions() {
        List<ContactTopicOptions> updates = new ArrayList<>();
        updates.add(ContactTopicOptions.builder()
                .id("b6d24b8e-af0b-4c3c-be0c-359bbd97381e")
                .subscription("opt_out")
                .build());
        updates.add(ContactTopicOptions.builder()
                .id("07d84122-7224-4881-9c31-1c048e204602")
                .subscription("opt_in")
                .build());

        return UpdateContactTopicsOptions.builder()
                .id("e169aa45-1ecf-4183-9955-b1499d5701d3")
                .topics(updates)
                .build();
    }

    public static UpdateContactTopicsResponse updateContactTopicsResponse() {
        return new UpdateContactTopicsResponse("e169aa45-1ecf-4183-9955-b1499d5701d3");
    }

    public static AddContactToSegmentOptions createAddContactToSegmentOptions() {
        return AddContactToSegmentOptions.builder()
                .id("e169aa45-1ecf-4183-9955-b1499d5701d3")
                .segmentId("78261eea-8f8b-4381-83c6-79fa7120f1cf")
                .build();
    }

    public static AddContactToSegmentResponseSuccess addContactToSegmentResponseSuccess() {
        AddContactToSegmentResponseSuccess response = new AddContactToSegmentResponseSuccess();
        response.setId("78261eea-8f8b-4381-83c6-79fa7120f1cf");
        return response;
    }

    public static RemoveContactFromSegmentOptions createRemoveContactFromSegmentOptions() {
        return RemoveContactFromSegmentOptions.builder()
                .id("e169aa45-1ecf-4183-9955-b1499d5701d3")
                .segmentId("78261eea-8f8b-4381-83c6-79fa7120f1cf")
                .build();
    }

    public static RemoveContactFromSegmentResponseSuccess removeContactFromSegmentResponseSuccess() {
        RemoveContactFromSegmentResponseSuccess response = new RemoveContactFromSegmentResponseSuccess();
        response.setId("78261eea-8f8b-4381-83c6-79fa7120f1cf");
        response.setDeleted(true);
        return response;
    }

    public static ListContactSegmentsResponseSuccess listContactSegmentsResponseSuccess() {
        List<ContactSegment> segments = new ArrayList<>();

        ContactSegment seg1 = new ContactSegment(
                "78261eea-8f8b-4381-83c6-79fa7120f1cf",
                "Registered Users",
                "2023-10-06T22:59:55.977Z"
        );
        ContactSegment seg2 = new ContactSegment(
                "b9d24c8e-bf1c-5d4d-cf1d-470cbd97482f",
                "Premium Users",
                "2023-11-12T14:23:10.123Z"
        );

        segments.add(seg1);
        segments.add(seg2);

        return new ListContactSegmentsResponseSuccess("list", segments, false);
    }
}
