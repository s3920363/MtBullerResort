public class Accommodation {
    private static int nextID = 101;
    private final int accID = nextID++;
    private String type;
    private int price;
    private boolean available = true;

    public Accommodation() {

    }

    public Accommodation(String type, int price) {
        this.type = type;
        this.price = price;

    }

    public static void main(String[] args) {
        Accommodation a1 = new Accommodation("Cabin", 200);
        Accommodation a2 = new Accommodation("Hotel Room", 400);

        // Print accommodations
        System.out.println(a1);
        System.out.println(a2);

        a1.setAvailable(false);
        a2.setAvailable(false);

        System.out.println(a1);
        System.out.println(a2);

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return String.format(
                "{ id: %d, type: \"%s\", price: %d, available: %b }",
                accID, type, price, available
        );
    }
}