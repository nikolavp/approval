package com.nikolavp.approval.example;


import com.nikolavp.approval.Approval;
import com.nikolavp.approval.Approvals;
import com.nikolavp.approval.reporters.Reporters;
import org.junit.Test;

import java.nio.file.Paths;

public class GettingStartedExamples {

    @Test
    public void testMyCoolThingReturnsProperString() {
        String result = MyCoolThing.getComplexMultilineString();
        Approvals.verify(result, Paths.get("src", "resources", "approval", "result.txt"));
    }

    @Test
    public void testMyCoolThingReturnsProperStringControlled() {
        String string = MyCoolThing.getComplexMultilineString();
        Approval<String> approver = Approval.of(String.class)
                .withReporter(Reporters.console())
                .build();
        approver.verify(string, Paths.get("src", "resources", "approval", "string.verified"));
    }


}
