package com.nikolavp.approval.example;

import com.nikolavp.approval.Approval;
import com.nikolavp.approval.reporters.Reporters;
import org.junit.Test;

import java.nio.file.Paths;

public class PathMappersExample {
    private static final Approval<String> APPROVER = Approval.of(String.class)
            .withReporter(Reporters.console())
            .build();

    @Test
    public void shoulProperlyTestString() throws Exception {
        APPROVER.verify("First string test", Paths.get("src", "test", "resources", "approvals", "first-test.txt"));
    }

    @Test
    public void shoulProperlyTestStringSecond() throws Exception {
        APPROVER.verify("Second string test", Paths.get("src", "test", "resources", "approvals", "second-test.txt"));
    }
}
