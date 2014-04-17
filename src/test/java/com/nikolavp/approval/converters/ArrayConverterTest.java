package com.nikolavp.approval.converters;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * User: nikolavp (Nikola Petrov)
 * Date: 07/04/14
 * Time: 10:49
 */
public class ArrayConverterTest {
    @Test
    public void shouldBeComposableWithAnotherConverter() throws Exception {
        //assign
        Converter<String> other = Mockito.mock(Converter.class);
        Mockito.when(other.getRawForm(Mockito.anyString())).thenReturn("test".getBytes());
        ArrayConverter<String> listConverter = new ArrayConverter<>(other);
        String[] strings = new String[]{"test", "foo", "bar"};

        //act
        byte[] rawForm = listConverter.getRawForm(strings);

        //assert
        Assert.assertThat(new String(rawForm), CoreMatchers.equalTo("[0] = test\n[1] = test\n[2] = test\n"));
        Mockito.verify(other, Mockito.times(3)).getRawForm(Mockito.anyString());
    }
}
