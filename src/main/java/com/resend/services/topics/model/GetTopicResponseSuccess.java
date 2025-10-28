package com.resend.services.topics.model;

/**
 * Represents a successful response when getting a single topic.
 */
public class GetTopicResponseSuccess extends AbstractTopic {

    /**
     * Default constructor for creating an empty GetTopicResponseSuccess object.
     */
    public GetTopicResponseSuccess() {
        super();
    }

    /**
     * Constructs a GetTopicResponseSuccess with the provided attributes.
     *
     * @param id The unique identifier of the topic.
     * @param name The name of the topic.
     * @param description The description of the topic.
     * @param defaultSubscription The default subscription preference.
     * @param createdAt The creation timestamp.
     */
    public GetTopicResponseSuccess(String id, String name, String description, String defaultSubscription, String createdAt) {
        super(id, name, description, defaultSubscription, createdAt);
    }
}
