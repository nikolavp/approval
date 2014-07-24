package com.nikolavp.approval.converters;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * User: nikolavp
 * Date: 28/02/14
 * Time: 15:10
 */
public class DefaultConverterTest {
    @Test
    public void shouldReturnTheSameContentThatWasPassedIn() throws Exception {
        byte[] someContent = "test".getBytes(StandardCharsets.UTF_8);
        Assert.assertThat(new DefaultConverter().getRawForm(someContent), CoreMatchers.equalTo(someContent));

    }
}
