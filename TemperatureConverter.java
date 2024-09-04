import java.util.Scanner;

public class TemperatureConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter temperature value:");
        double temp = scanner.nextDouble();
        System.out.println("Enter unit (C/F):");
        char unit = scanner.next().toUpperCase().charAt(0);
        scanner.close();

        if (unit == 'C') {
            System.out.printf("%.2f Celsius is %.2f Fahrenheit.%n", temp, celsiusToFahrenheit(temp));
        } else if (unit == 'F') {
            System.out.printf("%.2f Fahrenheit is %.2f Celsius.%n", temp, fahrenheitToCelsius(temp));
        } else {
            System.out.println("Invalid unit. Enter 'C' for Celsius or 'F' for Fahrenheit.");
        }
    }

    public static double celsiusToFahrenheit(double c) { return (c * 9/5) + 32; }
    public static double fahrenheitToCelsius(double f) { return (f - 32) * 5/9; }
}
