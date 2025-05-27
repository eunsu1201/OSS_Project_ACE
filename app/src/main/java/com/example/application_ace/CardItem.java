package com.example.application_ace;

public class CardItem {
    private final String title;
    private final String content;

    public CardItem(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
