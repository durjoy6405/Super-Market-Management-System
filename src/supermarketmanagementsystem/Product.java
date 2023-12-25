/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supermarketmanagementsystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Durjoy
 */
public class Product {
    private int productId;
    private String productName;
    private String category;
    private Date productDate;
    private int quantity;
    private List<Activity> activities;

    public Product(int productId, String productName, String category, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.productDate = new Date();
        this.quantity = quantity;
        this.activities = new ArrayList<>();
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public Date getDate() {
        return productDate;
    }

    public List<Activity> getActivities() {
        return activities;
    }
    
    public void setQuantity(int q){
        quantity = q;
    }

    public void addActivity(Activity activity) {
        
        activities.add(activity);
        if (activities.size() > 4) {
            activities.remove(0); // Keep only the last four activities
        }
    }
}
