package com.nikolavp.approval.example;

import com.nikolavp.approval.Approval;
import com.nikolavp.approval.pathmappers.ParentPathMapper;
import com.nikolavp.approval.reporters.Reporters;
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
