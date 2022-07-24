package com.eeit45.champion.vegetarian.model;

import javax.websocket.Session;

public class WebSocketClient {
    // 與某個客戶端的連接會話，需要通過它來給客戶端發送數據
    private Session session;

    //連接的uri
    private String uri;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}