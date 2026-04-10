package com.resend.services.automations.model;

/**
 * Represents options for retrieving a specific automation run.
 */
public class GetAutomationRunOptions {

    private final String automationId;
    private final String runId;

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

    public static class Builder {
        private String automationId;
        private String runId;

        public Builder automationId(String automationId) {
            this.automationId = automationId;
            return this;
        }

        public Builder runId(String runId) {
            this.runId = runId;
            return this;
        }

        public GetAutomationRunOptions build() {
            return new GetAutomationRunOptions(this);
        }
    }
}
