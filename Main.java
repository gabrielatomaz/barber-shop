import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int numBarbers = 6;
        int numChairs = 6;
        BarberShop shop = new BarberShop(numBarbers, numChairs);
        int maxCustomers = 100;

        List<Customer> customers = new ArrayList<>();

        for (int i = 0; i < maxCustomers; i++) {
            Customer customer = new Customer(shop);
            customers.add(customer);
            customer.start();
            try {
                Thread.sleep(new Random().nextInt(2000) + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        shop.printState();
    }
}