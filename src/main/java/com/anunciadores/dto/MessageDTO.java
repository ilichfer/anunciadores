package com.anunciadores.dto;

public class MessageDTO {
    public int message_id;
    public FromDTO from;
    public ChatDTO chat;
    public int date;
    public String text;
    public ContactDTO contact;

    public MessageDTO() {
    }

    public MessageDTO(int message_id, FromDTO from, ChatDTO chat, int date, String text, ContactDTO contact) {
        this.message_id = message_id;
        this.from = from;
        this.chat = chat;
        this.date = date;
        this.text = text;
        this.contact = contact;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public FromDTO getFrom() {
        return from;
    }

    public void setFrom(FromDTO from) {
        this.from = from;
    }

    public ChatDTO getChat() {
        return chat;
    }

    public void setChat(ChatDTO chat) {
        this.chat = chat;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ContactDTO getContact() {
        return contact;
    }

    public void setContact(ContactDTO contact) {
        this.contact = contact;
    }
}
