-- ============================================================
-- SIRUS-CLI - Sistem Reservasi Ruang Diskusi
-- Script Database MySQL
-- ============================================================

DROP DATABASE IF EXISTS sirus_db;
CREATE DATABASE sirus_db;
USE sirus_db;

-- ============================================================
-- 1. TABEL RUANGAN
-- ============================================================
CREATE TABLE ruangan (
    id_ruangan INT AUTO_INCREMENT PRIMARY KEY,
    nama_ruangan VARCHAR(50) NOT NULL,
    kapasitas INT NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'Tersedia'
);

-- ============================================================
-- 2. TABEL BOOKING
-- ============================================================
CREATE TABLE booking (
    id_booking INT AUTO_INCREMENT PRIMARY KEY,
    id_ruangan INT NOT NULL,
    nama_pemesan VARCHAR(50) NOT NULL,
    jenis_pengguna VARCHAR(20) NOT NULL,
    tanggal DATE NOT NULL,
    jam_mulai TIME NOT NULL,
    jam_selesai TIME NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'Aktif',
    CONSTRAINT fk_booking_ruangan FOREIGN KEY (id_ruangan)
        REFERENCES ruangan(id_ruangan)
);

-- ============================================================
-- 3. DATA AWAL (opsional, untuk testing)
-- ============================================================
INSERT INTO ruangan (nama_ruangan, kapasitas, status) VALUES
('Ruang Diskusi A', 6, 'Tersedia'),
('Ruang Diskusi B', 8, 'Tersedia'),
('Ruang Diskusi C', 4, 'Tersedia');

-- ============================================================
-- 4. STORED PROCEDURE: tambah_ruangan
-- Menambahkan data ruangan baru
-- ============================================================
DELIMITER $$

CREATE PROCEDURE tambah_ruangan(
    IN p_nama_ruangan VARCHAR(50),
    IN p_kapasitas INT
)
BEGIN
    INSERT INTO ruangan (nama_ruangan, kapasitas, status)
    VALUES (p_nama_ruangan, p_kapasitas, 'Tersedia');
END$$

DELIMITER ;

-- ============================================================
-- 5. FUNCTION: total_booking
-- Menghitung total booking aktif untuk suatu ruangan
-- ============================================================
DELIMITER $$

CREATE FUNCTION total_booking(p_id_ruangan INT)
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total
    FROM booking
    WHERE id_ruangan = p_id_ruangan;
    RETURN total;
END$$

DELIMITER ;

-- ============================================================
-- 6. TRIGGER: setelah booking dibuat -> status ruangan jadi Dipakai
-- ============================================================
DELIMITER $$

CREATE TRIGGER trg_after_insert_booking
AFTER INSERT ON booking
FOR EACH ROW
BEGIN
    UPDATE ruangan
    SET status = 'Dipakai'
    WHERE id_ruangan = NEW.id_ruangan;
END$$

DELIMITER ;

-- ============================================================
-- 7. TRIGGER: setelah status booking diubah menjadi Selesai/Dibatalkan
--    -> status ruangan kembali menjadi Tersedia
-- ============================================================
DELIMITER $$

CREATE TRIGGER trg_after_update_booking
AFTER UPDATE ON booking
FOR EACH ROW
BEGIN
    IF NEW.status IN ('Selesai', 'Dibatalkan') AND OLD.status = 'Aktif' THEN
        UPDATE ruangan
        SET status = 'Tersedia'
        WHERE id_ruangan = NEW.id_ruangan;
    END IF;
END$$

DELIMITER ;

-- ============================================================
-- 8. VIEW: daftar_booking
-- Menampilkan riwayat booking beserta nama ruangan
-- ============================================================
CREATE VIEW daftar_booking AS
SELECT
    b.id_booking,
    r.nama_ruangan,
    b.nama_pemesan,
    b.jenis_pengguna,
    b.tanggal,
    b.jam_mulai,
    b.jam_selesai,
    b.status
FROM booking b
JOIN ruangan r ON b.id_ruangan = r.id_ruangan
ORDER BY b.id_booking DESC;
