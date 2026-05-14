
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
    public double calculateTax() {
    
        return applyDiscount()*0.04;
    
    }
}
