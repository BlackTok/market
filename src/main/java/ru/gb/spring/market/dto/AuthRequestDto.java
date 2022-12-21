package ru.gb.spring.market.dto;

import lombok.Getter;

@Getter
public class AuthRequestDto {
    private String mail;
    private String password;
}
