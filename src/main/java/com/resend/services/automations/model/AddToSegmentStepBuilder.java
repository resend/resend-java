package com.resend.services.automations.model;

/**
 * Builder for creating add_to_segment automation steps.
 */
public class AddToSegmentStepBuilder extends AbstractStepBuilder<AddToSegmentStepBuilder> {

    /**
     * Constructs an AddToSegmentStepBuilder with the specified key.
     *
     * @param key The step key.
     */
    public AddToSegmentStepBuilder(String key) {
        super(key);
    }

    @Override
    protected StepType getType() {
        return StepType.ADD_TO_SEGMENT;
    }

    /**
     * Sets the segment ID to add the contact to.
     *
     * @param segmentId The segment ID.
     * @return The builder instance.
     */
    public AddToSegmentStepBuilder segmentId(String segmentId) {
        return addConfig("segment_id", segmentId);
    }
}
