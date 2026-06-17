package service;

import koneksi.Database;
import model.UserModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    // 1. Logika untuk Registrasi User Baru
    public boolean register(UserModel user) {

        // Validasi field kosong
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()
                || user.getEmail() == null || user.getEmail().trim().isEmpty()
                || user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return false;
        }

        // Validasi panjang password
        if (user.getPassword().length() < 8) {
            return false;
        }

        // Validasi format email
        String emailRegex
                = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (!user.getEmail().matches(emailRegex)) {
            return false;
        }

        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Database.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Eror Registrasi: " + e.getMessage());
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    // 2. Logika untuk Login User
    public UserModel login(String username, String password) {

        String sql = "SELECT * FROM users WHERE (username = ? OR email = ?) AND password = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, username);
            ps.setString(3, password);

            rs = ps.executeQuery();

            if (rs.next()) {
                UserModel user = new UserModel();
                user.setIdUser(rs.getInt("id_user"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                return user;
            }

        } catch (SQLException e) {
            System.out.println("Eror Login: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }

        return null;
    }

    // 3. Logika Mengambil Semua Data User untuk jTable1 (Hanya 4 Kolom Utama)
    public List<UserModel> getAllUsers() {
        List<UserModel> list = new ArrayList<>();
        String sql = "SELECT id_user, username, email, password FROM users";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                UserModel user = new UserModel();
                // Mengambil data dan memasukkannya ke UserModel kamu yang baru
                user.setIdUser(rs.getInt("id_user"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));

                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Eror Ambil Semua User: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
        return list;
    }
}
