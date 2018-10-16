package com.example.student.teamproject;

public final class UserModel {
    private static int id = 0;
    private static String name = "";
    private static String email = "";
    private static String messageIsSignedIn = "";

    //    public UserModel(int id, String name, String email, String messageIsSignedIn) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.messageIsSignedIn = messageIsSignedIn;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        UserModel.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        UserModel.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        UserModel.email = email;
    }

    public String getMessageIsSignedIn() {
        return messageIsSignedIn;
    }

    public void setMessageIsSignedIn(String messageIsSignedIn) {
        UserModel.messageIsSignedIn = messageIsSignedIn;
    }
}