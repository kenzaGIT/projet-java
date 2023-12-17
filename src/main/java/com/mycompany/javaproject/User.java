/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaproject;

/**
 *
 * @author macbookair
 */
public class User {
    private String id;
    private String login;
    private String password;
    
    
    
     public  User(String id, String login,String passsword) {
        this.id = id;
        this.login = login;
        this.password = password;
        
    }
     
    public String getId() {
        return id;
    }
    
     public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id='" + id + '\'' +
                ", Login'" + login+ '\'' +
                ", Password='" + password+ 
                '}';
    }

    
    
    
}
