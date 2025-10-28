package com.resend.services.util;

import com.resend.services.topics.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for creating test data for Topics service tests.
 */
public class TopicsUtil {

    /**
     * Creates a sample CreateTopicOptions for testing.
     *
     * @return A CreateTopicOptions instance.
     */
    public static CreateTopicOptions createTopicOptions() {
        return CreateTopicOptions.builder()
                .name("Weekly Newsletter")
                .defaultSubscription("opt_in")
                .description("Subscribe to our weekly newsletter for updates")
                .build();
    }

    /**
     * Creates a sample UpdateTopicOptions for testing.
     *
     * @return An UpdateTopicOptions instance.
     */
    public static UpdateTopicOptions updateTopicOptions() {
        return UpdateTopicOptions.builder()
                .name("Monthly Newsletter")
                .description("Subscribe to our monthly newsletter for updates")
                .build();
    }

    /**
     * Creates a sample CreateTopicResponse for testing.
     *
     * @return A CreateTopicResponse instance.
     */
    public static CreateTopicResponseSuccess createTopicResponse() {
        return new CreateTopicResponseSuccess("b6d24b8e-af0b-4c3c-be0c-359bbd97381e");
    }

    /**
     * Creates a sample UpdateTopicResponse for testing.
     *
     * @return An UpdateTopicResponse instance.
     */
    public static UpdateTopicResponseSuccess updateTopicResponse() {
        return new UpdateTopicResponseSuccess("b6d24b8e-af0b-4c3c-be0c-359bbd97381e");
    }

    /**
     * Creates a sample RemoveTopicResponse for testing.
     *
     * @return A RemoveTopicResponse instance.
     */
    public static RemoveTopicResponseSuccess removeTopicResponse() {
        return new RemoveTopicResponseSuccess("topic", "b6d24b8e-af0b-4c3c-be0c-359bbd97381e", true);
    }

    /**
     * Creates a sample Topic for testing.
     *
     * @return A Topic instance.
     */
    public static GetTopicResponseSuccess createTopic() {
        return new GetTopicResponseSuccess(
                "b6d24b8e-af0b-4c3c-be0c-359bbd97381e",
                "Weekly Newsletter",
                "Weekly newsletter for our subscribers",
                "opt_in",
                "2023-04-08T00:11:13.110779+00:00"
        );
    }

    /**
     * Creates a list of sample Topics for testing.
     *
     * @return A list of Topic instances.
     */
    public static List<Topic> createTopicList() {
        List<Topic> topics = new ArrayList<>();

        topics.add(new Topic(
                "b6d24b8e-af0b-4c3c-be0c-359bbd97381e",
                "Weekly Newsletter",
                "Weekly newsletter for our subscribers",
                "opt_in",
                "2023-04-08T00:11:13.110779+00:00"
        ));

        topics.add(new Topic(
                "c7e35c9f-bg1c-5d4d-cf1d-460cce08492f",
                "Monthly Updates",
                "Monthly updates and announcements",
                "opt_out",
                "2023-04-09T00:11:13.110779+00:00"
        ));

        topics.add(new Topic(
                "d8f46da0-ch2d-6e5e-dg2e-571ddf19503g",
                "Product Launches",
                "Get notified about new product launches",
                "opt_in",
                "2023-04-10T00:11:13.110779+00:00"
        ));

        return topics;
    }

    /**
     * Creates a sample ListTopicsResponse for testing.
     *
     * @return A ListTopicsResponse instance.
     */
    public static ListTopicsResponseSuccess createListTopicsResponse() {
        return new ListTopicsResponseSuccess("list", createTopicList(), false);
    }
}
