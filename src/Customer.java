public class Customer {
    private static int nextId = 1;
    private final int custId = nextId++;
    private String name;
    private String email;
    private String skillLevel;

    public Customer() {
    }

    public Customer(String name, String email, String skillLevel) {
        this.name = name;
        this.email = email;
        this.skillLevel = skillLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    @Override
    public String toString() {
        // Construct and return a string that describes the customer.
        return String.format(
                "{ id: %d, name: \"%s\", email: \"%s\", skillLevel: \"%s\" }",
                custId, name, email, skillLevel
        );
    }

}