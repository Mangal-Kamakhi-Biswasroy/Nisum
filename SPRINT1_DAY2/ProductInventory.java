import java.util.HashMap;
import java.util.Map;

public class ProductInventory {
    private HashMap<String, Integer> inventory = new HashMap<>();

    public void addProduct(String name, int quantity) {
        inventory.merge(name, quantity, Integer::sum);
    }

    public void updateProduct(String name, int newQuantity) {
        inventory.put(name, newQuantity);
    }

    public void removeProduct(String name) {
        inventory.remove(name);
    }

    public boolean isInStock(String name) {
        return inventory.containsKey(name) && inventory.get(name) > 0;
    }

    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty");
            return;
        }
        System.out.println("Current Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.printf("%s: %d units\n", entry.getKey(), entry.getValue());
        }
    }

    public static void main(String[] args) {
        ProductInventory inventory = new ProductInventory();
        inventory.addProduct("Laptop", 10);
        inventory.addProduct("Mouse", 50);
        inventory.addProduct("Keyboard", 30);
        
        inventory.displayInventory();
        
        System.out.println("\nIs Laptop in stock? " + inventory.isInStock("Laptop"));
        inventory.updateProduct("Laptop", 5);
        inventory.removeProduct("Keyboard");
        
        System.out.println("\nAfter updates:");
        inventory.displayInventory();
    }
}
