package com.nikolavp.approval;

import javax.annotation.Nonnull;
import java.nio.file.Path;

/**
 * An interface representing objects that will return an appropriate path for the approval process. Most of the times
 * those are used because you don't want to repeat yourself with the same parent path in {@link
 * com.nikolavp.approval.Approval#verify(Object, java.nio.file.Path)} for the path argument. This will map your approval
 * results file from the value for approval and a possible sub path.
 *
 * @param <T> the value that will be approved
 *
 * @see com.nikolavp.approval.pathmappers.ParentPathMapper
 */
public interface PathMapper<T> {
    /**
     * Gets the path for the approval result based on the value that we want to approve and a sub path for that.
     *
     * @param value            the value that will be approved
     * @param approvalFilePath a name/subpath for the approval. This will be the path that was passed to {@link
     *                         Approval#verify(Object, java.nio.file.Path)}
     * @return the full path for the approval result
     */
    @Nonnull
    Path getPath(T value, Path approvalFilePath);

}
