package com.example.jwt.enumeration;

public enum MessageResponseEnum {
    SUCCESS("Success"),
    METHOD_NOT_MATCH("HTTP Method tidak Sesuai"),
    NULL_POINTER("Terdapat service berupa null pointer"),
    SERVER_ERROR("Terjadi kesalahan pada sistem"),
    INVALID_PARAMETER("Terjadi kesalahan format parameter request"),
    MISSING_HEADER("Header Param tidak ditemukan"),
    METHOD_MOT_MATCH("HTTP Method tidak Sesuai"),
    CONTENT_TYPE_NOT_ACCEPTABLE("HTTP Method tidak Sesuai"),
    ERROR_HANDLE_BIND("Kesalahan binding socket ke local address dan port"),
    DATA_INTEGRITY_VIOLATION("Data terkait terelasi dengan data lain"),
    ELEMENT_NOT_FOUND("Element Data Tidak Ditemukan"),
    MESSAGE_NOT_READABLE("Format Request tidak sesuai"),
    MESSAGE_NOT_WRITEABLE("Format Request tidak dapat diproses"),
    MISSING_PARAM_REQUEST("Kehilangan salah satu parameter request"),
    INVALID_JSON_FORMAT("JWT Token invalid"),
    CONVERTING_NOT_MATCH("Terjadi kesalahan saat konversi data"),
    USER_NOT_FOUND("Username tidak ditemukan"),
    ACCESS_DENIED("akses ditolak"),
    JWT_INVALID("Jwt tidak valid"),
    TOKEN_EXPIRED("Jwt telah kadaluarsa"),
    USER_NOT_MATCH("Username atau password salah"),
    AUTHENTICATED_REQUIRED("Otentikasi penuh diperlukan untuk mengakses sumber daya ini"),

    HELLOWORLD("INI CONTOH DATA CUSTOM YA");

    private final String message;

    MessageResponseEnum(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
