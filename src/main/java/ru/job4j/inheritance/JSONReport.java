package ru.job4j.inheritance;

public class JSONReport extends TextReport {

    public String generate(String name, String body) {
        String lS = System.lineSeparator();
        return super.generate("{" + lS
                + "\t\"name\" : " + "\"" + name + "\"" + ",", "\t\"body\" : " + "\"" + body + "\"" + lS
                + "}");
    }

}
