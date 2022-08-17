package ru.job4j.early;

public class PasswordValidator {

    public static String validate(String password) {
        int countLowerCase = 0;
        int countUpperCase = 0;
        int countDigit = 0;
        int countSpec = 0;
        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("password length out of range");
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
            throw new IllegalArgumentException("The password does not contain");
        }

        return " password has been registered";
    }
}
