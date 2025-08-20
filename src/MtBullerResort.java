import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

        accommodations.add(new Accommodation("Hotel", 300));
        accommodations.add(new Accommodation("Apartment", 220));
        accommodations.add(new Accommodation("Lodge", 180));
        accommodations.add(new Accommodation("Cabin", 140));
        accommodations.add(new Accommodation("Hotel", 400));
        accommodations.add(new Accommodation("Apartment", 275));
        accommodations.add(new Accommodation("Lodge", 210));
        accommodations.add(new Accommodation("Cabin", 160));
        accommodations.add(new Accommodation("Hotel", 350));
        accommodations.add(new Accommodation("Apartment", 260));

    }

    public void run() {
        boolean running = true;
        System.out.println("------------ Welcome to Mount Buller Resort! ------------");

        while (running) {
            try {
                System.out.println("\n1. Display all accommodations");
                System.out.println("2. Display available accommodations");
                System.out.println("3. Add a customer");
                System.out.println("4. List all customers");
                System.out.println("5. Create a package");
                System.out.println("6. List all packages");
                System.out.println("11. Quit");

                System.out.print("\nPlease choose an option: ");

                int option = input.nextInt();
                input.nextLine(); // consume leftover newline

                switch (option) {
                    case 1 -> listAllAccommodations();
                    case 2 -> listAvailableAccommodations();
                    case 3 -> addCustomer();
                    case 4 -> listCustomers();
                    case 5 -> addPackage();
                    case 6 -> listPackages();
                    case 11 -> {
                        running = false;
                        System.out.println("Goodbye!");
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                input.nextLine();
            }
        }
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

    public void addCustomer() {
        boolean valid = false;

        while (!valid) {
            try {
                System.out.print("Enter customer name: ");
                String name = input.nextLine().trim();

                System.out.print("Enter customer email: ");
                String email = input.nextLine().trim();

                System.out.print("Enter skill level (Beginner / Intermediate / Expert): ");
                String skillLevel = input.nextLine().toLowerCase().trim();

                // empty check
                if (name.isEmpty() || email.isEmpty() || skillLevel.isEmpty()) {
                    throw new IllegalArgumentException("All fields must be filled.");
                }

                // no number only
                if (!name.matches(".*[a-zA-Z].*")) {
                    throw new IllegalArgumentException("Name must contain letters.");
                }
                if (!email.matches(".*[a-zA-Z].*")) {
                    throw new IllegalArgumentException("Email must contain letters.");
                }

                // skill check
                if (!(skillLevel.equals("beginner") || skillLevel.equals("intermediate") || skillLevel.equals("expert"))) {
                    throw new IllegalArgumentException("Skill level must be Beginner, Intermediate, or Expert.");
                }

                Customer newCustomer = new Customer(name, email, skillLevel);
                customers.add(newCustomer);

                System.out.println("\nCustomer added successfully: " + newCustomer);
                valid = true;

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + " Please try again.\n");
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                input.nextLine();
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

        LocalDate travelDate = null;
        while (travelDate == null) {
            System.out.print("Enter travel date (YYYY-MM-DD) or 'now': ");
            String dateInput = input.next().trim();

            if (dateInput.equalsIgnoreCase("now")) {
                travelDate = LocalDate.now();
            } else {
                try {
                    travelDate = LocalDate.parse(dateInput);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format! Please try again.");
                }
            }
        }

        System.out.println("Enter number of days:");
        try {
            int days = input.nextInt();

            if (days > 0) {
                TravelPackage newPackage = new TravelPackage(selectedCustomer, travelDate, days);
                newPackage.attachAccommodation(selectedAccommodation);
                packages.add(newPackage);
                selectedCustomer.setHasPackage();

                System.out.println("Package created successfully!");
                System.out.println(newPackage);
            } else {
                System.out.println("Number of days must be positive!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid number.");
            input.nextLine();
        }
    }

    public Customer selectCustomer() {
        Customer selected = null;
        listCustomers();

        while (selected == null) {
            System.out.println("Enter customer ID (or 0 to exit):");
            try {
                int choice = input.nextInt();

                if (choice == 0) {
                    System.out.println("Package creation cancelled.");
                    return null;
                }

                selected = searchCustomerByID(choice);
                if (selected == null) {
                    System.out.println("Customer not found! Please try again.");
                } else if (selected.inPackage()) {
                    System.out.println("Customer already has a package! Please select a different customer.");
                    selected = null;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                input.nextLine();
            }
        }
        return selected;
    }

    public Accommodation selectAccommodation() {
        Accommodation selected = null;
        listAvailableAccommodations();

        while (selected == null) {
            System.out.println("Enter accommodation ID (or 0 to exit):");
            try {
                int choice = input.nextInt();

                if (choice == 0) {
                    System.out.println("Package creation cancelled.");
                    return null;
                }

                selected = searchAccommodationByID(choice);
                if (selected != null && !selected.isAvailable()) {
                    System.out.println("Accommodation is not available! Please try again.");
                    selected = null;
                } else if (selected == null) {
                    System.out.println("Accommodation not found! Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                input.nextLine();
            }
        }
        return selected;
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