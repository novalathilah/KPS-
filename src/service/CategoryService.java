package service;

import config.Database;
import model.CategoryModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CategoryService {

    private Connection connection;
    
    public CategoryService() {
        try {
            this.connection = Database.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ========== CREATE ==========
    public boolean tambahCategory(CategoryModel category) {
        String sql = "INSERT INTO category (nama_category, tipe) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, category.getNamaCategory());
            ps.setString(2, category.getTipe());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // ========== READ ==========
    public void tampilData(JTable table) {
        try {
            String sql = "SELECT * FROM category ORDER BY id_category DESC";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Nama Category");
            model.addColumn("Tipe");

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_category"),
                    rs.getString("nama_category"),
                    rs.getString("tipe")
                });
            }
            table.setModel(model);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Object[]> getAllCategories() {
        List<Object[]> list = new ArrayList<>();
        String sql = "SELECT * FROM category ORDER BY id_category DESC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getInt("id_category"),
                    rs.getString("nama_category"),
                    rs.getString("tipe")
                };
                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public CategoryModel getCategoryById(int idCategory) {
        String sql = "SELECT * FROM category WHERE id_category = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCategory);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                CategoryModel category = new CategoryModel();
                category.setIdCategory(rs.getInt("id_category"));
                category.setNamaCategory(rs.getString("nama_category"));
                category.setTipe(rs.getString("tipe"));
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ========== UPDATE ==========
    public boolean updateCategory(CategoryModel category) {
        String sql = "UPDATE category SET nama_category = ?, tipe = ? WHERE id_category = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, category.getNamaCategory());
            ps.setString(2, category.getTipe());
            ps.setInt(3, category.getIdCategory());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // ========== DELETE ==========
    public boolean hapusCategory(int idCategory) {
        String sql = "DELETE FROM category WHERE id_category = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCategory);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // ========== SEARCH ==========
    public void cariCategory(JTable table, String keyword) {
        try {
            String sql = "SELECT * FROM category WHERE nama_category LIKE ? OR tipe LIKE ? ORDER BY id_category DESC";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Nama Category");
            model.addColumn("Tipe");

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_category"),
                    rs.getString("nama_category"),
                    rs.getString("tipe")
                });
            }
            table.setModel(model);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}