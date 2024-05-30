package com.sparta.todolist.exception.message;

import lombok.Data;

@Data
public class Message {

    private StatusEnum status;
    private String message;
    private Object data;

    public Message() {
        this.status = StatusEnum.OK;
        this.data = null;
        this.message = null;
    }

    public static Message createResponse(StatusEnum status, String message, Object data) {
        Message response = new Message();
        response.setStatus(status);
        response.setMessage(message);
        response.setData(data);
        return response;
    }



}
