
package javaproject;


public class Laptops extends Electronics {
    
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
public String toString() {
    return """
           Laptop Details:
           ID: """ + id + "\n" +
           "Name: " + name + "\n" +
           "Brand: " + brand + "\n" +
           "Price: $" + price + "\n" +
           "Quantity: " + quantity + "\n" +
           "Description: " + description + "\n" +
           "Warranty Years: " + warrantyYears + "\n" +
           "Power Usage: " + powerUsage + "W\n" +
           "Weight: " + weight + "kg\n" +
           "Shipping Cost: $" + shippingCost + "\n" +
           "RAM: " + ram + "\n" +
           "Storage: " + storage + "\n" +
           "Processor: " + processor;
}   
}
