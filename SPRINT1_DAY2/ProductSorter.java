import java.util.*;

class Product {
    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    // Getters
    public String getCategory() { return category; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("%s (%s): $%.2f", name, category, price);
    }
}

public class ProductSorter {
    public static void sortProducts(List<Product> products) {
        products.sort(Comparator
            .comparing(Product::getCategory)
            .thenComparingDouble(Product::getPrice));
    }

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", "Electronics", 999.99));
        products.add(new Product("Desk", "Furniture", 199.99));
        products.add(new Product("Mouse", "Electronics", 25.50));
        products.add(new Product("Chair", "Furniture", 89.99));
        
        System.out.println("Before sorting:");
        products.forEach(System.out::println);
        
        sortProducts(products);
        
        System.out.println("\nAfter sorting:");
        products.forEach(System.out::println);
    }
}
