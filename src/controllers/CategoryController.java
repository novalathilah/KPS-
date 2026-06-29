package controllers;

import model.CategoryModel;
import service.CategoryService;

import java.util.List;

public class CategoryController {

    private final CategoryService service = new CategoryService();

    // ==========================
    // CREATE
    // ==========================
    public boolean simpan(CategoryModel category) {

        if (!isValidCategory(category)) {
            return false;
        }

        category.setNamaCategory(category.getNamaCategory().trim());
        category.setTipe(category.getTipe().trim());

        return service.tambahCategory(category);

    }

    // ==========================
    // UPDATE
    // ==========================
    public boolean editCategory(int idCategory,
                                String namaCategory,
                                String tipe) {

        if (idCategory <= 0) {
            return false;
        }

        CategoryModel category = new CategoryModel(
                idCategory,
                namaCategory,
                tipe
        );

        if (!isValidCategory(category)) {
            return false;
        }

        category.setNamaCategory(category.getNamaCategory().trim());
        category.setTipe(category.getTipe().trim());

        return service.updateCategory(category);

    }

    // ==========================
    // DELETE
    // ==========================
    public boolean hapus(int idCategory) {

        if (idCategory <= 0) {
            return false;
        }

        return service.hapusCategory(idCategory);

    }

    // ==========================
    // READ
    // ==========================
    public List<Object[]> getAllCategories() {

        return service.getAllCategories();

    }

    // ==========================
    // GET BY ID
    // ==========================
    public CategoryModel getCategoryById(int idCategory) {

        if (idCategory <= 0) {
            return null;
        }

        return service.getCategoryById(idCategory);

    }

    // ==========================
    // VALIDATION
    // ==========================
    private boolean isValidCategory(CategoryModel category) {

        if (category == null) {
            return false;
        }

        String nama = category.getNamaCategory();
        String tipe = category.getTipe();

        if (nama == null || nama.trim().isEmpty()) {
            return false;
        }

        if (nama.trim().length() < 3) {
            return false;
        }

        if (tipe == null || tipe.trim().isEmpty()) {
            return false;
        }

        return tipe.equalsIgnoreCase("Pemasukan")
                || tipe.equalsIgnoreCase("Pengeluaran");

    }

}