/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supermarketmanagementsystem;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Durjoy
 */
public class Functionality {
    private static List<Product> products = new ArrayList<>();
    
    private static int lastProductId = 0; // Track the last used product ID
    
    public static void addProduct(String productName, String category, int quantity) {
        int productId = ++lastProductId; // Increment and use the new product ID
        Product product = new Product(productId, productName, category, quantity);
        products.add(product);
        Activity activity = new Activity(1, "NewProductAdded", quantity);
        product.addActivity(activity);
        //System.out.println(product.getProductId() + product.getProductName() + product.getCategory() + product.getQuantity() + product.getDate());
    }
    
    public static void deleteProduct(int productId) {
        boolean productFound = products.removeIf(product -> product.getProductId() == productId);

        if (productFound) {
            JOptionPane.showMessageDialog(null, "Product is deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Product with ID " + productId + " not found.");
        }
    }

    
    public static List<Product> getProductsByCategory(String category) {
        List<Product> productsByCategory = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                productsByCategory.add(product);
            }
        }
        return productsByCategory;
    } 
    
    public static void sortProductsByQuantity(List<Product> productList) {
        mergeSort(productList, 0, productList.size() - 1);
    }

    private static void mergeSort(List<Product> productList, int left, int right) {
        if (left < right) {
            // Find the middle point
            int mid = (left + right) / 2;

            // Recursively sort the first and second halves
            mergeSort(productList, left, mid);
            mergeSort(productList, mid + 1, right);

            // Merge the sorted halves
            merge(productList, left, mid, right);
        }
    }

    private static void merge(List<Product> productList, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays
        List<Product> leftArray = new ArrayList<>(productList.subList(left, mid + 1));
        List<Product> rightArray = new ArrayList<>(productList.subList(mid + 1, right + 1));

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray
        int k = left;

        // Merge the sorted halves
        while (i < n1 && j < n2) {
            if (leftArray.get(i).getQuantity() <= rightArray.get(j).getQuantity()) {
                productList.set(k, leftArray.get(i));
                i++;
            } else {
                productList.set(k, rightArray.get(j));
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArray[], if there are any
        while (i < n1) {
            productList.set(k, leftArray.get(i));
            i++;
            k++;
        }

        // Copy remaining elements of rightArray[], if there are any
        while (j < n2) {
            productList.set(k, rightArray.get(j));
            j++;
            k++;
        }
    }
    
    public static List<Product> getAllProducts() {
        return products;
    }
    
    public static Product findProductById(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;     
    }
    
    public static void addStock(int productId, int quantity) {
        Product product = findProductById(productId);
        if (product != null) {
            product.setQuantity(product.getQuantity() + quantity);
            //List<Activity> activities = product.getActivities();
            Activity lastActivity = product.getActivities().get(product.getActivities().size() - 1);
            Activity activity = new Activity(lastActivity.getActivityId() + 1, "AddToStock", quantity);
            product.addActivity(activity);
            JOptionPane.showMessageDialog(null, "The new stock is updated successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Product not found."
                    + " Kindly add as a brand new product",
                        "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public static void sellProduct(int productId, int quantity) {
        Product product = findProductById(productId);
        if (product != null) {
            int currentQuantity = product.getQuantity();
            if (currentQuantity >= quantity) {
                product.setQuantity(currentQuantity - quantity);
                //List<Activity> activities = product.getActivities();
                Activity lastActivity = product.getActivities().get(product.getActivities().size() - 1);
                Activity activity = new Activity(lastActivity.getActivityId() + 1, "RemoveFromStock", quantity);
                product.addActivity(activity);
                JOptionPane.showMessageDialog(null, "Product is sold");
            } else {
                //System.out.println("Insufficient stock for sale.");
                JOptionPane.showMessageDialog(null, "Insufficient stock for sale.",
                        "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Product not found.",
                        "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
}
