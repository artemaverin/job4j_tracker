package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает работу банковского сервиса по заведению Клиента и Счета, а также поиску информации
 * @author ARTEM AVERIN
 * @version 1.0
 */

public class BankService {
    /**
     * хранение данных всех Клиентов осуществляется в коллекции HashMap с привязанными к ним счетами
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * метод добавляет Клиента в базу
     * @param user новый Клиент банка
     */

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Метод добавляет банковский Счет для Клиента в базу
     * Клиент ищется по паспорту {@link  #findByPassport(String)}
     * @param passport реквизиты для счета
     * @param account сумма на счете
     */

    public void addAccount(String passport, Account account) {
       User user = findByPassport(passport);
       if ((user != null) && !users.get(user).contains(account)) {
            users.get(user).add(account);
       }
    }

    /**
     * Метод для поиска Клиента по номеру паспорту
     * @param passport  данные паспорта пользователя
     * @return возвращает Клиента или null, если Клиент не найден
     */

    public User findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(p -> p.getPassport().equals(passport))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод для поиска Счета по реквизитам
     * @param passport данные паспорта Клиента
     * @param requisite данные реквиты Счета
     * @return возвращает Счет или null, если Счет не найден
     */

    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            return accounts.stream()
                    .filter(r -> r.getRequisite().equals(requisite))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    /**
     * Метод переводит деньги с одного счета на другой
     * Счета находятся с помощью метода {@link #findByRequisite(String, String)}
     * @param srcPassport номер паспорта Клиента, со Счета которого списываются деньги
     * @param srcRequisite реквизиты Счета, с которого списываются деньги
     * @param destPassport номер паспорта Клиента, на Счет которого зачисляются деньги
     * @param destRequisite реквизиты Счета, на который зачисляются деньги
     * @param amount сумма на счете
     * @return возвращает true, если перевод успешно произведен или false, если Счет не найден или не хватает денег
     */

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account accountSrc = findByRequisite(srcPassport, srcRequisite);
        Account accountDest = findByRequisite(destPassport, destRequisite);
        if (accountDest != null && accountSrc != null && accountSrc.getBalance() >= amount) {
            accountDest.setBalance(amount + accountDest.getBalance());
            accountSrc.setBalance(accountSrc.getBalance() - amount);
            rsl = true;
        }
        return rsl;
    }
}
