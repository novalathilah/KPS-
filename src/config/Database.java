package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static Connection koneksi;
    
    public static Connection getConnection() {
        if (koneksi == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/db_manage_keuangan";
                String user = "root";
                String pass = ""; 
                
                Class.forName("com.mysql.jdbc.Driver"); 
                
                koneksi = DriverManager.getConnection(url, user, pass);
                System.out.println("Koneksi Berhasil!");
            } catch (Exception e) {
                System.out.println("Koneksi Gagal: " + e.getMessage());
            }
        }
        return koneksi;
    }
}


