package com.nikolavp.approval.pathmappers;

import com.nikolavp.approval.PathMapper;

import java.nio.file.Path;

/**
 * A path mapper that will put all approvals in a common parent path. Let's say you want to put all your approval
 * results in <b>src/test/resources/approval</b>(we assume a common maven directory structure) then you can use this
 * mapper as follows:
 * <p/>
 * <pre>{@code
 *  Approval approval = Approval.of(String.class)
 *  .withPathMapper(new ParentPathMapper(Paths.get("src/test/resources/approval")))
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
public class ParentPathMapper implements PathMapper {

    private final Path parentPath;

    /**
     * Creates a parent path mapper that puts approvals in the given parent path.
     *
     * @param parentPath the parent path for all approvals
     */
    public ParentPathMapper(Path parentPath) {
        this.parentPath = parentPath;
    }

    @Override
    public Path getPath(Object value, Path approvalFilePath) {
        return parentPath.resolve(approvalFilePath);
    }
}
