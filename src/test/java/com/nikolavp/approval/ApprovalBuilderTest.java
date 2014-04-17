package com.nikolavp.approval;

import com.nikolavp.approval.converters.Converter;
import com.nikolavp.approval.converters.Converters;
import com.nikolavp.approval.converters.DefaultConverter;
import com.nikolavp.approval.reporters.Reporters;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

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

    private static <T> Approval<T> build(Class<T> byteClass) {
        return Approval.of(byteClass).withReporter(Reporters.gvim()).build();
    }

    private static <T>  void verifyArrayConverter(Class<T> clazz, Converter<T> converter) {
        assertThat(build(clazz).getConverter(), CoreMatchers.sameInstance(converter));
    }

    @Test
    public void shouldntAllowApprovalBuildingWithoutConverterSet() throws Exception {
        //assign

        //act

        //assert
    }
}
