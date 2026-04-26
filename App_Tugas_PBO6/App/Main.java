import java.util.Scanner;
import Interface.Laptop;
import ModelLaptop.LaptopUser;
import ModelLaptop.Lenovo;
import ModelLaptop.Toshiba;
import ModelLaptop.MacBook;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===========================================");
        System.out.println("       SELAMAT DATANG DI LAPTOP MENU      ");
        System.out.println("===========================================");
        System.out.println("Pilih Laptop:");
        System.out.println("1. Lenovo");
        System.out.println("2. Toshiba");
        System.out.println("3. MacBook");
        System.out.print("Masukkan pilihan (1/2/3): ");
        String pilihan = scanner.nextLine();

        Laptop laptop;
        switch (pilihan) {
            case "1":
                laptop = new Lenovo();
                break;
            case "2":
                laptop = new Toshiba();
                break;
            case "3":
                laptop = new MacBook();
                break;
            default:
                System.out.println("Pilihan tidak valid. Default menggunakan Lenovo.");
                laptop = new Lenovo();
        }

        LaptopUser user = new LaptopUser(laptop);

        System.out.println("\n===========================================");
        System.out.println("Perintah yang tersedia:");
        System.out.println("  ON   - Menyalakan laptop");
        System.out.println("  OFF  - Mematikan laptop");
        System.out.println("  UP   - Menambah volume");
        System.out.println("  DOWN - Mengurangi volume");
        System.out.println("  EXIT - Keluar dari program");
        System.out.println("===========================================");

        String input;
        while (true) {
            System.out.print("\nMasukkan perintah: ");
            input = scanner.nextLine().trim().toUpperCase();

            switch (input) {
                case "ON":
                    user.turnOnLaptop();
                    break;
                case "OFF":
                    user.turnOffLaptop();
                    break;
                case "UP":
                    user.makeLaptopLouder();
                    break;
                case "DOWN":
                    user.makeLaptopSilence();
                    break;
                case "EXIT":
                    System.out.println("Terima kasih! Program selesai.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Perintah tidak dikenal. Gunakan: ON, OFF, UP, DOWN, atau EXIT");
            }
        }
    }
}
