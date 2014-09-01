package com.github.approval.example;

import com.github.approval.Approval;
import com.github.approval.pathmappers.ParentPathMapper;
import com.github.approval.reporters.Reporters;
import org.junit.Test;

import java.nio.file.Paths;

public class PathMappersExampleImproved {
    private static final Approval<String> APPROVER = Approval.of(String.class)
            .withReporter(Reporters.console())
            .withPathMapper(new ParentPathMapper<String>(Paths.get("src", "test", "resources", "approvals")))
            .build();

    @Test
    public void shoulProperlyTestString() throws Exception {
        APPROVER.verify("First string test", Paths.get("first-test.txt"));
    }

    @Test
    public void shoulProperlyTestStringSecond() throws Exception {
        APPROVER.verify("Second string test", Paths.get("second-test.txt"));
    }
}
