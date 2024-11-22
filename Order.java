package ecommerce.backend;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private String orderId;
    private String userId;
    private List<CartItem> items;
    private double total;
    private Date orderDate;
    private String status; // New attribute for order status

    public Order(String orderId, String userId, List<CartItem> items, double total) {
        this.orderId = orderId;
        this.userId = userId;
        this.items = items;
        this.total = total;
        this.orderDate = new Date();
        this.status = "Pending"; // Default status
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus(String status) {
        this.status = status;
    }
}
