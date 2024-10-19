package org.core;

public class UnknownCurrencyException extends Exception {
    public UnknownCurrencyException(String curr) {
        super("Error:" + curr + " currency doesn't exist!");
    }
}
