// PROGRAM UTAMA
public class Main {
    public static void main(String[] args) {

        System.out.println("=== PENGUJIAN OVERLOADING & BONUS CHALLENGE BIAYA TRANSFER (SUPER CLASS) ===");
        Bank bank = new Bank("Bank Umum");
        bank.transferUang(1000000, "123456"); // Overload 1 - transfer dasar

        // Transfer ke bank berbeda akan dikenakan biaya admin Rp 6.500
        bank.transferUang(2000000, "789012", "Mandiri"); // Overload 2 - dengan bank tujuan
        bank.transferUang(3000000, "345678", "BRI", "Bayar SPP"); // Overload 3 - dengan berita/pesan
        bank.sukuBunga();

        System.out.println("\n=== PENGUJIAN OVERRIDING (SUB CLASS) ===");
        BankBNI bni = new BankBNI();
        // bankTujuan dipaksa menjadi BNI oleh method yang di-override, sehingga biaya admin menjadi Rp0
        bni.transferUang(200000, "112233", "Mandiri");
        bni.sukuBunga();

        System.out.println();

        BankBCA bca = new BankBCA();
        // bankTujuan dipaksa menjadi BCA oleh method yang di-override, sehingga biaya admin menjadi Rp0
        bca.transferUang(300000, "445566", "BRI");
        bca.sukuBunga();
    }
}
