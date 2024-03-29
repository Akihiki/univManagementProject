package com.example.univmanag.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnect {

    public static Connection getConnection() {
        System.out.println("calling connection");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/univManag","root","");
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->"
                    + ex.getMessage());
            return null;
        }
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }
}