package com.example.matthewtaila.rendevous.model;

/**
 * Created by matthewtaila on 8/12/17.
 */

public class MessageEvent {

    String title;
    String uri;

    public MessageEvent(String title, String uri) {
        this.title = title;
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
