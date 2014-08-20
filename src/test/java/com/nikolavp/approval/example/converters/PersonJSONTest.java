package com.nikolavp.approval.example.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import org.junit.Test;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class PersonJSONTest {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final Person person = new Person("Luther Blissett", "lb@example.com");
        final String fixtureValue = Resources.toString(Resources.getResource("person.json"), UTF_8);
        assertThat(MAPPER.writeValueAsString(person), equalTo(fixtureValue));
    }
}
