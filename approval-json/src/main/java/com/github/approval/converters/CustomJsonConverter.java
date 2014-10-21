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
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.charset.StandardCharsets;

/**
 *
 * A class object used to represent the serialized entity you want to
 * customize offering a mechanism using Jackson MixIns to do that.
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * <p/>
 * Date added: 10/21/14.
 */

public class CustomJsonConverter<T> extends AbstractConverter<T> {

    /**
     * Default instance of a Jackson ObjectMapper  with useful adjustments
     * that should be considered in all use cases.
     */
    private ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    /**
     * A class object used to represent the serialized entity you want to
     * customize.
     */
    private Class<?> entityClass;

    /**
     * .
     * A class object representing a standard jackson MixIn which offers an indirect way
     * to add annotations to ignore or do something else with the members
     * of the entity you want to serialize without actually touching it.
     *
     * Learn more at: {@linktourl http://wiki.fasterxml.com/JacksonMixInAnnotations}
     */
    private Class<?> mixinClass;

    public CustomJsonConverter(Class<?> entityClass, Class<?> mixinClass) {
        this.entityClass = entityClass;
        this.mixinClass = mixinClass;
    }

    @Nonnull
    @Override
    public byte[] getRawForm(@Nullable T entity) {
        if (entity == null) {
            return "null".getBytes(StandardCharsets.UTF_8);
        }
        mapper.addMixInAnnotations(entityClass, mixinClass);
        JacksonJsonConverter<T> converter = JacksonJsonConverter
                .getInstanceWithObjectMapper(mapper);

        return converter.getRawForm(entity);
    }
}
