/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaproject;

import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
            conn.setAutoCommit(false); 

            String SQL = "INSERT INTO categorie (nomC, description) VALUES (?, ?)";
            System.out.println("SQL Statement: " + SQL); 

            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(SQL);
            pstmt.setString(1, c.nom);
            pstmt.setString(2, c.description);

            int r = pstmt.executeUpdate();

            System.out.println("pstmt.toString() : " + pstmt.toString());

            conn.commit(); 
        } catch (SQLException ex) {
            System.out.println("Impossible d'ajouter category");
            System.out.println(ex.getMessage());
        }
    }

    public static void modifierCategorie(Catégorie c) {
        try {
            Connection conn = mySQL.getConnection();
            conn.setAutoCommit(false); 

            String SQL = "UPDATE categorie SET nomC = ?, description = ? WHERE idC = ?";

            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(SQL);
            pstmt.setString(1, c.nom);
            pstmt.setString(2, c.description);
            pstmt.setInt(3, c.id);
            int rowsUpdated = pstmt.executeUpdate();

            conn.commit(); 

        } catch (SQLException ex) {
            System.out.println("Modification error");
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
            System.out.println("Deleting error");
            System.out.println(ex.getMessage());
        }
    }

    public static float venteGenererParCategory(String nomCategory) {
        try {
            Connection conn = mySQL.getConnection();
            conn.setAutoCommit(true);
            String SQL = "SELECT * from vente NATURAL JOIN produit NATURAL JOIN categorie WHERE nomC = ?";

            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(SQL);

            pstmt.setString(1, nomCategory);

            ResultSet rs = pstmt.executeQuery();
            Catégorie c = null;
            Produit p = null;
            LinkedList<Catégorie> listCategory = new LinkedList<Catégorie>();
            LinkedList<Produit> listProduit = new LinkedList<Produit>();

            float revenuParCategory = 0;

            while (rs.next()) {
                int idC = rs.getInt(1);
                int idP = rs.getInt(2);
                String nomP = rs.getString(6);
                float prixU = rs.getFloat(7);
                int quantite = rs.getInt(8);
                int quantiteVendue = rs.getInt(5);


                p = new Produit(idP, nomP, prixU, quantite, idC);
                listProduit.add(p);
                revenuParCategory += prixU * quantiteVendue;
            }

            System.out.println(revenuParCategory);
            return revenuParCategory;
        } catch (SQLException ex) {
            System.out.println("Error generated by category");
            System.err.println(ex.getMessage());
            return 0;
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
