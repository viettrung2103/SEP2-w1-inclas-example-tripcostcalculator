import java.security.spec.ECField;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class TripCostCalculator {
    // Method to calculate trip cost
    public static double calculateTripCost(double kilometers, double fuelPrice, double fuelConsumptionPer100Km) {
        double fuelNeeded = (kilometers / 100) * fuelConsumptionPer100Km;
        return fuelNeeded * fuelPrice;
    }

    public static void main(String[] args) {
        System.out.println("Select a language: ");
        System.out.println("1.Farsi");
        System.out.println("2.Finnish");
        System.out.println("3.Japanese");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

//      select language for localization

        Locale locale;
        switch (choice){
            case 1:
                locale = new Locale("fa", "IR");
                break;
            case 2:
                locale = new Locale("fi","FI");
                break;
            case 3:
                locale = new Locale("ja","JP");
                break;
            default:
                System.out.println("Invalid language selection");
                locale = new Locale( "en","US");
        }

        ResourceBundle rb;
//        in case there is no resource bundle for the selected language, it will default to English
        try {
            rb = ResourceBundle.getBundle("messages",locale);
        } catch (Exception e){
            System.out.println("Invalid, selected language is not available, translate to English");
            rb = ResourceBundle.getBundle("messages",new Locale("en","US"));
        }

        // Ask user for the distance to travel
//        System.out.print("Enter the number of kilometers to travel: ");
        System.out.print(rb.getString("km"));
        double kilometers = scanner.nextDouble();

        // Ask user for the fuel price per liter
//        System.out.print("Enter the fuel price per liter: ");
        System.out.print(rb.getString("fuelPrice"));
        double fuelPrice = scanner.nextDouble();

        // Define fuel consumption rate (liters per 100 km)
        double fuelConsumptionPer100Km = 5.0; // Example: 5 liters per 100 km

        // Calculate total cost of the trip
        double totalCost = calculateTripCost(kilometers, fuelPrice, fuelConsumptionPer100Km);

        // Display the total cost
//        System.out.printf("The total cost of the trip is: %.2f euros\n", totalCost);
        String outputPrice = MessageFormat.format(rb.getString("total"),String.format("%.2f", totalCost));
        System.out.printf(outputPrice);


        scanner.close();
    }
}