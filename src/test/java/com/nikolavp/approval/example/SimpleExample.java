package com.nikolavp.approval.example;

public class SimpleExample {
    public static String generateHtml(String pageTitle) {
        return String.format(
                "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "   <title>%s</title>\n" +
                "<meta charset=\"utf-8\"/>\n" +
                "<link href=\"css/myscript.css\"\n" +
                "      rel=\"stylesheet\"/>\n" +
                "<script src=\"scripts/myscript.js\">\n" +
                "</script>\n" +
                "</head>\n" +
                "<body>\n" +
                "...\n" +
                "</body>\n" +
                "</html>", pageTitle);
    }
}
