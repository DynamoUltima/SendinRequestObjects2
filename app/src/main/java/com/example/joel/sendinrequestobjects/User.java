package com.example.joel.sendinrequestobjects;

/**
 * Created by joel on 7/14/2018.
 */

public class User {
    private String token;
    private String name;
    private String email;
    private String address;
    private String password;
    private int phone_number;

    public User(String name, String email, String address, String password, int phone_number) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.password = password;
        this.phone_number = phone_number;
    }
   public String getToken(){
       return token;
   }
   public void setToken(String token){
       this.token =token;
   }




}
