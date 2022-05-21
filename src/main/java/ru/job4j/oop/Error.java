package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println("Текущее состояние: " + active);
        System.out.println("Текущий статус: " + status);
        System.out.println("Сообщение: " + message);
    }

    public static void main(String[] args) {
        Error error = new Error();
        Error error1 = new Error(true, 1, "введено не верное значение");
        Error error0 = new Error(false, 0, "Ошибок не найдено");
        Error error3 = new Error(true, 3, "необходимо заполнить все поля");
        error.printInfo();
        System.out.println();
        error1.printInfo();
        System.out.println();
        error0.printInfo();
        System.out.println();
        error3.printInfo();
    }
}
