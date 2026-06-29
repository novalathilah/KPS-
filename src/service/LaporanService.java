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

        String sql =
                "SELECT " +
                "COALESCE(SUM(CASE WHEN c.tipe = 'Pemasukan' THEN t.jumlah ELSE 0 END), 0) AS pemasukan, " +
                "COALESCE(SUM(CASE WHEN c.tipe = 'Pengeluaran' THEN t.jumlah ELSE 0 END), 0) AS pengeluaran, " +
                "COUNT(t.id_transaksi) AS jumlah_transaksi " +
                "FROM transaksi t " +
                "JOIN category c ON t.id_category = c.id_category " +
                "WHERE t.id_user = ? AND DATE(t.tanggal) = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idUser);
            ps.setString(2, tanggal);

            ResultSet rs = ps.executeQuery();

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

        String sql =
                "SELECT " +
                "COALESCE(SUM(CASE WHEN c.tipe = 'Pemasukan' THEN t.jumlah ELSE 0 END), 0) AS pemasukan, " +
                "COALESCE(SUM(CASE WHEN c.tipe = 'Pengeluaran' THEN t.jumlah ELSE 0 END), 0) AS pengeluaran, " +
                "COUNT(t.id_transaksi) AS jumlah_transaksi " +
                "FROM transaksi t " +
                "JOIN category c ON t.id_category = c.id_category " +
                "WHERE t.id_user = ? AND DATE(t.tanggal) BETWEEN ? AND ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idUser);
            ps.setString(2, tglMulai);
            ps.setString(3, tglSelesai);

            ResultSet rs = ps.executeQuery();

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
        laporan.setTanggalAkhir(bulan + "-31");

        String sql =
                "SELECT " +
                "COALESCE(SUM(CASE WHEN c.tipe = 'Pemasukan' THEN t.jumlah ELSE 0 END), 0) AS pemasukan, " +
                "COALESCE(SUM(CASE WHEN c.tipe = 'Pengeluaran' THEN t.jumlah ELSE 0 END), 0) AS pengeluaran, " +
                "COUNT(t.id_transaksi) AS jumlah_transaksi " +
                "FROM transaksi t " +
                "JOIN category c ON t.id_category = c.id_category " +
                "WHERE t.id_user = ? AND DATE_FORMAT(t.tanggal, '%Y-%m') = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idUser);
            ps.setString(2, bulan);

            ResultSet rs = ps.executeQuery();

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

        String sql =
                "SELECT " +
                "COALESCE(SUM(CASE WHEN c.tipe = 'Pemasukan' THEN t.jumlah ELSE 0 END), 0) AS pemasukan, " +
                "COALESCE(SUM(CASE WHEN c.tipe = 'Pengeluaran' THEN t.jumlah ELSE 0 END), 0) AS pengeluaran, " +
                "COUNT(t.id_transaksi) AS jumlah_transaksi " +
                "FROM transaksi t " +
                "JOIN category c ON t.id_category = c.id_category " +
                "WHERE t.id_user = ? AND YEAR(t.tanggal) = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idUser);
            ps.setInt(2, tahun);

            ResultSet rs = ps.executeQuery();

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
        String sql =
                "INSERT INTO laporan " +
                "(id_user, periode, tanggal_awal, tanggal_akhir, total_pemasukan, total_pengeluaran, saldo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, laporan.getIdUser());
            ps.setString(2, laporan.getPeriode());
            ps.setString(3, laporan.getTanggalAwal());
            ps.setString(4, laporan.getTanggalAkhir());
            ps.setDouble(5, laporan.getTotalPemasukan());
            ps.setDouble(6, laporan.getTotalPengeluaran());
            ps.setDouble(7, laporan.getSaldo());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ========== RIWAYAT ==========
    public List<LaporanModel> getLaporanByUser(int idUser) {
        List<LaporanModel> list = new ArrayList<>();

        String sql = "SELECT * FROM laporan WHERE id_user = ? ORDER BY id_laporan DESC";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idUser);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LaporanModel l = new LaporanModel();
                l.setIdLaporan(rs.getInt("id_laporan"));
                l.setIdUser(rs.getInt("id_user"));
                l.setPeriode(rs.getString("periode"));
                l.setTanggalAwal(rs.getString("tanggal_awal"));
                l.setTanggalAkhir(rs.getString("tanggal_akhir"));
                l.setTotalPemasukan(rs.getDouble("total_pemasukan"));
                l.setTotalPengeluaran(rs.getDouble("total_pengeluaran"));
                l.setSaldo(rs.getDouble("saldo"));
                list.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ========== DELETE ==========
    public boolean hapusLaporan(int idLaporan, int idUser) {
        String sql = "DELETE FROM laporan WHERE id_laporan = ? AND id_user = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idLaporan);
            ps.setInt(2, idUser);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ========== TOTAL ==========
    public double getTotalPemasukan(int idUser) {
        String sql =
                "SELECT COALESCE(SUM(t.jumlah),0) AS total " +
                "FROM transaksi t JOIN category c ON t.id_category=c.id_category " +
                "WHERE t.id_user=? AND c.tipe='Pemasukan'";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idUser);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getTotalPengeluaran(int idUser) {
        String sql =
                "SELECT COALESCE(SUM(t.jumlah),0) AS total " +
                "FROM transaksi t JOIN category c ON t.id_category=c.id_category " +
                "WHERE t.id_user=? AND c.tipe='Pengeluaran'";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idUser);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getSaldo(int idUser) {
        return getTotalPemasukan(idUser) - getTotalPengeluaran(idUser);
    }
}