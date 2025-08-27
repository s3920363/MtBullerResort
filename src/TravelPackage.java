import java.time.LocalDate;

public class TravelPackage {
    private static int nextID = 1;
    private final int pkgID = nextID++;
    private int days;
    private Customer cust;
    private Accommodation acc;
    private LocalDate date;
    private LiftPass liftPass;
    private boolean hasLiftPass = false;


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

    public void setDays(int days) {
        this.days = days;
    }

    public LiftPass getLiftPass() {
        return liftPass;
    }

    public void setLiftPass(LiftPass liftPass) {
        this.liftPass = liftPass;
    }

    public boolean getHasLiftPass() {
        return hasLiftPass;
    }

    public void setHasLiftPass() {
        this.hasLiftPass = true;
    }

    public double getTotalCost() {
        double totalCost = 0;
        if (liftPass != null)
            totalCost += liftPass.getCost();
        return totalCost;
    }

    @Override
    public String toString() {
        return String.format(
                "Package %d { customer: \"%s\", accommodation: %s, date: %s, days: %d }",
                pkgID,
                cust.getName(),
                acc.getType(),
                date,
                days
        );
    }
}