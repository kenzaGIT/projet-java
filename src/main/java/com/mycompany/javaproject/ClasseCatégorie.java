/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaproject;

import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author macbookair
 */
public class ClasseCatégorie {

    String nom;
    String description;

    public ClasseCatégorie(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public static void enregisterCategorie(ClasseCatégorie c) {
        try {
            Connection conn = mySQL.getConnection();
            conn.setAutoCommit(false); // Set auto-commit to false

            String SQL = "INSERT INTO categorie (nomC, description) VALUES (?, ?)";
            System.out.println("SQL Statement: " + SQL); // Print the SQL statement

            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(SQL);
            pstmt.setString(1, c.nom);
            pstmt.setString(2, c.description);

            int r = pstmt.executeUpdate();

            System.out.println("pstmt.toString() : " + pstmt.toString());

            conn.commit(); // Manually commit the transaction
        } catch (SQLException ex) {
            System.out.println("Impossible d'ajouter category");
            System.out.println(ex.getMessage());
        }
    }

    public String toString() {
        return """
               nomC: %s
               description: %s
               """.formatted(nom, description);
    }

}
