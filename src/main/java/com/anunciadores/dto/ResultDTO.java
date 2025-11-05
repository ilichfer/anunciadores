package com.anunciadores.dto;

public class ResultDTO {
    public int update_id;
    public MessageDTO message;

    public ResultDTO() {
    }


    public int getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(int update_id) {
        this.update_id = update_id;
    }

    public MessageDTO getMessage() {
        return message;
    }

    public void setMessage(MessageDTO message) {
        this.message = message;
    }
}
