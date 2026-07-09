package com.kampus.sirus.exception;

/**
 * Exception kustom yang dilempar ketika ruangan dengan ID tertentu
 * tidak ditemukan di database.
 */
public class RuanganTidakDitemukanException extends Exception {

    public RuanganTidakDitemukanException(String message) {
        super(message);
    }
}
