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
import java.util.LinkedList;
import java.util.Optional;

/**
 *
 * @author macbookair
 */
public class ClasseCatégorie {

    String nom;
    String description;
    int id;

    public ClasseCatégorie(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public ClasseCatégorie(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public static void nouvelCategorie(ClasseCatégorie c) {
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

    public static void modifierCategorie(ClasseCatégorie c) {

    }

    public static LinkedList<ClasseCatégorie> getCategory() {
        try {
            Connection conn = mySQL.getConnection();
            conn.setAutoCommit(true);

            String SQL = "SELECT * FROM categorie";

            PreparedStatement pstmt = conn.prepareStatement(SQL);

            ResultSet rs = pstmt.executeQuery();
            ClasseCatégorie c = null;
            LinkedList<ClasseCatégorie> listCategory = new LinkedList<ClasseCatégorie>();

            while (rs.next()) {
                int idC = rs.getInt(1);
                String nomC = rs.getString(2);
                String description = rs.getString(3);
                c = new ClasseCatégorie(idC, nomC, description);
                listCategory.add(c);
            }
           
            if (c == null) {
                System.out.println("Categorie introuvable");
            }

            return listCategory;

        } catch (SQLException ex) {
            System.out.println("Erreur Base de Données");
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public String toString() {
        if (id != 0) {
            return """
            id: %d
            nomC: %s
            description: %s
            """.formatted(id, nom, description);
        } else {
            return """
            nomC: %s
            description: %s
            """.formatted(nom, description);
        }
    }

}
