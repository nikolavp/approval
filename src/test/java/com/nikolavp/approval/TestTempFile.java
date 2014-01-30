package com.nikolavp.approval;

import java.io.File;
import java.io.IOException;

/**
 * Created by ontotext on 1/29/14.
 */
public class TestTempFile {
    private final File file;

    public TestTempFile() {
        try {
            this.file = File.createTempFile("unit.tests", null);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't create a temporary file. You are out of disc space or something!?",e);
        }
    }
    public File file() {
        return file;
    }
}
