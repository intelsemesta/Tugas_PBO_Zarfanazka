// Generic class Mahasiswa<A, B, C>
// A = tipe untuk NIM
// B = tipe untuk Nama
// C = tipe untuk Kelas/SKS

public class Mahasiswa<A, B, C> {

    private A nim;
    private B name;
    private C clas;

    // Constructor kosong
    public Mahasiswa() {}

    // Constructor dengan parameter
    public Mahasiswa(A nim, B name, C clas) {
        this.nim  = nim;
        this.name = name;
        this.clas = clas;
    }

    // Setter
    public void setNim(A nim)   { this.nim  = nim;  }
    public void setName(B name) { this.name = name; }
    public void setClas(C clas) { this.clas = clas; }

    // Getter
    public A getNim()  { return nim;  }
    public B getName() { return name; }
    public C getClas() { return clas; }

    @Override
    public String toString() {
        return "Mahasiswa{nim=" + nim + ", name=" + name + ", clas=" + clas + "}";
    }
}
