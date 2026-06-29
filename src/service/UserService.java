package service;

import koneksi.Database;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    // ==========================
    // REGISTER
    // ==========================
    public boolean register(UserModel user) {

        if (isUsernameExists(user.getUsername())) {
            System.out.println("Username sudah digunakan.");
            return false;
        }

        if (isEmailExists(user.getEmail())) {
            System.out.println("Email sudah digunakan.");
            return false;
        }

        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

        try (
                Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUsername().trim());
            ps.setString(2, user.getEmail().trim());
            ps.setString(3, user.getPassword());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error Register : " + e.getMessage());
            return false;
        }
    }

    // ==========================
    // LOGIN
    // ==========================
    public UserModel login(String usernameOrEmail, String password) {

        String sql = """
                SELECT id_user, username, email, password
                FROM users
                WHERE (username = ? OR email = ?)
                AND password = ?
                """;

        try (
                Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usernameOrEmail);
            ps.setString(2, usernameOrEmail);
            ps.setString(3, password);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new UserModel(
                            rs.getInt("id_user"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("password")
                    );

                }

            }

        } catch (SQLException e) {
            System.out.println("Error Login : " + e.getMessage());
        }

        return null;
    }

    // ==========================
    // GET ALL USERS
    // ==========================
    public List<UserModel> getAllUsers() {

        List<UserModel> users = new ArrayList<>();

        String sql = """
                SELECT id_user,
                       username,
                       email,
                       password
                FROM users
                ORDER BY id_user DESC
                """;

        try (
                Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                users.add(new UserModel(
                        rs.getInt("id_user"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                ));

            }

        } catch (SQLException e) {
            System.out.println("Error Get All Users : " + e.getMessage());
        }

        return users;
    }

    // ==========================
    // DELETE USER
    // ==========================
    public boolean deleteUser(int idUser) {

        String sql = "DELETE FROM users WHERE id_user = ?";

        try (
                Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUser);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error Delete User : " + e.getMessage());
            return false;

        }

    }

    // ==========================
    // UPDATE USER
    // ==========================
    public boolean updateUser(UserModel user) {

        String sql = """
                UPDATE users
                SET username = ?,
                    email = ?,
                    password = ?
                WHERE id_user = ?
                """;

        try (
                Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUsername().trim());
            ps.setString(2, user.getEmail().trim());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getIdUser());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error Update User : " + e.getMessage());
            return false;

        }

    }

    // ==========================
    // CHECK USERNAME
    // ==========================
    public boolean isUsernameExists(String username) {

        String sql = "SELECT 1 FROM users WHERE username = ? LIMIT 1";

        try (
                Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {

                return rs.next();

            }

        } catch (SQLException e) {

            System.out.println("Error Check Username : " + e.getMessage());

        }

        return false;
    }

    // ==========================
    // CHECK EMAIL
    // ==========================
    public boolean isEmailExists(String email) {

        String sql = "SELECT 1 FROM users WHERE email = ? LIMIT 1";

        try (
                Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {

                return rs.next();

            }

        } catch (SQLException e) {

            System.out.println("Error Check Email : " + e.getMessage());

        }

        return false;
    }

}
