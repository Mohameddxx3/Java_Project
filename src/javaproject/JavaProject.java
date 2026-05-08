package javaproject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class JavaProject extends Application {

    @Override
    public void start(Stage stage) {

        // TextFields
        TextField num1 = new TextField();
        TextField num2 = new TextField();
        TextField result = new TextField();

        result.setEditable(false);

        // Buttons
        Button add = new Button("+");
        Button sub = new Button("-");
        Button mul = new Button("*");
        Button div = new Button("/");

        // Action
        add.setOnAction(e -> calculate(num1, num2, result, "+"));
        sub.setOnAction(e -> calculate(num1, num2, result, "-"));
        mul.setOnAction(e -> calculate(num1, num2, result, "*"));
        div.setOnAction(e -> calculate(num1, num2, result, "/"));

        // Layout
        HBox buttons = new HBox(10, add, sub, mul, div);
        buttons.setAlignment(Pos.CENTER);

        VBox root = new VBox(10,
                new Label("First Number"),
                num1,
                new Label("Second Number"),
                num2,
                buttons,
                new Label("Result"),
                result
        );

        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(root, 300, 300);

        stage.setTitle("Mini Calculator");
        stage.setScene(scene);
        stage.show();
    }

    private void calculate(TextField n1, TextField n2,
                           TextField result, String op) {

        try {
            double a = Double.parseDouble(n1.getText());
            double b = Double.parseDouble(n2.getText());
            double r = 0;

            switch (op) {
                case "+":
                    r = a + b;
                    break;

                case "-":
                    r = a - b;
                    break;

                case "*":
                    r = a * b;
                    break;

                case "/":
                    if (b == 0) {
                        result.setText("Cannot divide by 0");
                        return;
                    }
                    r = a / b;
                    break;
            }

            result.setText(String.valueOf(r));

        } catch (Exception e) {
            result.setText("Invalid Input");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}