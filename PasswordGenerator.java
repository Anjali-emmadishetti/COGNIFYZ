import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();
        
        System.out.print("Password length: ");
        int length = sc.nextInt();
        sc.nextLine();

        System.out.print("Include numbers? (yes/no): ");
        String pool = sc.nextLine().equalsIgnoreCase("yes") ? "0123456789" : "";

        System.out.print("Include lowercase? (yes/no): ");
        pool += sc.nextLine().equalsIgnoreCase("yes") ? "abcdefghijklmnopqrstuvwxyz" : "";

        System.out.print("Include uppercase? (yes/no): ");
        pool += sc.nextLine().equalsIgnoreCase("yes") ? "ABCDEFGHIJKLMNOPQRSTUVWXYZ" : "";

        System.out.print("Include special characters? (yes/no): ");
        pool += sc.nextLine().equalsIgnoreCase("yes") ? "!@#$%^&*()-_+=<>?/" : "";

        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(pool.charAt(rnd.nextInt(pool.length())));
        }

        System.out.println("Generated Password: " + password.toString());
        sc.close();
    }
}
