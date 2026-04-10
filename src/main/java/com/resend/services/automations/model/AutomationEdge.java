package com.resend.services.automations.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an edge (connection) between steps in an automation workflow.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AutomationEdge {

    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private String to;

    @JsonProperty("edge_type")
    private EdgeType edgeType;

    public AutomationEdge() {
    }

    public AutomationEdge(Builder builder) {
        this.from = builder.from;
        this.to = builder.to;
        this.edgeType = builder.edgeType;
    }

    public AutomationEdge(String from, String to, EdgeType edgeType) {
        this.from = from;
        this.to = to;
        this.edgeType = edgeType;
    }

    /**
     * Retrieves the source step reference.
     *
     * @return The source step reference.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Retrieves the target step reference.
     *
     * @return The target step reference.
     */
    public String getTo() {
        return to;
    }

    /**
     * Retrieves the edge type.
     *
     * @return The edge type.
     */
    public EdgeType getEdgeType() {
        return edgeType;
    }

    /**
     * Creates a new builder instance for AutomationEdge.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String from;
        private String to;
        private EdgeType edgeType;

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public Builder to(String to) {
            this.to = to;
            return this;
        }

        public Builder edgeType(EdgeType edgeType) {
            this.edgeType = edgeType;
            return this;
        }

        public AutomationEdge build() {
            return new AutomationEdge(this);
        }
    }
}
