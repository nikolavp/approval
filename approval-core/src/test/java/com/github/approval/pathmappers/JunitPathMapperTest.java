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

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * User: github (Nikola Petrov) Date: 14-7-23 Time: 14:44
 */
public class JunitPathMapperTest {

    public static final Path PARENT_PATH = Paths.get("src/main/resources");

    @Rule
    public JunitPathMapper pathMapper = new JunitPathMapper(PARENT_PATH);

    private static final Path pathForClass = PARENT_PATH.resolve(Paths.get("com", "github", "approval", "pathmappers", "JunitPathMapperTest"));

    @Test
    public void shouldProperlyResolvePathBasedOnTest() throws Exception {
        final Path currentTestPath = pathMapper.getCurrentTestPath();

        final Path expected = pathForClass.resolve("shouldProperlyResolvePathBasedOnTest");

        Assert.assertThat(currentTestPath, CoreMatchers.equalTo(expected));
    }

    @Test
    public void shouldProperlyResolvePathBasedOnTestOther() throws Exception {
        final Path currentTestPath = pathMapper.getCurrentTestPath();

        final Path expected = pathForClass.resolve("shouldProperlyResolvePathBasedOnTestOther");

        Assert.assertThat(currentTestPath, CoreMatchers.equalTo(expected));
    }

    @Test
    public void shouldReturnProperPathWhenUsedAsFullPathMapper() throws Exception {
        final Path path = pathMapper.getApprovalPath("test");

        Assert.assertThat(path, CoreMatchers.equalTo(pathForClass.resolve("shouldReturnProperPathWhenUsedAsFullPathMapper.approved")));
    }

    @Test
    public void shouldReturnProperPathWhenUsedAsPathMapper() throws Exception {
        final Path path = pathMapper.getPath("Some cool value", Paths.get("subpath"));

        Assert.assertThat(path, CoreMatchers.equalTo(pathForClass.resolve(Paths.get("shouldReturnProperPathWhenUsedAsPathMapper", "subpath"))));
    }
}

