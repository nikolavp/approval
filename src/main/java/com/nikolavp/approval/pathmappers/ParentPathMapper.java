package com.nikolavp.approval.pathmappers;

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

import com.nikolavp.approval.PathMapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
 *
 * @param <T> the value that will be approved
 */
public class ParentPathMapper<T> implements PathMapper<T> {

    private final Path parentPath;

    /**
     * Creates a parent path mapper that puts approvals in the given parent path.
     *
     * @param parentPath the parent path for all approvals
     */
    public ParentPathMapper(Path parentPath) {
        this.parentPath = parentPath;
    }

    @Nonnull
    @Override
    public Path getPath(@Nullable T value, Path approvalFilePath) {
        return parentPath.resolve(approvalFilePath);
    }
}
