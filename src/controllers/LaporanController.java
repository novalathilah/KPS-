package controllers;

import model.LaporanModel;
import service.LaporanService;
import java.util.List;

public class LaporanController {

    private LaporanService laporanService = new LaporanService();

    private boolean validateIdUser(int idUser) {
        if (idUser <= 0) {
            System.out.println("Error: ID User tidak valid!");
            return false;
        }
        return true;
    }

    private boolean validateTanggal(String tanggal) {
        if (tanggal == null || tanggal.trim().isEmpty()) {
            System.out.println("Error: Tanggal tidak boleh kosong!");
            return false;
        }
        if (!tanggal.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Error: Format tanggal harus yyyy-MM-dd!");
            return false;
        }
        return true;
    }

    private boolean validateBulan(String bulan) {
        if (bulan == null || bulan.trim().isEmpty()) {
            System.out.println("Error: Bulan tidak boleh kosong!");
            return false;
        }
        if (!bulan.matches("\\d{4}-\\d{2}")) {
            System.out.println("Error: Format bulan harus yyyy-MM!");
            return false;
        }
        return true;
    }

    private boolean validateTahun(int tahun) {
        if (tahun < 2000 || tahun > 2100) {
            System.out.println("Error: Tahun tidak valid!");
            return false;
        }
        return true;
    }

    private boolean validateRange(String tglMulai, String tglSelesai) {
        if (tglMulai == null || tglMulai.trim().isEmpty()
                || tglSelesai == null || tglSelesai.trim().isEmpty()) {
            System.out.println("Error: Tanggal tidak boleh kosong!");
            return false;
        }
        if (!tglMulai.matches("\\d{4}-\\d{2}-\\d{2}")
                || !tglSelesai.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Error: Format tanggal harus yyyy-MM-dd!");
            return false;
        }
        return true;
    }

    private boolean validateLaporan(LaporanModel laporan) {
        if (laporan == null) {
            System.out.println("Error: Laporan tidak boleh kosong!");
            return false;
        }
        return true;
    }

    // HARIAN
    public LaporanModel getLaporanHarian(int idUser, String tanggal) {
        if (!validateIdUser(idUser)) {
            return null;
        }
        if (!validateTanggal(tanggal)) {
            return null;
        }
        return laporanService.getLaporanHarian(idUser, tanggal);
    }

    // MINGGUAN
    public LaporanModel getLaporanMingguan(int idUser, String tglMulai, String tglSelesai) {
        if (!validateIdUser(idUser)) {
            return null;
        }
        if (!validateRange(tglMulai, tglSelesai)) {
            return null;
        }
        return laporanService.getLaporanMingguan(idUser, tglMulai, tglSelesai);
    }

    // BULANAN
    public LaporanModel getLaporanBulanan(int idUser, String bulan) {
        if (!validateIdUser(idUser)) {
            return null;
        }
        if (!validateBulan(bulan)) {
            return null;
        }
        return laporanService.getLaporanBulanan(idUser, bulan);
    }

    // TAHUNAN
    public LaporanModel getLaporanTahunan(int idUser, int tahun) {
        if (!validateIdUser(idUser)) {
            return null;
        }
        if (!validateTahun(tahun)) {
            return null;
        }
        return laporanService.getLaporanTahunan(idUser, tahun);
    }

    // SIMPAN
    public boolean simpanLaporan(LaporanModel laporan) {
        if (!validateLaporan(laporan)) {
            return false;
        }
        if (!validateIdUser(laporan.getIdUser())) {
            return false;
        }
        return laporanService.simpanLaporan(laporan);
    }

    // AMBIL SEMUA
    public List<LaporanModel> getLaporanByUser(int idUser) {
        if (!validateIdUser(idUser)) {
            return null;
        }
        return laporanService.getLaporanByUser(idUser);
    }

    // HAPUS
    public boolean hapusLaporan(int idLaporan, int idUser) {
        if (!validateIdUser(idUser)) {
            return false;
        }
        if (idLaporan <= 0) {
            System.out.println("Error: ID Laporan tidak valid!");
            return false;
        }
        return laporanService.hapusLaporan(idLaporan, idUser);
    }

    // TOTAL PEMASUKAN
    public double getTotalPemasukan(int idUser) {
        if (!validateIdUser(idUser)) {
            return 0;
        }
        return laporanService.getTotalPemasukan(idUser);
    }

    // TOTAL PENGELUARAN
    public double getTotalPengeluaran(int idUser) {
        if (!validateIdUser(idUser)) {
            return 0;
        }
        return laporanService.getTotalPengeluaran(idUser);
    }

    // SALDO
    public double getSaldo(int idUser) {
        if (!validateIdUser(idUser)) {
            return 0;
        }
        return laporanService.getSaldo(idUser);
    }
}
