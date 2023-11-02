/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaminiproject;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class customerData {


    private Integer customerId;
    private String type;
    private String brand;
    private String productName;
    private Integer quantity;
    private Double price;
    private Date date;
    
    public customerData(Integer customerId, String type, String brand, String productName, Integer quantity, Double price, Date date) { 
        this.customerId = customerId;
        this.type = type;
        this.brand = brand;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

}