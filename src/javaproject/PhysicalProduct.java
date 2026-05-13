
package javaproject;


public class PhysicalProduct extends Product {
    
    protected double weight;
    protected double shippingCost;
    protected String brand;
    
    public PhysicalProduct(){};

    public PhysicalProduct(double weight, double shippingCost, String brand, int id, String name, double price, Discount discount, String description, int quantity) {
        super(id, name, price, discount, description, quantity);
        this.weight = weight;
        this.shippingCost = shippingCost;
        this.brand = brand;
    }
    
    
    
    
    public double calculateShipping(){
    
        return 0;
    }

    @Override
    public double applyDiscount(double price) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double getShippingCost() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double getFinalPrice() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
