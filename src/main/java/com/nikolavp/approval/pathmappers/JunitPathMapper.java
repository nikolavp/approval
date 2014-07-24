package com.nikolavp.approval.pathmappers;


import com.nikolavp.approval.FullPathMapper;
import com.nikolavp.approval.PathMapper;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A path mapper that have to be declared as a {@link org.junit.Rule} and will use the standard junit mechanisms to put
 * your approval results in $package-name-with-slashes/$classname/$methodname.
 *
 * <p> This class can be used as a {@link com.nikolavp.approval.PathMapper} in which case it will put your approval
 * results in that directory or you can use it as a {@link com.nikolavp.approval.FullPathMapper} in which case your
 * approval result for the <b>single virifacion</b> will be put in a file with that path. In the latter case you will
 * have to make sure that there aren't two approvals for a single test method. </p>
 */
public class JunitPathMapper implements TestRule, PathMapper, FullPathMapper {

    private final Path parentPath;
    private Path currentTestPath;

    /**
     * A parent path in which you want to put your approvals if any.
     *
     * @param parentPath the parent path
     */
    public JunitPathMapper(Path parentPath) {
        this.parentPath = parentPath;
    }

    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                currentTestPath = parentPath.
                        resolve(description.getClassName().replace(".", File.separator)).
                        resolve(description.getMethodName());
                try {
                    base.evaluate();
                } finally {
                    currentTestPath = null;
                }
            }
        };
    }

    Path getCurrentTestPath() {
        return currentTestPath;
    }

    @Override
    public Path getApprovalPath(Object value) {
        return Paths.get(currentTestPath + ".approved");
    }

    @Override
    public Path getPath(Object value, Path approvalFilePath) {
        return currentTestPath.resolve(approvalFilePath);
    }
}
