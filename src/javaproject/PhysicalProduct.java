
package javaproject;


public abstract class PhysicalProduct extends Product implements Taxable,Shippable {
    
    protected double weight;
    protected double shippingCost;
    protected String brand;
    
    public PhysicalProduct(){}

    public PhysicalProduct(double weight, double shippingCost, String brand,
            int id, String name, double price, Discount discount, String description, int quantity) {
        super(id, name, price, discount, description, quantity);
        this.weight = weight;
        this.shippingCost = shippingCost;
        this.brand = brand;
    }

     @Override
    public double getFinalPrice() {

        return (applyDiscount()+ calculateTax()+ getShippableCost());
    }
    @Override
    public double getShippableCost() {
    return shippingCost;
}
    
    }
        
