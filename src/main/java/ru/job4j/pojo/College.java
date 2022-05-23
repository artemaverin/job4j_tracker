package ru.job4j.pojo;

import java.time.LocalDate;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFio("Petrov Ivan Sergeevich");
        student.setGroup("1Y3");
        student.setDateEntrance(LocalDate.of(2020, 8, 10));
        System.out.println(student.getFio()
                + " studies in group:" + student.getGroup() + "."
                + " Entered the university on " + student.getDateEntrance());
    }
}
