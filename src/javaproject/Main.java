package javaproject;

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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
        "M", "Black", "Cotton",
        0.5, 50.0,
        "Nike", 101, "T-Shirt", 299.99,
        null,
        "Comfortable cotton t-shirt",
        20
);

Clothing c2 = new Clothing(
        "L", "Blue", "Denim",
        1.2, 80.0,
        "Levi's", 102, "Jeans", 799.99,
        null,
        "Classic blue denim jeans",
        15
);

// ================= LAPTOP OBJECTS =================

Laptops laptop1 = new Laptops(
        2, 65, 1.8, 20,
        "Dell", 1, "XPS 15",
        1500,
        null,
        "High performance laptop",
        10,
        "16GB",
        "512GB SSD",
        "Intel i7"
);

Laptops laptop2 = new Laptops(
        1, 60, 1.6, 25,
        "Apple", 2, "MacBook Pro",
        2500,
        null,
        "Apple laptop",
        5,
        "16GB",
        "1TB SSD",
        "M2 Pro"
);

// ================= PHONE OBJECTS =================

Phones phone1 = new Phones(
        2, 15, 0.18, 10,
        "Samsung", 101, "Galaxy S24",
        1200,
        null,
        "Flagship phone",
        20,
        50,
        5000
);

Phones phone2 = new Phones(
        1, 12, 0.17, 12,
        "Apple", 102, "iPhone 15",
        1300,
        null,
        "Apple phone",
        12,
        48,
        4500
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
        100
);

SoftwareLicense s2 = new SoftwareLicense(
        "QWE456-RTY111",
        LocalDate.of(2026, 12, 31),
        1.2,
        "https://download.com/software2",
        202,
        "Photo Editor Pro",
        79.99,
        null,
        "Professional photo editing software",
        50
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
        "Popular music collection",
        200
);

DigitalDownload d2 = new DigitalDownload(
        "PDF",
        3,
        15.2,
        "https://download.com/java_book",
        302,
        "Java Programming Guide",
        29.99,
        null,
        "Complete Java learning ebook",
        80
);
    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();
    // ================= HEADER ===============================================================================

                HBox header = new HBox();
            header.prefHeightProperty().bind(root.heightProperty().multiply(0.07));
            header.setMinHeight(40);
            header.maxHeightProperty().bind(root.heightProperty().multiply(0.07));

            Label title = new Label("E-Commerce Store");
            title.setStyle("""
                -fx-font-size: 32px;
                -fx-font-weight: bold;
                -fx-text-fill: white;
            """);

            header.getChildren().add(title);
            header.setAlignment(Pos.CENTER);
            header.setStyle("""
                -fx-background-color: linear-gradient(to right, #141e30, #243b55);
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
            glow.setColor(Color.web("#00c6ff"));
            glow.setRadius(0);
            title.setEffect(glow);

            Timeline glowPulse = new Timeline(
                new KeyFrame(Duration.ZERO,
                    new KeyValue(glow.radiusProperty(), 0),
                    new KeyValue(glow.spreadProperty(), 0)
                ),
                new KeyFrame(Duration.millis(1500),
                    new KeyValue(glow.radiusProperty(), 25),
                    new KeyValue(glow.spreadProperty(), 0.15)
                ),
                new KeyFrame(Duration.millis(3000),
                    new KeyValue(glow.radiusProperty(), 0),
                    new KeyValue(glow.spreadProperty(), 0)
                )
            );
            glowPulse.setCycleCount(Timeline.INDEFINITE);
            glowPulse.setDelay(Duration.millis(900));
            glowPulse.play();

            // ── Animation 3: Header background color cycle ──
            String[] gradients = {
                "linear-gradient(to right, #141e30, #243b55)",
                "linear-gradient(to right, #0f3460, #16213e)",
                "linear-gradient(to right, #1a1a2e, #16213e)",
                "linear-gradient(to right, #141e30, #243b55)"
            };
            int[] idx = {0};Timeline colorCycle = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
                idx[0] = (idx[0] + 1) % gradients.length;
                header.setStyle("""
                    -fx-background-color: %s;
                    -fx-padding: 25px;
                """.formatted(gradients[idx[0]]));
            }));
            colorCycle.setCycleCount(Timeline.INDEFINITE);
            colorCycle.play();

// ================= LEFT SIDEBAR ============================================
  
            VBox sidebar = new VBox(25);

            sidebar.setPadding(new Insets(25));

            sidebar.prefWidthProperty().bind(root.widthProperty().multiply(0.17));
            sidebar.setMinWidth(220);
           
            sidebar.setStyle("""
                -fx-background-color: linear-gradient(to bottom, #141e30, #243b55);
                
            """);

            // Logo

            Label logo = new Label("E-Commerce\nStore");

            logo.setFont(Font.font("Arial", 28));

            logo.setTextFill(Color.WHITE);

            // Buttons

            Button homeBtn = new Button("🏠 Home");

            Button clothesBtn = new Button("👕 Clothing");

            Button laptopsBtn = new Button("💻 Laptops");

            Button phonesBtn = new Button("📱 Phones");

            Button digitalBtn = new Button("🎮 Digital Products");

            Button clearCartBtn = new Button("🗑 Clear Cart");

// Button Style================================================================

            String menuStyle = """
                -fx-background-color: rgba(255,255,255,0.15);
                -fx-text-fill: white;
                -fx-font-size: 15px;
                -fx-font-weight: bold;
                -fx-background-radius: 12;
                -fx-padding: 12 15 12 15;
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
            String onClick ="""
                        -fx-background-color: white;
                        -fx-text-fill: #141e30;
                        -fx-font-size: 15px;
                        -fx-font-weight: bold;
                        -fx-background-radius: 12;
                        -fx-padding: 12 15 12 15;
                        -fx-cursor: hand;
                    """;
            String delateClick ="""
                        -fx-background-color: #ff4d4d;
                        -fx-text-fill: white;
                        -fx-font-size: 15px;
                        -fx-font-weight: bold;
                        -fx-background-radius: 12;
                        -fx-padding: 12 15 12 15;
                        -fx-cursor: hand;
                    """;
//============================================================================
            homeBtn.setOnMouseEntered(e ->homeBtn.setStyle(onClick));

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
            clothesBtn.setOnMouseEntered(e ->clothesBtn.setStyle(onClick));

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
            laptopsBtn.setOnMouseEntered(e ->laptopsBtn.setStyle(onClick));

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
            phonesBtn.setOnMouseEntered(e ->phonesBtn.setStyle(onClick));

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
            digitalBtn.setOnMouseEntered(e ->digitalBtn.setStyle(onClick));
            
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
            clearCartBtn.setOnMouseEntered(e ->clearCartBtn.setStyle(delateClick));

            clearCartBtn.setOnMouseExited(e -> clearCartBtn.setStyle(menuStyle));
            
            clearCartBtn.setOnAction(e -> {
                cartItemsBox.getChildren().clear();

                Label emptyText = new Label("Your cart is empty");
                Label emptyCart = new Label("🛒");
                emptyText.setFont(Font.font(18));

                emptyText.setTextFill(Color.web("#d1d5db"));

                // Add Empty Content

                cartItemsBox.getChildren().addAll(emptyCart,emptyText);
           
            });
//=============================================================================
            // Add Buttons

            sidebar.getChildren().addAll(
                    logo,
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
                -fx-background-color: linear-gradient(to bottom, #141e30, #243b55);
               
                -fx-border-color: rgba(255,255,255,0.15);
                -fx-border-width: 0 0 0 2;
            """);

            // Cart Title

            Label cartTitle = new Label("🛒 Shopping Cart");

            cartTitle.setFont(Font.font("Arial", 26));

            cartTitle.setTextFill(Color.WHITE);
            
            cartTitle.setWrapText(true);
            cartTitle.setMinHeight(Region.USE_PREF_SIZE);

            // Line

            Separator line = new Separator();

            line.setPrefHeight(2);

            line.setStyle("""
                -fx-background-color: rgba(255,255,255,0.2);
            """);

            // Cart Items Box

            cartItemsBox = new VBox(15);

            cartItemsBox.setAlignment(Pos.TOP_CENTER);

            cartItemsBox.setPrefHeight(500);

            cartItemsBox.setStyle("""
                -fx-background-color: rgba(255,255,255,0.08);
                -fx-background-radius: 18;
                -fx-border-radius: 18;
                -fx-border-color: rgba(255,255,255,0.1);
                -fx-padding: 25;
            """);

            // Empty Cart Icon

            Label emptyCart = new Label("🛒");

            emptyCart.setFont(Font.font(60));

            emptyCart.setTextFill(Color.WHITE);

            // Empty Text

            Label emptyText = new Label("Your cart is empty");

            emptyText.setFont(Font.font(18));

            emptyText.setTextFill(Color.web("#d1d5db"));

            // Add Empty Content

            cartItemsBox.getChildren().addAll(
                    emptyCart,
                    emptyText
            );

            // Checkout Button

            Button checkoutBtn = new Button("Checkout");

            checkoutBtn.setMaxWidth(Double.MAX_VALUE);
            checkoutBtn.setStyle("""
                -fx-background-color: linear-gradient(to right, #00c6ff, #0072ff);
                -fx-text-fill: white;
                -fx-font-size: 18px;
                -fx-font-weight: bold;
                -fx-padding: 14;
                -fx-background-radius: 15;
            """);

            // Hover Effect

            checkoutBtn.setOnMouseEntered(e ->
                    checkoutBtn.setStyle("""
                        -fx-background-color: linear-gradient(to right, #00aadd, #0055cc);
                        -fx-text-fill: white;
                        -fx-font-size: 18px;
                        -fx-font-weight: bold;
                        -fx-padding: 14;
                        -fx-background-radius: 15;
                        -fx-cursor: hand;
                    """)
            );

            checkoutBtn.setOnMouseExited(e ->
                    checkoutBtn.setStyle("""
                        -fx-background-color: linear-gradient(to right, #00c6ff, #0072ff);
                        -fx-text-fill: white;
                        -fx-font-size: 18px;
                        -fx-font-weight: bold;
                        -fx-padding: 14;
                        -fx-background-radius: 15;
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
                -fx-background-color: linear-gradient(to bottom, #141e30, #243b55);
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
                -fx-background: #141e30;
                -fx-background-color: #141e30;
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
        stage.setMinWidth(725);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
   private VBox createProductCard(Product p) {

    VBox card = new VBox(10);
    card.setPadding(new Insets(15));
    card.setPrefWidth(180);
    card.setAlignment(Pos.CENTER);
    card.setStyle("""
        -fx-background-color: rgba(255,255,255,0.08);
        -fx-background-radius: 15;
        -fx-border-color: rgba(255,255,255,0.15);
        -fx-border-radius: 15;
        -fx-cursor: hand;
    """);

//    Label icon = new Label(emoji);
//    icon.setFont(Font.font(50));

    Label nameLabel = new Label(p.name);
    nameLabel.setTextFill(Color.WHITE);
    nameLabel.setFont(Font.font(25));

    Label priceLabel = new Label("$" + p.getFinalPrice());
    priceLabel.setTextFill(Color.web("#22c55e"));
    priceLabel.setFont(Font.font(14));

    Button addBtn = new Button("Add To cart");
    addBtn.setMaxWidth(Double.MAX_VALUE);
    addBtn.setStyle("""
        -fx-background-color: #0d9488;
        -fx-text-fill: white;
        -fx-font-weight: bold;
        -fx-padding: 12;
        -fx-background-radius: 10;
        -fx-cursor: hand;
    """);
    
    addBtn.setOnMouseEntered(e -> addBtn.setStyle("""
        -fx-background-color: #0b7a70 ;
        -fx-text-fill: white;
        -fx-font-weight: bold;
        -fx-padding: 12;
        -fx-background-radius: 10;
        -fx-cursor: hand;
    """));
    addBtn.setOnMouseExited(e -> addBtn.setStyle("""
        -fx-background-color: #0d9488;
        -fx-text-fill: white;
        -fx-font-weight: bold;
        -fx-padding: 12;
        -fx-background-radius: 10;
    """));

    // ── Details Window ===================================
    
    card.setOnMouseClicked(e -> {
    
        Stage detailStage = new Stage();
        detailStage.setTitle(p.name + " - Details");

        VBox content = new VBox(20);
        content.setPadding(new Insets(40));
        content.setAlignment(Pos.CENTER);
        content.setStyle("""
            -fx-background-color: linear-gradient(to bottom, #141e30, #243b55);
        """);

// icon
//        Label bigIcon = new Label(emoji);
//        bigIcon.setFont(Font.font(90));
//        bigIcon.setTextFill(Color.WHITE);

// Name
        Label detailName = new Label(p.name);
        detailName.setFont(Font.font("Arial", 28));
        detailName.setTextFill(Color.WHITE);

// Rating
// Label detailRating = new Label(rating);
// detailRating.setFont(Font.font(18));
// detailRating.setTextFill(Color.web("#FFAD33"));
// Description
        Label detailDesc = new Label(p.description);
        detailDesc.setTextFill(Color.web("#d1d5db"));
        detailDesc.setFont(Font.font(15));
        detailDesc.setWrapText(true);
        detailDesc.setMaxWidth(320);
        detailDesc.setAlignment(Pos.CENTER);
        detailDesc.setTextAlignment(TextAlignment.CENTER);

        // Price
        Label detailPrice = new Label("$" + p.getFinalPrice());
        detailPrice.setFont(Font.font("Arial", 32));
        detailPrice.setTextFill(Color.web("#22c55e"));

        // Buy button
        Button buyBtn = new Button("🛒  Add to Cart");
        buyBtn.setPrefWidth(280);
        buyBtn.setStyle("""
            -fx-background-color: linear-gradient(to right, #00c6ff, #0072ff);
            -fx-text-fill: white;
            -fx-font-size: 16px;
            -fx-font-weight: bold;
            -fx-padding: 14;
            -fx-background-radius: 15;
        """);
        buyBtn.setOnMouseEntered(ev -> buyBtn.setStyle("""
            -fx-background-color: linear-gradient(to right, #00aadd, #0055cc);
            -fx-text-fill: white;
            -fx-font-size: 16px;
            -fx-font-weight: bold;
            -fx-padding: 14;
            -fx-background-radius: 15;
            -fx-cursor: hand;
        """));
        buyBtn.setOnMouseExited(ev -> buyBtn.setStyle("""
            -fx-background-color: linear-gradient(to right, #00c6ff, #0072ff);
            -fx-text-fill: white;
            -fx-font-size: 16px;
            -fx-font-weight: bold;
            -fx-padding: 14;
            -fx-background-radius: 15;
        """));
        buyBtn.setOnAction(ev -> detailStage.close());

        content.getChildren().addAll(
//            bigIcon, detailName, detailRating,
             detailDesc, detailPrice, buyBtn
        );

        Scene detailScene = new Scene(content, 420, 520);
        detailStage.setScene(detailScene);
        detailStage.setResizable(false);
        detailStage.show();
    });

    card.getChildren().addAll( nameLabel, priceLabel, addBtn);
    return card;
}
}