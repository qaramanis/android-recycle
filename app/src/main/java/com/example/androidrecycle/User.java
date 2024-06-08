package com.example.androidrecycle;

import androidx.lifecycle.ViewModelProvider;

public class User {

    private static User single_instance = null;
    private final int id;
    private final String username;
    private final int role;
    private int points;

    private User(int id, String username, int role, int points){
        this.id = id;
        this.username = username;
        this.role = role;
        this.points = points;
    }

    public static synchronized User getInstance(int id, String username, int role, int points){
        single_instance = new User(id, username, role, points);
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

    public int getPoints(){return points;}

    public void setPoints(int points){this.points += points; }

}
