package com.nikolavp.approval.pathlocators;

import com.nikolavp.approval.PathLocator;

import java.nio.file.Path;

/**
 * A path locator that will put all approvals in a common parent path. Let's say you want to put all your approval
 * results in <b>src/test/resources/approval</b>(we assume a common maven directory structure) then you can use this
 * locator as follows:
 * <p/>
 * <pre>{@code
 *  Approval approval = Approval.of(String.class)
 *  .withPathLocator(new ParentPathLocator(Paths.get("src/test/resources/approval")))
 *  .build();
 * }
 * </pre>
 * <p/>
 * now the following call
 * <pre>{@code
 *   approval.verify("Some cool string value", Paths.get("some_cool_value.txt");
 * }
 * </pre>
 * <p/>
 * will put the approved value in the file <b>src/test/resources/approval/some_cool_value.txt</b>
 */
public class ParentPathLocator implements PathLocator {

    private final Path parentPath;

    /**
     * Creates a parent path locator that puts approvals in the given parent path.
     *
     * @param parentPath the parent path for all approvals
     */
    public ParentPathLocator(Path parentPath) {
        this.parentPath = parentPath;
    }

    @Override
    public Path getPath(Object value, Path approvalFilePath) {
        return parentPath.resolve(approvalFilePath);
    }
}
