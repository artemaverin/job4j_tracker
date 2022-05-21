package ru.job4j.tracker.oop;

public class DummyDic {
    public static void main(String[] args) {
        DummyDic dummyDic = new DummyDic();
        String phrase = dummyDic.engToRus("рентгеноэлектрокардиографический");
        System.out.println(phrase);
    }

    public String engToRus(String eng) {
        return "Неизвестное слово. " + eng;
    }
}
