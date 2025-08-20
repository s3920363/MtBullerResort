import java.time.LocalDate;

public class TravelPackage {
    private static int nextID = 1;
    private int pkgID = nextID++;
    private int days;
    private Customer cust;
    private Accommodation acc;
    private LocalDate date;

    public TravelPackage() {

    }

    public TravelPackage(Customer cust, LocalDate date, int days) {
        this.cust = cust;
        this.date = date;
        this.days = days;
    }

    
    public void attachAccommodation(Accommodation acc) {
        this.acc = acc;
        acc.setAvailable(false);
    }

    public int getID() {
        return pkgID;
    }

    public Customer getCustomer() {
        return cust;
    }

    public Accommodation getAccommodation() {
        return acc;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getDays() {
        return days;
    }

    @Override
    public String toString() {
        return String.format(
                "Package { id: %d, customer: \"%s\", accommodation: %s, date: %s, days: %d }",
                pkgID,
                cust.getName(),
                acc.getType(),
                date,
                days
        );
    }
}