package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс - модель User содержит описание Клиента банка (пользователя)
 * @author ARTEM AVERIN
 * @version 1.0
 */

public class User {

    /**
     * Поле passport - служит для хранения номера паспорта Клиента
     * Поле username -  служит для хранения имени Клиента
     */

    private String passport;
    private String username;

    /**
     * Конструтор для создания экземпляра класса Клиента
     * @param passport инициализирует номер паспорта
     * @param username инициализирует имя Клиента
     */

    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    /**
     * Метод позволяет получить номер поспорта
     * @return возвращает номер поспорта или null, если номер паспорта пустой
     */

    public String getPassport() {
        return passport;
    }

    /**
     * Метод присваивает номер поспорта
     * @param passport номер паспорта
     */

    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * Метод позволяет получить имя пользователя
     * @return возвращает имя пользователя или null, если имя пустое
     */

    public String getUsername() {
        return username;
    }

    /**
     * Метод позволяет присвоить имя пользователя
     * @param username  имя пользователя
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Метод перопределяет работу метода equals,
     * использую в качестве сравнения объектов , поле с номером паспорта Клиента
     * @param o передает объект для сравнения
     * @return возвращает результат сравнения, равны объекты (True) или нет (False)
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(passport, user.passport);
    }

    /**
     * Метод переопределяет хэшкод объекта
     * @return возвращает хэш код объекта, сгенерированному по номеру паспорта Клиента
     */

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }

}
