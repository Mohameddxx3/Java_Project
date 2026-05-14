
package javaproject;


public abstract class DigitalProduct extends Product {
    
    protected double fileSize;
    protected String downloadLink;
public DigitalProduct(){};
    public DigitalProduct(double fileSize, String downloadLink, int id,
            String name, double price, Discount discount,
            String description, int quantity) {
        super(id, name, price, discount, description, quantity);
        this.fileSize = fileSize;
        this.downloadLink = downloadLink;
    }

    
    public abstract void download();
   
    @Override
    public  double getFinalPrice() {
        return applyDiscount();
        
    }

}