package com.hsbc.demo.util;

import org.springframework.util.DigestUtils;

public class Md5 {

    private String md5;

    public Md5(String str) {

        this.md5 = DigestUtils.md5DigestAsHex(str.getBytes());
    }

    public String get() {

        return this.md5;
    }
}
