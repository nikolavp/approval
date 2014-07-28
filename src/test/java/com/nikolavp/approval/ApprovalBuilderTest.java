package com.nikolavp.approval;

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

import com.nikolavp.approval.converters.Converter;
import com.nikolavp.approval.converters.Converters;
import com.nikolavp.approval.converters.DefaultConverter;
import com.nikolavp.approval.pathmappers.ParentPathMapper;
import com.nikolavp.approval.reporters.Reporters;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.assertThat;

/**
 * User: nikolavp
 * Date: 28/02/14
 * Time: 15:25
 */
public class ApprovalBuilderTest {

    @Test
    public void shouldBuildApprovalsWithDifferentParameters() throws Exception {
        //assign
        Approval<byte[]> approver = Approval.of(byte[].class)
                .withConveter(new DefaultConverter())
                .withReporter(Reporters.gvim()).build();

        assertThat(approver, CoreMatchers.notNullValue());
    }

    @Test
    public void shouldProvideDefaultConvertersForCommonObjects() throws Exception {
        verifyPrimitiveAndWrapper(byte.class, Byte.class, Converters.BYTE);
        verifyPrimitiveAndWrapper(short.class, Short.class, Converters.SHORT);
        verifyPrimitiveAndWrapper(int.class, Integer.class, Converters.INTEGER);
        verifyPrimitiveAndWrapper(long.class, Long.class, Converters.LONG);
        assertThat(build(String.class).getConverter(), CoreMatchers.sameInstance(Converters.STRING));
        verifyPrimitiveAndWrapper(char.class, Character.class, Converters.CHAR);
        verifyPrimitiveAndWrapper(float.class, Float.class, Converters.FLOAT);
        verifyPrimitiveAndWrapper(double.class, Double.class, Converters.DOUBLE);
        verifyPrimitiveAndWrapper(boolean.class, Boolean.class, Converters.BOOLEAN);
    }


    @Test
    public void shouldProvideProperWrapperForArrayOfPrimitiveValues() throws Exception {
        verifyArrayConverter(byte[].class, Converters.BYTE_ARRAY);
        verifyArrayConverter(short[].class, Converters.SHORT_ARRAY);
        verifyArrayConverter(int[].class, Converters.INTEGER_ARRAY);
        verifyArrayConverter(long[].class, Converters.LONG_ARRAY);
        verifyArrayConverter(float[].class, Converters.FLOAT_ARRAY);
        verifyArrayConverter(double[].class, Converters.DOUBLE_ARRAY);
        verifyArrayConverter(boolean[].class, Converters.BOOLEAN_ARRAY);
        verifyArrayConverter(char[].class, Converters.CHAR_ARRAY);
        verifyArrayConverter(String[].class, Converters.STRING_ARRAY);
    }

    private static <T> void verifyPrimitiveAndWrapper(Class<T> primitive, Class<T> wrapper, Converter<T> converter) {
        assertThat(build(wrapper).getConverter(), CoreMatchers.sameInstance(converter));
        assertThat(build(primitive).getConverter(), CoreMatchers.sameInstance(converter));
    }

    private static <T> Approval<T> build(Class<T> clazz) {
        return Approval.of(clazz).withReporter(Reporters.gvim()).build();
    }

    private static <T>  void verifyArrayConverter(Class<T> clazz, Converter<T> converter) {
        assertThat(build(clazz).getConverter(), CoreMatchers.sameInstance(converter));
    }

    @Test(expected = IllegalStateException.class)
    public void shouldntAllowApprovalBuildingWithoutConverterSet() throws Exception {
        //assign
        final Approval<Entity> build = build(Entity.class);
        //act

        //assert
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowAnExceptionIfNoReporterWasSet() throws Exception {
        Approval.of(int[].class).build();
    }

    @Test
    public void shouldProperlySetReporterOnBuilder() throws Exception {
        assertThat(build(short[].class).getReporter(), CoreMatchers.equalTo(Reporters.gvim()));
    }

    @Test
    public void shouldUseThePathMapperSet() throws Exception {
        final PathMapper<short[]> pathMapper = new ParentPathMapper<>(Paths.get("test"));
        assertThat(Approval.of(short[].class).withReporter(Reporters.gvim())
                .withPathMapper(pathMapper)
                .build().getPathMapper(), CoreMatchers.equalTo(pathMapper));
    }
}
