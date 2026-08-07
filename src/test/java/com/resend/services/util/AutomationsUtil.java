package com.resend.services.util;

import com.resend.services.automations.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutomationsUtil {

    public static CreateAutomationOptions createAutomationRequest() {
        AutomationStep triggerStep = AutomationStep.trigger("trigger_1")
                .eventName("user.signup")
                .build();

        AutomationStep emailStep = AutomationStep.sendEmail("email_1")
                .template("tmpl_123")
                .subject("Welcome!")
                .from("onboarding@kewynakshlley.me")
                .build();

        AutomationConnection connection = AutomationConnection.builder()
                .from("trigger_1")
                .to("email_1")
                .type(ConnectionType.DEFAULT)
                .build();

        return CreateAutomationOptions.builder()
                .name("Welcome Automation")
                .status(AutomationStatus.DISABLED)
                .steps(triggerStep, emailStep)
                .connections(connection)
                .build();
    }

    public static CreateAutomationResponseSuccess createAutomationResponse() {
        return new CreateAutomationResponseSuccess("automation", "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794");
    }

    public static UpdateAutomationOptions updateAutomationRequest() {
        return UpdateAutomationOptions.builder()
                .id("49a3999c-0ce1-4ea6-ab68-afcd6dc2e794")
                .name("Updated Automation")
                .status(AutomationStatus.ENABLED)
                .build();
    }

    public static UpdateAutomationResponseSuccess updateAutomationResponse() {
        return new UpdateAutomationResponseSuccess("automation", "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794");
    }

    public static Automation getAutomationResponse() {
        Map<String, Object> triggerConfig = new HashMap<>();
        triggerConfig.put("event_name", "user.signup");

        AutomationStepResponse triggerStep = new AutomationStepResponse("step_1", StepType.TRIGGER, triggerConfig);
        AutomationConnection connection = new AutomationConnection("step_1", "step_2", ConnectionType.DEFAULT);

        return new Automation(
                "automation",
                "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794",
                "Welcome Automation",
                AutomationStatus.ENABLED,
                "2024-12-01 10:00:00+00",
                "2024-12-02 10:00:00+00",
                Arrays.asList(triggerStep),
                Arrays.asList(connection)
        );
    }

    public static ListAutomationsResponseSuccess listAutomationsResponse() {
        List<AutomationListItem> automations = new ArrayList<>();
        automations.add(new AutomationListItem("1", "Automation 1", AutomationStatus.ENABLED, "2024-12-01 10:00:00+00", "2024-12-01 10:00:00+00"));
        automations.add(new AutomationListItem("2", "Automation 2", AutomationStatus.DISABLED, "2024-12-02 10:00:00+00", "2024-12-02 10:00:00+00"));
        automations.add(new AutomationListItem("3", "Automation 3", AutomationStatus.ENABLED, "2024-12-03 10:00:00+00", "2024-12-03 10:00:00+00"));

        return new ListAutomationsResponseSuccess("list", false, automations);
    }

    public static DeleteAutomationResponseSuccess deleteAutomationResponse() {
        return new DeleteAutomationResponseSuccess("automation", "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794", true);
    }

    public static StopAutomationResponseSuccess stopAutomationResponse() {
        return new StopAutomationResponseSuccess("automation", "49a3999c-0ce1-4ea6-ab68-afcd6dc2e794", AutomationStatus.DISABLED);
    }

    public static ListAutomationRunsResponseSuccess listAutomationRunsResponse() {
        List<AutomationRunListItem> runs = new ArrayList<>();
        runs.add(new AutomationRunListItem("run_1", RunStatus.COMPLETED, "2024-12-01 10:00:00+00", "2024-12-01 10:05:00+00", "2024-12-01 10:00:00+00"));
        runs.add(new AutomationRunListItem("run_2", RunStatus.RUNNING, "2024-12-02 10:00:00+00", null, "2024-12-02 10:00:00+00"));
        runs.add(new AutomationRunListItem("run_3", RunStatus.FAILED, "2024-12-03 10:00:00+00", "2024-12-03 10:02:00+00", "2024-12-03 10:00:00+00"));

        return new ListAutomationRunsResponseSuccess("list", false, runs);
    }

    public static AutomationRun getAutomationRunResponse() {
        AutomationRunStep step1 = new AutomationRunStep("trigger_1", StepType.TRIGGER, "completed", "2024-12-01 10:00:00+00", "2024-12-01 10:00:01+00", null, null, "2024-12-01 10:00:00+00");
        AutomationRunStep step2 = new AutomationRunStep("email_1", StepType.SEND_EMAIL, "completed", "2024-12-01 10:00:01+00", "2024-12-01 10:00:05+00", null, null, "2024-12-01 10:00:01+00");

        return new AutomationRun(
                "automation_run",
                "run_1",
                RunStatus.COMPLETED,
                "2024-12-01 10:00:00+00",
                "2024-12-01 10:00:05+00",
                "2024-12-01 10:00:00+00",
                Arrays.asList(step1, step2)
        );
    }
}
