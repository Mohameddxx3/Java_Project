//package javaproject;
//
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.control.*;
//import javafx.scene.layout.*;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import java.time.LocalDate;
//
//public class Main {
//
//    // ── Shopping Cart ──────────────────────────────────────────
//    private ShoppingCart cart = new ShoppingCart(50);
//
//    // ── Cart display widgets ───────────────────────────────────
//    private TextArea cartArea    = new TextArea();
//    private Label    totalLabel  = new Label("Total: $0.00");
//    private Label    msgLabel    = new Label("");
//
//    // ── Products ───────────────────────────────────────────────
//    private Clothing c1, c2;
//    private Laptops  l1, l2;
//    private Phones   p1, p2;
//    private SoftwareLicense  s1, s2;
//    private DigitalDownload  d1, d2;
//
//    public Main() {
//        buildProducts();
//    }
//
//    // ==========================================================
//    //  ROOT  –  BorderPane
//    // ==========================================================
//    public BorderPane buildUI() {
//
//        BorderPane root = new BorderPane();
//
//        root.setTop(buildHeader());          // TOP
//        root.setCenter(buildCenter());       // CENTER  (tabs with GridPanes)
//        root.setRight(buildCartPanel());     // RIGHT   (cart)
//        root.setBottom(buildBottomBar());    // BOTTOM  (message bar)
//
//        return root;
//    }
//
//    // ==========================================================
//    //  TOP  –  Header
//    // ==========================================================
//    private HBox buildHeader() {
//
//        Label title = new Label("Product Management System");
//        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
//        title.setStyle("-fx-text-fill: white;");
//
//        Label sub = new Label("Java OOP Project");
//        sub.setStyle("-fx-text-fill: #aaaaaa; -fx-font-size: 13px;");
//
//        VBox titleBox = new VBox(3, title, sub);
//
//        HBox header = new HBox(titleBox);
//        header.setPadding(new Insets(15, 20, 15, 20));
//        header.setStyle("-fx-background-color: #2c3e50;");
//        header.setAlignment(Pos.CENTER_LEFT);
//
//        return header;
//    }
//
//    // ==========================================================
//    //  CENTER  –  TabPane  (one tab per product category)
//    // ==========================================================
//    private TabPane buildCenter() {
//
//        TabPane tabPane = new TabPane();
//        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
//
//        tabPane.getTabs().addAll(
//            buildClothingTab(),
//            buildLaptopsTab(),
//            buildPhonesTab(),
//            buildSoftwareTab(),
//            buildDigitalTab()
//        );
//
//        return tabPane;
//    }
//
//    // ----------------------------------------------------------
//    //  TAB 1 – Clothing
//    // ----------------------------------------------------------
//    private Tab buildClothingTab() {
//
//        Tab tab = new Tab("Clothing");
//
//        // GridPane: 2 columns (one card per column)
//        GridPane grid = new GridPane();
//        grid.setPadding(new Insets(20));
//        grid.setHgap(20);
//        grid.setVgap(20);
//
//        grid.add(buildCard(c1,
//            "Size: " + c1.getSize() +
//            "  |  Color: " + c1.getColor() +
//            "  |  Material: " + c1.getMaterial() +
//            "  |  Brand: " + c1.getBrand()), 0, 0);
//
//        grid.add(buildCard(c2,
//            "Size: " + c2.getSize() +
//            "  |  Color: " + c2.getColor() +
//            "  |  Material: " + c2.getMaterial() +
//            "  |  Brand: " + c2.getBrand()), 1, 0);
//
//        tab.setContent(new ScrollPane(grid));
//        return tab;
//    }
//
//    // ----------------------------------------------------------
//    //  TAB 2 – Laptops
//    // ----------------------------------------------------------
//    private Tab buildLaptopsTab() {
//
//        Tab tab = new Tab("Laptops");
//
//        GridPane grid = new GridPane();
//        grid.setPadding(new Insets(20));
//        grid.setHgap(20);
//        grid.setVgap(20);
//
//        grid.add(buildCard(l1,
//            "RAM: " + l1.ram +
//            "  |  Storage: " + l1.storage) +
//            "  |  CPU: " + l1.processor +
//            "  |  Brand: " + l1.brand), 0, 0);
//
//        grid.add(buildCard(l2,
//            "RAM: " + l2.ram +
//            "  |  Storage: " + l2.storage +
//            "  |  CPU: " + l2.processor+
//            "  |  Brand: " + l2.brand), 1, 0);
//
//        tab.setContent(new ScrollPane(grid));
//        return tab;
//    }
//
//    // ----------------------------------------------------------
//    //  TAB 3 – Phones
//    // ----------------------------------------------------------
//    private Tab buildPhonesTab() {
//
//        Tab tab = new Tab("Phones");
//
//        GridPane grid = new GridPane();
//        grid.setPadding(new Insets(20));
//        grid.setHgap(20);
//        grid.setVgap(20);
//
//        grid.add(buildCard(p1,
//            "Camera: " + p1.getCameraMp() + "MP" +
//            "  |  Battery: " + p1.getBatteryDetails() + "mAh" +
//            "  |  Brand: " + p1.getBrand()), 0, 0);
//
//        grid.add(buildCard(p2,
//            "Camera: " + p2.getCameraMp() + "MP" +
//            "  |  Battery: " + p2.getBatteryDetails() + "mAh" +
//            "  |  Brand: " + p2.getBrand()), 1, 0);
//
//        tab.setContent(new ScrollPane(grid));
//        return tab;
//    }
//
//    // ----------------------------------------------------------
//    //  TAB 4 – Software Licenses
//    // ----------------------------------------------------------
//    private Tab buildSoftwareTab() {
//
//        Tab tab = new Tab("Software License");
//
//        GridPane grid = new GridPane();
//        grid.setPadding(new Insets(20));
//        grid.setHgap(20);
//        grid.setVgap(20);
//
//        grid.add(buildCard(s1,
//            "Key: " + s1.getLicenseKey() +
//            "  |  Expires: " + s1.getExpiryDate() +
//            "  |  Active: " + (s1.activateLicense() ? "Yes" : "Expired")), 0, 0);
//
//        grid.add(buildCard(s2,
//            "Key: " + s2.getLicenseKey() +
//            "  |  Expires: " + s2.getExpiryDate() +
//            "  |  Active: " + (s2.activateLicense() ? "Yes" : "Expired")), 1, 0);
//
//        tab.setContent(new ScrollPane(grid));
//        return tab;
//    }
//
//    // ----------------------------------------------------------
//    //  TAB 5 – Digital Downloads
//    // ----------------------------------------------------------
//    private Tab buildDigitalTab() {
//
//        Tab tab = new Tab("Digital Download");
//
//        GridPane grid = new GridPane();
//        grid.setPadding(new Insets(20));
//        grid.setHgap(20);
//        grid.setVgap(20);
//
//        grid.add(buildCard(d1,
//            "Format: " + d1.getFormat() +
//            "  |  Downloads left: " + d1.getDownloadLimit() +
//            "  |  Size: " + d1.getFileSize() + " MB"), 0, 0);
//
//        grid.add(buildCard(d2,
//            "Format: " + d2.getFormat() +
//            "  |  Downloads left: " + d2.getDownloadLimit() +
//            "  |  Size: " + d2.getFileSize() + " MB"), 1, 0);
//
//        tab.setContent(new ScrollPane(grid));
//        return tab;
//    }
//
//    // ==========================================================
//    //  PRODUCT CARD  –  VBox  (name / price / specs / button)
//    // ==========================================================
//    private VBox buildCard(Product product, String specs) {
//
//        VBox card = new VBox(10);
//        card.setPadding(new Insets(15));
//        card.setPrefWidth(280);
//        card.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1;" +
//                      "-fx-border-radius: 8; -fx-background-color: #f9f9f9;" +
//                      "-fx-background-radius: 8;");
//
//        // Name
//        Label nameLabel = new Label(product.getName());
//        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
//
//        // Price
//        Label priceLabel = new Label(String.format("Final Price: $%.2f", product.getFinalPrice()));
//        priceLabel.setStyle("-fx-text-fill: green; -fx-font-size: 14px;");
//
//        // Original Price
//        Label origLabel = new Label(String.format("Original: $%.2f", product.getPrice()));
//        origLabel.setStyle("-fx-text-fill: grey; -fx-font-size: 12px;");
//
//        // Description
//        Label descLabel = new Label(product.getDescription());
//        descLabel.setWrapText(true);
//        descLabel.setStyle("-fx-font-size: 12px;");
//
//        // Specs
//        Label specsLabel = new Label(specs);
//        specsLabel.setWrapText(true);
//        specsLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #555555;" +
//                            "-fx-background-color: #eeeeee; -fx-padding: 5;" +
//                            "-fx-background-radius: 5;");
//
//        // Separator
//        Separator sep = new Separator();
//
//        // Quantity spinner
//        Label qtyLabel = new Label("Quantity:");
//        Spinner<Integer> spinner = new Spinner<>(1, 10, 1);
//        spinner.setPrefWidth(75);
//        HBox qtyBox = new HBox(8, qtyLabel, spinner);
//        qtyBox.setAlignment(Pos.CENTER_LEFT);
//
//        // Discount combo
//        Label discLabel = new Label("Discount:");
//        ComboBox<String> discCombo = new ComboBox<>();
//        discCombo.getItems().addAll("None", "Flat $20", "10% Off", "20% Off");
//        discCombo.setValue("None");
//        discCombo.setPrefWidth(110);
//
//        discCombo.setOnAction(e -> {
//            switch (discCombo.getValue()) {
//                case "Flat $20" -> product.setDiscount(new FlatDiscount(20));
//                case "10% Off"  -> product.setDiscount(new PercentageDiscount(10));
//                case "20% Off"  -> product.setDiscount(new PercentageDiscount(20));
//                default         -> product.setDiscount(null);
//            }
//            priceLabel.setText(String.format("Final Price: $%.2f", product.getFinalPrice()));
//        });
//
//        HBox discBox = new HBox(8, discLabel, discCombo);
//        discBox.setAlignment(Pos.CENTER_LEFT);
//
//        // Add to Cart Button
//        Button addBtn = new Button("Add to Cart");
//        addBtn.setPrefWidth(Double.MAX_VALUE);
//        addBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;" +
//                        "-fx-font-size: 13px; -fx-font-weight: bold;" +
//                        "-fx-background-radius: 6;");
//
//        addBtn.setOnAction(e -> {
//            int qty = spinner.getValue();
//            boolean ok = cart.addProduct(product, qty);
//            if (ok) {
//                refreshCart();
//                showMessage("'" + product.getName() + "'  added to cart  (x" + qty + ")");
//            } else {
//                showMessage("Cart is full!");
//            }
//        });
//
//        card.getChildren().addAll(
//            nameLabel, priceLabel, origLabel,
//            descLabel, specsLabel,
//            sep,
//            qtyBox, discBox,
//            addBtn
//        );
//
//        return card;
//    }
//
//    // ==========================================================
//    //  RIGHT  –  Cart Panel
//    // ==========================================================
//    private VBox buildCartPanel() {
//
//        VBox panel = new VBox(12);
//        panel.setPrefWidth(280);
//        panel.setPadding(new Insets(15));
//        panel.setStyle("-fx-background-color: #ecf0f1;" +
//                       "-fx-border-color: #cccccc; -fx-border-width: 0 0 0 1;");
//
//        // Title
//        Label title = new Label("Shopping Cart");
//        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
//
//        // Cart text area (read-only list of items)
//        cartArea.setPrefHeight(260);
//        cartArea.setEditable(false);
//        cartArea.setPromptText("Cart is empty...");
//        cartArea.setStyle("-fx-font-size: 12px; -fx-font-family: monospace;");
//
//        // Separator
//        Separator sep = new Separator();
//
//        // Total
//        totalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
//        totalLabel.setStyle("-fx-text-fill: green;");
//
//        // GridPane for control buttons (2 columns)
//        GridPane btnGrid = new GridPane();
//        btnGrid.setHgap(10);
//        btnGrid.setVgap(10);
//
//        // Remove by ID
//        Label removeLabel = new Label("Product ID to remove:");
//        TextField removeField = new TextField();
//        removeField.setPromptText("Enter ID...");
//        Button removeBtn = new Button("Remove");
//        removeBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;" +
//                           "-fx-font-weight: bold; -fx-background-radius: 5;");
//        removeBtn.setOnAction(e -> {
//            try {
//                int id = Integer.parseInt(removeField.getText().trim());
//                boolean ok = cart.removeProduct(id);
//                refreshCart();
//                showMessage(ok ? "Product removed." : "ID not found in cart.");
//                removeField.clear();
//            } catch (NumberFormatException ex) {
//                showMessage("Please enter a valid numeric ID.");
//            }
//        });
//
//        btnGrid.add(removeLabel, 0, 0, 2, 1);
//        btnGrid.add(removeField, 0, 1);
//        btnGrid.add(removeBtn,   1, 1);
//
//        // Clear Cart button
//        Button clearBtn = new Button("Clear Cart");
//        clearBtn.setMaxWidth(Double.MAX_VALUE);
//        clearBtn.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white;" +
//                          "-fx-font-weight: bold; -fx-background-radius: 5;");
//        clearBtn.setOnAction(e -> {
//            cart.clearCart();
//            refreshCart();
//            showMessage("Cart cleared.");
//        });
//
//        // Checkout button
//        Button checkoutBtn = new Button("Checkout");
//        checkoutBtn.setMaxWidth(Double.MAX_VALUE);
//        checkoutBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white;" +
//                             "-fx-font-size: 14px; -fx-font-weight: bold;" +
//                             "-fx-background-radius: 5;");
//        checkoutBtn.setOnAction(e -> {
//            if (cart.isEmpty()) {
//                showMessage("Cart is empty! Add products first.");
//                return;
//            }
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Checkout");
//            alert.setHeaderText("Order Confirmed!");
//            alert.setContentText(String.format(
//                "Items: %d\nTotal: $%.2f\n\nThank you for your purchase!",
//                cart.getSize(), cart.calculateTotal()));
//            alert.showAndWait();
//            cart.clearCart();
//            refreshCart();
//            showMessage("Order placed successfully.");
//        });
//
//        panel.getChildren().addAll(
//            title, cartArea, sep,
//            totalLabel, btnGrid,
//            clearBtn, checkoutBtn
//        );
//
//        return panel;
//    }
//
//    // ==========================================================
//    //  BOTTOM  –  Message Bar
//    // ==========================================================
//    private HBox buildBottomBar() {
//
//        msgLabel.setStyle("-fx-text-fill: #2c3e50; -fx-font-size: 12px;");
//
//        HBox bar = new HBox(msgLabel);
//        bar.setPadding(new Insets(6, 16, 6, 16));
//        bar.setStyle("-fx-background-color: #dde3ea;");
//        bar.setAlignment(Pos.CENTER_LEFT);
//
//        return bar;
//    }
//
//    // ==========================================================
//    //  HELPERS
//    // ==========================================================
//
//    /** Refresh the cart TextArea and total label */
//    private void refreshCart() {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < cart.getSize(); i++) {
//            CartItem ci = cart.getItems()[i];
//            sb.append(String.format(
//                "[ID:%d] %-18s x%d = $%.2f%n",
//                ci.getProduct().getId(),
//                ci.getProduct().getName(),
//                ci.getQuantity(),
//                ci.getSubTotal()
//            ));
//        }
//        cartArea.setText(sb.toString());
//        totalLabel.setText(String.format("Total: $%.2f", cart.calculateTotal()));
//    }
//
//    private void showMessage(String text) {
//        msgLabel.setText("  " + text);
//    }
//
//    // ==========================================================
//    //  SAMPLE PRODUCTS  (same as original Main.java)
//    // ==========================================================
//    private void buildProducts() {
//
//        // Clothing
//        c1 = new Clothing("M","Black","Cotton",0.5,50,"Nike",
//                101,"T-Shirt",299.99,null,"Comfortable cotton t-shirt",20);
//        c2 = new Clothing("L","Blue","Denim",1.2,80,"Levi's",
//                102,"Jeans",799.99,null,"Classic blue denim jeans",15);
//
//        // Laptops
//        l1 = new Laptops(2,65,1.8,20,"Dell",1,"XPS 15",
//                1500,null,"High performance laptop",10,
//                "16GB","512GB SSD","Intel i7");
//        l2 = new Laptops(1,60,1.6,25,"Apple",2,"MacBook Pro",
//                2500,null,"Apple laptop",5,
//                "16GB","1TB SSD","M2 Pro");
//
//        // Phones
//        p1 = new Phones(2,15,0.18,10,"Samsung",201,"Galaxy S24",
//                1200,null,"Flagship phone",20,50,5000);
//        p2 = new Phones(1,12,0.17,12,"Apple",202,"iPhone 15",
//                1300,null,"Apple phone",12,48,4500);
//
//        // Software Licenses
//        s1 = new SoftwareLicense("ABC123-XYZ789",LocalDate.of(2027,5,14),
//                2.5,"https://download.com/software1",
//                301,"Windows Antivirus",49.99,null,"Premium antivirus software",100);
//        s2 = new SoftwareLicense("QWE456-RTY111",LocalDate.of(2026,12,31),
//                1.2,"https://download.com/software2",
//                302,"Photo Editor Pro",79.99,null,"Professional photo editing",50);
//
//        // Digital Downloads
//        d1 = new DigitalDownload("MP3",5,120.5,
//                "https://download.com/music_album",
//                401,"Top Hits Album",19.99,null,"Popular music collection",200);
//        d2 = new DigitalDownload("PDF",3,15.2,
//                "https://download.com/java_book",
//                402,"Java Programming Guide",29.99,null,"Complete Java ebook",80);
//    }
//}
