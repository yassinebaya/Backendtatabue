package com.example.demo.dtos;

public record ChangePasswordRequestDTO(String currentPassword,String newPassword,String confirmPassword,String Token) {

}
