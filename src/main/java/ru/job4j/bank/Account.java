package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс Account содержит описание модели данных "Банковсий счет"
 * @author  ARTEM AVERIN
 * @version  1.0
 */

public class Account {

    /**
     * в поле requisite хранится информация о банковских реквизитах Счета
     * в поле balance - сумма остатка ден. средств на Счете
     */

    private String requisite;
    private double balance;

    /**
     * Для создания Счета (экземпляра класса) необходимы реквизиты и сумма
     * @param requisite реквизиты счета
     * @param balance сумма на счете
     */

    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    /**
     * Метод - геттер, получает информацию о реквизитах Счета
     * @return возвращает реквизиты Счета у объекта класса Account
     */

    public String getRequisite() {
        return requisite;
    }

    /**
     * Метод - сеттер, устанвливает в поле реквизиты
     * @param requisite в параметры передаются реквизиты счета
     */

    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    /**
     * Метод - геттер, получает информацию о балансе Счета
     * @return возвращает баланс счета у объекта класса Account
     */

    public double getBalance() {
        return balance;
    }

    /**
     * Метод - сеттер, устанвливает в поле сумму баланса
     * @param balance через параметр задается сумма на Счете
     */

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Метод перопределяет работу метода equals,
     * использую в качестве сравнения объектов , поле с реквизитами Счета
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
        Account account = (Account) o;
        return Objects.equals(requisite, account.requisite);
    }

    /**
     * Метод переопределяет хэшкод объекта
     * @return возвращает хэш код объекта, сгенерированному по реквизитам Счета
     */

    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }

}
