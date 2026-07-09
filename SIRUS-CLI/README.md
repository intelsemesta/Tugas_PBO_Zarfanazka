# SIRUS-CLI — Sistem Reservasi Ruang Diskusi

Aplikasi CLI Java sederhana dengan database MySQL untuk mata kuliah PBO.

## Struktur Package

```
com.kampus.sirus
├── model       -> Pengguna (abstract), Mahasiswa, Dosen, Ruangan, Booking
├── exception   -> RuanganTidakDitemukanException, RuanganSudahDipakaiException
├── util        -> KoneksiDatabase
├── dao         -> RuanganDAO, BookingDAO
└── main        -> Main (menu CLI)
```

## Konsep OOP yang Diterapkan

- **Class & Object**: Ruangan, Booking, Pengguna, dll.
- **Inheritance**: Mahasiswa dan Dosen mewarisi class abstrak Pengguna.
- **Polimorfisme**: method `getDurasiMaksimal()` dan `getPrioritasBooking()`
  di-override berbeda di Mahasiswa dan Dosen.
- **Enkapsulasi**: semua atribut model bersifat `private`, diakses lewat getter/setter.
- **Package**: kode dipisah menjadi model, dao, exception, util, main.
- **Exception Handling**: custom exception untuk kasus ruangan tidak ditemukan
  dan ruangan sudah dipakai.

## Langkah Setup

### 1. Setup Database
1. Buka MySQL Workbench / terminal MySQL.
2. Jalankan seluruh isi file `database/schema.sql`.
   File ini akan membuat database `sirus_db`, tabel `ruangan` dan `booking`,
   stored procedure `tambah_ruangan`, function `total_booking`,
   trigger status ruangan, dan view `daftar_booking`.

### 2. Konfigurasi Koneksi Java
Buka file:
```
src/main/java/com/kampus/sirus/util/KoneksiDatabase.java
```
Sesuaikan `USER` dan `PASSWORD` dengan akun MySQL di komputer Anda.

### 3. Menjalankan Aplikasi

**Opsi A — Menggunakan Maven (disarankan)**
```
mvn clean package
java -jar target/sirus-cli-jar-with-dependencies.jar
```

**Opsi B — Tanpa Maven (manual)**
1. Unduh driver JDBC MySQL (`mysql-connector-j-8.3.0.jar`) dari situs resmi MySQL.
2. Compile:
   ```
   javac -d out -cp mysql-connector-j-8.3.0.jar $(find src -name "*.java")
   ```
3. Jalankan:
   ```
   java -cp "out;mysql-connector-j-8.3.0.jar" com.kampus.sirus.main.Main
   ```
   (di Linux/Mac gunakan `:` sebagai pemisah classpath, bukan `;`)

## Menu Aplikasi

1. **Kelola Ruangan**
   - Tambah Ruangan
   - Lihat Daftar Ruangan
2. **Booking Ruangan**
   - Booking Ruangan
   - Selesaikan / Batalkan Booking
3. **Laporan Booking**
   - Lihat Riwayat Booking (dari view `daftar_booking`)
   - Lihat Total Booking per Ruangan (dari function `total_booking`)
0. **Keluar**
