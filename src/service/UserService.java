package service;

import koneksi.Database;
import model.UserModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    // 1. Logika untuk Registrasi User Baru
    public boolean register(UserModel user) {
        // FIX: Cek duplikat username/email dulu
        if (isUserExists(user.getUsername(), user.getEmail())) {
            System.out.println("Username atau email sudah terdaftar!");
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
            ps.setString(3, user.getPassword()); // FIX: Sebaiknya hash password dulu

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error Registrasi: " + e.getMessage());
            return false;
        } finally {
            closeResources(ps, null);
        }
    }

    // FIX: Cek user sudah ada atau belum
    public boolean isUserExists(String username, String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ? OR email = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error Cek User: " + e.getMessage());
        } finally {
            closeResources(ps, rs);
        }
        return false;
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
            ps.setString(3, password); // FIX: Sebaiknya hash password dulu

            rs = ps.executeQuery();

            if (rs.next()) {
                UserModel user = new UserModel();
                user.setIdUser(rs.getInt("id_user"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                return user;
            }

        } catch (SQLException e) {
            System.out.println("Error Login: " + e.getMessage());
        } finally {
            closeResources(ps, rs);
        }

        return null;
    }

    // 3. Logika Mengambil Semua Data User
    public List<UserModel> getAllUsers() {
        List<UserModel> list = new ArrayList<>();
        String sql = "SELECT id_user, username, email, password FROM users ORDER BY id_user DESC"; // FIX: Urutkan dari terbaru
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                UserModel user = new UserModel();
                user.setIdUser(rs.getInt("id_user"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));

                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error Ambil Semua User: " + e.getMessage());
        } finally {
            closeResources(ps, rs);
        }
        return list;
    }

    // 4. Hapus User
    public boolean deleteUser(int idUser) {
        String sql = "DELETE FROM users WHERE id_user = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Database.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idUser);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error Hapus User: " + e.getMessage());
            return false;
        } finally {
            closeResources(ps, null);
        }
    }

    // 5. Update User
    public boolean updateUser(UserModel user) {
        String sql = "UPDATE users SET username = ?, email = ?, password = ? WHERE id_user = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Database.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getIdUser());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error Update User: " + e.getMessage());
            return false;
        } finally {
            closeResources(ps, null);
        }
    }

    // 6. Helper untuk close resources (biar gak repetitif)
    private void closeResources(PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            // ignore
        }
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            // ignore
        }
    }
}