package com.example.demo.entity;

import lombok.Data;

@Data
public class ThymeleafUser {
    private String name;
    private String sex;
    private String num;

    public ThymeleafUser(String name, String sex, String num) {
        this.name = name;
        this.sex = sex;
        this.num = num;
    }
}
