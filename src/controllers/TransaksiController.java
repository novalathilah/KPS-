package controllers;

import model.TransaksiModel;
import service.TransaksiService;

/*
 * Controller berfungsi sebagai penghubung
 * antara View dan Service.
 */
public class TransaksiController {

    private final TransaksiService service =
            new TransaksiService();

    /*
     * Method simpan digunakan untuk meneruskan
     * data transaksi dari form ke Service
     */
    public boolean simpan(TransaksiModel transaksi) {

        return service.tambahTransaksi(transaksi);

    }
}