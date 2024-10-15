package org.core;

public class InvalidCurrencyCode extends Exception {
    public InvalidCurrencyCode() {
        super("Such a currency doesn't exist!");
    }
}
