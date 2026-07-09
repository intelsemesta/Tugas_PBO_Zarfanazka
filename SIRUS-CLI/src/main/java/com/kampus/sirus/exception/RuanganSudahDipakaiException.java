package com.kampus.sirus.exception;

/**
 * Exception kustom yang dilempar ketika pengguna mencoba
 * booking ruangan yang statusnya sedang "Dipakai".
 */
public class RuanganSudahDipakaiException extends Exception {

    public RuanganSudahDipakaiException(String message) {
        super(message);
    }
}
