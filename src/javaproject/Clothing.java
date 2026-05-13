
package javaproject;

public class Clothing extends PhysicalProduct {
    
    protected String size;
    protected String color;
    protected String material;

    public Clothing(String size, String color, String material, double weight, double shippingCost, String brand, int id, String name, double price, Discount discount, String description, int quantity) {
        super(weight, shippingCost, brand, id, name, price, discount, description, quantity);
        this.size = size;
        this.color = color;
        this.material = material;
    }
    @Override
public String toString() {
    return """
           Clothing Details:
           ID: """ + id + "\n" +
           "Name: " + name + "\n" +
           "Brand: " + brand + "\n" +
           "Price: $" + price + "\n" +
           "Quantity: " + quantity + "\n" +
           "Description: " + description + "\n" +
           "Weight: " + weight + "kg\n" +
           "Shipping Cost: $" + shippingCost + "\n" +
           "Size: " + size + "\n" +
           "Color: " + color + "\n" +
           "Material: " + material;
}
    
}
