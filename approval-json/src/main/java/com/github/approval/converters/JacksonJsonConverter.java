package com.github.approval.converters;

/*
 * #%L
 * approval-json
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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.annotation.Nonnull;

/**
 * A generic json converter that uses jackson for the serialization.
 *
 * @param <T> the type of objects that we are going to convert
 */
public final class JacksonJsonConverter<T> extends AbstractStringConverter<T> {

    private static final JacksonJsonConverter SANE_DEFAULTS_INSTANCE = new JacksonJsonConverter(
            new ObjectMapper()
                    .enable(SerializationFeature.INDENT_OUTPUT)
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                    .configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true)
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
    );

    private final ObjectMapper mapper;


    private JacksonJsonConverter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Gets a converter with sane defaults from jackson. The sane defaults are the following:
     *
     * <ul>
     *     <li>format/indent the output</li>
     *     <li>write dates in a human readable format</li>
     *     <li>sort properties alphabetically</li>
     *     <li>don't include properties that were null</li>
     * </ul>
     * @param <T> the type of objects that will be converted
     * @return a converter with sane defaults
     */
    public static <T> JacksonJsonConverter<T> getInstanceWithSaneDefaults() {
        //noinspection unchecked
        return SANE_DEFAULTS_INSTANCE;
    }

    /**
     * Gets a converter for the specified object mapper instance.
     *
     * @param objectMapper the object mapper that will be used
     * @param <T> the type of objects that will be converted
     * @return a converter for the specified mapper instance
     */
    public static <T> JacksonJsonConverter<T> getInstanceWithObjectMapper(ObjectMapper objectMapper) {
        return new JacksonJsonConverter<T>(objectMapper);
    }

    /**
     * Integrates a special Jackson mixin entity which modifies the json representation of your
     * entity through Jackson Annotations without actually touching it.
     *
     * @param entityClass class object of the entity you want to represent in json form.
     * @param mixinClass  class object representing the standard Jackson Mixin which offers an indirect way
     *                    to add annotations to ignore or do something else with the members
     *                    of the entity you want to serialize without actually touching it.
     *                    Learn more at: {@linktourl http://wiki.fasterxml.com/JacksonMixInAnnotations}
     * @return same instance of converter for the specified mapper instance
     */
    public void withJacksonMixin(Class<?> entityClass, Class<?> mixinClass) {
        mapper.addMixInAnnotations(entityClass, mixinClass);
    }

    @Nonnull
    @Override
    protected String getStringForm(T entity) {
        try {
            return mapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            throw new AssertionError("Could not convert " + entity.getClass() + "object", e);
        }
    }
}