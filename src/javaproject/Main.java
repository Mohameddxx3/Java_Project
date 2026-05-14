
package javaproject;

import java.time.LocalDate;



public class Main {
    public static void main(String[] args) {
        
            // =========================
            // 👕 CLOTHING OBJECTS
            // =========================

            // size, color, material, weight, shippingCost, brand, id, name,
            // price, discount, description, quantity

            Clothing c1 = new Clothing("M", "Black", "Cotton", 0.5, 50.0,
                    "Nike", 101, "T-Shirt", 299.99,
                    null, "Comfortable cotton t-shirt", 20);

            Clothing c2 = new Clothing("L", "Blue", "Denim", 1.2, 80.0,
                    "Levi's", 102, "Jeans", 799.99,
                    null, "Classic blue denim jeans", 15);
            // =========================
            // 💻 LAPTOP OBJECTS
            // =========================

            // screenSize, batteryLife, weight, shippingCost, brand, id, name,
            // price, discount, description, quantity, ram, storage, processor

            Laptops laptop1 = new Laptops(2, 65, 1.8, 20, "Dell", 1, "XPS 15",
                    1500, null, "High performance laptop", 10,
                    "16GB", "512GB SSD", "Intel i7");

            Laptops laptop2 = new Laptops(1, 60, 1.6, 25, "Apple", 2, "MacBook Pro",
                    2500, null, "Apple laptop", 5,
                    "16GB", "1TB SSD", "M2 Pro");


            // =========================
            // 📱 PHONE OBJECTS
            // =========================

            // simCount, chargerWatt, weight, shippingCost, brand, id, name,
            // price, discount, description, quantity, cameraMP, batteryCapacity

            Phones phone1 = new Phones(2, 15, 0.18, 10, "Samsung", 101, "Galaxy S24",
                    1200, null, "Flagship phone", 20, 50, 5000);

            Phones phone2 = new Phones(1, 12, 0.17, 12, "Apple", 102, "iPhone 15",
                    1300, null, "Apple phone", 12, 48, 4500);

      
            // =========================
            // 💻 SOFTWARE LICENSE OBJECTS
            // =========================

            // licenseKey, expiryDate, fileSize, downloadLink, id, name,
            // price, discount, description, quantity

            SoftwareLicense s1 = new SoftwareLicense(
                    "ABC123-XYZ789",
                    LocalDate.of(2027, 5, 14),
                    2.5,
                    "https://download.com/software1",
                    201,"Windows Antivirus",49.99,null,"Premium antivirus software",100);

            SoftwareLicense s2 = new SoftwareLicense(
                    "QWE456-RTY111",
                    LocalDate.of(2026, 12, 31),
                    1.2,
                    "https://download.com/software2",
                    202,
                    "Photo Editor Pro",79.99,null,"Professional photo editing software",50);
            
            // =========================
            // 🎵 DIGITAL DOWNLOAD OBJECTS
            // =========================

            // format, downloadLimit, fileSize, downloadLink, id, name,
            // price, discount, description, quantity

            DigitalDownload d1 = new DigitalDownload(
                    "MP3",
                    5,
                    120.5,
                    "https://download.com/music_album",
                    301,"Top Hits Album",19.99,null,"Popular music collection",200);

            DigitalDownload d2 = new DigitalDownload(
                    "PDF",
                    3,
                    15.2,
                    "https://download.com/java_book",
                    302,
                    "Java Programming Guide",29.99,null,"Complete Java learning ebook",80);

            
    }
}