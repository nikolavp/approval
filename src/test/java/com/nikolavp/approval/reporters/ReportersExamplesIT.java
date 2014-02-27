package com.nikolavp.approval.reporters;

import com.nikolavp.approval.Approval;
import org.junit.Assume;
import org.junit.Test;

import java.awt.*;
import java.nio.file.FileSystems;

/**
 *
 * User: nikolavp
 * Date: 26/02/14
 * Time: 14:28
 */

public class ReportersExamplesIT {

    @Test
    public void testGvimApprovalProcess() throws Exception {
        Assume.assumeTrue(Desktop.isDesktopSupported());
        Approval approval = new Approval(Reporters.gvim());

        approval.verify("some test content".getBytes(), FileSystems.getDefault().getPath("target", "verifications", "testGvimApprovalProcess.txt"));
    }

    @Test
    public void testConsoleApprovalProcess() throws Exception {
        Assume.assumeTrue(Desktop.isDesktopSupported());
        Approval approval = new Approval(Reporters.console());
        approval.verify("some test content".getBytes(), FileSystems.getDefault().getPath("target", "verifications", "testConsoleApprovalProcess.txt"));
    }

    @Test
    public void testGeditApprovalProcess() throws Exception {
        Assume.assumeTrue(Desktop.isDesktopSupported());
        Approval approval = new Approval(Reporters.gedit());
        approval.verify("some test content".getBytes(), FileSystems.getDefault().getPath("target", "verifications", "testGeditApprovalProcess.txt"));
    }
}
