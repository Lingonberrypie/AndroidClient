package com.example.androidclient;

public class APIError {
    private int statusCode;
    private String message = "Неизвестная ошибка.";

    public APIError() {
    }

    public int status() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
