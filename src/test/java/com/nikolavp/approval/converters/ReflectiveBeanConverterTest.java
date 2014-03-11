package com.nikolavp.approval.converters;

import com.nikolavp.approval.Entity;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * User: nikolavp
 * Date: 28/02/14
 * Time: 15:12
 */
public class ReflectiveBeanConverterTest {
    @Test
    public void shouldIntrospectFieldsAndShowThemToTheUser() throws Exception {
        //assign
        ReflectiveBeanConverter<Entity> converter = new ReflectiveBeanConverter<Entity>();

        //act
        byte[] rawForm = converter.getRawForm(new Entity("my simple name", 1000));

        //assert
        Assert.assertThat(new String(rawForm), CoreMatchers.equalTo("name = my simple name\nage = 1000\n"));
    }

    @Test
    public void shouldNotIncludeFieldsThatAreNull() throws Exception {
        //assign
        ReflectiveBeanConverter<Entity> converter = new ReflectiveBeanConverter<Entity>();

        //act
        byte[] rawForm = converter.getRawForm(new Entity(null, 1000));

        //assert
        Assert.assertThat(new String(rawForm), CoreMatchers.equalTo("age = 1000\n"));
    }
}
