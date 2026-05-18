package javaproject;

import java.awt.Desktop;
import java.net.URI;
import java.time.LocalDate;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
FlowPane productsPane = new FlowPane();
ScrollPane scroll = new ScrollPane(productsPane);
VBox cartItemsBox = new VBox(15);
//Add product
           // ================= CLOTHING OBJECTS =================

Clothing c1 = new Clothing(
        new String[]{"S", "M","L","Xl"}, new String[]{"GREEN", "BLACk"},
        50.0,
        "Nike", 101, "T-Shirt", 299.99,
        new FlatDiscount(50),
        "Comfortable cotton t-shirt",
        20,
        4,96
);

Clothing c2 = new Clothing(
        new String[]{"S", "M","L"}, new String[]{"RED", "BLUE"},
        80.0,
        "Levi's", 102, "Jeans", 799.99,
        new PercentageDiscount(10),
        "Classic blue denim jeans",
        15,
        3,60
);

// ================= LAPTOP OBJECTS =================

Laptops laptop1 = new Laptops(
         20,
        "Dell", 1, "XPS 15",
        1500,
        new FlatDiscount(10),
        "Intel i7 processor, 2 year warranty. Perfect for work and gaming.",
        10,
        5,170,
        new String[]{"8GB", "16GB", "32GB"},
        new String[]{"128GB", "256GB", "512GB" ,"1T"}
);

Laptops laptop2 = new Laptops(
         25,
        "Apple", 2, "MacBook Pro",
        2500,
        new PercentageDiscount(10),
        "Apple M3 processor, 2 year warranty. Best for creative professionals.",
        5,
        4,80,
        new String[]{"4","8GB", "16GB"},
        new String[]{ "256GB", "512GB" ,"1T","2T"}
);

// ================= PHONE OBJECTS =================

Phones phone1 = new Phones(new String[]{"128GB", "256GB", "512GB"},new String[]{"PINK", "BLACk"},
         10,
        "Samsung", 101, "Galaxy S24",
        1200,
        new FlatDiscount(10),
        "108MP camera, 5000mAh battery, 6.7\" AMOLED display, 2 year warranty.",
        20,
        4,55
);

Phones phone2 = new Phones(new String[]{"64GB","128GB", "256GB"},new String[]{"RED", "BLUE"},
         12,
        "Apple", 102, "iPhone 15",
        1300,
        null,
        "48MP camera, 3877mAh battery, 6.1\" Super Retina XDR display, 2 year warranty.",
        12,
        5,120
);

// ================= SOFTWARE LICENSE OBJECTS =================

SoftwareLicense s1 = new SoftwareLicense(
        "ABC123-XYZ789",
        LocalDate.of(2027, 5, 14),
        2.5,
        "https://download.com/software1",
        201,
        "Windows Antivirus",
        49.99,
        null,
        "Premium antivirus software",
        100,
        4,110
);

SoftwareLicense s2 = new SoftwareLicense(
        "QWE456-RTY111",
        LocalDate.of(2026, 5, 17),
        1.2,
        "https://download.com/software2",
        202,
        "Photo Editor Pro",
        79.99,
        null,
        "Professional photo editing software",
        50
        ,3,130
);

// ================= DIGITAL DOWNLOAD OBJECTS =================

DigitalDownload d1 = new DigitalDownload(
        "MP3",
        5,
        120.5,
        "https://download.com/music_album",
        301,
        "Top Hits Album",
        19.99,
        null,
        " 320kbps quality, Instant access after purchase.",
        200,
        4,150
);

DigitalDownload d2 = new DigitalDownload(
        "PDF",
        3,
        15.2,
        "https://download.com/java_book",
        302,
        "Java Book",
        29.99,
        null,
        "Complete Java learning ebook",
        80
        ,5,100
);
    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();
        root.setStyle("""
            -fx-background-color: #f5f7fa;
        """);

    // ================= HEADER ===============================================================================

            HBox header = new HBox();
            header.prefHeightProperty().bind(root.heightProperty().multiply(0.07));
            header.setMinHeight(40);
            header.maxHeightProperty().bind(root.heightProperty().multiply(0.07));
            
            Label title = new Label("E-Commerce Store");
            title.setStyle("""
                -fx-font-size: 32px;
                -fx-font-weight: bold;
                -fx-text-fill: #1f2937;
            """);

            header.getChildren().add(title);
            header.setAlignment(Pos.CENTER);
            header.setStyle("""
                -fx-background-color: white;
                -fx-border-color: #dcdfe6;
                -fx-border-width: 0 0 1 0;
                -fx-padding: 25px;
            """);

            root.setTop(header); 

            // ── Animation 1: Fade + Slide-down on startup ──
        title.setOpacity(0);
        title.setTranslateY(-30);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(900), title);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        TranslateTransition slideDown = new TranslateTransition(Duration.millis(900), title);
        slideDown.setFromY(-30);
        slideDown.setToY(0);
        slideDown.setInterpolator(Interpolator.EASE_OUT);

        ParallelTransition intro = new ParallelTransition(fadeIn, slideDown);
        intro.play();

        // ── Animation 2: Glow pulse on title ──
        DropShadow glow = new DropShadow();
        glow.setColor(Color.web("#2563eb"));
        glow.setRadius(0);
        title.setEffect(glow);

        Timeline glowPulse = new Timeline(
            new KeyFrame(Duration.ZERO,
                new KeyValue(glow.radiusProperty(), 0),
                new KeyValue(glow.spreadProperty(), 0)
            ),
            new KeyFrame(Duration.millis(1500),
                new KeyValue(glow.radiusProperty(), 15),
                new KeyValue(glow.spreadProperty(), 0.08)
            ),
            new KeyFrame(Duration.millis(3000),
                new KeyValue(glow.radiusProperty(), 0),
                new KeyValue(glow.spreadProperty(), 0)
            )
        );
        glowPulse.setCycleCount(Timeline.INDEFINITE);
        glowPulse.setDelay(Duration.millis(900));
        glowPulse.play();

        // ── Animation 3: Header subtle border color cycle ──
        String[] headerStyles = {
            "-fx-background-color: white; -fx-padding: 15px; -fx-border-color: #dcdfe6; -fx-border-width: 0 0 1 0;",
            "-fx-background-color: #f0f7ff; -fx-padding: 15px; -fx-border-color: #bfdbfe; -fx-border-width: 0 0 1 0;",
            "-fx-background-color: white; -fx-padding: 15px; -fx-border-color: #dcdfe6; -fx-border-width: 0 0 1 0;",
        };
        int[] idx = {0};
        Timeline colorCycle = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
            idx[0] = (idx[0] + 1) % headerStyles.length;
            header.setStyle(headerStyles[idx[0]]);
        }));
        colorCycle.setCycleCount(Timeline.INDEFINITE);
        colorCycle.play();

// ================= LEFT SIDEBAR ============================================
  
            VBox sidebar = new VBox(25);

            sidebar.setPadding(new Insets(25));

            sidebar.prefWidthProperty().bind(root.widthProperty().multiply(0.17));
            sidebar.setMinWidth(225);
           
            sidebar.setStyle("""
                -fx-background-color: #e9eef5;
                -fx-spacing: 15px;
                -fx-border-color: #d0d7e2;
                -fx-border-width: 0 1 0 0;
            """);

            // Logo

            Label logo = new Label("E-Commerce\nStore");

            logo.setFont(Font.font("Arial", 28));

            logo.setStyle("""
                -fx-font-weight: bold;
                -fx-text-fill: #2563eb;
            """);

            // Search Box
            TextField searchField = new TextField();

            searchField.setPromptText("Search Product...");
            searchField.setPrefHeight(40);

            searchField.setStyle("""
            -fx-background-radius: 10;
            -fx-font-size: 14px;
            -fx-padding: 8;
             """);

searchField.textProperty().addListener((obs, oldVal, newVal) -> {
    searchProducts(newVal);
});
            // Buttons

            Button homeBtn = new Button("🏠 Home");

            Button clothesBtn = new Button("👕 Clothing");

            Button laptopsBtn = new Button("💻 Laptops");

            Button phonesBtn = new Button("📱 Phones");

            Button digitalBtn = new Button("🎮 Digital Products");

            Button clearCartBtn = new Button("🗑 Clear Cart");

// Button Style================================================================

            String menuStyle = """
                -fx-background-color: transparent;
                -fx-text-fill: #1f2937;
                -fx-font-size: 16px;
                -fx-font-weight: 600;
                -fx-background-radius: 10;
                -fx-padding: 14 18 14 18;
                -fx-alignment: CENTER-LEFT;
                -fx-cursor: hand;
            """;


            homeBtn.setStyle(menuStyle);

            clothesBtn.setStyle(menuStyle);

            laptopsBtn.setStyle(menuStyle);

            phonesBtn.setStyle(menuStyle);

            digitalBtn.setStyle(menuStyle);

            clearCartBtn.setStyle(menuStyle);

// Button Width===============================================================

            homeBtn.setMaxWidth(Double.MAX_VALUE);

            clothesBtn.setMaxWidth(Double.MAX_VALUE);

            laptopsBtn.setMaxWidth(Double.MAX_VALUE);

            phonesBtn.setMaxWidth(Double.MAX_VALUE);

            digitalBtn.setMaxWidth(Double.MAX_VALUE);

            clearCartBtn.setMaxWidth(Double.MAX_VALUE);

            // Hover Effect
             String hoverStyle = """
                -fx-background-color: #dbeafe;
                -fx-text-fill: #2563eb;
                -fx-font-size: 16px;
                -fx-font-weight: bold;
                -fx-background-radius: 10;
                -fx-padding: 14 18 14 18;
                -fx-alignment: CENTER-LEFT;
                -fx-cursor: hand;
            """;

            String clearCartDefault = """
                -fx-background-color: white;
                -fx-text-fill: #111827;
                -fx-font-size: 15px;
                -fx-font-weight: bold;
                -fx-background-radius: 8;
                -fx-border-color: #d1d5db;
                -fx-border-radius: 8;
                -fx-padding: 12;
                -fx-cursor: hand;
            """;
            
             String clearCartHover = """
                -fx-background-color: #fee2e2;
                -fx-text-fill: #b91c1c;
                -fx-font-size: 15px;
                -fx-font-weight: bold;
                -fx-background-radius: 8;
                -fx-border-color: #fca5a5;
                -fx-border-radius: 8;
                -fx-padding: 12;
                -fx-cursor: hand;
            """;

            clearCartBtn.setStyle(clearCartDefault);


//============================================================================
            homeBtn.setOnMouseEntered(e ->homeBtn.setStyle(hoverStyle));

            homeBtn.setOnMouseExited(e -> homeBtn.setStyle(menuStyle));
            
            homeBtn.setOnAction(e -> {

                    productsPane.getChildren().setAll(
                    createProductCard(laptop1),
                    createProductCard(laptop2),
                    createProductCard(phone1),
                    createProductCard(phone2),
                    createProductCard(c1),
                    createProductCard(c2),
                    createProductCard(s1),
                    createProductCard(s2),
                    createProductCard(d1),
                    createProductCard(d2)
                    );


                    scroll.setContent(productsPane);
                    root.setCenter(scroll);
            });
//====================================================================      
            clothesBtn.setOnMouseEntered(e ->clothesBtn.setStyle(hoverStyle));

            clothesBtn.setOnMouseExited(e -> clothesBtn.setStyle(menuStyle));
            
            clothesBtn.setOnAction(e -> {

                    productsPane.getChildren().setAll(
                    createProductCard(c1),
                    createProductCard(c2)
                    );

                    scroll.setContent(productsPane);
                    root.setCenter(scroll);
            });
//============================================================================
            laptopsBtn.setOnMouseEntered(e ->laptopsBtn.setStyle(hoverStyle));

            laptopsBtn.setOnMouseExited(e -> laptopsBtn.setStyle(menuStyle));
            
            laptopsBtn.setOnAction(e -> {

                    productsPane.getChildren().setAll(
                    createProductCard(laptop1),
                    createProductCard(laptop2)
                    );

                    scroll.setContent(productsPane);
                    root.setCenter(scroll);
            });
//============================================================================
            phonesBtn.setOnMouseEntered(e ->phonesBtn.setStyle(hoverStyle));

            phonesBtn.setOnMouseExited(e -> phonesBtn.setStyle(menuStyle));
            
           phonesBtn.setOnAction(e -> {

                productsPane.getChildren().setAll(
                createProductCard(phone1),
                createProductCard(phone2)
                 );

                scroll.setContent(productsPane);
                root.setCenter(scroll);
            });
//=============================================================================         
            digitalBtn.setOnMouseEntered(e ->digitalBtn.setStyle(hoverStyle));
            
            digitalBtn.setOnMouseExited(e -> digitalBtn.setStyle(menuStyle));
            
            digitalBtn.setOnAction(e -> {

                productsPane.getChildren().setAll(
                createProductCard(s1),
                createProductCard(s2),
                createProductCard(d1),
                createProductCard(d2)
                );

                scroll.setContent(productsPane);
                root.setCenter(scroll);
            });
//=============================================================================
            clearCartBtn.setOnMouseEntered(e ->clearCartBtn.setStyle(clearCartHover));

            clearCartBtn.setOnMouseExited(e -> clearCartBtn.setStyle(clearCartDefault));
            
            clearCartBtn.setOnAction(e -> {
                cartItemsBox.getChildren().clear();

                // Empty Cart Icon

                Label emptyCart = new Label("🛒");

                emptyCart.setFont(Font.font(60));

                emptyCart.setTextFill(Color.web("#9ca3af"));

                // Empty Text

                Label emptyText = new Label("Your cart is empty");

                emptyText.setFont(Font.font(18));

                emptyText.setTextFill(Color.web("#6b7280"));

                // Add Empty Content

                cartItemsBox.getChildren().addAll(
                        emptyCart,
                        emptyText
            );
            });
//=============================================================================
            // Add Buttons

            sidebar.getChildren().addAll(
                    logo,
                    searchField,
                    homeBtn,
                    clothesBtn,
                    laptopsBtn,
                    phonesBtn,
                    digitalBtn,
                    clearCartBtn
            );

            // Set Left
         
            root.setLeft(sidebar);
          
//================== RIGHT CART GUI============================================
    
            VBox cartPane = new VBox(20);

            cartPane.setPadding(new Insets(25));

             cartPane.prefWidthProperty().bind(root.widthProperty().multiply(0.20));
             cartPane.setMinWidth(250);

            cartPane.setStyle("""
                -fx-background-color: white;
                -fx-padding: 20px;
                -fx-border-color: #dcdfe6;
                -fx-border-width: 0 0 0 1;
            """);

            // Cart Title

            Label cartTitle = new Label("🛒 Shopping Cart");

            cartTitle.setFont(Font.font("Arial", 26));

             cartTitle.setStyle("""
                -fx-font-size: 28px;
                -fx-font-weight: bold;
                -fx-text-fill: #111827;
            """);
            
            cartTitle.setWrapText(true);
            cartTitle.setMinHeight(Region.USE_PREF_SIZE);

            // Line

            Separator line = new Separator();

            line.setPrefHeight(2);

            line.setStyle("""
                -fx-background-color: #e5e7eb;
            """);

            // Cart Items Box

            cartItemsBox = new VBox(15);

            cartItemsBox.setAlignment(Pos.TOP_CENTER);

            cartItemsBox.setPrefHeight(500);

            cartItemsBox.setStyle("""
                -fx-background-color: white;
                -fx-border-color: #e5e7eb;
                -fx-border-radius: 10;
                -fx-background-radius: 10;
                -fx-padding: 15;
            """);

            // Empty Cart Icon

            Label emptyCart = new Label("🛒");

            emptyCart.setFont(Font.font(60));

            emptyCart.setTextFill(Color.web("#9ca3af"));

            // Empty Text

            Label emptyText = new Label("Your cart is empty");

            emptyText.setFont(Font.font(18));

            emptyText.setTextFill(Color.web("#6b7280"));

            // Add Empty Content

            cartItemsBox.getChildren().addAll(
                    emptyCart,
                    emptyText
            );

            // Checkout Button

            Button checkoutBtn = new Button("Checkout");

            checkoutBtn.setMaxWidth(Double.MAX_VALUE);
            checkoutBtn.setStyle("""
                -fx-background-color: #2fb344;
                -fx-text-fill: white;
                -fx-font-size: 18px;
                -fx-font-weight: bold;
                -fx-background-radius: 8;
                -fx-padding: 14;
            """);

            // Hover Effect

            checkoutBtn.setOnMouseEntered(e ->
                    checkoutBtn.setStyle("""
                        -fx-background-color: #24963a;
                        -fx-text-fill: white;
                        -fx-font-size: 18px;
                        -fx-font-weight: bold;
                        -fx-background-radius: 8;
                        -fx-padding: 14;
                        -fx-cursor: hand;
                    """)
            );

            checkoutBtn.setOnMouseExited(e ->
                    checkoutBtn.setStyle("""
                        -fx-background-color: #2fb344;
                        -fx-text-fill: white;
                        -fx-font-size: 18px;
                        -fx-font-weight: bold;
                        -fx-background-radius: 8;
                        -fx-padding: 14;
                    """)
            );

            // Add To CartPane
                    cartPane.getChildren().addAll(
                    cartTitle,
                    line,
                    cartItemsBox,
                    checkoutBtn
            );

            // Set Right Side
           
            root.setRight(cartPane);
    // ================= CENTER PRODUCTS ==========================================================================

            productsPane.setPadding(new Insets(20));
            productsPane.setHgap(20);
            productsPane.setVgap(20);
            productsPane.setAlignment(Pos.TOP_CENTER);

            productsPane.setStyle("""
                -fx-background-color: #fff;
            """);

           productsPane.getChildren().addAll(

                    // 👕 Clothing
                    createProductCard(c1),
                    createProductCard(c2),

                    // 💻 Laptops
                    createProductCard(laptop1),
                    createProductCard(laptop2),

                    // 📱 Phones
                    createProductCard(phone1),
                    createProductCard(phone2),

                    // 💿 Software Licenses
                    createProductCard(s1),
                    createProductCard(s2),

                    // 🎮 Digital Downloads
                    createProductCard(d1),
                    createProductCard(d2)
            );

            scroll = new ScrollPane(productsPane);
            scroll.setFitToWidth(true);
            scroll.setStyle("""
                -fx-background: #f5f7fa;
                -fx-background-color: #f5f7fa;
                -fx-border-color: transparent;
            """);
            scroll.prefWidthProperty().bind(
            root.widthProperty()
        .subtract(sidebar.widthProperty())
        .subtract(cartPane.widthProperty())
);

            root.setCenter(scroll);
            
        // Scene
        Scene scene = new Scene(root);

        stage.setTitle("E-Commerce Product Store");
        stage.setMinHeight(645);
        stage.setMinWidth(730);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private String getProductImageUrl(Product p) {
        if (p instanceof Laptops) {
            if (p.name.toLowerCase().contains("mac"))
                return "https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=200&h=140&fit=crop";
            return "https://images.unsplash.com/photo-1593642632559-0c6d3fc62b89?w=200&h=140&fit=crop";
        } else if (p instanceof Phones) {
            if (p.name.toLowerCase().contains("iphone"))
                return "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=200&h=140&fit=crop";
            return "https://images.unsplash.com/photo-1610945265064-0e34e5519bbf?w=200&h=140&fit=crop";
        } else if (p instanceof Clothing) {
            if (p.name.toLowerCase().contains("jeans"))
                return "https://images.unsplash.com/photo-1542272604-787c3835535d?w=200&h=140&fit=crop";
            return "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=200&h=140&fit=crop";
        } else if (p instanceof SoftwareLicense) {
            if (p.name.toLowerCase().contains("photo"))
                return "https://images.unsplash.com/photo-1611532736597-de2d4265fba3?w=200&h=140&fit=crop";
            return "https://images.unsplash.com/photo-1558494949-ef010cbdcc31?w=200&h=140&fit=crop";
        } else if (p instanceof DigitalDownload) {
            if (p.name.toLowerCase().contains("music") || p.name.toLowerCase().contains("album"))
                return "https://images.unsplash.com/photo-1470225620780-dba8ba36b745?w=200&h=140&fit=crop";
            return "https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?w=200&h=140&fit=crop";
        }
        return "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=200&h=140&fit=crop";
    }
    
   private VBox createProductCard(Product p) {

    VBox card = new VBox(10);
    card.setPadding(new Insets(15));
    card.setPrefWidth(180);
    card.setAlignment(Pos.CENTER);
    card.setStyle("""
        -fx-background-color: white;
        -fx-background-radius: 12;
        -fx-border-color: #e5e7eb;
        -fx-border-radius: 12;
        -fx-padding: 15;
        -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 10, 0, 0, 3);
        -fx-cursor: hand;
    """);
            
    card.setOnMouseEntered(e -> {
        TranslateTransition tt = new TranslateTransition(Duration.millis(150), card);
        tt.setToY(-5);
        tt.play();
    });

    card.setOnMouseExited(e -> {
        TranslateTransition tt = new TranslateTransition(Duration.millis(150), card);
        tt.setToY(0);
        tt.play();
    });

// ── Product Image ──
        ImageView imageView = new ImageView();
        imageView.setFitWidth(170);
        imageView.setFitHeight(120);
        imageView.setPreserveRatio(false);
        imageView.setStyle("-fx-background-radius: 8;");
        try {
            Image img = new Image(getProductImageUrl(p), 200, 140, false, true, true);
            imageView.setImage(img);
        } catch (Exception ex) {
            // silently skip if image fails to load
        }

    Label nameLabel = new Label(p.name);
    nameLabel.setStyle("""
            -fx-font-size: 19px;
            -fx-font-weight: bold;
            -fx-text-fill: #111827;
        """);

    Label priceLabel = new Label(String.format("$%.2f", p.getFinalPrice()));
    priceLabel.setStyle("""
            -fx-font-size: 20px;
            -fx-font-weight: bold;
            -fx-text-fill: #111827;
        """);

    Button addBtn = new Button("Add To cart");
    addBtn.setMaxWidth(Double.MAX_VALUE);
    addBtn.setStyle("""
            -fx-background-color: #edf2f7;
            -fx-text-fill: #2563eb;
            -fx-font-size: 15px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
            -fx-padding: 10 18 10 18;
            -fx-cursor: hand;
        """);

    
    addBtn.setOnMouseEntered(e -> addBtn.setStyle("""
        -fx-background-color: #dbeafe;
        -fx-text-fill: #1d4ed8;
        -fx-font-size: 15px;
        -fx-font-weight: bold;
        -fx-background-radius: 8;
        -fx-padding: 10 18 10 18;
        -fx-cursor: hand;
    """));
    addBtn.setOnMouseExited(e -> addBtn.setStyle("""
        -fx-background-color: #edf2f7;
        -fx-text-fill: #2563eb;
        -fx-font-size: 15px;
        -fx-font-weight: bold;
        -fx-background-radius: 8;
        -fx-padding: 10 18 10 18;
    """));

    // ── Details Window ========================================================================================================
    
    card.setOnMouseClicked(e -> {
    
        Stage detailStage = new Stage();
        detailStage.setTitle(p.name + " - Details");

        VBox content = new VBox(20);
        content.setPadding(new Insets(40));
        content.setAlignment(Pos.CENTER);
        content.setStyle("""
            -fx-background-color: #f5f7fa;
        """);

        ImageView imageViewIn = new ImageView();
        imageViewIn.setFitWidth(420);
        imageViewIn.setFitHeight(300);
        imageViewIn.setPreserveRatio(false);
//        imageViewIn.setStyle("-fx-background-radius: 8;");
        try {
            Image img = new Image(getProductImageUrl(p), 200, 140, false, true, true);
            imageViewIn.setImage(img);
        } catch (Exception ex) {
            // silently skip if image fails to load
        }
        
        content.getChildren().add(imageViewIn);

// Name
        Label detailName = new Label(p.name);
        detailName.setFont(Font.font("Arial", 28));
        detailName.setStyle("""
                -fx-font-weight: bold;
                -fx-text-fill: #111827;
            """);
        detailName.setMaxWidth(320);
        detailName.setTextAlignment(TextAlignment.LEFT);
        
        
// Rating
        HBox rateBox = new HBox(10);
        HBox stars = new HBox(3);
        for (int i = 1; i <= 5; i++) {
            Label star = new Label("⭐");
            star.setStyle("-fx-font-size: 30px; -fx-padding: -15 0 -25 0;");
            star.setTextFill(Color.web("#FFAD33"));
            if (i > p.rating) {
                star.setOpacity(0.6);
            }
            stars.getChildren().add(star);
        }
        Label reviews = new Label("(" + p.reviews + " reviews)");
        reviews.setFont(Font.font(16));
        reviews.setTextFill(Color.web("#aaa"));
        
        rateBox.getChildren().addAll(stars,reviews);
        rateBox.setAlignment(Pos.CENTER_LEFT);
        

        // Description
        Label detailDesc = new Label(p.description);
        detailDesc.setTextFill(Color.web("#6b7280"));
        detailDesc.setFont(Font.font(15));
        detailDesc.setWrapText(true);
        detailDesc.setMaxWidth(320);
        detailDesc.setTextAlignment(TextAlignment.LEFT);

        HBox HBoxPrice = new HBox(20);
        // Price
        Label detailPrice = new Label(String.format("$%.2f", p.getFinalPrice()));
        detailPrice.setFont(Font.font("Arial", 32));
        detailPrice.setTextFill(Color.web("#2fb344"));
        detailPrice.setMaxWidth(320);
        detailPrice.setTextAlignment(TextAlignment.LEFT);
        
        //old price
        double tax = (p instanceof PhysicalProduct) ? ((PhysicalProduct) p).calculateTax() : 0;
        double shipping = (p instanceof PhysicalProduct) ? ((PhysicalProduct) p).getShippableCost() : 0;

        Text oldPrice = new Text("");
        if (p.discount != null) {
            oldPrice.setText(String.format("$%.2f", (p.price + tax + shipping)));
            oldPrice.setFont(Font.font("Arial", 30));
            oldPrice.setStrikethrough(true);
            oldPrice.setFill(Color.web("#999"));
            oldPrice.setStyle("-fx-strikethrough: true; -fx-text-fill: gray;");
            oldPrice.setTextAlignment(TextAlignment.LEFT);
        }
        
        HBoxPrice.getChildren().addAll(detailPrice,oldPrice);
        
        
        // Buy button
        Button buyBtn = new Button("🛒  Add to Cart");
        buyBtn.setPrefWidth(320);
        buyBtn.setStyle("""
            -fx-background-color: #2563eb;
            -fx-text-fill: white;
            -fx-font-size: 16px;
            -fx-font-weight: bold;
            -fx-padding: 14;
            -fx-background-radius: 8;
        """);
        buyBtn.setOnMouseEntered(ev -> buyBtn.setStyle("""
            -fx-background-color: #1d4ed8;
            -fx-text-fill: white;
            -fx-font-size: 16px;
            -fx-font-weight: bold;
            -fx-padding: 14;
            -fx-background-radius: 8;
            -fx-cursor: hand;
        """));
        buyBtn.setOnMouseExited(ev -> buyBtn.setStyle("""
            -fx-background-color: #2563eb;
            -fx-text-fill: white;
            -fx-font-size: 16px;
            -fx-font-weight: bold;
            -fx-padding: 14;
            -fx-background-radius: 8;
        """));
        buyBtn.setOnAction(ev -> detailStage.close());
        
        content.getChildren().addAll(detailName, detailDesc, rateBox);
        
        //clothing =======================
        //color
        if(p instanceof Clothing) {
            HBox colorBox = new HBox(15);
            Label colorLabel = new Label("Color : ");
            colorLabel.setFont(Font.font("Arial", 22));
            colorLabel.setTextFill(Color.web("#555"));
            colorBox.getChildren().add(colorLabel);

            String[] selectedColor = {""};
            String[] colors = ((Clothing) p).colors;
            Circle[] circles = new Circle[colors.length];

            for (int i = 0; i < colors.length; i++) {
                Circle c = new Circle(8);
                c.setFill(Color.web(colors[i]));
                circles[i] = c;
                int index = i;
                c.setOnMouseEntered(event -> c.setStyle("-fx-cursor: hand;"));
                c.setOnMouseClicked(event -> {
                    selectedColor[0] = colors[index];
                    for (Circle circle : circles) {
                        circle.setStroke(Color.TRANSPARENT);
                    }
                    c.setStroke(Color.web("#999"));
                    c.setStrokeWidth(2);
                });
                colorBox.getChildren().add(c);
            }
            colorBox.setAlignment(Pos.CENTER_LEFT);
            content.getChildren().add(colorBox);
        }
        
        
        //size
        if (p instanceof Clothing) {
            HBox sizeBox = new HBox(10);

            Label sizeLabel = new Label("Size : ");
            sizeLabel.setFont(Font.font("Arial", 22));
            sizeLabel.setTextFill(Color.web("#555"));

            String[] clothingSizes = ((Clothing) p).sizes;
            String[] selectedSize = {clothingSizes[0]};

            sizeBox.getChildren().add(sizeLabel);
            for (String size : clothingSizes) {
                Button sizeBtn = new Button(size);

                if (size.equals(clothingSizes[0])) {
                    sizeBtn.setStyle("-fx-background-color: linear-gradient(to right, #00c6ff, #0072ff); -fx-text-fill: white; -fx-font-weight: bold;-fx-cursor: hand;");
                } else {
                    sizeBtn.setStyle("-fx-font-weight: bold;");
                }

                sizeBtn.setOnMouseEntered(event -> {
                    if (!selectedSize[0].equals(size)) {
                        sizeBtn.setStyle("-fx-font-weight: bold; -fx-cursor: hand;");
                    }
                });
                sizeBtn.setOnMouseExited(event -> {
                    if (!selectedSize[0].equals(size)) {
                        sizeBtn.setStyle("-fx-font-weight: bold;");
                    }
                });
                sizeBtn.setOnMouseClicked(event -> {
                    selectedSize[0] = size;
                    for (var node : sizeBox.getChildren()) {
                        if (node instanceof Button btn) {
                            btn.setStyle("-fx-font-weight: bold;");
                        }
                    }
                    sizeBtn.setStyle("-fx-background-color: linear-gradient(to right, #00c6ff, #0072ff); -fx-text-fill: white; -fx-font-weight: bold;-fx-cursor: hand;");
                });

                sizeBox.getChildren().add(sizeBtn);
                
            }
            content.getChildren().add(sizeBox);
        }
        
        
        
        //phones =======================
        
         //color
        if(p instanceof Phones) {
            HBox colorBox = new HBox(15);
            Label colorLabel = new Label("Color : ");
            colorLabel.setFont(Font.font("Arial", 22));
            colorLabel.setTextFill(Color.web("#555"));
            colorBox.getChildren().add(colorLabel);

            String[] selectedColor = {""};
            String[] colors = ((Phones) p).colors;
            Circle[] circles = new Circle[colors.length];

            for (int i = 0; i < colors.length; i++) {
                Circle c = new Circle(8);
                c.setFill(Color.web(colors[i]));
                circles[i] = c;
                int index = i;
                c.setOnMouseEntered(event -> c.setStyle("-fx-cursor: hand;"));
                c.setOnMouseClicked(event -> {
                    selectedColor[0] = colors[index];
                    for (Circle circle : circles) {
                        circle.setStroke(Color.TRANSPARENT);
                    }
                    c.setStroke(Color.web("#999"));
                    c.setStrokeWidth(2);
                });
                colorBox.getChildren().add(c);
            }
            colorBox.setAlignment(Pos.CENTER_LEFT);
            content.getChildren().add( colorBox);
            
         }
        
        //storage
        if (p instanceof Phones) {
            HBox storageBox = new HBox(10);

            Label storageLabel = new Label("Storage : ");
            storageLabel.setFont(Font.font("Arial", 22));
            storageLabel.setTextFill(Color.web("#555"));

            String[] phoneStorages = ((Phones) p).storages;
            String[] selectedStorage = {phoneStorages[0]};

            storageBox.getChildren().add(storageLabel);
            for (String storage : phoneStorages) {
                Button storageBtn = new Button(storage);

                if (storage.equals(phoneStorages[0])) {
                    storageBtn.setStyle("-fx-background-color: linear-gradient(to right, #00c6ff, #0072ff); -fx-text-fill: white; -fx-font-weight: bold;-fx-cursor: hand;");
                } else {
                    storageBtn.setStyle("-fx-font-weight: bold;");
                }

                storageBtn.setOnMouseEntered(event -> {
                    if (!selectedStorage[0].equals(storage)) {
                        storageBtn.setStyle("-fx-font-weight: bold; -fx-cursor: hand;");
                    }
                });
                storageBtn.setOnMouseExited(event -> {
                    if (!selectedStorage[0].equals(storage)) {
                        storageBtn.setStyle("-fx-font-weight: bold;");
                    }
                });
                storageBtn.setOnMouseClicked(event -> {
                    selectedStorage[0] = storage;
                    for (var node : storageBox.getChildren()) {
                        if (node instanceof Button btn) {
                            btn.setStyle("-fx-font-weight: bold;");
                        }
                    }
                    storageBtn.setStyle("-fx-background-color: linear-gradient(to right, #00c6ff, #0072ff); -fx-text-fill: white; -fx-font-weight: bold;-fx-cursor: hand;");
                });

                storageBox.getChildren().add(storageBtn);
            }
            content.getChildren().add( storageBox);
        }
        
        
        //labtop ===========================================
        if (p instanceof Laptops) {
            HBox laptopStorageBox = new HBox(10);
            
            Label laptopStorageLabel = new Label("Storage : ");
            laptopStorageLabel.setFont(Font.font("Arial", 22));
            laptopStorageLabel.setTextFill(Color.web("#555"));
            String[] laptopStorages = ((Laptops) p).storages;
            String[] selectedLaptopStorage = {laptopStorages[0]};
            laptopStorageBox.getChildren().add(laptopStorageLabel);
            for (String storage : laptopStorages) {
                Button storageBtn = new Button(storage);
                if (storage.equals(laptopStorages[0])) {
                    storageBtn.setStyle("-fx-background-color: linear-gradient(to right, #00c6ff, #0072ff); -fx-text-fill: white; -fx-font-weight: bold;-fx-cursor: hand;");
                } else {
                    storageBtn.setStyle("-fx-font-weight: bold;");
                }
                storageBtn.setOnMouseEntered(event -> {
                    if (!selectedLaptopStorage[0].equals(storage)) {
                        storageBtn.setStyle("-fx-font-weight: bold; -fx-cursor: hand;");
                    }
                });
                storageBtn.setOnMouseExited(event -> {
                    if (!selectedLaptopStorage[0].equals(storage)) {
                        storageBtn.setStyle("-fx-font-weight: bold;");
                    }
                });
                storageBtn.setOnMouseClicked(event -> {
                    selectedLaptopStorage[0] = storage;
                    for (var node : laptopStorageBox.getChildren()) {
                        if (node instanceof Button btn) {
                            btn.setStyle("-fx-font-weight: bold;");
                        }
                    }
                    storageBtn.setStyle("-fx-background-color: linear-gradient(to right, #00c6ff, #0072ff); -fx-text-fill: white; -fx-font-weight: bold;-fx-cursor: hand;");
                });
                laptopStorageBox.getChildren().add(storageBtn);
            }
            content.getChildren().add( laptopStorageBox);
        }
        
        //ram
        if (p instanceof Laptops) {
            HBox ramBox = new HBox(10);
            
            Label ramLabel = new Label("RAM : ");
            ramLabel.setFont(Font.font("Arial", 22));
            ramLabel.setTextFill(Color.web("#555"));
            String[] rams = ((Laptops) p).rams;
            String[] selectedRam = {rams[0]};
            ramBox.getChildren().add(ramLabel);
            for (String ram : rams) {
                Button ramBtn = new Button(ram);
                if (ram.equals(rams[0])) {
                    ramBtn.setStyle("-fx-background-color: linear-gradient(to right, #00c6ff, #0072ff); -fx-text-fill: white; -fx-font-weight: bold;-fx-cursor: hand;");
                } else {
                    ramBtn.setStyle("-fx-font-weight: bold;");
                }
                ramBtn.setOnMouseEntered(event -> {
                    if (!selectedRam[0].equals(ram)) {
                        ramBtn.setStyle("-fx-font-weight: bold; -fx-cursor: hand;");
                    }
                });
                ramBtn.setOnMouseExited(event -> {
                    if (!selectedRam[0].equals(ram)) {
                        ramBtn.setStyle("-fx-font-weight: bold;");
                    }
                });
                ramBtn.setOnMouseClicked(event -> {
                    selectedRam[0] = ram;
                    for (var node : ramBox.getChildren()) {
                        if (node instanceof Button btn) {
                            btn.setStyle("-fx-font-weight: bold;");
                        }
                    }
                    ramBtn.setStyle("-fx-background-color: linear-gradient(to right, #00c6ff, #0072ff); -fx-text-fill: white; -fx-font-weight: bold;-fx-cursor: hand;");
                });
                ramBox.getChildren().add(ramBtn);
            }
            content.getChildren().add( ramBox);
        }
       
        //DigitalProduct========================================
        
        if (p instanceof DigitalProduct) {
        DigitalProduct dp = (DigitalProduct) p;

        Label linkLabel = new Label("Download Link: " + dp.downloadLink);
        linkLabel.setTextFill(Color.web("#00c6ff"));
        linkLabel.setStyle("-fx-cursor: hand;");
        linkLabel.setOnMouseClicked(ev -> {
            try {
                Label downloadLimitLabel = new Label("");
                if (dp instanceof DigitalDownload dd) {
                    if (dd.downloadLimit > 0) {
                        dd.download();
                        downloadLimitLabel.setText("Downloads remaining: " + dd.downloadLimit);
                        Desktop.getDesktop().browse(new URI(dp.downloadLink));
                    }
                }else
                    Desktop.getDesktop().browse(new URI(dp.downloadLink));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        Label fileSizeLabel = new Label("File Size: " + dp.fileSize + " MB");
        fileSizeLabel.setTextFill(Color.web("#555"));
        fileSizeLabel.setFont(Font.font("Arial", 15));
        fileSizeLabel.setMaxWidth(320);
        fileSizeLabel.setAlignment(Pos.CENTER_LEFT);
        content.getChildren().addAll( linkLabel,fileSizeLabel);

        if (p instanceof DigitalDownload) {
            DigitalDownload dd = (DigitalDownload) p;
            
            Label formatLabel = new Label("Format: " + dd.format);
            formatLabel.setTextFill(Color.web("#555"));
            formatLabel.setFont(Font.font("Arial", 15));
            formatLabel.setMaxWidth(320);
            formatLabel.setAlignment(Pos.CENTER_LEFT);
            content.getChildren().add(formatLabel);
            
            Label downloadLimitLabel = new Label("Downloads remaining: " + dd.downloadLimit);
            downloadLimitLabel.setTextFill(Color.web("#555"));
            downloadLimitLabel.setFont(Font.font("Arial", 15));
            downloadLimitLabel.setMaxWidth(320);
            downloadLimitLabel.setAlignment(Pos.CENTER_LEFT);
            content.getChildren().add(downloadLimitLabel);
        }
        
        if (p instanceof SoftwareLicense sl) {
            Label licenseKeyLabel = new Label("License Key: " + sl.licenseKey);
            licenseKeyLabel.setTextFill(Color.web("#555"));
            licenseKeyLabel.setFont(Font.font("Arial", 15));
            licenseKeyLabel.setMaxWidth(320);
            licenseKeyLabel.setAlignment(Pos.CENTER_LEFT);

            Label expiryLabel = new Label("Expires: " + sl.expiryDate);
            expiryLabel.setTextFill(Color.web("#555"));
            expiryLabel.setFont(Font.font("Arial", 15));
            expiryLabel.setAlignment(Pos.CENTER_LEFT);

            Button activateBtn = new Button("Activate License");
            activateBtn.setStyle("-fx-background-color: linear-gradient(to right, #00c6ff, #0072ff); -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-radius: 8;");
            
            boolean[] activated = {false};
            activateBtn.setOnMouseExited(ev -> {
                if (!activated[0]) {
                    activateBtn.setStyle("-fx-background-color: linear-gradient(to right, #00c6ff, #0072ff); -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-radius: 8;");
                }
            });

            activateBtn.setOnMouseClicked(ev -> {
                if (sl.activateLicense()) {
                    activated[0] = true;
                    activateBtn.setText("✓ Activated");
                    activateBtn.setStyle("-fx-background-color: #0d9488; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8;");
                } else {
                    activated[0] = true;
                    activateBtn.setText("✗ License Expired");
                    activateBtn.setStyle("-fx-background-color: #e53e3e; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8;");
                }
            });

            HBox expiryBox = new HBox(15, expiryLabel, activateBtn);
            expiryBox.setAlignment(Pos.CENTER_LEFT);

            content.getChildren().addAll(licenseKeyLabel, expiryBox);
        }
    }

        content.getChildren().addAll(HBoxPrice,buyBtn);

        Scene detailScene = new Scene(content, 420, 750);
        detailStage.setScene(detailScene);
        detailStage.setResizable(false);
        detailStage.show();
    });

    card.getChildren().addAll(imageView, nameLabel, priceLabel, addBtn);
    return card;}
    //================ Searching =====================================================

   private void searchProducts(String keyword) {

    productsPane.getChildren().clear();

    Product[] allProducts = {
        c1,c2,
        laptop1,laptop2,
        phone1,phone2,
        s1,s2,
        d1,d2
    };

    for(Product p : allProducts){

        if(p.name.toLowerCase().contains(keyword.toLowerCase())){
            productsPane.getChildren().add(createProductCard(p));
        }

    }
}
   
   }
