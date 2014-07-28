package com.nikolavp.approval.converters;

/*
 * #%L
 * com.nikolavp.approval:core
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

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;

/**
 * User: nikolavp (Nikola Petrov) Date: 14-7-25 Time: 15:58
 */
public class AbstractStringConverterTest {

    private static final AbstractStringConverter<String> CONVERTER = new AbstractStringConverter<String>() {
        @Nonnull
        @Override
        protected String getStringForm(String value) {
            return value;
        }
    };

    @Test
    public void shouldCallGetStringFormForValidResult() throws Exception {
        final String testValue = "test1\n\n";
        final byte[] rawForm = CONVERTER.getRawForm(testValue);
        Assert.assertThat(rawForm, CoreMatchers.equalTo(testValue.getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    public void shouldReturnTheStringNullAsRawFormOnNullValues() throws Exception {
        final byte[] rawForm = CONVERTER.getRawForm(null);
        Assert.assertThat(rawForm, CoreMatchers.equalTo("null\n\n".getBytes(StandardCharsets.UTF_8)));
    }
}
