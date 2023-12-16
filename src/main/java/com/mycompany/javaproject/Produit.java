/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Produit {
    private int idP;
    private String nomP;
    private float prixU;
    private int quantite;
    private String idC;

     public Produit(int idP,String nomP, float prixU, int quantite, String idC) {
         this.idP = idP;
         this.nomP = nomP;
         this.prixU = prixU;
         this.quantite = quantite;
         this.idC = idC;
    }
    public Produit(String nomP, float prixU, int quantite, String idC) {
         this.nomP = nomP;
         this.prixU= prixU;
         this.quantite = quantite;
         this.idC = idC;
    }
    
     public int getId() {
        return idP;
    }
    
     public String getNomP() {
        return nomP;
    }
     
     public  float getPrixU() {
        return prixU;
    }


    public int getQuantite() {
        return quantite;
    }

    public String getIdC() {
        return idC;
    }
    
    
    public static boolean enregistrerProduit(Produit p) {
        
        
        try {
            Connection conn = mySQL.getConnection();
     

            String SQL = "INSERT INTO categorie (idP, nomP, prixU, quantite, idC) VALUES (null,?, ?, ?, ?)";
            System.out.println("SQL Statement: " + SQL); // Print the SQL statement

            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(SQL);
            pstmt.setString(1, p.nomP);
            pstmt.setFloat(2, p.prixU);
            pstmt.setInt(3, p.quantite);
            pstmt.setString(4, p.idC);
            
            

            int r = pstmt.executeUpdate();

            System.out.println("pstmt.toString() : " + pstmt.toString());

     
        } catch (SQLException ex) {
            System.out.println("Problème d'ajout du nouveau produit");
            System.out.println(ex.getMessage());
        }
        
    }
    
            
        
   





}
   // public static boolean addProduct(Produit P){
    //    boolean res = false;
     //   int r = 0;
        
    //    try {
    //        Connection conn = mySQL.getConnection();
    //        String SQL = "INSERT INTO produit(idP, prixU, quantite, idC" + "VALUES(null, ?, ?,,?";
            
    //        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(SQL);
            
    //        pstmt.setString(1)
    //    }
          
    //}
    

    

