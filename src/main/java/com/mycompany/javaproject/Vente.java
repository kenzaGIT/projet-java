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
/**
 *
 * @author macbookair
 */

    public class Vente {
    private int idVente;
    private int idP;
    private String  dateVente;
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
     
     public String getDateVente() {
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
    
    
    
      public void dateVente(String dateVente) {
        this.dateVente= dateVente;
    }
     
     

    public void  quantiteVendue(int quantiteVendue) {
        this.quantiteVendue= quantiteVendue;
    }
    
   
    
}
