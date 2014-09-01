package com.github.approval.example;

import com.github.approval.Approvals;
import org.junit.Test;

import java.nio.file.Paths;

public class SimpleExampleApprovalTest {

    @Test
    public void shouldReturnSomethingToTestOut() throws Exception {
        //assign
        String title = "myTitle";

        //act
        String actual = SimpleExample.generateHtml(title);

        //verify
        Approvals.verify(actual, Paths.get("test.txt"));
    }
}