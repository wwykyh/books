package com.dragon.book.util;

public class PasswordAdapter  extends DataOperator {

    final  int key =20;

    Caesar caesar;

    public PasswordAdapter( Caesar caesar) {

        this.caesar = caesar;
    }


    @Override
    public String doEncrypt(String ps) {
        return caesar.doEncrypt(key, ps);
    }
}
