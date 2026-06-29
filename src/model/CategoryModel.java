package model;

public class CategoryModel {

    private int idCategory;
    private String namaCategory;
    private String tipe;

    // Constructor kosong
    public CategoryModel() {
    }

    // Constructor lengkap
    public CategoryModel(int idCategory, String namaCategory, String tipe) {
        this.idCategory = idCategory;
        this.namaCategory = namaCategory;
        this.tipe = tipe;
    }

    // Getter & Setter
    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNamaCategory() {
        return namaCategory;
    }

    public void setNamaCategory(String namaCategory) {
        this.namaCategory = namaCategory;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "idCategory=" + idCategory +
                ", namaCategory='" + namaCategory + '\'' +
                ", tipe='" + tipe + '\'' +
                '}';
    }
}