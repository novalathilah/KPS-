package controllers;

import model.UserModel;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    private final UserService userService = new UserService();

    // ==========================
    // LOGIN
    // ==========================
    public UserModel loginUser(String usernameOrEmail, String password) {

        if (usernameOrEmail == null || usernameOrEmail.trim().isEmpty()) {
            return null;
        }

        if (password == null || password.trim().isEmpty()) {
            return null;
        }

        return userService.login(
                usernameOrEmail.trim(),
                password
        );
    }

    // ==========================
    // REGISTER
    // ==========================
    public String registerUser(String username,
            String email,
            String password) {

        username = username == null ? "" : username.trim();
        email = email == null ? "" : email.trim();
        password = password == null ? "" : password;

        // Username
        if (username.isEmpty()) {
            return "Username tidak boleh kosong.";
        }

        if (username.length() < 3) {
            return "Username minimal 3 karakter.";
        }

        // Email
        if (email.isEmpty()) {
            return "Email tidak boleh kosong.";
        }

        String emailRegex
                = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (!email.matches(emailRegex)) {
            return "Format email tidak valid.";
        }

        // Password
        if (password.isEmpty()) {
            return "Password tidak boleh kosong.";
        }

        if (password.length() < 8) {
            return "Password minimal 8 karakter.";
        }

        // Cek Username
        if (userService.isUsernameExists(username)) {
            return "Username sudah digunakan.";
        }

        // Cek Email
        if (userService.isEmailExists(email)) {
            return "Email sudah digunakan.";
        }

        UserModel user = new UserModel(
                0,
                username,
                email,
                password
        );

        boolean success = userService.register(user);

        if (success) {
            return "SUCCESS";
        }

        return "Registrasi gagal.";
    }

    // ==========================
    // GET ALL USER
    // ==========================
    public List<Object[]> getAllUsers() {

        List<Object[]> tableData = new ArrayList<>();

        List<UserModel> users = userService.getAllUsers();

        for (UserModel user : users) {

            tableData.add(new Object[]{
                user.getIdUser(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
            });

        }

        return tableData;
    }

    // ==========================
    // UPDATE USER
    // ==========================
    public boolean updateUser(UserModel user) {

        if (user == null) {
            return false;
        }

        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            return false;
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return false;
        }

        if (user.getPassword() == null || user.getPassword().length() < 8) {
            return false;
        }

        return userService.updateUser(user);
    }

    // ==========================
    // DELETE USER
    // ==========================
    public boolean deleteUser(int idUser) {

        if (idUser <= 0) {
            return false;
        }

        return userService.deleteUser(idUser);
    }

}
