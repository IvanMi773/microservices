package com.web_server.webserver.model;

public class Account {

    private String id;
    private String username;

    public Account(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public Account () {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
