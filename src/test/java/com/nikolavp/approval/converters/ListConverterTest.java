package com.nikolavp.approval.converters;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

/**
 * User: nikolavp
 * Date: 28/02/14
 * Time: 17:47
 */
public class ListConverterTest {
    @Test
    public void shouldBeComposableWithAnotherConverter() throws Exception {
        //assign
        Converter<String> other = Mockito.mock(Converter.class);
        Mockito.when(other.getRawForm(Mockito.anyString())).thenReturn("test".getBytes());
        ListConverter<String> listConverter = new ListConverter<>(other);
        List<String> strings = Arrays.asList("test", "foo", "bar");

        //act
        byte[] rawForm = listConverter.getRawForm(strings);

        //assert
        Assert.assertThat(new String(rawForm), CoreMatchers.equalTo("[0] = test\n[1] = test\n[2] = test\n"));
        Mockito.verify(other, Mockito.times(3)).getRawForm(Mockito.anyString());
    }

}
