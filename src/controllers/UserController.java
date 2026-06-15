package controllers;

import model.UserModel;
import service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class UserController {
    // memanggil Service
    private UserService UserService = new UserService();

    // Controller untuk Login
    public UserModel loginUser(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return null;
        }
        return UserService.login(username, password);
    }

    // Controller untuk Register
    public String registerUser(String username, String email, String password) {
    

    if (username.trim().isEmpty()) {
        return "Username tidak boleh kosong";
    }

    if (email.trim().isEmpty()) {
        return "Email tidak boleh kosong";
    }

    if (password.trim().isEmpty()) {
        return "Password tidak boleh kosong";
    }

   try {
    String emailRegex =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    boolean emailValid = email.matches(emailRegex);
    boolean passwordValid = password.length() >= 8;

    if (!emailValid && !passwordValid) {
        throw new Exception("Input tidak valid");
    }

    if (!emailValid) {
        throw new Exception("Format email tidak valid");
    }

    if (!passwordValid) {
        throw new Exception("Password tidak sesuai kriteria");
    }

} catch (Exception e) {
    return e.getMessage();
}

    UserModel newUser = new UserModel();
    newUser.setUsername(username);
    newUser.setEmail(email);
    newUser.setPassword(password);

    boolean sukses = UserService.register(newUser);

    if (sukses) {
        return "SUCCESS";
    }

    return "Registrasi gagal";
}
    public List<Object[]> getAllUsers() {
        List<Object[]> dataTabel = new ArrayList<>();
        
        // Mengambil data user 
        List<UserModel> userList = UserService.getAllUsers();
        
        // Mengubah List<UserModel> menjadi List<Object[]> 
        if (userList != null) {
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