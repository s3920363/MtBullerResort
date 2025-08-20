import java.util.ArrayList;

public class MtBullerResort {
    ArrayList<Customer> customers = new ArrayList<>();
    ArrayList<Accommodation> accommodations = new ArrayList<>();
    ArrayList<TravelPackage> packages = new ArrayList<>();

    public void populateLists() {
        customers.add(new Customer("John", "john@gmail.com", "Expert"));
        customers.add(new Customer("Alice", "alice@yahoo.com", "Beginner"));
        customers.add(new Customer("Bob", "bob@hotmail.com", "Intermediate"));
        customers.add(new Customer("Diana", "diana@outlook.com", "Expert"));

        // Add demo accommodations
        accommodations.add(new Accommodation("Lodge", 200));
        accommodations.add(new Accommodation("Cabin", 150));
        accommodations.add(new Accommodation("Apartment", 250));
        accommodations.add(new Accommodation("Chalet", 300));

    }

    public void listCustomers() {
        for (Customer customer : customers) {
            System.out.println(customer);

        }
    }
}