package com.example.androidrecycle.user;

public class User {

    private static User single_instance = null;
    private int id;
    private String username;
    private int role;

    private User(int id, String username, int role){
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public static synchronized User getInstance(int id, String username, int role){
        if (single_instance == null)
            single_instance = new User(id, username, role);
        return single_instance;
    }

    public static synchronized User getInstance(){
        if (single_instance == null)
            return null;
        return single_instance;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getRole() {
        return role;
    }
}
