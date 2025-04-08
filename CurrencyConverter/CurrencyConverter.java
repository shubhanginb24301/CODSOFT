package cc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;


public class CurrencyConverter {
    private static final String API_BASE_URL = "https://api.exchangerate-api.com/v4/latest/";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] currencies = {"USD", "EUR", "GBP", "JPY", "AUD", "CAD", "CHF", "CNY", "INR"};
        
        while (true) {
            System.out.println("\nCurrency Converter");
            System.out.println("Available currencies:");
            for (String currency : currencies) {
                System.out.print(currency + " ");
            }
            
            // Get base currency
            System.out.print("\n\nEnter base currency (or 'exit' to quit): ");
            String fromCurrency = scanner.nextLine().toUpperCase();
            if (fromCurrency.equals("EXIT")) break;
            
            // Get target currency
            System.out.print("Enter target currency: ");
            String toCurrency = scanner.nextLine().toUpperCase();
            
            // Get amount
            System.out.print("Enter amount to convert: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // consume newline
            
            try {
                // Fetch and perform conversion
                double exchangeRate = getExchangeRate(fromCurrency, toCurrency);
                double convertedAmount = amount * exchangeRate;
                
                // Display results
                System.out.printf("\nConversion Result:\n");
                System.out.printf("%.2f %s = %.2f %s\n", amount, fromCurrency, convertedAmount, toCurrency);
                System.out.printf("Exchange Rate: 1 %s = %.4f %s\n", fromCurrency, exchangeRate, toCurrency);
                
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        scanner.close();
        System.out.println("Thank you for using Currency Converter!");
    }
    
    private static double getExchangeRate(String fromCurrency, String toCurrency) throws Exception {
        // Create URL for API request
        URL url = new URL(API_BASE_URL + fromCurrency);
        
        // Open connection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        
        // Check response code
        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new Exception("Failed to fetch exchange rates. HTTP Error: " + responseCode);
        }
        
        // Read response
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        
        // Parse JSON response
        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONObject rates = jsonResponse.getJSONObject("rates");
        
        if (!rates.has(toCurrency)) {
            throw new Exception("Invalid target currency: " + toCurrency);
        }
        
        return rates.getDouble(toCurrency);
    }
}
