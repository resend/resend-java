package com.resend.services.automations.model;

import org.jspecify.annotations.Nullable;

import java.util.Map;

/**
 * Helpers shared by the rule builders ({@link ConditionRule}, {@link FilterRule}).
 */
final class RuleMaps {

    private RuleMaps() {
    }

    /**
     * Puts {@code value} under {@code key} unless it is {@code null}, so unset settings are omitted from the rule map.
     *
     * @param rule  The rule map being built.
     * @param key   The rule key.
     * @param value The value, or {@code null} to leave the key out.
     */
    static void putIfSet(Map<String, @Nullable Object> rule, String key, @Nullable Object value) {
        if (value != null) {
            rule.put(key, value);
        }
    }
}
