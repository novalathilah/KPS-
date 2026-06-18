package model;

public class UserModel {

    private int idUser;
    private String username;
    private String email;
    private String password;

    // Constructor Kosong
    public UserModel() {

    }

    // Constructor Berisi
    public UserModel(int idUser, String username, String email, String password) {
        this.idUser = idUser;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getter dan Setter (Wajib ada agar data bisa diakses oleh Service/Controller)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
