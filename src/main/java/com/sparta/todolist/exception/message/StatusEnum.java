package com.sparta.todolist.exception.message;

public enum StatusEnum {

    OK(200, "OK"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    NOT_FOUND(404, "NOT_FOUND"),
    UNAUTHORIZED(401,"UNAUTHORIZED"),
    INTERNAL_SERER_ERROR(500, "INTERNAL_SERVER_ERROR");



    final int statusCode;
    final String code;

    StatusEnum(int statusCode, String code) {
        this.statusCode = statusCode;
        this.code = code;
    }
}