import java.util.ArrayList;

class CartItem {
    private String name;
    private double price;
    private int quantity;

    public CartItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and setters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotalPrice() {
        return price * quantity;
    }
}

public class ShoppingCart {
    private ArrayList<CartItem> items = new ArrayList<>();

    public void addItem(String name, double price, int quantity) {
        items.add(new CartItem(name, price, quantity));
    }

    public void removeItem(String name) {
        items.removeIf(item -> item.getName().equals(name));
    }

    public void viewCart() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty");
            return;
        }
        System.out.println("Items in your cart:");
        for (CartItem item : items) {
            System.out.printf("%s - $%.2f x %d = $%.2f\n", 
                item.getName(), item.getPrice(), item.getQuantity(), item.getTotalPrice());
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Laptop", 999.99, 1);
        cart.addItem("Mouse", 25.50, 2);
        cart.viewCart();
        System.out.printf("Total: $%.2f\n", cart.calculateTotal());
        cart.removeItem("Mouse");
        cart.viewCart();
    }
}
