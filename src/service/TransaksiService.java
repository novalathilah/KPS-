package service;

import config.Database;
import model.TransaksiModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TransaksiService {

    private Connection connection;

    public TransaksiService() {
        try {
            this.connection = Database.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ========== TAMBAH TRANSAKSI ==========
    public boolean tambahTransaksi(TransaksiModel transaksi) {
        String sql = "INSERT INTO transaksi (id_user, id_category, jumlah, deskripsi, tanggal) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, transaksi.getIdUser());
            pstmt.setInt(2, transaksi.getIdCategory());
            pstmt.setDouble(3, transaksi.getJumlah());
            pstmt.setString(4, transaksi.getDeskripsi());
            pstmt.setDate(5, transaksi.getTanggal());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ========== TAMPIL DATA KE JTABEL (SEMUA USER) ==========
    public void tampilData(JTable table) {
        try {
            String sql = "SELECT t.id_transaksi, t.tanggal, c.nama_category, t.jumlah, t.deskripsi "
                    + "FROM transaksi t "
                    + "LEFT JOIN category c ON t.id_category = c.id_category "
                    + "ORDER BY t.tanggal DESC";

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Tanggal");
            model.addColumn("Kategori");
            model.addColumn("Jumlah");
            model.addColumn("Deskripsi");

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_transaksi"),
                    rs.getDate("tanggal"),
                    rs.getString("nama_category"),
                    rs.getDouble("jumlah"),
                    rs.getString("deskripsi")
                });
            }
            table.setModel(model);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // ========== AMBIL TRANSAKSI BERDASARKAN ID USER ==========
    public List<TransaksiModel> getTransaksiByUser(int idUser) {
        List<TransaksiModel> list = new ArrayList<>();
        String sql = "SELECT t.*, c.nama_category FROM transaksi t "
                + "LEFT JOIN category c ON t.id_category = c.id_category "
                + "WHERE t.id_user = ? "
                + "ORDER BY t.tanggal DESC";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idUser);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                TransaksiModel transaksi = new TransaksiModel();
                transaksi.setIdTransaksi(rs.getInt("id_transaksi"));
                transaksi.setIdUser(rs.getInt("id_user"));
                transaksi.setIdCategory(rs.getInt("id_category"));
                transaksi.setNamaCategory(rs.getString("nama_category"));
                transaksi.setJumlah(rs.getDouble("jumlah"));
                transaksi.setDeskripsi(rs.getString("deskripsi"));
                transaksi.setTanggal(rs.getDate("tanggal"));
                list.add(transaksi);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ========== UPDATE TRANSAKSI (EDIT) ==========
    public boolean update(TransaksiModel transaksi) {
        String sql = "UPDATE transaksi SET id_category = ?, jumlah = ?, deskripsi = ?, tanggal = ? WHERE id_transaksi = ? AND id_user = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, transaksi.getIdCategory());
            pstmt.setDouble(2, transaksi.getJumlah());
            pstmt.setString(3, transaksi.getDeskripsi());
            pstmt.setDate(4, transaksi.getTanggal());
            pstmt.setInt(5, transaksi.getIdTransaksi());
            pstmt.setInt(6, transaksi.getIdUser());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ========== HAPUS TRANSAKSI ==========
    public boolean hapusTransaksi(int idTransaksi) {
        String sql = "DELETE FROM transaksi WHERE id_transaksi = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idTransaksi);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
