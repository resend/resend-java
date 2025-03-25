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
                "2024-12-01T19:32:22.980Z",
                null,
                null,
                "broadcast",
                "Announcements",
                "Acme <onboarding@resend.dev>",
                "Hello World",
                null,
                "Check out our latest announcements"
        );
    }

    public static RemoveBroadcastResponseSuccess removeBroadcastResponseSuccess() {
        return new RemoveBroadcastResponseSuccess("559ac32e-9ef5-46fb-82a1-b76b840c0f7b", "object",true);
    }

    public static ListBroadcastsResponseSuccess createBroadcastsListResponse() {
        List<Broadcast> broadcastList = new ArrayList<>();

        broadcastList.add(new Broadcast("1", "78261eea-8f8b-4381-83c6-79fa7120f1cf", "draft", "2024-12-01T19:32:22.980Z", null, null));
        broadcastList.add(new Broadcast("2", "78261eea-8f8b-4381-83c6-79fa7120f1cf", "sent", "2024-12-02T10:15:30.000Z", "2024-12-02T11:00:00.000Z", "2024-12-02T12:00:00.000Z"));
        broadcastList.add(new Broadcast("3", "78261eea-8f8b-4381-83c6-79fa7120f1cf", "queued", "2024-12-03T08:45:00.000Z", null, null));

        return new ListBroadcastsResponseSuccess("list", broadcastList);
    }

    public static SendBroadcastOptions sendBroadcastRequest() {
        return SendBroadcastOptions.builder()
                .scheduledAt("2024-12-18T15:00:00.000Z")
                .build();
    }
}

