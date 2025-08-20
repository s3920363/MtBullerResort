import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class MtBullerResort {
    ArrayList<Customer> customers = new ArrayList<>();
    ArrayList<Accommodation> accommodations = new ArrayList<>();
    ArrayList<TravelPackage> packages = new ArrayList<>();

    Scanner input = new Scanner(System.in);

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
        System.out.println("\n----- Customers -----");
        for (Customer customer : customers) {
            System.out.println(customer);

        }
    }

    public void listAllAccommodations() {
        System.out.println("\n----- All Accommodations -----");
        for (Accommodation accommodation : accommodations) {
            System.out.println(accommodation);
        }
    }

    public void listAvailableAccommodations() {
        System.out.println("\n----- Available Accommodations -----");
        for (Accommodation accommodation : accommodations) {
            if (accommodation.isAvailable()) {
                System.out.println(accommodation);
            }

        }
    }

    public void listPackages() {
        System.out.println("\n----- Packages -----");
        for (TravelPackage pkg : packages) {
            System.out.println(pkg);
        }
    }

    public void addPackage() {
        Customer selectedCustomer = selectCustomer();
        if (selectedCustomer == null) {
            return;
        }

        Accommodation selectedAccommodation = selectAccommodation();
        if (selectedAccommodation == null) {
            return;
        }

        System.out.println("Enter travel date (YYYY-MM-DD):");
        String dateInput = input.next();
        LocalDate travelDate;
        try {
            travelDate = LocalDate.parse(dateInput);
        } catch (Exception e) {
            System.out.println("Invalid date format!");
            return;
        }

        System.out.println("Enter number of days:");
        int days = input.nextInt();

        if (days > 0) {
            TravelPackage newPackage = new TravelPackage(selectedCustomer, travelDate, days);
            newPackage.attachAccommodation(selectedAccommodation);
            packages.add(newPackage);

            System.out.println("Package created successfully!");
            System.out.println(newPackage);
        } else {
            System.out.println("Number of days must be positive!");
        }
    }

    public Customer selectCustomer() {
        Customer selectedCustomer = null;
        listCustomers();

        while (selectedCustomer == null) {
            System.out.println("Enter customer ID (or 0 to exit):");
            try {
                int choice = input.nextInt();

                if (choice == 0) {
                    System.out.println("Customer selection cancelled.");
                    return null;
                }

                selectedCustomer = searchCustomerByID(choice);
                if (selectedCustomer == null) {
                    System.out.println("Customer not found! Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                input.nextLine();
            }
        }
        return selectedCustomer;
    }

    public Accommodation selectAccommodation() {
        Accommodation selectedAccommodation = null;

        while (selectedAccommodation == null) {
            listAvailableAccommodations();
            System.out.println("Enter accommodation ID (or 0 to exit):");
            try {
                int choice = input.nextInt();

                if (choice == 0) {
                    System.out.println("Accommodation selection cancelled.");
                    return null;
                }

                selectedAccommodation = searchAccommodationByID(choice);
                if (selectedAccommodation != null && !selectedAccommodation.isAvailable()) {
                    System.out.println("Accommodation is not available! Please try again.");
                    selectedAccommodation = null;
                } else if (selectedAccommodation == null) {
                    System.out.println("Accommodation not found! Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                input.nextLine();
            }
        }
        return selectedAccommodation;
    }

    public Customer searchCustomerByID(int ID) {
        for (Customer customer : customers) {
            if (ID == customer.getID()) {
                return customer;
            }

        }
        return null;
    }

    public Accommodation searchAccommodationByID(int ID) {
        for (Accommodation accommodation : accommodations) {
            if (ID == accommodation.getID()) {
                return accommodation;
            }
        }
        return null;
    }
}