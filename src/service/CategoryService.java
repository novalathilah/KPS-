package service;

import koneksi.Database;
import model.CategoryModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CategoryService {

    // ==========================
    // CREATE
    // ==========================
    public boolean tambahCategory(CategoryModel category) {

        String sql = """
                INSERT INTO category
                (nama_category, tipe)
                VALUES (?, ?)
                """;

        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, category.getNamaCategory());
            ps.setString(2, category.getTipe());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error Tambah Category : " + e.getMessage());
            return false;

        }

    }

    // ==========================
    // READ JTable
    // ==========================
    public void tampilData(JTable table) {

        String sql = """
                SELECT
                    id_category,
                    nama_category,
                    tipe
                FROM category
                ORDER BY id_category DESC
                """;

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Nama Category");
        model.addColumn("Tipe");

        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                model.addRow(new Object[]{
                    rs.getInt("id_category"),
                    rs.getString("nama_category"),
                    rs.getString("tipe")
                });

            }

            table.setModel(model);

        } catch (SQLException e) {

            System.out.println("Error Tampil Data : " + e.getMessage());

        }

    }

    // ==========================
    // GET ALL CATEGORY
    // ==========================
    public List<Object[]> getAllCategories() {

        List<Object[]> list = new ArrayList<>();

        String sql = """
                SELECT
                    id_category,
                    nama_category,
                    tipe
                FROM category
                ORDER BY id_category DESC
                """;

        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                list.add(new Object[]{
                    rs.getInt("id_category"),
                    rs.getString("nama_category"),
                    rs.getString("tipe")
                });

            }

        } catch (SQLException e) {

            System.out.println("Error Get Categories : " + e.getMessage());

        }

        return list;

    }

    // ==========================
    // GET CATEGORY BY ID
    // ==========================
    public CategoryModel getCategoryById(int idCategory) {

        String sql = """
                SELECT
                    id_category,
                    nama_category,
                    tipe
                FROM category
                WHERE id_category = ?
                """;

        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, idCategory);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new CategoryModel(
                            rs.getInt("id_category"),
                            rs.getString("nama_category"),
                            rs.getString("tipe")
                    );

                }

            }

        } catch (SQLException e) {

            System.out.println("Error Get Category : " + e.getMessage());

        }

        return null;

    }

    // ==========================
    // UPDATE
    // ==========================
    public boolean updateCategory(CategoryModel category) {

        String sql = """
                UPDATE category
                SET nama_category = ?,
                    tipe = ?
                WHERE id_category = ?
                """;

        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, category.getNamaCategory());
            ps.setString(2, category.getTipe());
            ps.setInt(3, category.getIdCategory());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error Update Category : " + e.getMessage());
            return false;

        }

    }

    // ==========================
    // DELETE
    // ==========================
    public boolean hapusCategory(int idCategory) {

        String sql = """
                DELETE FROM category
                WHERE id_category = ?
                """;

        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, idCategory);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error Hapus Category : " + e.getMessage());
            return false;

        }

    }

    // ==========================
    // SEARCH
    // ==========================
    public void cariCategory(JTable table, String keyword) {

        String sql = """
                SELECT
                    id_category,
                    nama_category,
                    tipe
                FROM category
                WHERE nama_category LIKE ?
                   OR tipe LIKE ?
                ORDER BY id_category DESC
                """;

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Nama Category");
        model.addColumn("Tipe");

        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    model.addRow(new Object[]{
                        rs.getInt("id_category"),
                        rs.getString("nama_category"),
                        rs.getString("tipe")
                    });

                }

            }

            table.setModel(model);

        } catch (SQLException e) {

            System.out.println("Error Cari Category : " + e.getMessage());

        }

    }

}