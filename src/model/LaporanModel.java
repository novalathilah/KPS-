package model;

public class LaporanModel {

    private int idLaporan;
    private int idUser;
    private String periode;
    private String tanggalAwal;
    private String tanggalAkhir;
    private double totalPemasukan;
    private double totalPengeluaran;
    private double saldo;
    private String username;
    private int jumlahTransaksi;

    public LaporanModel() {
    }

    public LaporanModel(String periode, String tanggalAwal, String tanggalAkhir,
            double totalPemasukan, double totalPengeluaran, double saldo) {
        this.periode = periode;
        this.tanggalAwal = tanggalAwal;
        this.tanggalAkhir = tanggalAkhir;
        this.totalPemasukan = totalPemasukan;
        this.totalPengeluaran = totalPengeluaran;
        this.saldo = saldo;
    }

    // GETTERS & SETTERS
    public int getIdLaporan() {
        return idLaporan;
    }

    public void setIdLaporan(int idLaporan) {
        this.idLaporan = idLaporan;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getTanggalAwal() {
        return tanggalAwal;
    }

    public void setTanggalAwal(String tanggalAwal) {
        this.tanggalAwal = tanggalAwal;
    }

    public String getTanggalAkhir() {
        return tanggalAkhir;
    }

    public void setTanggalAkhir(String tanggalAkhir) {
        this.tanggalAkhir = tanggalAkhir;
    }

    public double getTotalPemasukan() {
        return totalPemasukan;
    }

    public void setTotalPemasukan(double totalPemasukan) {
        this.totalPemasukan = totalPemasukan;
    }

    public double getTotalPengeluaran() {
        return totalPengeluaran;
    }

    public void setTotalPengeluaran(double totalPengeluaran) {
        this.totalPengeluaran = totalPengeluaran;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getJumlahTransaksi() {
        return jumlahTransaksi;
    }

    public void setJumlahTransaksi(int jumlahTransaksi) {
        this.jumlahTransaksi = jumlahTransaksi;
    }
}
