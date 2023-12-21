/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaproject;

import java.sql.Connection;
import java.util.Date;
import java.time.LocalDateTime;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 *
 * @author macbookair
 */
public class ClasseVendeur {

    private int idVente;
    private int idP;
    private int quantiteVendue;
    private LocalDateTime dateVente;

    public ClasseVendeur(int idP, int quantiteVendue) {
        this.idP = idP;
        this.quantiteVendue = quantiteVendue;
    }

    public ClasseVendeur(int idVente, int idP, int quantiteVendue, LocalDateTime dateVente) {
        this.idVente = idVente;
        this.idP = idP;
        this.quantiteVendue = quantiteVendue;
        this.dateVente = LocalDateTime.now();
    }

    public int getIdVente() {
        return idVente;
    }

    public int getIdP() {
        return idP;
    }

    public int getquantiteVendue() {
        return quantiteVendue;
    }

    public LocalDateTime getdateVente() {
        return dateVente;
    }

    public static int retrieveIdP(String nomP, Connection conn) throws SQLException {
        int idP = -1; 

        String sql = "SELECT idP FROM produit WHERE nomP = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nomP);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    idP = resultSet.getInt("idP");
                }
            }
        }

        return idP;
    }

    public static int retrieveQuantite(String nomP, Connection conn) {
        int quantite = -1;

        String sql = "SELECT quantite FROM produit WHERE nomP = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nomP);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    quantite = resultSet.getInt("quantite");
                } else {
                    System.out.println("Product not found in the database.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return quantite;
    }

    public static LinkedList<Produit> getProducts() {
        try {
            Connection conn = mySQL.getConnection();
            conn.setAutoCommit(true);

            String SQL = "SELECT * FROM produit";

            PreparedStatement pstmt = conn.prepareStatement(SQL);

            ResultSet rs = pstmt.executeQuery();
            Produit p = null;
            LinkedList<Produit> listProduit = new LinkedList<Produit>();

            while (rs.next()) {
                int idP = rs.getInt("idP");
                String nomP = rs.getString("nomP");
                int quantite = rs.getInt("quantite");
                float prixU = rs.getFloat("prixU");
                int idC = rs.getInt("idC");
                p = new Produit(nomP, prixU, quantite, idC);
                listProduit.add(p);
            }

            if (p == null) {
                System.out.println("Unfound Product");
            }

            return listProduit;

        } catch (SQLException ex) {
            System.out.println("Database error");
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
