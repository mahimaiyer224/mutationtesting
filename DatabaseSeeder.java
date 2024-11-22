package ecommerce.backend;

public class DatabaseSeeder {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();

        // Adding sample products
        Product product1 = new Product("P001", "Laptop", 75000.0, 10, "Electronics");
        Product product2 = new Product("P002", "Smartphone", 50000.0, 20, "Electronics");
        Product product3 = new Product("P003", "Headphones", 1500.0, 50, "Accessories");
        Product product4 = new Product("P004", "Notebook", 50.0, 100, "Stationery");
        Product product5 = new Product("P005", "Office Chair", 4500.0, 5, "Furniture");
        Product product6 = new Product("P006", "Pen", 20.0, 200, "Stationery");

        // Adding products to the database
        dbManager.addProduct(product1);
        dbManager.addProduct(product2);
        dbManager.addProduct(product3);
        dbManager.addProduct(product4);
        dbManager.addProduct(product5);
        dbManager.addProduct(product6);

        System.out.println("Products have been successfully added to the database!");
    }
}
