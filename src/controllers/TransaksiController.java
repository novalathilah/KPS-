package controllers;

import model.TransaksiModel;
import service.TransaksiService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TransaksiController {

    private final TransaksiService service = new TransaksiService();

    // ========== DASHBOARD ==========
    public int getTotalTransaksi(int idUser) {
        if (idUser <= 0) {
            return 0;
        }
        return service.getTotalTransaksi(idUser);
    }

    public double getTotalPemasukan(int idUser) {
        if (idUser <= 0) {
            return 0;
        }
        return service.getTotalPemasukan(idUser);
    }

    public double getTotalPengeluaran(int idUser) {
        if (idUser <= 0) {
            return 0;
        }
        return service.getTotalPengeluaran(idUser);
    }

    // ========== GET TRANSAKSI ==========
    public List<TransaksiModel> getTransaksiByUser(int idUser) {
        if (idUser <= 0) {
            return new ArrayList<>(); // FIX
        }
        return service.getTransaksiByUser(idUser);
    }

    // ========== HAPUS ==========
    public boolean hapusTransaksi(int idTransaksi) {
        if (idTransaksi <= 0) {
            return false;
        }
        return service.hapusTransaksi(idTransaksi);
    }

    // ========== TAMBAH ==========
    public boolean tambahTransaksi(int idUser, int idCategory, double jumlah,
            String deskripsi, String tanggalStr) {

        if (idUser <= 0 || idCategory <= 0 || jumlah <= 0) {
            return false;
        }
        if (tanggalStr == null || tanggalStr.trim().isEmpty()) {
            return false;
        }

        Date tanggal;

        try {
            tanggal = Date.valueOf(tanggalStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Format tanggal salah (yyyy-MM-dd)");
            return false;
        }

        TransaksiModel t = new TransaksiModel();
        t.setIdUser(idUser);
        t.setIdCategory(idCategory);
        t.setJumlah(jumlah);
        t.setDeskripsi(deskripsi != null ? deskripsi.trim() : "");
        t.setTanggal(tanggal);

        return service.tambahTransaksi(t);
    }

    // ========== UPDATE ==========
    public boolean updateTransaksi(int idTransaksi, int idUser, int idCategory,
            double jumlah, String deskripsi, String tanggalStr) {

        if (idTransaksi <= 0 || idUser <= 0 || idCategory <= 0 || jumlah <= 0) {
            return false;
        }

        Date tanggal;

        try {
            tanggal = Date.valueOf(tanggalStr);
        } catch (Exception e) {
            return false;
        }

        TransaksiModel t = new TransaksiModel();
        t.setIdTransaksi(idTransaksi);
        t.setIdUser(idUser);
        t.setIdCategory(idCategory);
        t.setJumlah(jumlah);
        t.setDeskripsi(deskripsi != null ? deskripsi.trim() : "");
        t.setTanggal(tanggal);

        return service.update(t);
    }
}
