package com.nikolavp.approval.converters;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * User: nikolavp
 * Date: 28/02/14
 * Time: 15:10
 */
public class DefaultConverterTest {
    @Test
    public void shouldReturnTheSameContentThatWasPassedIn() throws Exception {
        byte[] someContent = "test".getBytes();
        Assert.assertThat(new DefaultConverter().getRawForm(someContent), CoreMatchers.equalTo(someContent));

    }
}
