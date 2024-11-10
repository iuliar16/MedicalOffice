package com.idm.server.db;

public class User {
    private int user_id;

    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User() {

    }
    public User(int user_id, String username, String password, String role) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void setId_user(int id_user) {
        this.user_id = id_user;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public int getUser_id(){return user_id;}

    public String getId_user() {
        return String.valueOf(user_id);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
