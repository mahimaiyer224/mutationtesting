package ecommerce.backend;

import java.io.Serializable;

public class Product implements Serializable {
    private String productId;
    private String name;
    private double price;
    private int stock;
    private String category; // New attribute

    public Product(String productId, String name, double price, int stock, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getCategory() {
        return category;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
