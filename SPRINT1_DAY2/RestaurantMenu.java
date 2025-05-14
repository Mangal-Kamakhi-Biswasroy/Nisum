import java.util.*;

class MenuItem {
    private String name;
    private String description;
    private double price;

    public MenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s - %s ($%.2f)", name, description, price);
    }
}

public class RestaurantMenu {
    private LinkedHashMap<String, List<MenuItem>> menu = new LinkedHashMap<>();

    public void addCategory(String category) {
        menu.putIfAbsent(category, new ArrayList<>());
    }

    public void addMenuItem(String category, MenuItem item) {
        if (!menu.containsKey(category)) {
            addCategory(category);
        }
        menu.get(category).add(item);
    }

    public void removeMenuItem(String category, String itemName) {
        if (menu.containsKey(category)) {
            menu.get(category).removeIf(item -> item.toString().contains(itemName));
        }
    }

    public void displayMenu() {
        if (menu.isEmpty()) {
            System.out.println("Menu is empty");
            return;
        }
        System.out.println("Restaurant Menu:");
        for (Map.Entry<String, List<MenuItem>> entry : menu.entrySet()) {
            System.out.println("\n" + entry.getKey() + ":");
            for (MenuItem item : entry.getValue()) {
                System.out.println("  " + item);
            }
        }
    }

    public static void main(String[] args) {
        RestaurantMenu restaurant = new RestaurantMenu();
        
        restaurant.addMenuItem("Appetizers", new MenuItem("Bruschetta", "Toasted bread with tomatoes", 8.99));
        restaurant.addMenuItem("Main Course", new MenuItem("Pasta Carbonara", "Pasta with creamy sauce", 16.99));
        restaurant.addMenuItem("Desserts", new MenuItem("Tiramisu", "Coffee-flavored dessert", 7.99));
        restaurant.addMenuItem("Appetizers", new MenuItem("Calamari", "Fried squid", 12.99));
        
        restaurant.displayMenu();
        
        System.out.println("\nAfter removing Bruschetta:");
        restaurant.removeMenuItem("Appetizers", "Bruschetta");
        restaurant.displayMenu();
    }
}
