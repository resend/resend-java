package com.resend.services.util;

import com.resend.services.contacts.model.ContactImport;
import com.resend.services.contacts.model.ContactImportColumnMap;
import com.resend.services.contacts.model.ContactImportCounts;
import com.resend.services.contacts.model.ContactImportPropertyMapping;
import com.resend.services.contacts.model.ContactImportSegmentReference;
import com.resend.services.contacts.model.ContactImportTopicSubscription;
import com.resend.services.contacts.model.CreateContactImportOptions;
import com.resend.services.contacts.model.CreateContactImportResponseSuccess;
import com.resend.services.contacts.model.GetContactImportResponseSuccess;
import com.resend.services.contacts.model.ListContactImportsResponseSuccess;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ContactImportsUtil {

    public static final String CONTACT_IMPORT_ID = "479e3145-dd38-476b-932c-529ceb705947";
    public static final String SEGMENT_ID = "78e7a5c6-9a91-4c63-9d1f-3b9c0b5b9ab6";
    public static final String TOPIC_ID = "059ac693-2fc8-4c13-8b27-01350d638a17";

    public static CreateContactImportOptions createContactImportOptions(File file) {
        ContactImportColumnMap columnMap = ContactImportColumnMap.builder()
                .email("Email")
                .firstName("First Name")
                .lastName("Last Name")
                .property("plan", ContactImportPropertyMapping.builder()
                        .column("Plan")
                        .type("string")
                        .build())
                .build();

        return CreateContactImportOptions.builder()
                .file(file)
                .columnMap(columnMap)
                .onConflict("upsert")
                .segments(new ContactImportSegmentReference(SEGMENT_ID))
                .topics(ContactImportTopicSubscription.builder()
                        .id(TOPIC_ID)
                        .subscription("opt_in")
                        .build())
                .build();
    }

    public static CreateContactImportResponseSuccess createContactImportResponseSuccess() {
        return new CreateContactImportResponseSuccess("contact_import", CONTACT_IMPORT_ID);
    }

    public static GetContactImportResponseSuccess getContactImportResponseSuccess() {
        ContactImportCounts counts = new ContactImportCounts(1200, 800, 300, 75, 25);
        return new GetContactImportResponseSuccess(
                "contact_import",
                CONTACT_IMPORT_ID,
                "completed",
                "2026-05-15 18:32:37.823+00",
                "2026-05-15 18:33:42.916+00",
                counts);
    }

    public static ListContactImportsResponseSuccess listContactImportsResponseSuccess() {
        List<ContactImport> imports = new ArrayList<>();
        ContactImportCounts counts = new ContactImportCounts(1200, 800, 300, 75, 25);
        imports.add(new ContactImport(
                "contact_import",
                CONTACT_IMPORT_ID,
                "completed",
                "2026-05-15 18:32:37.823+00",
                "2026-05-15 18:33:42.916+00",
                counts));
        return new ListContactImportsResponseSuccess("list", false, imports);
    }
}
