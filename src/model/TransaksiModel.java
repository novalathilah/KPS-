package model;

import java.sql.Date;

/*
 * Class TransaksiModel digunakan sebagai wadah data transaksi.
 * Data yang diinput user akan disimpan sementara di objek ini
 * sebelum dikirim ke database.
 */
public class TransaksiModel {

    private int idTransaksi;
    private int idUser;
    private int idCategory;
    private double jumlah;
    private String deskripsi;
    private Date tanggal;

    // Getter dan Setter ID Transaksi
    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    // Getter dan Setter ID User
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    // Getter dan Setter ID Category
    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    // Getter dan Setter Jumlah Transaksi
    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    // Getter dan Setter Deskripsi
    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    // Getter dan Setter Tanggal
    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
}