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
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author macbookair
 */

    public class Vente {
    private int idVente;
    private int idP;
    private Date dateVente;
    private int quantiteVendue;
    

     public Vente(int idVente, int idP, String dateVente, int quantiteVendue) {
         this.idVente= idVente;
         this.idP = idP;
         this.quantiteVendue = quantiteVendue;
    }
    
    public Vente(){
    
}
    
     public int getIdVente() {
        return idVente;
    }
    
     public int getIdP() {
        return idP;
    }
     
     public Date getDateVente() {
        return dateVente;
    }
 

    public int getQuantiteVendue() {
        return quantiteVendue;
    }
    
   

    
    public void idVente(int idVente) {
        this.idVente= idVente;
    }
    
    public void idP(int idP) {
        this.idP= idP;
    }
    
    
    
      public void dateVente(Date dateVente) {
        this.dateVente= dateVente;
    }
     
     

    public void  quantiteVendue(int quantiteVendue) {
        this.quantiteVendue= quantiteVendue;
    }
    
    
    public static double generateRevenue(Date dateDebut, Date dateFin) {
    double revenu = 0;
    try {
        Connection conn = mySQL.getConnection();
        String sql = "SELECT SUM(prixU * quantiteVendue) AS revenuTotal FROM vente NATURAL JOIN produit WHERE dateVente BETWEEN ? AND ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);


        pstmt.setDate(1, new java.sql.Date(dateDebut.getTime()));
        pstmt.setDate(2, new java.sql.Date(dateFin.getTime()));

        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            revenu = rs.getDouble("revenuTotal");
             if (rs.wasNull()) {
        revenu = 0; 
    }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return revenu;
}

    
   
    
}
