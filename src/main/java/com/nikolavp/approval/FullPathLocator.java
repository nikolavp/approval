package com.nikolavp.approval;

import java.nio.file.Path;

/**
 * A locator that unlike {@link com.nikolavp.approval.PathLocator} doesn't resolve the approval file path based on a
 * given sub path but only needs the value. Of course there are possible implementations that don'n even need the value
 * like {@link com.nikolavp.approval.pathlocators.JunitPathLocator}.
 *
 * @param <T> the value that will be approved
 *
 * @see com.nikolavp.approval.PathLocator
 */
public interface FullPathLocator<T> {
    /**
     * Get the full approval path based on the value.
     *
     * @param value the value that will be approved and for which the approval path will be built
     * @return a Path for the given value
     */
    Path getApprovalPath(T value);
}
