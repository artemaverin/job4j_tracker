package ru.job4j.early;

import java.util.Arrays;
import java.util.List;

public class PasswordValidator {

    public static String validate(String password) {
        int countLowerCase = 0;
        int countUpperCase = 0;
        int countDigit = 0;
        int countSpec = 0;
        List<String> list = Arrays.asList("qwerty", "12345", "password", "admin", "user");
        if (password == null) {
            throw new IllegalArgumentException("Password does not contain any symbol");
        }
        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("password length out of range");
        }
        for (String s : list) {
            if (password.toLowerCase().contains(s.toLowerCase())) {
                throw new IllegalArgumentException("Password contains forbidden words");
            }
        }
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLetter(password.charAt(i)) && Character.isUpperCase(password.charAt(i))) {
                countUpperCase++;
            }
            if (Character.isLetter(password.charAt(i)) && Character.isLowerCase(password.charAt(i))) {
                countLowerCase++;
            }
            if (Character.isDigit(password.charAt(i))) {
                countDigit++;
            }
            if (!Character.isDigit(password.charAt(i)) && !Character.isLetter(password.charAt(i))) {
                countSpec++;
            }
            if (countDigit > 0 && countSpec > 0 && countLowerCase > 0 && countUpperCase > 0) {
                break;
            }
        }

        if (countUpperCase == 0) {
            throw new IllegalArgumentException("Password does not contain at least one uppercase character");
        }
        if (countLowerCase == 0) {
            throw new IllegalArgumentException("Password does not contain at least one lowercase character");
        }
        if (countDigit == 0) {
            throw new IllegalArgumentException("Password does not contain numbers");
        }
        if (countSpec == 0) {
            throw new IllegalArgumentException("Password does not contain special symbols");
        }

        return "Password is valid";
    }
}
