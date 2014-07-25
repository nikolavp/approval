package com.nikolavp.approval.converters;

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

import com.nikolavp.approval.StaticUtilityTestAbstract;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * User: nikolavp
 * Date: 11/03/14
 * Time: 19:23
 */
public class ConvertersTest extends StaticUtilityTestAbstract {
    @Test
    public void shouldJustCallToStringOnPrimitiveTheObject() throws Exception {
        byte[] rawForm = Converters.DOUBLE.getRawForm(1.0D);
        Assert.assertThat(new String(rawForm, StandardCharsets.UTF_8), CoreMatchers.equalTo("1.0"));
    }

    @Test
    public void shouldProperlySerializePrimitiveArrays() throws Exception {
        final byte[] rawForm = Converters.BYTE_ARRAY.getRawForm(new byte[]{1, 2, 3});
        Assert.assertThat(new String(rawForm, StandardCharsets.UTF_8), CoreMatchers.equalTo("[0] = 1\n[1] = 2\n[2] = 3\n"));
    }
    
    @Test
    public void justHitThePrivateConstructor() throws Exception {

    }

    @Override
    protected Class<?> getUtilityClassUnderTest() {
        return Converters.class;
    }
}
