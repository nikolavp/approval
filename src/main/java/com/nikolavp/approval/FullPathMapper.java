package com.nikolavp.approval;

import javax.annotation.Nonnull;
import java.nio.file.Path;

/**
 * A mapper that unlike {@link PathMapper} doesn't resolve the approval file path based on a
 * given sub path but only needs the value. Of course there are possible implementations that don'n even need the value
 * like {@link com.nikolavp.approval.pathmappers.JunitPathMapper}.
 *
 * @param <T> the value that will be approved
 *
 * @see PathMapper
 */
public interface FullPathMapper<T> {
    /**
     * Get the full approval path based on the value.
     *
     * @param value the value that will be approved and for which the approval path will be built
     * @return a Path for the given value
     */
    @Nonnull
    Path getApprovalPath(T value);
}
