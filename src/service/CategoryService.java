package service;

import config.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.CategoryModel;

public class CategoryService {

    Connection conn = Database.getConnection();

    public boolean tambahCategory(CategoryModel category) {

        try {

            String sql =
                    "INSERT INTO category "
                    + "(nama_category, tipe) "
                    + "VALUES (?,?)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, category.getNamaCategory());
            ps.setString(2, category.getTipe());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return false;

        }

    }

    public void tampilData(JTable table) {

        try {

            String sql = "SELECT * FROM category";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            DefaultTableModel model =
                    new DefaultTableModel();

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

    // METHOD HAPUS
    public boolean hapusCategory(int idCategory) {

        try {

            String sql =
                    "DELETE FROM category WHERE id_category = ?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1, idCategory);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return false;

        }

    }

    public void cariCategory(JTable jTable1, String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}