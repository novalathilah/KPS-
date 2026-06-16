package controllers;

import model.CategoryModel;
import service.CategoryService;
import java.util.List;

public class CategoryController {

    private final CategoryService service = new CategoryService();

    // ========== SIMPAN / TAMBAH DENGAN VALIDASI ==========
    public boolean simpan(CategoryModel category) {
        // VALIDASI 1: Cek null
        if (category == null) {
            System.out.println("Error: Category tidak boleh null");
            return false;
        }

        // VALIDASI 2: Cek nama category kosong
        String nama = category.getNamaCategory();
        if (nama == null || nama.trim().isEmpty()) {
            System.out.println("Error: Nama Category tidak boleh kosong");
            return false;
        }

        // VALIDASI 3: Cek tipe kosong
        String tipe = category.getTipe();
        if (tipe == null || tipe.trim().isEmpty()) {
            System.out.println("Error: Tipe Category tidak boleh kosong");
            return false;
        }

        // VALIDASI 4: Cek tipe harus "Pemasukan" atau "Pengeluaran"
        if (!tipe.equalsIgnoreCase("Pemasukan") && !tipe.equalsIgnoreCase("Pengeluaran")) {
            System.out.println("Error: Tipe harus 'Pemasukan' atau 'Pengeluaran'");
            return false;
        }

        // VALIDASI 5: Cek panjang nama minimal 3 karakter
        if (nama.trim().length() < 3) {
            System.out.println("Error: Nama Category minimal 3 karakter");
            return false;
        }

        // Bersihkan data sebelum disimpan
        category.setNamaCategory(nama.trim());
        category.setTipe(tipe.trim());

        return service.tambahCategory(category);
    }

    // ========== HAPUS DENGAN VALIDASI ==========
    public boolean hapus(int idCategory) {
        // VALIDASI: ID harus lebih dari 0
        if (idCategory <= 0) {
            System.out.println("Error: ID Category tidak valid");
            return false;
        }

        return service.hapusCategory(idCategory);
    }

    // ========== AMBIL SEMUA CATEGORY ==========
    public List<Object[]> getAllCategories() {
        return service.getAllCategories();
    }

    // ========== EDIT CATEGORY DENGAN VALIDASI ==========
    public boolean editCategory(int idCategory, String namaCategory, String tipe) {
        // VALIDASI 1: Cek ID valid
        if (idCategory <= 0) {
            System.out.println("Error: ID Category tidak valid");
            return false;
        }

        // VALIDASI 2: Cek nama category kosong
        if (namaCategory == null || namaCategory.trim().isEmpty()) {
            System.out.println("Error: Nama Category tidak boleh kosong");
            return false;
        }

        // VALIDASI 3: Cek tipe kosong
        if (tipe == null || tipe.trim().isEmpty()) {
            System.out.println("Error: Tipe Category tidak boleh kosong");
            return false;
        }

        // VALIDASI 4: Cek tipe harus "Pemasukan" atau "Pengeluaran"
        if (!tipe.equalsIgnoreCase("Pemasukan") && !tipe.equalsIgnoreCase("Pengeluaran")) {
            System.out.println("Error: Tipe harus 'Pemasukan' atau 'Pengeluaran'");
            return false;
        }

        // VALIDASI 5: Cek panjang nama minimal 3 karakter
        if (namaCategory.trim().length() < 3) {
            System.out.println("Error: Nama Category minimal 3 karakter");
            return false;
        }

        CategoryModel category = new CategoryModel();
        category.setIdCategory(idCategory);
        category.setNamaCategory(namaCategory.trim());
        category.setTipe(tipe.trim());

        return service.updateCategory(category);
    }
}