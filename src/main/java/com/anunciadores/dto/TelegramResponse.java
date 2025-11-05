package com.anunciadores.dto;

import java.util.List;

public class TelegramResponse {
    private boolean ok;
    private List<Update> result;

    // Getters y setters

    public class Update {
        private long update_id;
        private Message message;

        // Getters y setters

        public long getUpdate_id() {
            return update_id;
        }

        public void setUpdate_id(long update_id) {
            this.update_id = update_id;
        }

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }
    }

    public static class Message {
        private long message_id;
        private From from;
        private Chat chat;
        private String text;

        public long getMessage_id() {
            return message_id;
        }

        public void setMessage_id(long message_id) {
            this.message_id = message_id;
        }

        public From getFrom() {
            return from;
        }

        public void setFrom(From from) {
            this.from = from;
        }

        public Chat getChat() {
            return chat;
        }

        public void setChat(Chat chat) {
            this.chat = chat;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class From {
        private long id;
        private String first_name;
        private String username;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public static class Chat {
        private long id;
        private String type;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}




