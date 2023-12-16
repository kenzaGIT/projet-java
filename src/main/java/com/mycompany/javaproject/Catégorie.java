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
public class Catégorie {

    String nom;
    String description;
    int id;

    public Catégorie() {

    }

    public Catégorie(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public Catégorie(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public static LinkedList<Catégorie> getCategory() {
        try {
            Connection conn = mySQL.getConnection();
            conn.setAutoCommit(true);

            String SQL = "SELECT * FROM categorie";

            PreparedStatement pstmt = conn.prepareStatement(SQL);

            ResultSet rs = pstmt.executeQuery();
            Catégorie c = null;
            LinkedList<Catégorie> listCategory = new LinkedList<Catégorie>();

            while (rs.next()) {
                int idC = rs.getInt(1);
                String nomC = rs.getString(2);
                String description = rs.getString(3);
                c = new Catégorie(idC, nomC, description);
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

    public static void nouvelCategorie(Catégorie c) {
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

    public static void modifierCategorie(Catégorie c) {
        try {
            Connection conn = mySQL.getConnection();
            conn.setAutoCommit(false); // Set auto-commit to false

            String SQL = "UPDATE categorie SET nomC = ?, description = ? WHERE idC = ?";

            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(SQL);
            pstmt.setString(1, c.nom);
            pstmt.setString(2, c.description);
            pstmt.setInt(3, c.id);
            int rowsUpdated = pstmt.executeUpdate();

            conn.commit(); // Manually commit the transaction
            System.out.println(rowsUpdated + " row(s) updated");

        } catch (SQLException ex) {
            System.out.println("Erreur dans la modification");
            System.out.println(ex.getMessage());
        }
    }

    public static void deleteCategory(Catégorie c) {
        try {
            Connection conn = mySQL.getConnection();
            conn.setAutoCommit(true);

            String SQL = "DELETE from categorie WHERE idC = ?";
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(SQL);

            pstmt.setInt(1, c.id);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erreur dans la suppression");
            System.out.println(ex.getMessage());
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
