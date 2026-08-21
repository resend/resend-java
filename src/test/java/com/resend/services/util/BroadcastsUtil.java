package com.resend.services.util;

import com.resend.services.broadcasts.model.*;
import java.util.ArrayList;
import java.util.List;

public class BroadcastsUtil {

    public static CreateBroadcastOptions createBroadcastRequest() {
        return CreateBroadcastOptions.builder()
                .audienceId("78261eea-8f8b-4381-83c6-79fa7120f1cf")
                .from("example@resend.dev")
                .subject("Sample Subject")
                .html("<p>This is a test email.</p>")
                .text("This is a test email.")
                .name("Sample Broadcast")
                .build();
    }

    public static UpdateBroadcastOptions updateBroadcastRequest() {
        return UpdateBroadcastOptions.builder()
                .id("123")
                .audienceId("78261eea-8f8b-4381-83c6-79fa7120f1cf")
                .from("example@resend.dev")
                .subject("Sample Subject")
                .html("<p>This is a test email.</p>")
                .text("This is a test email.")
                .name("Sample Broadcast")
                .build();
    }

    public static CreateBroadcastResponseSuccess createBroadcastResponse() {
        return new CreateBroadcastResponseSuccess(
                "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794"
        );
    }

    public static UpdateBroadcastResponseSuccess updateBroadcastResponse() {
        return new UpdateBroadcastResponseSuccess(
                "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794"
        );
    }

    public static SendBroadcastResponseSuccess sendBroadcastResponse() {
        return new SendBroadcastResponseSuccess(
                "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794"
        );
    }

    public static GetBroadcastResponseSuccess getBroadcastResponse() {
        return new GetBroadcastResponseSuccess(
                "559ac32e-9ef5-46fb-82a1-b76b840c0f7b",
                "78261eea-8f8b-4381-83c6-79fa7120f1cf",
                "draft",
                "2024-12-01 19:32:22.98+00",
                null,
                null,
                "broadcast",
                "Announcements",
                "Acme <onboarding@resend.dev>",
                "<p>Hello World</p>",
                "Hello World",
                null,
                "Check out our latest announcements",
                "The plain text of the broadcast"
        );
    }

    public static RemoveBroadcastResponseSuccess removeBroadcastResponseSuccess() {
        return new RemoveBroadcastResponseSuccess("559ac32e-9ef5-46fb-82a1-b76b840c0f7b", "object",true);
    }

    public static ListBroadcastsResponseSuccess createBroadcastsListResponse() {
        List<Broadcast> broadcastList = new ArrayList<>();

        broadcastList.add(new Broadcast("1", "78261eea-8f8b-4381-83c6-79fa7120f1cf", "draft", "2024-12-01 19:32:22.98+00", null, null));
        broadcastList.add(new Broadcast("2", "78261eea-8f8b-4381-83c6-79fa7120f1cf", "sent", "2024-12-02 10:15:30+00", "2024-12-02 11:00:00+00", "2024-12-02 12:00:00+00"));
        broadcastList.add(new Broadcast("3", "78261eea-8f8b-4381-83c6-79fa7120f1cf", "queued", "2024-12-03 08:45:00+00", null, null));

        return new ListBroadcastsResponseSuccess("list", broadcastList, true);
    }

    public static ListBroadcastClickedLinksResponseSuccess createBroadcastClickedLinksResponse() {
        List<BroadcastClickedLink> clickedLinks = new ArrayList<>();

        clickedLinks.add(new BroadcastClickedLink("b2Zmc2V0OjA", "https://resend.com/pricing", 42, 30));
        clickedLinks.add(new BroadcastClickedLink("b2Zmc2V0OjE", "https://resend.com/docs", 17, 15));

        return new ListBroadcastClickedLinksResponseSuccess("list", clickedLinks, false);
    }

    public static SendBroadcastOptions sendBroadcastRequest() {
        return SendBroadcastOptions.builder()
                .scheduledAt("2024-12-18T15:00:00.000Z")
                .build();
    }

    public static CreateBroadcastOptions createAndSendBroadcastRequest() {
        return CreateBroadcastOptions.builder()
                .audienceId("78261eea-8f8b-4381-83c6-79fa7120f1cf")
                .from("example@resend.dev")
                .subject("Sample Subject")
                .html("<p>This is a test email.</p>")
                .text("This is a test email.")
                .name("Sample Broadcast")
                .send(true)
                .build();
    }

    public static CreateBroadcastOptions createAndScheduleBroadcastRequest() {
        return CreateBroadcastOptions.builder()
                .audienceId("78261eea-8f8b-4381-83c6-79fa7120f1cf")
                .from("example@resend.dev")
                .subject("Sample Subject")
                .html("<p>This is a test email.</p>")
                .text("This is a test email.")
                .name("Sample Broadcast")
                .send(true)
                .scheduledAt("2024-12-18T15:00:00.000Z")
                .build();
    }
}

