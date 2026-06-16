package controllers;

import model.TransaksiModel;
import service.TransaksiService;
import java.sql.Date;
import java.util.List;

public class TransaksiController {

    private final TransaksiService service = new TransaksiService();
    
    // ========== AMBIL TRANSAKSI BERDASARKAN USER ==========
    public List<TransaksiModel> getTransaksiByUser(int idUser) {
        if (idUser <= 0) {
            return null;
        }
        return service.getTransaksiByUser(idUser);
    }
    
    // ========== HAPUS TRANSAKSI ==========
    public boolean hapusTransaksi(int idTransaksi) {
        if (idTransaksi <= 0) {
            return false;
        }
        return service.hapusTransaksi(idTransaksi);
    }
    
    // ========== TAMBAH TRANSAKSI ==========
    public boolean tambahTransaksi(int idUser, int idCategory, double jumlah, 
                                   String deskripsi, String tanggalStr) {
        
        if (idUser <= 0) {
            System.out.println("Error: ID User tidak valid!");
            return false;
        }
        
        if (idCategory <= 0) {
            System.out.println("Error: ID Category tidak valid!");
            return false;
        }
        
        if (jumlah <= 0) {
            System.out.println("Error: Jumlah harus lebih dari 0!");
            return false;
        }
        
        if (tanggalStr == null || tanggalStr.trim().isEmpty()) {
            System.out.println("Error: Tanggal tidak boleh kosong!");
            return false;
        }
        
        Date tanggal;
        try {
            tanggal = Date.valueOf(tanggalStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Format tanggal tidak valid! Gunakan yyyy-MM-dd");
            return false;
        }
        
        TransaksiModel transaksi = new TransaksiModel();
        transaksi.setIdUser(idUser);
        transaksi.setIdCategory(idCategory);
        transaksi.setJumlah(jumlah);
        transaksi.setDeskripsi(deskripsi != null ? deskripsi.trim() : "");
        transaksi.setTanggal(tanggal);
        
        return service.tambahTransaksi(transaksi);
    }
    
    // ========== UPDATE TRANSAKSI (EDIT) ==========
    public boolean updateTransaksi(int idTransaksi, int idUser, int idCategory, 
                                   double jumlah, String deskripsi, String tanggalStr) {
        
        // VALIDASI
        if (idTransaksi <= 0) {
            System.out.println("Error: ID Transaksi tidak valid!");
            return false;
        }
        
        if (idUser <= 0) {
            System.out.println("Error: ID User tidak valid!");
            return false;
        }
        
        if (idCategory <= 0) {
            System.out.println("Error: ID Category tidak valid!");
            return false;
        }
        
        if (jumlah <= 0) {
            System.out.println("Error: Jumlah harus lebih dari 0!");
            return false;
        }
        
        if (tanggalStr == null || tanggalStr.trim().isEmpty()) {
            System.out.println("Error: Tanggal tidak boleh kosong!");
            return false;
        }
        
        Date tanggal;
        try {
            tanggal = Date.valueOf(tanggalStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Format tanggal tidak valid! Gunakan yyyy-MM-dd");
            return false;
        }
        
        TransaksiModel transaksi = new TransaksiModel();
        transaksi.setIdTransaksi(idTransaksi);
        transaksi.setIdUser(idUser);
        transaksi.setIdCategory(idCategory);
        transaksi.setJumlah(jumlah);
        transaksi.setDeskripsi(deskripsi != null ? deskripsi.trim() : "");
        transaksi.setTanggal(tanggal);
        
        return service.update(transaksi);
    }
}