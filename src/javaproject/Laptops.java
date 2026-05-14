
package javaproject;


public class Laptops extends Electronics  {
    
    protected String ram;
    protected String storage;
    protected String processor;

    public Laptops(int warrantyYears, double powerUsage, double weight,
            double shippingCost, String brand, int id, String name, double price
            , Discount discount, String description, int quantity,String ram,
            String storage,String processor)
    {
        super(warrantyYears, powerUsage, weight, shippingCost, brand, id, name,
                price, discount, description, quantity);
        this.ram=ram;
        this.storage=storage;
        this.processor=processor;
    }



    @Override
    public double calculateTax() {
    return applyDiscount()*0.05;
    }

   
}
