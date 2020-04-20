package com.newsportal.newsportal.test;

import java.time.LocalDateTime;

public class Test1 {

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.getDayOfMonth());
    }
}
