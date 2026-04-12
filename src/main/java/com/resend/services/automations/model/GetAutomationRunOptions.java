package com.resend.services.automations.model;

/**
 * Represents options for retrieving a specific automation run.
 */
public class GetAutomationRunOptions {

    private final String automationId;
    private final String runId;

    /**
     * Constructs GetAutomationRunOptions using the provided builder.
     *
     * @param builder The builder to construct the options.
     */
    public GetAutomationRunOptions(Builder builder) {
        this.automationId = builder.automationId;
        this.runId = builder.runId;
    }

    /**
     * Retrieves the automation ID.
     *
     * @return The automation ID.
     */
    public String getAutomationId() {
        return automationId;
    }

    /**
     * Retrieves the run ID.
     *
     * @return The run ID.
     */
    public String getRunId() {
        return runId;
    }

    /**
     * Creates a new builder instance for GetAutomationRunOptions.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing GetAutomationRunOptions objects.
     */
    public static class Builder {
        private String automationId;
        private String runId;

        /**
         * Sets the automation ID.
         *
         * @param automationId The automation ID.
         * @return The builder instance.
         */
        public Builder automationId(String automationId) {
            this.automationId = automationId;
            return this;
        }

        /**
         * Sets the run ID.
         *
         * @param runId The run ID.
         * @return The builder instance.
         */
        public Builder runId(String runId) {
            this.runId = runId;
            return this;
        }

        /**
         * Builds a new GetAutomationRunOptions instance.
         *
         * @return A new GetAutomationRunOptions.
         */
        public GetAutomationRunOptions build() {
            return new GetAutomationRunOptions(this);
        }
    }
}
