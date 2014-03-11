package com.nikolavp.approval.converters;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * User: nikolavp
 * Date: 11/03/14
 * Time: 19:23
 */
public class ConvertersTest {
    @Test
    public void shouldJustCallToStringOnPrimitiveTheObject() throws Exception {
        byte[] rawForm = Converters.DOUBLE.getRawForm(1.0D);
        Assert.assertThat(new String(rawForm), CoreMatchers.equalTo("1.0"));
    }
}
