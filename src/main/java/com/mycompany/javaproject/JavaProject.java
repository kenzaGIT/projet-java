/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.javaproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author macbookair
 */
public class JavaProject {

    public static void main(String[] args) {

        Scanner scN = new Scanner(System.in);

        System.out.print("saisir le nom de la category");
        String nomC = scN.next();
        System.out.print("saisir un description");
        String description = scN.next();

        ClasseCatégorie newCat = new ClasseCatégorie(nomC, description);
        ClasseCatégorie.enregisterCategorie(newCat);

    }
}
