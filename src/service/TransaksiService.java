package service;

import config.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.TransaksiModel;

/*
 * Class Service bertugas berkomunikasi langsung dengan database.
 * Semua proses CRUD transaksi dilakukan di sini.
 */
public class TransaksiService {

    Connection conn = Database.getConnection();

    /*
     * Method untuk menambahkan transaksi baru ke database
     */
    public boolean tambahTransaksi(TransaksiModel transaksi) {

        try {

            String sql =
                    "INSERT INTO transaksi "
                    + "(id_user,id_category,jumlah,deskripsi,tanggal)"
                    + " VALUES (?,?,?,?,?)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1, transaksi.getIdUser());
            ps.setInt(2, transaksi.getIdCategory());
            ps.setDouble(3, transaksi.getJumlah());
            ps.setString(4, transaksi.getDeskripsi());
            ps.setDate(5, transaksi.getTanggal());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return false;

        }
    }

    /*
     * Method untuk mengambil seluruh data transaksi
     * kemudian menampilkannya ke JTable
     */
    public void tampilData(JTable table) {

        try {

            String sql =
                    "SELECT t.id_transaksi, "
                    + "t.tanggal, "
                    + "c.nama_category, "
                    + "c.tipe, "
                    + "t.jumlah, "
                    + "t.deskripsi "
                    + "FROM transaksi t "
                    + "JOIN category c "
                    + "ON t.id_category = c.id_category";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            DefaultTableModel model =
                    new DefaultTableModel();

            model.addColumn("ID");
            model.addColumn("Tanggal");
            model.addColumn("Kategori");
            model.addColumn("Tipe");
            model.addColumn("Jumlah");
            model.addColumn("Deskripsi");

            while (rs.next()) {

                model.addRow(new Object[]{
                    rs.getInt("id_transaksi"),
                    rs.getDate("tanggal"),
                    rs.getString("nama_category"),
                    rs.getString("tipe"),
                    rs.getDouble("jumlah"),
                    rs.getString("deskripsi")
                });

            }

            table.setModel(model);

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
    }
}