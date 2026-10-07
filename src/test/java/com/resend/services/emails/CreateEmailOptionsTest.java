package com.resend.services.emails;

import com.resend.services.emails.model.Attachment;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateEmailOptionsTest {

    private static CreateEmailOptions.Builder base() {
        return CreateEmailOptions.builder().from("sender@example.com").subject("Subject");
    }

    @Test
    public void toListAcceptsImmutableListAndAllowsAddTo() {
        CreateEmailOptions options = base()
                .to(Collections.singletonList("a@example.com"))
                .addTo("b@example.com")
                .build();

        assertEquals(Arrays.asList("a@example.com", "b@example.com"), options.getTo());
    }

    @Test
    public void toListDoesNotWriteThroughToCallerList() {
        List<String> recipients = new ArrayList<>(Collections.singletonList("a@example.com"));

        CreateEmailOptions options = base().to(recipients).addTo("b@example.com").build();
        recipients.add("c@example.com");

        assertEquals(Arrays.asList("a@example.com", "c@example.com"), recipients);
        assertEquals(Arrays.asList("a@example.com", "b@example.com"), options.getTo());
    }

    @Test
    public void tagsListAcceptsImmutableListAndAllowsAddTag() {
        Tag first = Tag.builder().name("a").value("1").build();
        Tag second = Tag.builder().name("b").value("2").build();

        CreateEmailOptions options = base()
                .tags(Collections.singletonList(first))
                .addTag(second)
                .build();

        assertEquals(Arrays.asList(first, second), options.getTags());
    }

    @Test
    public void attachmentsListAcceptsImmutableListAndAllowsAddAttachment() {
        Attachment first = Attachment.builder().fileName("a.txt").content("YQ==").build();
        Attachment second = Attachment.builder().fileName("b.txt").content("Yg==").build();

        CreateEmailOptions options = base()
                .attachments(Collections.singletonList(first))
                .addAttachment(second)
                .build();

        assertEquals(Arrays.asList(first, second), options.getAttachments());
    }

    @Test
    public void headersMapAcceptsImmutableMapAndAllowsAddHeader() {
        CreateEmailOptions options = base()
                .headers(Collections.singletonMap("X-One", "1"))
                .addHeader("X-Two", "2")
                .build();

        Map<String, String> headers = options.getHeaders();
        assertEquals("1", headers.get("X-One"));
        assertEquals("2", headers.get("X-Two"));
    }
}
