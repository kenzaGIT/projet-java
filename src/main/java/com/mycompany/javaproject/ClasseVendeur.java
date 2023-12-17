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


/**
 *
 * @author macbookair
 */
public class ClasseVendeur {
    private int idVente;
    private int idP;
    private int quantiteVendue;
    private LocalDateTime dateVente;
    
    public ClasseVendeur(int idP, int quantiteVendue){
         this.idP= idP;
        this.quantiteVendue= quantiteVendue;
    }
    
    public ClasseVendeur(int idVente, int idP, int quantiteVendue, LocalDateTime dateVente) {
        this.idVente= idVente;
        this.idP= idP;
        this.quantiteVendue= quantiteVendue;
        // Enregistre automatiquement la date actuelle lors de la création de l'objet
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
        int idP = -1; // Default value if the product is not found or an error occurs

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
}

