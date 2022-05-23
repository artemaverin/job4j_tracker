package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book book1 = new Book("Three bears", 35);
        Book book2 = new Book("Kolobok", 46);
        Book book3 = new Book("Golden fish", 41);
        Book book4 = new Book("Clean code", 2);
        Book[] library = new Book[4];
        library[0] = book1;
        library[1] = book2;
        library[2] = book3;
        library[3] = book4;
        for (int index = 0; index < library.length; index++) {
            Book book = library[index];
            System.out.println("Book's name: " + book.getName() + "," + " number of pages :" + book.getPagesCount());
        }
        System.out.println();
        Book temp = library[0];
        library[0] = library[3];
        library[3] = temp;
        for (int index = 0; index < library.length; index++) {
            Book book = library[index];
            System.out.println("Book's name: " + book.getName() + "," + " number of pages :" + book.getPagesCount());
        }
        System.out.println();
        for (int index = 0; index < library.length; index++) {
            Book book = library[index];
            if (book.getName().equals("Clean code")) {
                System.out.println("Book's name: " + book.getName() + "," + " number of pages :" + book.getPagesCount());
            }
        }
    }
}
