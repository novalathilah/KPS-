package controllers;

import model.LaporanModel;
import service.LaporanService;
import java.util.List;

public class LaporanController {

    private LaporanService laporanService = new LaporanService();

    private boolean validateIdUser(int idUser) {
        return idUser > 0;
    }

    private boolean validateTanggal(String tanggal) {
        return tanggal != null && tanggal.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    private boolean validateBulan(String bulan) {
        return bulan != null && bulan.matches("\\d{4}-\\d{2}");
    }

    private boolean validateTahun(int tahun) {
        return tahun >= 2000 && tahun <= 2100;
    }

    private boolean validateRange(String a, String b) {
        return validateTanggal(a) && validateTanggal(b);
    }

    // HARIAN
    public LaporanModel getLaporanHarian(int idUser, String tanggal) {
        if (!validateIdUser(idUser) || !validateTanggal(tanggal)) {
            return null;
        }
        return laporanService.getLaporanHarian(idUser, tanggal);
    }

    // MINGGUAN
    public LaporanModel getLaporanMingguan(int idUser, String a, String b) {
        if (!validateIdUser(idUser) || !validateRange(a, b)) {
            return null;
        }
        return laporanService.getLaporanMingguan(idUser, a, b);
    }

    // BULANAN
    public LaporanModel getLaporanBulanan(int idUser, String bulan) {
        if (!validateIdUser(idUser) || !validateBulan(bulan)) {
            return null;
        }
        return laporanService.getLaporanBulanan(idUser, bulan);
    }

    // TAHUNAN
    public LaporanModel getLaporanTahunan(int idUser, int tahun) {
        if (!validateIdUser(idUser) || !validateTahun(tahun)) {
            return null;
        }
        return laporanService.getLaporanTahunan(idUser, tahun);
    }

    // SIMPAN
    public boolean simpanLaporan(LaporanModel laporan) {
        if (laporan == null || !validateIdUser(laporan.getIdUser())) {
            return false;
        }
        return laporanService.simpanLaporan(laporan);
    }

    // RIWAYAT
    public List<LaporanModel> getLaporanByUser(int idUser) {
        if (!validateIdUser(idUser)) {
            return null;
        }
        return laporanService.getLaporanByUser(idUser);
    }

    // DELETE
    public boolean hapusLaporan(int idLaporan, int idUser) {
        if (!validateIdUser(idUser) || idLaporan <= 0) {
            return false;
        }
        return laporanService.hapusLaporan(idLaporan, idUser);
    }

    // TOTAL
    public double getTotalPemasukan(int idUser) {
        if (!validateIdUser(idUser)) {
            return 0;
        }
        return laporanService.getTotalPemasukan(idUser);
    }

    public double getTotalPengeluaran(int idUser) {
        if (!validateIdUser(idUser)) {
            return 0;
        }
        return laporanService.getTotalPengeluaran(idUser);
    }

    public double getSaldo(int idUser) {
        if (!validateIdUser(idUser)) {
            return 0;
        }
        return laporanService.getSaldo(idUser);
    }
}
