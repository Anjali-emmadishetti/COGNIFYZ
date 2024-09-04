import java.util.Scanner;

public class PasswordStrengthChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a password:");
        String password = scanner.nextLine();
        System.out.println("Password Strength: " + getPasswordStrength(password));
        scanner.close();
    }

    private static String getPasswordStrength(String pw) {
        boolean hasUpper = !pw.equals(pw.toLowerCase());
        boolean hasLower = !pw.equals(pw.toUpperCase());
        boolean hasDigit = pw.matches(".*\\d.*");
        boolean hasSpecial = pw.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
        return (pw.length() >= 8 && hasUpper && hasLower && hasDigit && hasSpecial) ? "Strong" :
               (pw.length() >= 8 && (hasUpper || hasLower) && (hasDigit || hasSpecial)) ? "Moderate" :
               "Weak";
    }
}
