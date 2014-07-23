package com.nikolavp.approval;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by nikolavp on 1/29/14.
 */
public class TestTempFile implements MethodRule {
    private File file;

    public TestTempFile() {

    }
    public File file() {
        return file;
    }

    public Path path() {
        return file.toPath();
    }

    @Override
    public Statement apply(Statement statement, FrameworkMethod frameworkMethod, Object o) {
        try {
            this.file = File.createTempFile("unit.tests", null);
            // we don't want this to be created but just want a temporary name
            this.file.delete();
        } catch (IOException e) {
            throw new RuntimeException("Couldn't create a temporary file. You are out of disc space or something!?",e);
        }
        return statement;
    }
}
