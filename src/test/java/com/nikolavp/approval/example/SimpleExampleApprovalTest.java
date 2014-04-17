package com.nikolavp.approval.example;

import com.nikolavp.approval.Approval;
import com.nikolavp.approval.reporters.Reporters;
import org.junit.Test;

import java.nio.file.Paths;

/**
 * User: nikolavp
 * Date: 19/03/14
 * Time: 18:11
 */
public class SimpleExampleApprovalTest {

    @Test
    public void shouldReturnSomethingToTestOut() throws Exception {
        //assign
        Approval<String> approver = Approval.of(String.class)
                .withReporter(Reporters.gvim())
                .build();
        String title = "myTitle";

        //act
        String actual = SimpleExample.generateHtml(title);

        //verify
        approver.verify(actual, Paths.get("./test.txt"));
    }
}