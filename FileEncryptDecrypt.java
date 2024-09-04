import java.io.*;
import java.util.Scanner;

public class FileEncryptDecrypt {

    private static final int SHIFT = 3;

    private static String processText(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append(Character.isLetter(c) ?
                (char) ((c - (Character.isUpperCase(c) ? 'A' : 'a') + shift + 26) % 26 + (Character.isUpperCase(c) ? 'A' : 'a')) :
                c);
        }
        return result.toString();
    }

    private static void processFile(String fileName, boolean encrypt) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter((encrypt ? "encrypted_" : "decrypted_") + fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(processText(line, encrypt ? SHIFT : -SHIFT));
                writer.newLine();
            }
        }
        System.out.println("File " + (encrypt ? "encrypted" : "decrypted") + " successfully.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 for Encryption, 2 for Decryption:");
        boolean encrypt = scanner.nextInt() == 1;
        scanner.nextLine();  // Consume newline
        System.out.println("Enter file name:");
        try {
            processFile(scanner.nextLine(), encrypt);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        scanner.close();
    }
}
