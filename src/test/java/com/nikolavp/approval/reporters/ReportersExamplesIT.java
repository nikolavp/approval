package com.nikolavp.approval.reporters;

import com.nikolavp.approval.Approval;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;

import java.awt.*;
import java.nio.file.FileSystems;

/**
 *
 * User: nikolavp
 * Date: 26/02/14
 * Time: 14:28
 */
@Ignore
public class ReportersExamplesIT {

    @Test
    public void testGvimApprovalProcess() throws Exception {
        Assume.assumeTrue(Desktop.isDesktopSupported());
        Approval<String> approval = Approval.of(String.class).withReporter(Reporters.gvim()).build();

        approval.verify("some test content\n", FileSystems.getDefault().getPath("target", "verifications", "testGvimApprovalProcess.txt"));
    }

    @Test
    public void testConsoleApprovalProcess() throws Exception {
        Assume.assumeTrue(Desktop.isDesktopSupported());
        Approval<String> approval = Approval.of(String.class).withReporter(Reporters.console()).build();
        approval.verify("some test content\n", FileSystems.getDefault().getPath("target", "verifications", "testConsoleApprovalProcess.txt"));
    }

    @Test
    public void testGeditApprovalProcess() throws Exception {
        Assume.assumeTrue(Desktop.isDesktopSupported());
        Approval<String> approval = Approval.of(String.class).withReporter (Reporters.gedit()).build();
        approval.verify("some test content\n", FileSystems.getDefault().getPath("target", "verifications", "testGeditApprovalProcess.txt"));
    }
}
