class Bank {
    protected String namaBank;

    // Konstruktor Super Class
    public Bank(String namaBank) {
        this.namaBank = namaBank;
    }

    // --- BONUS CHALLENGE: Fitur menghitung biaya transfer ---
    protected int hitungBiayaTransfer(String bankTujuan) {
        if (this.namaBank.equalsIgnoreCase(bankTujuan)) {
            return 0; // Sesama bank = transfer gratis
        } else {
            return 6500; // Beda bank = dikenakan biaya admin
        }
    }

    // Overload 1: Transfer ke rekening lain (2 parameter)
    public void transferUang(int jumlah, String rekeningTujuan) {
        System.out.println("Transfer Rp " + jumlah + " | Ke Rekening " + rekeningTujuan);
    }

    // Overload 2: Transfer ke rekening lain di bank berbeda (3 parameter)
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        int biaya = hitungBiayaTransfer(bankTujuan);
        System.out.println("Transfer Rp " + jumlah + " | Ke Rekening " + rekeningTujuan + " (" + bankTujuan + ")" + " | Biaya Admin: Rp" + biaya);
    }

    // Overload 3: Transfer dengan tambahan berita/pesan (4 parameter)
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan, String berita) {
        int biaya = hitungBiayaTransfer(bankTujuan);
        System.out.println("Transfer Rp " + jumlah + " | Ke Rekening " + rekeningTujuan + " (" + bankTujuan + ") " + "    | " + berita + " | Biaya Admin: Rp" + biaya);
    }

    // Method biasa yang akan di-override oleh sub class
    public void sukuBunga() {
        System.out.println("Suku Bunga standar adalah 3%");
    }
}

// SUB CLASS 1
class BankBNI extends Bank {

    // Konstruktor Sub Class 1
    public BankBNI() {
        super("BNI"); // Mengirim nama bank ke konstruktor super class
    }

    // Meng-override method suku bunga dengan nilai khusus BNI
    @Override
    public void sukuBunga() {
        System.out.println("Suku Bunga dari BNI adalah : 4%");
    }

    // Meng-override method transfer untuk memaksa bank tujuan menjadi BNI
    @Override
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        bankTujuan = "BNI"; // Memaksa bank tujuan menjadi BNI, apapun inputnya
        super.transferUang(jumlah, rekeningTujuan, bankTujuan); // Memanggil method dari super class
    }
}

// SUB CLASS 2
class BankBCA extends Bank {

    // Konstruktor Sub Class 2
    public BankBCA() {
        super("BCA"); // Mengirim nama bank ke konstruktor super class
    }

    // Meng-override method suku bunga dengan nilai khusus BCA
    @Override
    public void sukuBunga() {
        System.out.println("Suku Bunga dari BCA adalah : 4.5%");
    }

    // Meng-override method transfer untuk memaksa bank tujuan menjadi BCA
    @Override
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        bankTujuan = "BCA"; // Memaksa bank tujuan menjadi BCA, apapun inputnya
        super.transferUang(jumlah, rekeningTujuan, bankTujuan); // Memanggil method dari super class
    }
}
