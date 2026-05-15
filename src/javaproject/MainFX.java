package javaproject;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX GUI — E-Commerce Store
 * Built on top of the existing OOP layer (Product, Cart, CartItem …)
 * Package: javaproject
 */
public class MainFX extends Application {

    // ── OOP LAYER ────────────────────────────────────────────────────────
    private final ShoppingCart cart = new ShoppingCart(50);
    private final List<Product> allProducts  = new ArrayList<>();

    // ── UI STATE ─────────────────────────────────────────────────────────
    private String       currentCategory = "Home";
    private TextField    searchField;
    private FlowPane     productGrid;
    private Label        categoryTitle;
    private VBox         cartItemsBox;
    private Label        totalItemsLabel;
    private Label        totalPriceLabel;
    private Label        statusLabel;
    private final List<Button> navButtons = new ArrayList<>();

    // ── ENTRY POINT ──────────────────────────────────────────────────────
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage stage) {
        initProducts();

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f0f4f8;");
        root.setLeft(buildSidebar());
        root.setCenter(buildCenter());
        root.setRight(buildCartPanel());
        root.setBottom(buildStatusBar());

        stage.setScene(new Scene(root, 1150, 720));
        stage.setTitle("HeXa-Store");
        stage.show();
    }

    // ════════════════════════════════════════════════════════════════════
    // INIT PRODUCTS  (same objects as Main.java)
    // ════════════════════════════════════════════════════════════════════
    private void initProducts() {

        // ── Clothing ──────────────────────────────────────────────────
        allProducts.add(new Clothing(
            "M","Black","Cotton", 0.5, 50.0, "Nike",
            101, "T-Shirt", 299.99,
            null, "Comfortable cotton t-shirt", 20));

        allProducts.add(new Clothing(
            "L","Blue","Denim", 1.2, 80.0, "Levi's",
            102, "Jeans", 799.99,
            null, "Classic blue denim jeans", 15));

        // ── Laptops ───────────────────────────────────────────────────
        allProducts.add(new Laptops(
            2, 65, 1.8, 20, "Dell",
            201, "Dell XPS 15", 1500,
            null, "High performance laptop", 10,
            "16GB", "512GB SSD", "Intel i7"));

        allProducts.add(new Laptops(
            1, 60, 1.6, 25, "Apple",
            202, "MacBook Pro", 2500,
            null, "Premium Apple laptop", 5,
            "16GB", "1TB SSD", "M2 Pro"));

        // ── Phones ────────────────────────────────────────────────────
        allProducts.add(new Phones(
            2, 15, 0.18, 10, "Samsung",
            301, "Galaxy S24", 1200,
            null, "Flagship Samsung phone", 20, 50, 5000));

        allProducts.add(new Phones(
            1, 12, 0.17, 12, "Apple",
            302, "iPhone 15", 1300,
            null, "Latest Apple iPhone", 12, 48, 4500));

        // ── Software License ──────────────────────────────────────────
        allProducts.add(new SoftwareLicense(
            "ABC123-XYZ789", LocalDate.of(2027, 5, 14),
            2.5, "https://download.com/software1",
            401, "Windows Antivirus", 49.99,
            null, "Premium antivirus software", 100));

        allProducts.add(new SoftwareLicense(
            "QWE456-RTY111", LocalDate.of(2026, 12, 31),
            1.2, "https://download.com/software2",
            402, "Photo Editor Pro", 79.99,
            null, "Professional photo editing", 50));

        // ── Digital Download ──────────────────────────────────────────
        allProducts.add(new DigitalDownload(
            "MP3", 5, 120.5, "https://download.com/music",
            501, "Top Hits Album", 19.99,
            null, "Popular music collection", 200));

        allProducts.add(new DigitalDownload(
            "PDF", 3, 15.2, "https://download.com/java",
            502, "Java Programming Guide", 29.99,
            null, "Complete Java learning ebook", 80));
    }

    // ════════════════════════════════════════════════════════════════════
    // SIDEBAR
    // ════════════════════════════════════════════════════════════════════
    private VBox buildSidebar() {
        VBox sidebar = new VBox();
        sidebar.setPrefWidth(205);
        sidebar.setStyle("-fx-background-color: white;"
                       + "-fx-border-color: #e0e0e0;"
                       + "-fx-border-width: 0 1 0 0;");

        // ── Logo ──────────────────────────────────────────────────────
        VBox logoBox = new VBox(4);
        logoBox.setPadding(new Insets(20, 15, 20, 15));

        Label cartIcon = new Label("\uD83D\uDED2");   // 🛒
        cartIcon.setStyle("-fx-font-size: 28px;");

        Label storeName = new Label("  HeXa  \n  Store");
        storeName.setStyle("-fx-font-size: 16px;"
                         + "-fx-font-weight: bold;"
                         + "-fx-text-fill: #1565C0;");

        logoBox.getChildren().addAll(cartIcon, storeName);

        Separator sep = new Separator();
        VBox.setMargin(sep, new Insets(0));

        // ── Navigation ────────────────────────────────────────────────
        VBox navBox = new VBox(4);
        navBox.setPadding(new Insets(10));

        String[][] navItems = {
            {"\uD83C\uDFE0", "Home"},            // 🏠
            {"\uD83D\uDC55", "Clothing"},        // 👕
            {"\uD83D\uDCBB", "Laptops"},         // 💻
            {"\uD83D\uDCF1", "Phones"},          // 📱
            {"\uD83D\uDCBE", "Digital Products"} // 💾
        };

        for (String[] item : navItems) {
            Button btn = new Button(item[0] + "   " + item[1]);
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setAlignment(Pos.CENTER_LEFT);
            btn.setPadding(new Insets(10, 14, 10, 14));
            btn.setStyle(navStyle(false));
            navButtons.add(btn);

            String cat = item[1];
            btn.setOnAction(e -> {
                currentCategory = cat;
                setActiveNav(btn);
                refreshProducts();
            });
            navBox.getChildren().add(btn);
        }
        setActiveNav(navButtons.get(0));   // Home active by default

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // ── Clear Cart ────────────────────────────────────────────────
        Button clearBtn = new Button("\uD83D\uDDD1   Clear Cart");   // 🗑
        clearBtn.setMaxWidth(Double.MAX_VALUE);
        clearBtn.setPadding(new Insets(10, 14, 10, 14));
        clearBtn.setStyle("-fx-background-color: white;"
                        + "-fx-border-color: #bdbdbd;"
                        + "-fx-border-radius: 5;"
                        + "-fx-background-radius: 5;"
                        + "-fx-cursor: hand;"
                        + "-fx-font-size: 13px;");

        clearBtn.setOnAction(e -> { cart.clearCart(); refreshCart(); });

        VBox clearBox = new VBox(clearBtn);
        clearBox.setPadding(new Insets(8, 10, 20, 10));

        sidebar.getChildren().addAll(logoBox, sep, navBox, spacer, clearBox);
        return sidebar;
    }

    private String navStyle(boolean active) {
        return active
            ? "-fx-background-color: #1565C0; -fx-text-fill: white;"
            + "-fx-font-size: 13px; -fx-background-radius: 6; -fx-cursor: hand;"
            : "-fx-background-color: transparent; -fx-text-fill: #424242;"
            + "-fx-font-size: 13px; -fx-background-radius: 6; -fx-cursor: hand;";
    }

    private void setActiveNav(Button active) {
        navButtons.forEach(b -> b.setStyle(navStyle(b == active)));
    }

    // ════════════════════════════════════════════════════════════════════
    // CENTER  (Search + Product Grid)
    // ════════════════════════════════════════════════════════════════════
    private ScrollPane buildCenter() {
        VBox centerBox = new VBox(15);
        centerBox.setPadding(new Insets(20));
        centerBox.setStyle("-fx-background-color: #f0f4f8;");

        // ── Search bar ────────────────────────────────────────────────
        HBox searchBox = new HBox(10);

        searchField = new TextField();
        searchField.setPromptText("Search products...");
        searchField.setPrefHeight(38);
        searchField.setStyle("-fx-background-radius: 4;"
                           + "-fx-border-radius: 4;"
                           + "-fx-border-color: #bdbdbd;"
                           + "-fx-font-size: 13px;");
        HBox.setHgrow(searchField, Priority.ALWAYS);

        Button searchBtn = new Button("Search");
        searchBtn.setPrefHeight(38);
        searchBtn.setStyle("-fx-background-color: white;"
                         + "-fx-border-color: #bdbdbd;"
                         + "-fx-border-radius: 4;"
                         + "-fx-background-radius: 4;"
                         + "-fx-cursor: hand;"
                         + "-fx-font-size: 13px;");

        searchBtn.setOnAction(e -> refreshProducts());
        searchField.setOnAction(e -> refreshProducts());

        searchBox.getChildren().addAll(searchField, searchBtn);

        // ── Section title ─────────────────────────────────────────────
        categoryTitle = new Label("All Products");
        categoryTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // ── Product grid ──────────────────────────────────────────────
        productGrid = new FlowPane();
        productGrid.setHgap(12);
        productGrid.setVgap(12);
        productGrid.setPrefWrapLength(660);

        centerBox.getChildren().addAll(searchBox, categoryTitle, productGrid);
        refreshProducts();

        ScrollPane scroll = new ScrollPane(centerBox);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: #f0f4f8; -fx-background: #f0f4f8;");
        return scroll;
    }

    // ── Product Card ─────────────────────────────────────────────────────
    private VBox buildProductCard(Product p) {
        VBox card = new VBox(8);
        card.setPadding(new Insets(15));
        card.setPrefWidth(170);
        card.setAlignment(Pos.CENTER);
        card.setStyle("-fx-background-color: white;"
                    + "-fx-background-radius: 8;"
                    + "-fx-border-color: #e0e0e0;"
                    + "-fx-border-radius: 8;"
                    + "-fx-effect: dropshadow(gaussian,rgba(0,0,0,0.05),6,0,0,2);");

        // Icon
        Label icon = new Label(productIcon(p));
        icon.setStyle("-fx-font-size: 56px;");

        // Name
        Label name = new Label(p.name);
        name.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #212121;");
        name.setWrapText(true);
        name.setTextAlignment(TextAlignment.CENTER);
        name.setMaxWidth(140);

        // Price  — display base price to match screenshot
        Label price = new Label(String.format("$%.2f", p.price));
        price.setStyle("-fx-font-size: 13px; -fx-text-fill: #424242;");

        // Add to Cart button
        Button addBtn = new Button("Add to Cart");
        addBtn.setMaxWidth(Double.MAX_VALUE);
        addBtn.setStyle("-fx-background-color: white;"
                      + "-fx-border-color: #1565C0;"
                      + "-fx-text-fill: #1565C0;"
                      + "-fx-border-radius: 4;"
                      + "-fx-background-radius: 4;"
                      + "-fx-cursor: hand;"
                      + "-fx-font-size: 12px;");

        addBtn.setOnAction(e -> { cart.addProduct(p, 1); refreshCart(); });

        // Hover effect
        addBtn.setOnMouseEntered(e -> addBtn.setStyle(
            "-fx-background-color: #1565C0; -fx-text-fill: white;"
          + "-fx-border-radius: 4; -fx-background-radius: 4;"
          + "-fx-cursor: hand; -fx-font-size: 12px;"));
        addBtn.setOnMouseExited(e -> addBtn.setStyle(
            "-fx-background-color: white; -fx-border-color: #1565C0;"
          + "-fx-text-fill: #1565C0; -fx-border-radius: 4;"
          + "-fx-background-radius: 4; -fx-cursor: hand; -fx-font-size: 12px;"));

        card.getChildren().addAll(icon, name, price, addBtn);
        return card;
    }

    /** Map each product type to an emoji icon */
    private String productIcon(Product p) {
        if (p instanceof Clothing c) {
            return c.name.toLowerCase().contains("jean") ? "\uD83D\uDC56" : "\uD83D\uDC55"; // 👖 👕
        }
        if (p instanceof Laptops)  return "\uD83D\uDCBB"; // 💻
        if (p instanceof Phones ph) {
            return ph.brand.equalsIgnoreCase("Apple") ? "\uD83D\uDCF1" : "\uD83D\uDCF1"; // 📱
        }
        if (p instanceof SoftwareLicense s) {
            return s.name.contains("Photo") ? "\uD83D\uDDBC" : "\uD83D\uDEE1"; // 🖼 🛡
        }
        if (p instanceof DigitalDownload d) {
            return d.format.equals("MP3") ? "\uD83C\uDFB5" : "\uD83D\uDCDA"; // 🎵 📚
        }
        return "\uD83D\uDCE6"; // 📦
    }

    /** Filter products by category + search, then rebuild the grid */
    private void refreshProducts() {
        productGrid.getChildren().clear();

        String query = searchField == null
            ? "" : searchField.getText().toLowerCase().trim();

        boolean anyFound = false;
        for (Product p : allProducts) {
            if (!matchesCategory(p)) continue;
            if (!query.isEmpty() && !p.name.toLowerCase().contains(query)) continue;
            productGrid.getChildren().add(buildProductCard(p));
            anyFound = true;
        }

        categoryTitle.setText(currentCategory.equals("Home") ? "All Products" : currentCategory);

        if (!anyFound) {
            Label none = new Label("No products found.");
            none.setStyle("-fx-text-fill: #9e9e9e; -fx-font-size: 14px;");
            productGrid.getChildren().add(none);
        }
    }

    /** instanceof polymorphism — leverages the OOP class hierarchy */
    private boolean matchesCategory(Product p) {
        return switch (currentCategory) {
            case "Clothing"          -> p instanceof Clothing;
            case "Laptops"           -> p instanceof Laptops;
            case "Phones"            -> p instanceof Phones;
            case "Digital Products"  -> p instanceof DigitalProduct;
            default                  -> true;   // "Home" shows all
        };
    }

    // ════════════════════════════════════════════════════════════════════
    // CART PANEL
    // ════════════════════════════════════════════════════════════════════
    private VBox buildCartPanel() {
        VBox panel = new VBox();
        panel.setPrefWidth(285);
        panel.setPadding(new Insets(15));
        panel.setStyle("-fx-background-color: white;"
                     + "-fx-border-color: #e0e0e0;"
                     + "-fx-border-width: 0 0 0 1;");

        // Title
        Label title = new Label("Shopping Cart");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        VBox.setMargin(title, new Insets(0, 0, 8, 0));

        // Column headers
        HBox header = buildCartHeader();
        Separator sep1 = new Separator();

        // Cart items (scrollable)
        cartItemsBox = new VBox(4);
        ScrollPane cartScroll = new ScrollPane(cartItemsBox);
        cartScroll.setFitToWidth(true);
        cartScroll.setPrefHeight(340);
        cartScroll.setStyle("-fx-background-color: transparent; -fx-background: transparent;");
        cartScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        cartScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        VBox.setVgrow(cartScroll, Priority.ALWAYS);

        Separator sep2 = new Separator();
        VBox.setMargin(sep2, new Insets(10, 0, 10, 0));

        // Totals
        HBox rowItems = buildTotalRow("Total Items:", false);
        totalItemsLabel = (Label) rowItems.getChildren().get(1);

        HBox rowPrice = buildTotalRow("Total Price:", true);
        totalPriceLabel = (Label) rowPrice.getChildren().get(1);

        // Checkout button
        Button checkoutBtn = new Button("Checkout");
        checkoutBtn.setMaxWidth(Double.MAX_VALUE);
        checkoutBtn.setPrefHeight(42);
        checkoutBtn.setStyle("-fx-background-color: #388E3C;"
                           + "-fx-text-fill: white;"
                           + "-fx-font-size: 14px;"
                           + "-fx-font-weight: bold;"
                           + "-fx-background-radius: 6;"
                           + "-fx-cursor: hand;");
        VBox.setMargin(checkoutBtn, new Insets(12, 0, 0, 0));

        checkoutBtn.setOnAction(e -> {
            if (cart.isEmpty()) {
                showAlert("Cart Empty", "Please add items before checking out.");
            } else {
                showAlert("Order Placed!",
                    "Thank you for your purchase!\n"
                    + "Total: " + totalPriceLabel.getText()
                    + "  (" + totalItemsLabel.getText() + " items)");
                cart.clearCart();
                refreshCart();
            }
        });

        panel.getChildren().addAll(
            title, header, sep1,
            cartScroll,
            sep2, rowItems, rowPrice, checkoutBtn
        );

        refreshCart();
        return panel;
    }

    private HBox buildCartHeader() {
        HBox h = new HBox();
        h.setPadding(new Insets(0, 0, 6, 0));

        Label hProd = new Label("Product");
        hProd.setStyle("-fx-font-size: 12px; -fx-text-fill: #757575;");
        HBox.setHgrow(hProd, Priority.ALWAYS);

        Label hQty = new Label("Qty");
        hQty.setPrefWidth(32);
        hQty.setStyle("-fx-font-size: 12px; -fx-text-fill: #757575;");

        Label hSub = new Label("SubTotal");
        hSub.setPrefWidth(72);
        hSub.setStyle("-fx-font-size: 12px; -fx-text-fill: #757575;");

        h.getChildren().addAll(hProd, hQty, hSub);
        return h;
    }

    private HBox buildTotalRow(String labelText, boolean blue) {
        HBox row = new HBox();
        row.setPadding(new Insets(3, 0, 3, 0));

        Label lbl = new Label(labelText);
        lbl.setStyle("-fx-font-size: 13px; -fx-font-weight: bold;");
        HBox.setHgrow(lbl, Priority.ALWAYS);

        String style = blue
            ? "-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #1565C0;"
            : "-fx-font-size: 13px; -fx-font-weight: bold;";

        Label val = new Label(blue ? "$0.00" : "0");
        val.setStyle(style);

        row.getChildren().addAll(lbl, val);
        return row;
    }

    // ── Cart Item Row ─────────────────────────────────────────────────────
    private HBox buildCartItemRow(CartItem item) {
        HBox row = new HBox(5);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(5, 0, 5, 0));

        Label name = new Label(item.getProduct().name);
        name.setStyle("-fx-font-size: 12px;");
        name.setWrapText(true);
        name.setMaxWidth(110);
        HBox.setHgrow(name, Priority.ALWAYS);

        Label qty = new Label(String.valueOf(item.getQuantity()));
        qty.setPrefWidth(30);
        qty.setStyle("-fx-font-size: 12px;");

        // Display base price × qty  (matches screenshot)
        double sub = item.getProduct().price * item.getQuantity();
        Label subTotalLbl = new Label(String.format("$%.2f", sub));
        subTotalLbl.setPrefWidth(65);
        subTotalLbl.setStyle("-fx-font-size: 12px;");

        Button removeBtn = new Button("X");
        removeBtn.setStyle("-fx-background-color: #FFEBEE;"
                         + "-fx-text-fill: #c62828;"
                         + "-fx-font-weight: bold;"
                         + "-fx-background-radius: 4;"
                         + "-fx-cursor: hand;"
                         + "-fx-font-size: 10px;");
        removeBtn.setPrefSize(24, 24);
        removeBtn.setOnAction(e -> {
            cart.removeProduct(item.getProduct().id);
            refreshCart();
        });

        row.getChildren().addAll(name, qty, subTotalLbl, removeBtn);
        return row;
    }

    /** Rebuild cart panel contents and update totals */
    public void refreshCart() {
        cartItemsBox.getChildren().clear();

        int totalQty   = 0;
        double totalPrice = 0;

        for (int i = 0; i < cart.getSize(); i++) {
            CartItem item = cart.getItems()[i];
            cartItemsBox.getChildren().add(buildCartItemRow(item));
            totalQty   += item.getQuantity();
            totalPrice += item.getProduct().price * item.getQuantity();
        }

        totalItemsLabel.setText(String.valueOf(totalQty));
        totalPriceLabel.setText(String.format("$%.2f", totalPrice));

        if (statusLabel != null)
            statusLabel.setText("Total Products in Cart: " + totalQty);
    }

    // ════════════════════════════════════════════════════════════════════
    // STATUS BAR
    // ════════════════════════════════════════════════════════════════════
    private HBox buildStatusBar() {
        HBox bar = new HBox();
        bar.setPadding(new Insets(8, 15, 8, 15));
        bar.setStyle("-fx-background-color: #e3f2fd;"
                   + "-fx-border-color: #bbdefb;"
                   + "-fx-border-width: 1 0 0 0;");

        Label welcome = new Label("Welcome to our store!");
        welcome.setStyle("-fx-font-size: 12px;");
        HBox.setHgrow(welcome, Priority.ALWAYS);

        statusLabel = new Label("Total Products in Cart: 0");
        statusLabel.setStyle("-fx-font-size: 12px;");

        bar.getChildren().addAll(welcome, statusLabel);
        return bar;
    }

    // ════════════════════════════════════════════════════════════════════
    // HELPERS
    // ════════════════════════════════════════════════════════════════════
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}