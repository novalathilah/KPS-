# 💰 KPS Tracking - Personal Finance Management System

Aplikasi manajemen keuangan pribadi berbasis desktop dengan Java Swing dan MySQL.

---

## 📋 **Fitur**

### 🔐 Autentikasi & Manajemen Pengguna
- **Login** — Masuk menggunakan username atau email
- **Register** — Pendaftaran pengguna baru dengan validasi email & password (min 8 karakter)
- **Session Management** — In-memory session (Guest/Login) dengan gate check di setiap menu

### 📂 Manajemen Category (Pemasukan/Pengeluaran)
- **Tambah Category** — Nama + tipe (Pemasukan/Pengeluaran)
- **Lihat Category** — Tabel dengan kolom ID, Nama, Tipe
- **Edit Category** — Ubah nama & tipe
- **Hapus Category** — Dengan konfirmasi
- **Cari Category** — Filter berdasarkan nama/tipe

### 💳 Manajemen Transaksi
- **Tambah Transaksi** — Pilih kategori, jumlah (Rp), tanggal (kalender), deskripsi
- **Lihat Transaksi** — Tabel ID/Tanggal/Kategori/Jumlah/Deskripsi
- **Edit Transaksi** — Ubah data transaksi dengan konfirmasi
- **Hapus Transaksi** — Dengan konfirmasi
- **Cari Transaksi** — Filter berdasarkan kategori/deskripsi
- Format mata uang Rupiah (Rp)

### 📊 Laporan Keuangan
- **Laporan Harian** — Ringkasan pemasukan & pengeluaran per hari
- **Laporan Mingguan** — Ringkasan per periode tanggal
- **Laporan Bulanan** — Ringkasan per bulan
- **Laporan Tahunan** — Ringkasan per tahun
- **Simpan Laporan** — Persist laporan ke database
- **Riwayat Laporan** — Lihat & cari laporan yang tersimpan

### 🛡️ Keamanan & Validasi
- **PreparedStatement** — Proteksi SQL Injection
- **Validasi Input** — Client-side (View) + Server-side (Controller)
- **Autorisasi Data** — Setiap user hanya melihat data miliknya sendiri
- **Konfirmasi Popup** — Sebelum hapus/edit/logout

---

## 🛠️ **Tech Stack**
- Java (Swing), MySQL, JDBC, NetBeans
- Library: `mysql-connector-java`, `jcalendar-1.4`

---

## 🚀 **Cara Jalankan**
1. Clone repo
2. Import database `db_manage_keuangan` ke MySQL
3. Setting koneksi di `Database.java`
4. Clean & Build
5. Run

---

## 🏗️ **Arsitektur**
- **MVC Pattern** — Model, View, Controller, Service
- **Singleton Database** — Koneksi database terpusat
- **Layered Architecture** — View → Controller → Service → Database

---

## 📌 **Catatan Pengembangan**
- Password masih disimpan dalam bentuk **plaintext** (belum di-hash)
- Menu "Lihat Laporan" dan fitur "Hapus Laporan" belum diimplementasikan
- Tidak ada unit test
- UI masih standar Swing

---

## 👥 **Tim Pengembang**

| Nama | Role |
|------|------|
| Wigananda Pramudita | Developer |
| Jingles Girsang | Developer |
| Noval Athilah | Developer |
| Reysa Eka | Developer |
| Ferlin Putra | Developer |

---

## 📄 **License**
Copyright © 2024 KPS Tracking Team

---
