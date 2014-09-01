package com.github.approval;

/*
 * #%L
 * approval
 * %%
 * Copyright (C) 2014 Nikolavp
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import javax.annotation.Nonnull;
import java.nio.file.Path;

/**
 * A mapper that unlike {@link PathMapper} doesn't resolve the approval file path based on a
 * given sub path but only needs the value. Of course there are possible implementations that don'n even need the value
 * like {@link com.github.approval.pathmappers.JunitPathMapper}.
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
