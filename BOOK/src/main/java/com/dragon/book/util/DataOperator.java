package com.dragon.book.util;

public abstract class DataOperator {
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public abstract String doEncrypt(String ps);

}

