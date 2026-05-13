
package javaproject;

public abstract class Product {
    protected int id;
    protected String name;
    protected double price;
    protected Discount discount;
    protected String description;
    protected int quantity;
   
    public Product(){}
    
    public Product(int id, String name, double price, Discount discount, String description, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.description = description;
        this.quantity = quantity;
       
    }
    
    
    public abstract double applyDiscount(double price);
    public abstract double getShippingCost();
    public abstract String getDescription();
    public abstract double getFinalPrice();
   
    
}
