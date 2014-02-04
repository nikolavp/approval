package com.nikolavp.approval;

import java.io.File;

/**
 * Created by ontotext on 2/2/14.
 */
public class TestUtils {
    public static final String VALUE = "some\nmultiline\nnsimple\nstring";
    public static final byte[] RAW_VALUE = VALUE.getBytes();

    public static File forApproval(TestTempFile tmpTestFile) {
        return Approval.getApprovalPath(tmpTestFile.file().toPath()).toFile();
    }
}
