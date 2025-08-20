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
}