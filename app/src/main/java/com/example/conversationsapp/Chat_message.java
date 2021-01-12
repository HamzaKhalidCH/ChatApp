package com.example.conversationsapp;

public class Chat_message {
    private String message;
    private String senderName;
    private String senderInitials;
    private String timeSent;

    public Chat_message(String message, String senderName, String senderInitials, String timeSent) {
        this.message = message;
        this.senderName = senderName;
        this.senderInitials = senderInitials;
        this.timeSent = timeSent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderInitials() {
        return senderInitials;
    }

    public void setSenderInitials(String senderInitials) {
        this.senderInitials = senderInitials;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(String timeSent) {
        this.timeSent = timeSent;
    }

    @Override
    public String toString() {
        return "Chat_message{" +
                "message='" + message + '\'' +
                ", senderName='" + senderName + '\'' +
                ", senderInitials='" + senderInitials + '\'' +
                ", timeSent='" + timeSent + '\'' +
                '}';
    }
}
