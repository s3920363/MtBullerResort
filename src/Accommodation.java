public class Accommodation {
    private String type;
    private int price;
    private static int nextID = 1;
    private final int accID;
    private boolean available;

    public Accommodation(){
        this.accID = nextID++;
    }

    public Accommodation(String type, int price) {
        this.accID = nextID++;
        this.type = type;
        this.price = price;
        this.available = true;
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

    public static void main(String[] args) {
        Accommodation a1 = new Accommodation("Cabin", 200);
        Accommodation a2 = new Accommodation("Hotel Room", 400);

        // Print accommodations
        System.out.println(a1);
        System.out.println(a2);
    }
}