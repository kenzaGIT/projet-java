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
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;




public class Produit {
    private int idP;
    String nomP;
    float prixU;
    int quantite;
    String nomC;
    int idC;

     public Produit(int idP,String nomP, float prixU, int quantite, int idC) {
         this.idP = idP;
         this.nomP = nomP;
         this.prixU = prixU;
         this.quantite = quantite;
         this.idC = idC;
    }
      public Produit(String nomP, float prixU, int quantite, int idC) {
         this.nomP = nomP;
         this.prixU= prixU;
         this.quantite = quantite;
         this.idC = idC;
    }
    public Produit(String nomP, float prixU, int quantite, String nomC,int idC) {
         this.nomP = nomP;
         this.prixU= prixU;
         this.quantite = quantite;
         this.nomC = nomC;
         this.idC = idC;
    }
    
    public Produit(){
    
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
    
     public String getnomCategorie() {
        return nomC;
    }


    public void idP(int idP) {
        this.idP= idP;
    }
    
    
    
      public void nomP(String nomP) {
        this.nomP= nomP;
    }
     
      public void prixU(float prixU) {
        this.prixU= prixU;
    }


    public void  quantite(int quantite) {
        this.quantite= quantite;
    }
    
     public void nomC(String nomC) {
        this.nomC = nomC;
    }


    public void idC( int idC) {
        this.idC = idC;
    }
    
    
    
        
    public static boolean enregistrerProduit(Produit p){
        boolean res = false;
        int r =0;
        try{
            Connection conn = mySQL.getConnection();
            String SQL = "INSERT INTO produit(nomP, prixU, quantite, idC)" + "VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(SQL);
            
            pstmt.setString(1, p.nomP);
            pstmt.setFloat(2, p.prixU);
            pstmt.setInt(3, p.quantite);
            pstmt.setInt(4, p.idC);
            
            System.out.println(pstmt.toString());
            
            
            r = pstmt.executeUpdate();
            
            if (r == 1)
                res = true;
                
            
        }catch(SQLException ex){
                System.out.print("Problème d'ajout du nouveau produit ");
                System.out.println(ex.getMessage());
        }
        
        return res;
    }
    
    public static boolean modifierProduit(Produit p) {
        boolean res = false;
        try {
            Connection conn = mySQL.getConnection();
            String sql = "UPDATE produit SET nomP = ?, prixU = ?, quantite = ?, idC = ? WHERE idP = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, p.nomP);
            pstmt.setFloat(2, p.prixU);
            pstmt.setInt(3, p.quantite);
            pstmt.setInt(4, p.idC);
            pstmt.setInt(5, p.idP);
            
          

            int affectedRows = pstmt.executeUpdate();
           if (affectedRows == 1)
                res = true;
                
        } catch (SQLException ex) {
            System.out.print("Problème de modification du produit ");
            System.out.println(ex.getMessage());
        }
        
        return res;
    }
    
    public static boolean supprimerProduit(int idP){
        boolean res = false;
        try {
            Connection conn = mySQL.getConnection();
            
            String sql = "DELETE FROM produit WHERE idP = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, idP);
            
            System.out.println("HEEELLOOOOOO  " + pstmt);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 1)
                res = true;
                
        } catch (SQLException ex) {
            System.out.print("Problème de suppression du produit ");
            System.out.println(ex.getMessage());
        }
        
        return res;
    }
        
    }
  
    
   

            
        
   






  
    

    

