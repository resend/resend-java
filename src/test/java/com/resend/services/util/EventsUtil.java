package com.resend.services.util;

import com.resend.services.events.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventsUtil {

    public static CreateEventOptions createEventRequest() {
        Map<String, String> schema = new HashMap<>();
        schema.put("user_id", "string");
        schema.put("plan", "string");

        return CreateEventOptions.builder()
                .name("user.signup")
                .schema(schema)
                .build();
    }

    public static CreateEventResponseSuccess createEventResponse() {
        return new CreateEventResponseSuccess("event", "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794");
    }

    public static Event getEventResponse() {
        Map<String, String> schema = new HashMap<>();
        schema.put("user_id", "string");
        schema.put("plan", "string");

        return new Event(
                "event",
                "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794",
                "user.signup",
                schema,
                "2024-12-01T10:00:00.000Z",
                "2024-12-02T10:00:00.000Z"
        );
    }

    public static ListEventsResponseSuccess listEventsResponse() {
        List<EventSummary> events = new ArrayList<>();
        events.add(new EventSummary("1", "user.signup", null, "2024-12-01T10:00:00.000Z", null));
        events.add(new EventSummary("2", "user.verified", null, "2024-12-02T10:00:00.000Z", null));
        events.add(new EventSummary("3", "order.created", null, "2024-12-03T10:00:00.000Z", null));

        return new ListEventsResponseSuccess("list", false, events);
    }

    public static UpdateEventOptions updateEventRequest() {
        Map<String, String> schema = new HashMap<>();
        schema.put("user_id", "string");
        schema.put("plan", "string");
        schema.put("amount", "number");

        return UpdateEventOptions.builder()
                .identifier("49a3999c-0ce1-4ea6-ab68-afcd6dc2e794")
                .schema(schema)
                .build();
    }

    public static UpdateEventResponseSuccess updateEventResponse() {
        return new UpdateEventResponseSuccess("event", "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794");
    }

    public static RemoveEventResponseSuccess removeEventResponse() {
        return new RemoveEventResponseSuccess("event", "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794", true);
    }

    public static SendEventOptions sendEventRequest() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("user_id", "usr_123");
        payload.put("plan", "premium");

        return SendEventOptions.builder()
                .event("user.signup")
                .email("kewynakshlley@gmail.com")
                .payload(payload)
                .build();
    }

    public static SendEventResponseSuccess sendEventResponse() {
        return new SendEventResponseSuccess("event", "user.signup");
    }
}
