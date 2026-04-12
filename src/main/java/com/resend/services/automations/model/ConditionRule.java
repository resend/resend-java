package com.resend.services.automations.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a condition rule for condition steps.
 */
public class ConditionRule {

    private final Map<String, Object> rule;

    private ConditionRule(Map<String, Object> rule) {
        this.rule = Collections.unmodifiableMap(new HashMap<>(rule));
    }

    /**
     * Returns the condition rule as a Map.
     *
     * @return An unmodifiable copy of the condition rule map.
     */
    public Map<String, Object> toMap() {
        return rule;
    }

    /**
     * Creates a new builder instance for a simple rule.
     *
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Creates a rule that checks if a field equals a value.
     *
     * @param field The field name.
     * @param value The value to compare.
     * @return A new ConditionRule.
     */
    public static ConditionRule eq(String field, Object value) {
        return builder().field(field).operator(ConditionOperator.EQ).value(value).build();
    }

    /**
     * Creates a rule that checks if a field does not equal a value.
     *
     * @param field The field name.
     * @param value The value to compare.
     * @return A new ConditionRule.
     */
    public static ConditionRule neq(String field, Object value) {
        return builder().field(field).operator(ConditionOperator.NEQ).value(value).build();
    }

    /**
     * Creates a rule that checks if a field is greater than a value.
     *
     * @param field The field name.
     * @param value The value to compare.
     * @return A new ConditionRule.
     */
    public static ConditionRule gt(String field, Object value) {
        return builder().field(field).operator(ConditionOperator.GT).value(value).build();
    }

    /**
     * Creates a rule that checks if a field is greater than or equal to a value.
     *
     * @param field The field name.
     * @param value The value to compare.
     * @return A new ConditionRule.
     */
    public static ConditionRule gte(String field, Object value) {
        return builder().field(field).operator(ConditionOperator.GTE).value(value).build();
    }

    /**
     * Creates a rule that checks if a field is less than a value.
     *
     * @param field The field name.
     * @param value The value to compare.
     * @return A new ConditionRule.
     */
    public static ConditionRule lt(String field, Object value) {
        return builder().field(field).operator(ConditionOperator.LT).value(value).build();
    }

    /**
     * Creates a rule that checks if a field is less than or equal to a value.
     *
     * @param field The field name.
     * @param value The value to compare.
     * @return A new ConditionRule.
     */
    public static ConditionRule lte(String field, Object value) {
        return builder().field(field).operator(ConditionOperator.LTE).value(value).build();
    }

    /**
     * Creates a rule that checks if a field is empty.
     *
     * @param field The field name.
     * @return A new ConditionRule.
     */
    public static ConditionRule isEmpty(String field) {
        return builder().field(field).operator(ConditionOperator.IS_EMPTY).build();
    }

    /**
     * Creates a rule that checks if a field is not empty.
     *
     * @param field The field name.
     * @return A new ConditionRule.
     */
    public static ConditionRule isNotEmpty(String field) {
        return builder().field(field).operator(ConditionOperator.IS_NOT_EMPTY).build();
    }

    /**
     * Creates a rule that checks if a field contains a value.
     *
     * @param field The field name.
     * @param value The value to search for.
     * @return A new ConditionRule.
     */
    public static ConditionRule contains(String field, Object value) {
        return builder().field(field).operator(ConditionOperator.CONTAINS).value(value).build();
    }

    /**
     * Creates a rule that checks if a field does not contain a value.
     *
     * @param field The field name.
     * @param value The value to search for.
     * @return A new ConditionRule.
     */
    public static ConditionRule notContains(String field, Object value) {
        return builder().field(field).operator(ConditionOperator.NOT_CONTAINS).value(value).build();
    }

    /**
     * Creates a rule that checks if a field starts with a value.
     *
     * @param field The field name.
     * @param value The prefix value.
     * @return A new ConditionRule.
     */
    public static ConditionRule startsWith(String field, Object value) {
        return builder().field(field).operator(ConditionOperator.STARTS_WITH).value(value).build();
    }

    /**
     * Creates a rule that checks if a field ends with a value.
     *
     * @param field The field name.
     * @param value The suffix value.
     * @return A new ConditionRule.
     */
    public static ConditionRule endsWith(String field, Object value) {
        return builder().field(field).operator(ConditionOperator.ENDS_WITH).value(value).build();
    }

    /**
     * Creates a rule that checks if a field value is within the last specified time period.
     *
     * @param field The field name.
     * @param value The time period value.
     * @return A new ConditionRule.
     */
    public static ConditionRule inLast(String field, Object value) {
        return builder().field(field).operator(ConditionOperator.IN_LAST).value(value).build();
    }

    /**
     * Creates a rule that checks if a field value is not within the last specified time period.
     *
     * @param field The field name.
     * @param value The time period value.
     * @return A new ConditionRule.
     */
    public static ConditionRule notInLast(String field, Object value) {
        return builder().field(field).operator(ConditionOperator.NOT_IN_LAST).value(value).build();
    }

    /**
     * Creates an AND condition combining multiple rules.
     *
     * @param rules The rules to combine.
     * @return A new ConditionRule.
     */
    public static ConditionRule and(ConditionRule... rules) {
        return combineRules("and", rules);
    }

    /**
     * Creates an OR condition combining multiple rules.
     *
     * @param rules The rules to combine.
     * @return A new ConditionRule.
     */
    public static ConditionRule or(ConditionRule... rules) {
        return combineRules("or", rules);
    }

    private static ConditionRule combineRules(String type, ConditionRule... rules) {
        Map<String, Object> combinedRule = new HashMap<>();
        combinedRule.put("type", type);
        List<Map<String, Object>> rulesList = new ArrayList<>();
        for (ConditionRule rule : rules) {
            rulesList.add(rule.toMap());
        }
        combinedRule.put("rules", rulesList);
        return new ConditionRule(combinedRule);
    }

    /**
     * Builder class for constructing ConditionRule objects.
     */
    public static class Builder {

        private String field;
        private ConditionOperator operator;
        private Object value;

        /**
         * Constructs a new Builder instance.
         */
        public Builder() {}

        /**
         * Sets the field to evaluate.
         *
         * @param field The field name.
         * @return The builder instance.
         */
        public Builder field(String field) {
            this.field = field;
            return this;
        }

        /**
         * Sets the comparison operator.
         *
         * @param operator The operator.
         * @return The builder instance.
         */
        public Builder operator(ConditionOperator operator) {
            this.operator = operator;
            return this;
        }

        /**
         * Sets the value to compare against.
         *
         * @param value The comparison value.
         * @return The builder instance.
         */
        public Builder value(Object value) {
            this.value = value;
            return this;
        }

        /**
         * Builds a new ConditionRule instance.
         *
         * @return A new ConditionRule.
         */
        public ConditionRule build() {
            Map<String, Object> rule = new HashMap<>();
            rule.put("type", "rule");
            rule.put("field", field);
            rule.put("operator", operator != null ? operator.getValue() : null);
            rule.put("value", value);
            return new ConditionRule(rule);
        }
    }
}
