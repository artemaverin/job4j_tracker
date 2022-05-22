package ru.job4j.inheritance;

public class JSONReport {

    public String generate(String name, String body) {
        String lS = System.lineSeparator();
        return "{" + lS
                + "\t\"name\" : " + "\"" + name + "\"" + "," + lS
                + "\t\"body\" : " + "\"" + body + "\"" + lS
                + "}";
    }

}
