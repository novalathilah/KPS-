package controllers;

import model.CategoryModel;
import service.CategoryService;

public class CategoryController {

    private final CategoryService service =
            new CategoryService();

    public boolean simpan(CategoryModel category) {

        return service.tambahCategory(category);

    }

    public boolean hapus(int idCategory) {

        return service.hapusCategory(idCategory);

    }

}