package com.sparta.todolist.exception.message;

import lombok.Data;

@Data
public class ErrorMessage {

    private StatusEnum status;
    private String message;

    public ErrorMessage() {
        this.status = StatusEnum.BAD_REQUEST;
        this.message = null;
    }

    public static ErrorMessage createErrorResponse(StatusEnum status, String message) {
        ErrorMessage response = new ErrorMessage();
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }

}
