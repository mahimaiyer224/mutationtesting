package ecommerce.backend;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private String userId;
    private List<CartItem> items;

    public Cart(String userId) {
        this.userId = userId;
        this.items = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) {
        items.add(new CartItem(product, quantity));
    }

    public void removeProduct(String productId) {
        items.removeIf(item -> item.getProduct().getProductId().equals(productId));
    }

    public double calculateTotal() {
        return items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void clearCart() {
        items.clear();
    }
}

class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
