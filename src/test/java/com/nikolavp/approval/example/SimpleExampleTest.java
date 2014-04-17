package com.nikolavp.approval.example;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * User: nikolavp
 * Date: 19/03/14
 * Time: 19:17
 */
public class SimpleExampleTest {

    @Test
    public void shouldReturnSomethingToTestOut() throws Exception {
        //arrange
        String title = "myTitle";
        String expected = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "   <title>" + title +"</title>\n" +
                "<meta charset=\"utf-8\"/>\n" +
                "<link href=\"css/myscript.css\"\n" +
                "      rel=\"stylesheet\"/>\n" +
                "<script src=\"scripts/myscript.js\">\n" +
                "</script>\n" +
                "</head>\n" +
                "<body>\n" +
                "...\n" +
                "</body>\n" +
                "</html>";

        //act
        String actual = SimpleExample.generateHtml(title);

        //assert
        assertThat(actual, equalTo(expected));
    }
}
