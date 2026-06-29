package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/db_manage_keuangan";
            String user = "root";
            String pass = "";

            Class.forName("com.mysql.jdbc.Driver");

            return DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            System.out.println("Koneksi Gagal: " + e.getMessage());
            return null;
        }
    }
}
