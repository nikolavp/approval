package com.github.approval.converters;

/*
 * #%L
 * com.github.nikolavp:approval-json
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

public class JacksonJsonConverterTest {

    private Entity entity;

    @Before
    public void setup() {
        entity = new Entity();
        entity.age = 10;
        entity.date = new Date(0);
        entity.firstName = "Tom";
        entity.lastName = "Smith";
        entity.homeTown = "Washington";
    }

    @Test
    public void shouldCreateProperSerializationWithSaneDefaults() throws Exception {
        final JacksonJsonConverter<Entity> converter = JacksonJsonConverter.getInstanceWithSaneDefaults();

        Assert.assertThat(converter.getStringForm(entity), CoreMatchers.equalTo(
                "{\n  \"age\" : 10,\n  \"firstName\" : \"Tom\",\n  \"homeTown\" : \"Washington\",\n  \"lastName\" : \"Smith\"\n}")
        );
    }

    @Test
    public void shouldSerializeEmptyWithSaneDefaults() throws Exception {
        final JacksonJsonConverter<Entity> converter = JacksonJsonConverter.getInstanceWithSaneDefaults();
        entity = new Entity();
        Assert.assertThat(converter.getStringForm(entity), CoreMatchers.equalTo("{\n  \"age\" : 0\n}"));
    }

    @Test
    public void shouldUseTheProvidedMapper() throws Exception {
        final JacksonJsonConverter<Entity> converter = JacksonJsonConverter.getInstanceWithObjectMapper(new ObjectMapper());
        final String stringForm = converter.getStringForm(entity);

        Assert.assertThat(stringForm, CoreMatchers.equalTo("{\"date\":0,\"firstName\":\"Tom\",\"lastName\":\"Smith\",\"age\":10,\"homeTown\":\"Washington\"}"));
    }


    @Test(expected = AssertionError.class)
    public void shouldThrowAssertionErrorIfThereIsAProblemWithTheSerialization() throws Exception {
        final ObjectMapper objectMapper = Mockito.mock(ObjectMapper.class);
        class MyJacksonProccessingException extends JsonProcessingException {
            protected MyJacksonProccessingException(String s) {
                super(s);
            }
        }
        Mockito.when(objectMapper.writeValueAsString(Mockito.anyObject())).thenThrow(new MyJacksonProccessingException("test exception"));

        final JacksonJsonConverter<Entity> converter = JacksonJsonConverter.getInstanceWithObjectMapper(objectMapper);
        final String stringForm = converter.getStringForm(entity);
    }

    @Test
    public void shouldIgnoreAgeDateFirstNameBasedOnJacksonMixinInfo() {
        final JacksonJsonConverter<Entity> converter = JacksonJsonConverter.getInstanceWithObjectMapper(new ObjectMapper());
        converter.withJacksonMixin(entity.getClass(), EntityMixin1.class);
        final String stringForm = converter.getStringForm(entity);

        Assert.assertThat(stringForm, CoreMatchers.equalTo("{\"lastName\":\"Smith\",\"homeTown\":\"Washington\"}"));
    }

    @Test
    public void shouldIgnoreLastNameHometownBasedOnJacksonMixinInfo() {
        final JacksonJsonConverter<Entity> converter = JacksonJsonConverter.getInstanceWithObjectMapper(new ObjectMapper());
        converter.withJacksonMixin(entity.getClass(), EntityMixin2.class);
        final String stringForm = converter.getStringForm(entity);

        Assert.assertThat(stringForm, CoreMatchers.equalTo("{\"date\":0,\"firstName\":\"Tom\",\"age\":10}"));
    }
}