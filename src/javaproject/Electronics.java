
package javaproject;


public class Electronics extends PhysicalProduct {
   
    protected int warrantyYears;
    protected double powerUsage;

    public Electronics(int warrantyYears, double powerUsage, double weight, double shippingCost, String brand, int id, String name, double price, Discount discount, String description, int quantity) {
        super(weight, shippingCost, brand, id, name, price, discount, description, quantity);
        this.warrantyYears = warrantyYears;
        this.powerUsage = powerUsage;
    }
    
    public String getWarrantyInfo(){
        
        return null;
        
    }
}
