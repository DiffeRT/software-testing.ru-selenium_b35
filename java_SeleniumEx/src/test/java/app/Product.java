package app;

public class Product {
    private final String name;

    public Product() {
        name = "My_Item" + (int)(Math.random() * 1000);
    }

    public String getName() {
        return name;
    }
}
