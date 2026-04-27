import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;

public class ContohCollection {

    public static void main(String[] args) {

        // =============================================
        // BAGIAN 1 : ArrayList
        // =============================================
        System.out.println("========== ArrayList ==========");

        ArrayList<String> daftarMahasiswa = new ArrayList<>();

        // Menambahkan data
        daftarMahasiswa.add("Zarfan");
        daftarMahasiswa.add("Rahmi");
        daftarMahasiswa.add("fika");
        daftarMahasiswa.add("Andi");
        daftarMahasiswa.add("Dewi");

        System.out.println("Data awal   : " + daftarMahasiswa);
        System.out.println("Ukuran      : " + daftarMahasiswa.size());
        System.out.println("Elemen ke-2 : " + daftarMahasiswa.get(1));

        // Update elemen
        daftarMahasiswa.set(0, "Zarfan");
        System.out.println("Setelah update index 0 : " + daftarMahasiswa);

        // Hapus elemen
        daftarMahasiswa.remove("Andi");
        System.out.println("Setelah hapus 'Andi'   : " + daftarMahasiswa);

        // Cek keberadaan
        System.out.println("Ada 'Sari'? : " + daftarMahasiswa.contains("Sari"));

        // Iterasi
        System.out.print("Iterasi     : ");
        for (String nama : daftarMahasiswa) {
            System.out.print(nama + " ");
        }
        System.out.println();

        // =============================================
        // BAGIAN 2 : ArrayDeque
        // =============================================
        System.out.println("\n========== ArrayDeque ==========");

        Deque<String> antrian = new ArrayDeque<>();

        // Menambah ke depan dan belakang
        antrian.addLast("Mahasiswa A");   // enqueue belakang
        antrian.addLast("Mahasiswa B");
        antrian.addLast("Mahasiswa C");
        antrian.addFirst("Mahasiswa Z");  // enqueue depan (prioritas)

        System.out.println("Isi antrian      : " + antrian);
        System.out.println("Peek depan       : " + antrian.peekFirst());
        System.out.println("Peek belakang    : " + antrian.peekLast());

        // Gunakan sebagai Stack (LIFO)
        System.out.println("\n--- Stack Mode (LIFO) ---");
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Stack isi : " + stack);
        System.out.println("Pop       : " + stack.pop());
        System.out.println("Stack sisa: " + stack);

        // Gunakan sebagai Queue (FIFO)
        System.out.println("\n--- Queue Mode (FIFO) ---");
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(100);
        queue.offer(200);
        queue.offer(300);
        System.out.println("Queue isi : " + queue);
        System.out.println("Poll      : " + queue.poll());
        System.out.println("Queue sisa: " + queue);
    }
}
