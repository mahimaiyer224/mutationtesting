package ecommerce.backend;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class ProductManager {
    private DatabaseManager dbManager;

    public ProductManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Product Management ---");
            System.out.println("1. View All Products");
            System.out.println("2. Search Product by Name");
            System.out.println("3. Filter Products by Category");
            System.out.println("4. Update Product Stock");
            System.out.println("5. Apply Discount to a Category");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> viewAllProducts();
                case 2 -> searchProductByName(scanner);
                case 3 -> filterProductsByCategory(scanner);
                case 4 -> updateProductStock(scanner);
                case 5 -> applyDiscount(scanner);
                case 6 -> System.out.println("Exiting Product Management...");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private void viewAllProducts() {
        List<Product> products = new ArrayList<>(dbManager.getProducts().values());
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Product product : products) {
                displayProduct(product);
            }
        }
    }

    private void searchProductByName(Scanner scanner) {
        System.out.print("Enter product name to search: ");
        String name = scanner.next();
        List<Product> products = dbManager.searchProductsByName(name);
        if (products.isEmpty()) {
            System.out.println("No matching products found.");
        } else {
            for (Product product : products) {
                displayProduct(product);
            }
        }
    }

    private void filterProductsByCategory(Scanner scanner) {
        System.out.print("Enter category to filter: ");
        String category = scanner.next();
        List<Product> products = dbManager.filterProductsByCategory(category);
        if (products.isEmpty()) {
            System.out.println("No products found in this category.");
        } else {
            for (Product product : products) {
                displayProduct(product);
            }
        }
    }

    private void updateProductStock(Scanner scanner) {
        System.out.print("Enter Product ID to update stock: ");
        String productId = scanner.next();
        System.out.print("Enter new stock quantity: ");
        int newStock = scanner.nextInt();
        dbManager.updateProductStock(productId, newStock);
    }

    private void applyDiscount(Scanner scanner) {
        System.out.print("Enter category to apply discount: ");
        String category = scanner.next();
        System.out.print("Enter discount percentage: ");
        double discount = scanner.nextDouble();
        dbManager.applyDiscount(category, discount);
    }

    private void displayProduct(Product product) {
        System.out.printf("Product ID: %s | Name: %s | Price: %.2f | Stock: %d | Category: %s%n",
                product.getProductId(), product.getName(), product.getPrice(), product.getStock(), product.getCategory());
    }
}
