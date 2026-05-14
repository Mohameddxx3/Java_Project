package javaproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class MainFX extends Application {

    // ── Data ──────────────────────────────────────────────────────────────────

    private final Clothing c1 = new Clothing("M", "Black", "Cotton", 0.5, 50.0,
            "Nike", 101, "T-Shirt", 299.99, null, "Comfortable cotton t-shirt", 20);

    private final Clothing c2 = new Clothing("L", "Blue", "Denim", 1.2, 80.0,
            "Levi's", 102, "Jeans", 799.99, null, "Classic blue denim jeans", 15);

    private final Laptops laptop1 = new Laptops(2, 65, 1.8, 20, "Dell", 1, "XPS 15",
            1500, null, "High performance laptop", 10, "16GB", "512GB SSD", "Intel i7");

    private final Laptops laptop2 = new Laptops(1, 60, 1.6, 25, "Apple", 2, "MacBook Pro",
            2500, null, "Apple flagship laptop", 5, "16GB", "1TB SSD", "M2 Pro");

    private final Phones phone1 = new Phones(2, 15, 0.18, 10, "Samsung", 101, "Galaxy S24",
            1200, null, "Android flagship phone", 20, 50, 5000);

    private final Phones phone2 = new Phones(1, 12, 0.17, 12, "Apple", 102, "iPhone 15",
            1300, null, "Apple flagship phone", 12, 48, 4500);

    // ── State ─────────────────────────────────────────────────────────────────

    private VBox productList;
    private VBox detailPanel;
    private Label detailTitle;
    private VBox detailFields;
    private Label placeholderLabel;
    private String activeCategory = "";

    // ── Colors ────────────────────────────────────────────────────────────────

    private static final String BG_DARK      = "#0f1117";
    private static final String BG_SIDEBAR   = "#16181f";
    private static final String BG_CARD      = "#1c1e28";
    private static final String BG_CARD_HOV  = "#23263a";
    private static final String BG_DETAIL    = "#1c1e28";
    private static final String ACCENT       = "#6c63ff";
    private static final String ACCENT_DIM   = "#3d3880";
    private static final String TEXT_PRIMARY  = "#e8eaf0";
    private static final String TEXT_SECONDARY= "#8b8fa8";
    private static final String TEXT_MUTED    = "#4a4e6a";
    private static final String BORDER        = "#2a2d3e";

    // ── Entry ─────────────────────────────────────────────────────────────────

    @Override
    public void start(Stage stage) {

        // ── Root layout ───────────────────────────────────────────────────────
        HBox root = new HBox();
        root.setStyle("-fx-background-color:" + BG_DARK + ";");

        // ── Sidebar ───────────────────────────────────────────────────────────
        VBox sidebar = buildSidebar();
        sidebar.setPrefWidth(200);

        // ── Divider ───────────────────────────────────────────────────────────
        Region div1 = new Region();
        div1.setPrefWidth(1);
        div1.setStyle("-fx-background-color:" + BORDER + ";");

        // ── Product list ──────────────────────────────────────────────────────
        productList = new VBox(12);
        productList.setPadding(new Insets(20));
        productList.setStyle("-fx-background-color:" + BG_DARK + ";");

        ScrollPane listScroll = new ScrollPane(productList);
        listScroll.setFitToWidth(true);
        listScroll.setPrefWidth(320);
        listScroll.setStyle(
            "-fx-background-color:" + BG_DARK + ";" +
            "-fx-background:" + BG_DARK + ";" +
            "-fx-border-color:transparent;"
        );
        listScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // placeholder when nothing is selected
        placeholderLabel = new Label("← Choose a category");
        placeholderLabel.setStyle(
            "-fx-text-fill:" + TEXT_MUTED + ";" +
            "-fx-font-size:14px;" +
            "-fx-font-family:'Courier New';"
        );
        placeholderLabel.setPadding(new Insets(40, 20, 0, 20));
        productList.getChildren().add(placeholderLabel);

        // ── Divider 2 ─────────────────────────────────────────────────────────
        Region div2 = new Region();
        div2.setPrefWidth(1);
        div2.setStyle("-fx-background-color:" + BORDER + ";");

        // ── Detail panel ──────────────────────────────────────────────────────
        detailPanel = buildDetailPanel();
        HBox.setHgrow(detailPanel, Priority.ALWAYS);

        root.getChildren().addAll(sidebar, div1, listScroll, div2, detailPanel);

        Scene scene = new Scene(root, 960, 620);
        stage.setTitle("Product Viewer");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    // ── Sidebar ───────────────────────────────────────────────────────────────

    private VBox buildSidebar() {
        VBox sb = new VBox();
        sb.setStyle("-fx-background-color:" + BG_SIDEBAR + ";");
        sb.setAlignment(Pos.TOP_CENTER);

        // Logo / header
        VBox header = new VBox(4);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(28, 20, 24, 20));

        Label appTitle = new Label("STORE");
        appTitle.setStyle(
            "-fx-text-fill:" + TEXT_PRIMARY + ";" +
            "-fx-font-size:20px;" +
            "-fx-font-weight:bold;" +
            "-fx-font-family:'Courier New';" +
            "-fx-letter-spacing:4;"
        );

        Label appSub = new Label("Product Viewer");
        appSub.setStyle(
            "-fx-text-fill:" + TEXT_MUTED + ";" +
            "-fx-font-size:11px;" +
            "-fx-font-family:'Courier New';"
        );

        // Accent underline
        Region underline = new Region();
        underline.setPrefHeight(2);
        underline.setPrefWidth(40);
        underline.setStyle("-fx-background-color:" + ACCENT + ";");
        underline.setTranslateY(4);

        header.getChildren().addAll(appTitle, appSub, underline);

        // Section label
        Label catLabel = new Label("CATEGORIES");
        catLabel.setPadding(new Insets(10, 20, 6, 20));
        catLabel.setStyle(
            "-fx-text-fill:" + TEXT_MUTED + ";" +
            "-fx-font-size:10px;" +
            "-fx-font-family:'Courier New';" +
            "-fx-font-weight:bold;"
        );

        // Nav buttons
        StackPane btnClothing = navButton("👕  Clothing",  "Clothing");
        StackPane btnLaptops  = navButton("💻  Laptops",   "Laptops");
        StackPane btnPhones   = navButton("📱  Phones",    "Phones");

        // Footer
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Label footer = new Label("v1.0.0");
        footer.setPadding(new Insets(0, 0, 16, 20));
        footer.setStyle(
            "-fx-text-fill:" + TEXT_MUTED + ";" +
            "-fx-font-size:10px;" +
            "-fx-font-family:'Courier New';"
        );

        sb.getChildren().addAll(header, catLabel, btnClothing, btnLaptops, btnPhones, spacer, footer);
        return sb;
    }

    private StackPane navButton(String label, String category) {
        Label lbl = new Label(label);
        lbl.setStyle(
            "-fx-text-fill:" + TEXT_SECONDARY + ";" +
            "-fx-font-size:13px;" +
            "-fx-font-family:'Courier New';"
        );

        Region accent = new Region();
        accent.setPrefWidth(3);
        accent.setPrefHeight(36);
        accent.setStyle("-fx-background-color:" + ACCENT + ";");
        accent.setVisible(false);

        HBox row = new HBox(10, accent, lbl);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPrefHeight(44);
        row.setPrefWidth(200);
        row.setPadding(new Insets(0, 14, 0, 0));
        row.setStyle("-fx-background-color:transparent;-fx-cursor:hand;");

        StackPane btn = new StackPane(row);
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setStyle("-fx-background-color:transparent;");

        btn.setOnMouseEntered(e -> {
            if (!activeCategory.equals(category)) {
                row.setStyle("-fx-background-color:" + BG_CARD + ";-fx-cursor:hand;");
                lbl.setStyle("-fx-text-fill:" + TEXT_PRIMARY + ";-fx-font-size:13px;-fx-font-family:'Courier New';");
            }
        });
        btn.setOnMouseExited(e -> {
            if (!activeCategory.equals(category)) {
                row.setStyle("-fx-background-color:transparent;-fx-cursor:hand;");
                lbl.setStyle("-fx-text-fill:" + TEXT_SECONDARY + ";-fx-font-size:13px;-fx-font-family:'Courier New';");
            }
        });

        btn.setOnMouseClicked(e -> {
            activeCategory = category;
            // Reset all to inactive — simple approach: re-style this one
            accent.setVisible(true);
            row.setStyle("-fx-background-color:" + BG_CARD_HOV + ";-fx-cursor:hand;");
            lbl.setStyle(
                "-fx-text-fill:" + TEXT_PRIMARY + ";" +
                "-fx-font-size:13px;" +
                "-fx-font-family:'Courier New';" +
                "-fx-font-weight:bold;"
            );
            loadCategory(category);
        });

        return btn;
    }

    // ── Product list cards ────────────────────────────────────────────────────

    private void loadCategory(String category) {
        productList.getChildren().clear();
        clearDetail();

        Label sectionLbl = new Label(category.toUpperCase());
        sectionLbl.setStyle(
            "-fx-text-fill:" + TEXT_MUTED + ";" +
            "-fx-font-size:10px;" +
            "-fx-font-family:'Courier New';" +
            "-fx-font-weight:bold;"
        );
        sectionLbl.setPadding(new Insets(0, 0, 4, 0));
        productList.getChildren().add(sectionLbl);

        switch (category) {
            case "Clothing"  -> { addCard("T-Shirt",      "Nike  ·  M  ·  Black",   "$299.99",  "20 in stock", () -> showClothing(c1));
                                  addCard("Jeans",        "Levi's  ·  L  ·  Blue",  "$799.99",  "15 in stock", () -> showClothing(c2)); }
            case "Laptops"   -> { addCard("XPS 15",       "Dell  ·  Intel i7",       "$1,500.00","10 in stock", () -> showLaptop(laptop1));
                                  addCard("MacBook Pro",  "Apple  ·  M2 Pro",        "$2,500.00","5 in stock",  () -> showLaptop(laptop2)); }
            case "Phones"    -> { addCard("Galaxy S24",   "Samsung  ·  50 MP",       "$1,200.00","20 in stock", () -> showPhone(phone1));
                                  addCard("iPhone 15",    "Apple  ·  48 MP",         "$1,300.00","12 in stock", () -> showPhone(phone2)); }
        }
    }

    private void addCard(String title, String subtitle, String price, String stock, Runnable onClick) {
        VBox card = new VBox(5);
        card.setPadding(new Insets(14, 16, 14, 16));
        card.setStyle(
            "-fx-background-color:" + BG_CARD + ";" +
            "-fx-background-radius:8;" +
            "-fx-border-color:" + BORDER + ";" +
            "-fx-border-radius:8;" +
            "-fx-cursor:hand;"
        );

        Label titleLbl = new Label(title);
        titleLbl.setStyle("-fx-text-fill:" + TEXT_PRIMARY + ";-fx-font-size:14px;-fx-font-weight:bold;-fx-font-family:'Courier New';");

        Label subLbl = new Label(subtitle);
        subLbl.setStyle("-fx-text-fill:" + TEXT_SECONDARY + ";-fx-font-size:11px;-fx-font-family:'Courier New';");

        HBox bottom = new HBox();
        bottom.setAlignment(Pos.CENTER_LEFT);
        Label priceLbl = new Label(price);
        priceLbl.setStyle("-fx-text-fill:" + ACCENT + ";-fx-font-size:13px;-fx-font-weight:bold;-fx-font-family:'Courier New';");
        Region sp = new Region(); HBox.setHgrow(sp, Priority.ALWAYS);
        Label stockLbl = new Label(stock);
        stockLbl.setStyle("-fx-text-fill:" + TEXT_MUTED + ";-fx-font-size:11px;-fx-font-family:'Courier New';");
        bottom.getChildren().addAll(priceLbl, sp, stockLbl);

        card.getChildren().addAll(titleLbl, subLbl, bottom);

        card.setOnMouseEntered(e -> card.setStyle(
            "-fx-background-color:" + BG_CARD_HOV + ";" +
            "-fx-background-radius:8;" +
            "-fx-border-color:" + ACCENT_DIM + ";" +
            "-fx-border-radius:8;" +
            "-fx-cursor:hand;"
        ));
        card.setOnMouseExited(e -> card.setStyle(
            "-fx-background-color:" + BG_CARD + ";" +
            "-fx-background-radius:8;" +
            "-fx-border-color:" + BORDER + ";" +
            "-fx-border-radius:8;" +
            "-fx-cursor:hand;"
        ));
        card.setOnMouseClicked(e -> {
            onClick.run();
            animateDetail();
        });

        productList.getChildren().add(card);
    }

    // ── Detail panel ──────────────────────────────────────────────────────────

    private VBox buildDetailPanel() {
        VBox dp = new VBox(0);
        dp.setStyle("-fx-background-color:" + BG_DETAIL + ";");
        dp.setAlignment(Pos.TOP_LEFT);

        // Header bar
        HBox headerBar = new HBox();
        headerBar.setPadding(new Insets(22, 28, 18, 28));
        headerBar.setAlignment(Pos.CENTER_LEFT);
        headerBar.setStyle("-fx-border-color:transparent transparent " + BORDER + " transparent;");

        detailTitle = new Label("Select a product");
        detailTitle.setStyle(
            "-fx-text-fill:" + TEXT_PRIMARY + ";" +
            "-fx-font-size:18px;" +
            "-fx-font-weight:bold;" +
            "-fx-font-family:'Courier New';"
        );
        headerBar.getChildren().add(detailTitle);

        // Fields area
        detailFields = new VBox(0);
        detailFields.setPadding(new Insets(24, 28, 24, 28));

        Label hint = new Label("Click any product card to see its details here.");
        hint.setStyle("-fx-text-fill:" + TEXT_MUTED + ";-fx-font-size:13px;-fx-font-family:'Courier New';");
        detailFields.getChildren().add(hint);

        ScrollPane scroll = new ScrollPane(detailFields);
        scroll.setFitToWidth(true);
        scroll.setStyle(
            "-fx-background-color:" + BG_DETAIL + ";" +
            "-fx-background:" + BG_DETAIL + ";" +
            "-fx-border-color:transparent;"
        );
        VBox.setVgrow(scroll, Priority.ALWAYS);

        dp.getChildren().addAll(headerBar, scroll);
        return dp;
    }

    private void clearDetail() {
        detailTitle.setText("Select a product");
        detailFields.getChildren().clear();
        Label hint = new Label("Click any product card to see its details here.");
        hint.setStyle("-fx-text-fill:" + TEXT_MUTED + ";-fx-font-size:13px;-fx-font-family:'Courier New';");
        detailFields.getChildren().add(hint);
    }

    private void animateDetail() {
        FadeTransition ft = new FadeTransition(Duration.millis(220), detailFields);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    private void addDetailRow(String key, String value) {
        addDetailRow(key, value, false);
    }

    private void addDetailRow(String key, String value, boolean highlight) {
        HBox row = new HBox(0);
        row.setPrefHeight(40);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(0, 0, 0, 0));
        row.setStyle("-fx-border-color:transparent transparent " + BORDER + " transparent;");

        Label k = new Label(key);
        k.setPrefWidth(160);
        k.setStyle("-fx-text-fill:" + TEXT_MUTED + ";-fx-font-size:12px;-fx-font-family:'Courier New';");

        Label v = new Label(value);
        v.setStyle(
            highlight
                ? "-fx-text-fill:" + ACCENT + ";-fx-font-size:14px;-fx-font-weight:bold;-fx-font-family:'Courier New';"
                : "-fx-text-fill:" + TEXT_PRIMARY + ";-fx-font-size:13px;-fx-font-family:'Courier New';"
        );

        row.getChildren().addAll(k, v);
        detailFields.getChildren().add(row);
    }

    private void addSectionDivider(String sectionName) {
        Label lbl = new Label(sectionName.toUpperCase());
        lbl.setPadding(new Insets(18, 0, 6, 0));
        lbl.setStyle(
            "-fx-text-fill:" + ACCENT + ";" +
            "-fx-font-size:10px;" +
            "-fx-font-weight:bold;" +
            "-fx-font-family:'Courier New';"
        );
        detailFields.getChildren().add(lbl);
    }

    // ── Show methods ──────────────────────────────────────────────────────────

    private void showClothing(Clothing c) {
        detailTitle.setText(c.name);
        detailFields.getChildren().clear();

        addSectionDivider("Product Info");
        addDetailRow("ID",          String.valueOf(c.id));
        addDetailRow("Name",        c.name);
        addDetailRow("Brand",       c.brand);
        addDetailRow("Price",       String.format("$%.2f", c.price), true);
        addDetailRow("Quantity",    c.quantity + " units");
        addDetailRow("Description", c.description);

        addSectionDivider("Clothing Details");
        addDetailRow("Size",        c.size);
        addDetailRow("Color",       c.color);
        addDetailRow("Material",    c.material);

        addSectionDivider("Shipping");
        addDetailRow("Weight",       c.weight + " kg");
        addDetailRow("Shipping Cost","$" + c.shippingCost);
    }

    private void showLaptop(Laptops l) {
        detailTitle.setText(l.name);
        detailFields.getChildren().clear();

        addSectionDivider("Product Info");
        addDetailRow("ID",          String.valueOf(l.id));
        addDetailRow("Name",        l.name);
        addDetailRow("Brand",       l.brand);
        addDetailRow("Price",       String.format("$%.2f", l.price), true);
        addDetailRow("Quantity",    l.quantity + " units");
        addDetailRow("Description", l.description);

        addSectionDivider("Specs");
        addDetailRow("Processor",   l.processor);
        addDetailRow("RAM",         l.ram);
        addDetailRow("Storage",     l.storage);

        addSectionDivider("Hardware");
        addDetailRow("Warranty",    l.warrantyYears + " year(s)");
        addDetailRow("Power Usage", l.powerUsage + " W");

        addSectionDivider("Shipping");
        addDetailRow("Weight",       l.weight + " kg");
        addDetailRow("Shipping Cost","$" + l.shippingCost);
    }

    private void showPhone(Phones p) {
        detailTitle.setText(p.name);
        detailFields.getChildren().clear();

        addSectionDivider("Product Info");
        addDetailRow("ID",          String.valueOf(p.id));
        addDetailRow("Name",        p.name);
        addDetailRow("Brand",       p.brand);
        addDetailRow("Price",       String.format("$%.2f", p.price), true);
        addDetailRow("Quantity",    p.quantity + " units");
        addDetailRow("Description", p.description);

        addSectionDivider("Specs");
        addDetailRow("Camera",      p.cameraMp + " MP");
        addDetailRow("Battery",     p.batteryDetails + " mAh");
        addDetailRow("Charger",     p.powerUsage + " W");

        addSectionDivider("Hardware");
        addDetailRow("SIM Count",   String.valueOf(p.warrantyYears));
        addDetailRow("Warranty",    p.warrantyYears + " year(s)");

        addSectionDivider("Shipping");
        addDetailRow("Weight",       p.weight + " kg");
        addDetailRow("Shipping Cost","$" + p.shippingCost);
    }

    // ── Main ──────────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        launch(args);
    }
}