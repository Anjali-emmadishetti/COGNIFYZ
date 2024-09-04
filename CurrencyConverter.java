import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Amount in USD: ");
            double amount = sc.nextDouble();
            System.out.print("Target currency code: ");
            String targetCurrency = sc.next().toUpperCase();
            
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.exchangerate-api.com/v4/latest/USD").openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            JSONObject rates = new JSONObject(in.readLine()).getJSONObject("rates");
            double rate = rates.getDouble(targetCurrency);
            
            System.out.printf("Converted amount: %.2f %s%n", amount * rate, targetCurrency);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error.");
        }
    }
}
