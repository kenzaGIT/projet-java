/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//Login kimacag417@dpsols.com
// password: 
package com.mycompany.javaproject;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Ryad
 */
public class mySQL {

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://sql.freedb.tech:3306/freedb_java-projet?allowPublicKeyRetrieval=true&useSSL=false";
            String username = "freedb_ryad benmanser";
            String password = "W?2QQh4*uFasAeN";

            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}
