package com.github.approval.pathmappers;

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


import com.github.approval.FullPathMapper;
import com.github.approval.PathMapper;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A path mapper that have to be declared as a {@link org.junit.Rule} and will use the standard junit mechanisms to put
 * your approval results in $package-name-with-slashes/$classname/$methodname.
 *
 * <p> This class can be used as a {@link com.github.approval.PathMapper} in which case it will put your approval
 * results in that directory or you can use it as a {@link com.github.approval.FullPathMapper} in which case your
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

    @Nonnull
    @Override
    public Path getApprovalPath(Object value) {
        return Paths.get(currentTestPath + ".approved");
    }

    @Nonnull
    @Override
    public Path getPath(@Nullable Object value, Path approvalFilePath) {
        return currentTestPath.resolve(approvalFilePath);
    }
}
