package com.github.approval.converters;

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
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * User: github
 * Date: 28/02/14
 * Time: 15:10
 */
public class DefaultConverterTest {
    @Test
    public void shouldReturnTheSameContentThatWasPassedIn() throws Exception {
        byte[] someContent = "test".getBytes(StandardCharsets.UTF_8);
        Assert.assertThat(new DefaultConverter().getRawForm(someContent), CoreMatchers.equalTo(someContent));
    }

    @Test
    public void shouldReturnTheStringNullAsRawFormOnNullValues() throws Exception {
        final byte[] rawForm = new DefaultConverter().getRawForm(null);
        Assert.assertThat(rawForm, CoreMatchers.equalTo("null".getBytes(StandardCharsets.UTF_8)));
    }
}
