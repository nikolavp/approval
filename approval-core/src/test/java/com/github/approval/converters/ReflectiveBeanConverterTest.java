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

import com.github.approval.example.Entity;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * User: github
 * Date: 28/02/14
 * Time: 15:12
 */
public class ReflectiveBeanConverterTest {
    @Test
    public void shouldIntrospectFieldsAndShowThemToTheUser() throws Exception {
        //assign
        ReflectiveBeanConverter<Entity> converter = new ReflectiveBeanConverter<Entity>();

        //act
        byte[] rawForm = converter.getRawForm(new Entity("my simple name", 1000));

        //assert
        Assert.assertThat(new String(rawForm, StandardCharsets.UTF_8), CoreMatchers.equalTo("name = my simple name\nage = 1000\n\n"));
    }

    @Test
    public void shouldNotIncludeFieldsThatAreNull() throws Exception {
        //assign
        ReflectiveBeanConverter<Entity> converter = new ReflectiveBeanConverter<Entity>();

        //act
        byte[] rawForm = converter.getRawForm(new Entity(null, 1000));

        //assert
        Assert.assertThat(new String(rawForm, StandardCharsets.UTF_8), CoreMatchers.equalTo("age = 1000\n\n"));
    }
}
