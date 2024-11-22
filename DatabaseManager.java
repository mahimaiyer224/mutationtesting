package ecommerce.backend;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager {
    private static final String USER_FILE = "users.dat";
    private static final String PRODUCT_FILE = "products.dat";
    private static final String ORDER_FILE = "orders.dat";

    private Map<String, User> users = new HashMap<>();
    private Map<String, Product> products = new HashMap<>();
    private Map<String, List<Order>> orders = new HashMap<>();

    // Add a user to the database
    public void addUser(User user) {
        users.put(user.getUserId(), user);
        saveData(USER_FILE, users);
    }

    // Add a product to the database
    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
        saveData(PRODUCT_FILE, products);
    }

    // Add an order to the database
    public void addOrder(String userId, Order order) {
        orders.computeIfAbsent(userId, k -> new ArrayList<>()).add(order);
        saveData(ORDER_FILE, orders);
    }

    // Retrieve all users
    public Map<String, User> getUsers() {
        return loadData(USER_FILE);
    }

    // Retrieve all products
    public Map<String, Product> getProducts() {
        return loadData(PRODUCT_FILE);
    }

    // Retrieve orders for a specific user
    public List<Order> getUserOrders(String userId) {
        return orders.getOrDefault(userId, new ArrayList<>());
    }

    // Search products by name
    public List<Product> searchProductsByName(String name) {
        List<Product> matchingProducts = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    // Filter products by category
    public List<Product> filterProductsByCategory(String category) {
        List<Product> matchingProducts = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    // Update stock for a specific product
    public void updateProductStock(String productId, int newStock) {
        if (products.containsKey(productId)) {
            Product product = products.get(productId);
            product.setStock(newStock);
            saveData(PRODUCT_FILE, products);
            System.out.println("Stock updated successfully for product ID: " + productId);
        } else {
            System.out.println("Product not found with ID: " + productId);
        }
    }

    // Apply a discount to a specific category of products
    public void applyDiscount(String category, double discountPercentage) {
        for (Product product : products.values()) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                double newPrice = product.getPrice() * (1 - discountPercentage / 100);
                product.setPrice(newPrice);
            }
        }
        saveData(PRODUCT_FILE, products);
        System.out.println("Discount of " + discountPercentage + "% applied successfully to category: " + category);
    }

    // Save data to file
    private void saveData(String fileName, Object data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load data from file
    @SuppressWarnings("unchecked")
    private <T> Map<String, T> loadData(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Map<String, T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new HashMap<>();
        }
    }
}
