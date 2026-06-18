package controllers;

import model.UserModel;
import service.UserService;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    // memanggil Service
    private UserService userService = new UserService(); // FIX: huruf kecil biar sesuai Java convention

    // Controller untuk Login
    public UserModel loginUser(String username, String password) {
        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
            return null;
        }
        return userService.login(username, password);
    }

    // Controller untuk Register
    public String registerUser(String username, String email, String password) {

        if (username == null || username.trim().isEmpty()) {
            return "Username tidak boleh kosong";
        }

        if (email == null || email.trim().isEmpty()) {
            return "Email tidak boleh kosong";
        }

        if (password == null || password.trim().isEmpty()) {
            return "Password tidak boleh kosong";
        }

        try {
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
            boolean emailValid = email.matches(emailRegex);
            boolean passwordValid = password.length() >= 8;

            // FIX: Validasi terpisah biar jelas
            if (!emailValid) {
                return "Format email tidak valid";
            }

            if (!passwordValid) {
                return "Password minimal 8 karakter";
            }

        } catch (Exception e) {
            return e.getMessage();
        }

        UserModel newUser = new UserModel();
        newUser.setUsername(username.trim());
        newUser.setEmail(email.trim());
        newUser.setPassword(password); // FIX: Sebaiknya hash password di service

        boolean sukses = userService.register(newUser);

        if (sukses) {
            return "SUCCESS";
        }

        return "Registrasi gagal";
    }

    public List<Object[]> getAllUsers() {
        List<Object[]> dataTabel = new ArrayList<>();

        List<UserModel> userList = userService.getAllUsers();

        if (userList != null && !userList.isEmpty()) { // FIX: Cek juga apakah kosong
            for (UserModel user : userList) {
                Object[] row = new Object[]{
                    user.getIdUser(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword()
                };
                dataTabel.add(row);
            }
        }

        return dataTabel;
    }
}