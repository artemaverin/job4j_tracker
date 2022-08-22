package ru.job4j.early;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordValidatorTest {

    @Test (expected = IllegalArgumentException.class)
    public void whenInvalidLength() {
        String password = "$4@b1Ti";
        String passValidate = PasswordValidator.validate(password);
        String expected = "password length out of range";
        assertEquals(passValidate, expected);
    }

    @Test
    public void whenValidLength() {
        String password = "$4@b1Ti2";
        String passValidate = PasswordValidator.validate(password);
        String expected = "Password is valid";
        assertEquals(passValidate, expected);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenAllLetterLowerCase() {
        String password = "$4@b1ti2";
        String passValidate = PasswordValidator.validate(password);
        String expected = "Password does not contain at least one uppercase character";
        assertEquals(passValidate, expected);
    }

    @Test
    public void whenLetterIsUpperCase() {
        String password = "$4@b1Ti2";
        String passValidate = PasswordValidator.validate(password);
        String expected = "Password is valid";
        assertEquals(passValidate, expected);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenAllLetterUpperCase() {
        String password = "$4@B1TI2";
        String passValidate = PasswordValidator.validate(password);
        String expected = "Password does not contain at least one lowercase character";
        assertEquals(passValidate, expected);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenNoNumbers() {
        String password = "$a@BsTI*";
        String passValidate = PasswordValidator.validate(password);
        String expected = "Password does not contain numbers";
        assertEquals(passValidate, expected);
    }

    @Test
    public void whenIsNumber() {
        String password = "$1@BsTI*";
        String passValidate = PasswordValidator.validate(password);
        String expected = "Password is valid";
        assertEquals(passValidate, expected);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenNoSymbols() {
        String password = "q12BsTIK";
        String passValidate = PasswordValidator.validate(password);
        String expected = "Password does not contain special symbols";
        assertEquals(passValidate, expected);
    }

    @Test
    public void whenIsSymbol() {
        String password = "q1?BsTI*";
        String passValidate = PasswordValidator.validate(password);
        String expected = "Password is valid";
        assertEquals(passValidate, expected);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenPassFromForbiddenList() {
        String password = "QwErty12#";
        String passValidate = PasswordValidator.validate(password);
        String expected = "Password contains forbidden words";
        assertEquals(passValidate, expected);
    }

    @Test
    public void whenPassWithoutForbiddenWords() {
        String password = "wErty12#";
        String passValidate = PasswordValidator.validate(password);
        String expected = "Password is valid";
        assertEquals(passValidate, expected);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenPassIsNull() {
        String password = null;
        String passValidate = PasswordValidator.validate(password);
        String expected = "Password does not contain any symbol";
        assertEquals(passValidate, expected);
    }

}