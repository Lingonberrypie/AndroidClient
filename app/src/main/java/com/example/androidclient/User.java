package com.example.androidclient;

// POJO для JSON-ответа
public class User {
    private int id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String token;

    private String message;

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    // конструктор для создания пользователя
    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    // конструктор для получения токена
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
