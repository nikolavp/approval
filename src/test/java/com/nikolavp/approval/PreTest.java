package com.nikolavp.approval;

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

/**
 * User: nikolavp (Nikola Petrov) Date: 14-7-25 Time: 15:30
 */
public class PreTest extends StaticUtilityTestAbstract {
    @Test
    public void shouldThrowIllegalArgumentExceptionIfArgumentIsNull() throws Exception {
        try {
            Pre.notNull(null, "myName");
            Assert.fail("Should throw an exception!");
        } catch (IllegalArgumentException ex) {
            final String message = ex.getMessage();
            Assert.assertThat(message, CoreMatchers.equalTo("myName must not be null!"));
        }
    }

    @Override
    protected Class<?> getUtilityClassUnderTest() {
        return Pre.class;
    }
}
