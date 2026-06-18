package service;

import koneksi.Database;
import model.LaporanModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LaporanService {

    private Connection connection;

    public LaporanService() {
        try {
            this.connection = Database.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ========== HARIAN ==========
    public LaporanModel getLaporanHarian(int idUser, String tanggal) {
        LaporanModel laporan = new LaporanModel();
        laporan.setPeriode("harian");
        laporan.setTanggalAwal(tanggal);
        laporan.setTanggalAkhir(tanggal);

        String sql = "SELECT "
                + "COALESCE(SUM(CASE WHEN c.tipe = 'Pemasukan' THEN t.jumlah ELSE 0 END), 0) AS pemasukan, "
                + "COALESCE(SUM(CASE WHEN c.tipe = 'Pengeluaran' THEN t.jumlah ELSE 0 END), 0) AS pengeluaran, "
                + "COUNT(*) AS jumlah_transaksi "
                + "FROM transaksi t "
                + "JOIN category c ON t.id_category = c.id_category "
                + "WHERE t.id_user = ? AND t.tanggal = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idUser);
            pstmt.setString(2, tanggal);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                double pemasukan = rs.getDouble("pemasukan");
                double pengeluaran = rs.getDouble("pengeluaran");
                laporan.setTotalPemasukan(pemasukan);
                laporan.setTotalPengeluaran(pengeluaran);
                laporan.setSaldo(pemasukan - pengeluaran);
                laporan.setJumlahTransaksi(rs.getInt("jumlah_transaksi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return laporan;
    }

    // ========== MINGGUAN ==========
    public LaporanModel getLaporanMingguan(int idUser, String tglMulai, String tglSelesai) {
        LaporanModel laporan = new LaporanModel();
        laporan.setPeriode("mingguan");
        laporan.setTanggalAwal(tglMulai);
        laporan.setTanggalAkhir(tglSelesai);

        String sql = "SELECT "
                + "COALESCE(SUM(CASE WHEN c.tipe = 'Pemasukan' THEN t.jumlah ELSE 0 END), 0) AS pemasukan, "
                + "COALESCE(SUM(CASE WHEN c.tipe = 'Pengeluaran' THEN t.jumlah ELSE 0 END), 0) AS pengeluaran, "
                + "COUNT(*) AS jumlah_transaksi "
                + "FROM transaksi t "
                + "JOIN category c ON t.id_category = c.id_category "
                + "WHERE t.id_user = ? AND t.tanggal BETWEEN ? AND ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idUser);
            pstmt.setString(2, tglMulai);
            pstmt.setString(3, tglSelesai);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                double pemasukan = rs.getDouble("pemasukan");
                double pengeluaran = rs.getDouble("pengeluaran");
                laporan.setTotalPemasukan(pemasukan);
                laporan.setTotalPengeluaran(pengeluaran);
                laporan.setSaldo(pemasukan - pengeluaran);
                laporan.setJumlahTransaksi(rs.getInt("jumlah_transaksi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return laporan;
    }

    // ========== BULANAN ==========
    public LaporanModel getLaporanBulanan(int idUser, String bulan) {
        LaporanModel laporan = new LaporanModel();
        laporan.setPeriode("bulanan");
        laporan.setTanggalAwal(bulan + "-01");
        laporan.setTanggalAkhir(bulan + "-01");

        String sql = "SELECT "
                + "COALESCE(SUM(CASE WHEN c.tipe = 'Pemasukan' THEN t.jumlah ELSE 0 END), 0) AS pemasukan, "
                + "COALESCE(SUM(CASE WHEN c.tipe = 'Pengeluaran' THEN t.jumlah ELSE 0 END), 0) AS pengeluaran, "
                + "COUNT(*) AS jumlah_transaksi "
                + "FROM transaksi t "
                + "JOIN category c ON t.id_category = c.id_category "
                + "WHERE t.id_user = ? AND DATE_FORMAT(t.tanggal, '%Y-%m') = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idUser);
            pstmt.setString(2, bulan);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                double pemasukan = rs.getDouble("pemasukan");
                double pengeluaran = rs.getDouble("pengeluaran");
                laporan.setTotalPemasukan(pemasukan);
                laporan.setTotalPengeluaran(pengeluaran);
                laporan.setSaldo(pemasukan - pengeluaran);
                laporan.setJumlahTransaksi(rs.getInt("jumlah_transaksi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return laporan;
    }

    // ========== TAHUNAN ==========
    public LaporanModel getLaporanTahunan(int idUser, int tahun) {
        LaporanModel laporan = new LaporanModel();
        laporan.setPeriode("tahunan");
        laporan.setTanggalAwal(tahun + "-01-01");
        laporan.setTanggalAkhir(tahun + "-12-31");

        String sql = "SELECT "
                + "COALESCE(SUM(CASE WHEN c.tipe = 'Pemasukan' THEN t.jumlah ELSE 0 END), 0) AS pemasukan, "
                + "COALESCE(SUM(CASE WHEN c.tipe = 'Pengeluaran' THEN t.jumlah ELSE 0 END), 0) AS pengeluaran, "
                + "COUNT(*) AS jumlah_transaksi "
                + "FROM transaksi t "
                + "JOIN category c ON t.id_category = c.id_category "
                + "WHERE t.id_user = ? AND YEAR(t.tanggal) = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idUser);
            pstmt.setInt(2, tahun);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                double pemasukan = rs.getDouble("pemasukan");
                double pengeluaran = rs.getDouble("pengeluaran");
                laporan.setTotalPemasukan(pemasukan);
                laporan.setTotalPengeluaran(pengeluaran);
                laporan.setSaldo(pemasukan - pengeluaran);
                laporan.setJumlahTransaksi(rs.getInt("jumlah_transaksi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return laporan;
    }

    // ========== SIMPAN ==========
    public boolean simpanLaporan(LaporanModel laporan) {
        String sql = "INSERT INTO laporan (id_user, periode, tanggal_awal, tanggal_akhir, total_pemasukan, total_pengeluaran, saldo) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, laporan.getIdUser());
            pstmt.setString(2, laporan.getPeriode());
            pstmt.setString(3, laporan.getTanggalAwal());
            pstmt.setString(4, laporan.getTanggalAkhir());
            pstmt.setDouble(5, laporan.getTotalPemasukan());
            pstmt.setDouble(6, laporan.getTotalPengeluaran());
            pstmt.setDouble(7, laporan.getSaldo());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ========== AMBIL SEMUA ==========
    public List<LaporanModel> getLaporanByUser(int idUser) {
        List<LaporanModel> list = new ArrayList<>();
        String sql = "SELECT * FROM laporan WHERE id_user = ? ORDER BY id_laporan DESC";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idUser);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                LaporanModel laporan = new LaporanModel();
                laporan.setIdLaporan(rs.getInt("id_laporan"));
                laporan.setIdUser(rs.getInt("id_user"));
                laporan.setPeriode(rs.getString("periode"));
                laporan.setTanggalAwal(rs.getString("tanggal_awal"));
                laporan.setTanggalAkhir(rs.getString("tanggal_akhir"));
                laporan.setTotalPemasukan(rs.getDouble("total_pemasukan"));
                laporan.setTotalPengeluaran(rs.getDouble("total_pengeluaran"));
                laporan.setSaldo(rs.getDouble("saldo"));
                list.add(laporan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ========== HAPUS ==========
    public boolean hapusLaporan(int idLaporan, int idUser) {
        String sql = "DELETE FROM laporan WHERE id_laporan = ? AND id_user = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idLaporan);
            pstmt.setInt(2, idUser);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ========== TOTAL PEMASUKAN ==========
    public double getTotalPemasukan(int idUser) {
        String sql = "SELECT COALESCE(SUM(t.jumlah), 0) as total FROM transaksi t "
                + "JOIN category c ON t.id_category = c.id_category "
                + "WHERE t.id_user = ? AND c.tipe = 'Pemasukan'";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idUser);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // ========== TOTAL PENGELUARAN ==========
    public double getTotalPengeluaran(int idUser) {
        String sql = "SELECT COALESCE(SUM(t.jumlah), 0) as total FROM transaksi t "
                + "JOIN category c ON t.id_category = c.id_category "
                + "WHERE t.id_user = ? AND c.tipe = 'Pengeluaran'";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idUser);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // ========== SALDO ==========
    public double getSaldo(int idUser) {
        return getTotalPemasukan(idUser) - getTotalPengeluaran(idUser);
    }
}
