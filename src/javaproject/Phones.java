
package javaproject;



public class Phones extends Electronics {

    protected int cameraMp;
    protected int batteryDetails;

    public Phones(int warrantyYears, double powerUsage, double weight,
            double shippingCost, String brand, int id, String name,
            double price, Discount discount, String description,
            int quantity, int cameraMp, int batteryDetails) {

        super(warrantyYears, powerUsage, weight, shippingCost, brand,
                id, name, price, discount, description, quantity);

        this.cameraMp = cameraMp;
        this.batteryDetails = batteryDetails;
    }

    @Override
    public String toString() {
        return "Phone Details:\n" +
               "ID: " + id + "\n" +
               "Name: " + name + "\n" +
               "Brand: " + brand + "\n" +
               "Price: $" + price + "\n" +
               "Quantity: " + quantity + "\n" +
               "Description: " + description + "\n" +
               "Warranty Years: " + warrantyYears + "\n" +
               "Power Usage: " + powerUsage + "W\n" +
               "Weight: " + weight + "kg\n" +
               "Shipping Cost: $" + shippingCost + "\n" +
               "Camera: " + cameraMp + " MP\n" +
               "Battery: " + batteryDetails + " mAh";
    }
}
