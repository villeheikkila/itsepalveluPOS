package itsepalvelupos.ui;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import itsepalvelupos.database.AccountDao;
import itsepalvelupos.database.Database;
import itsepalvelupos.database.ProductDao;
import itsepalvelupos.domain.Account;
import itsepalvelupos.domain.AccountService;
import itsepalvelupos.domain.ProductService;
import itsepalvelupos.domain.Store;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.SQLException;


public class PosUI extends Application {

    private Database database;

    @Override
    public void init() throws Exception {
        database = new Database("pos.db");
        database.initDatabase();
        AccountDao accountdao = new AccountDao(database);
        ProductDao productDao = new ProductDao(database);
        ProductService productService = new ProductService(productDao);
        AccountService accountservice = new AccountService(accountdao);
        productService.addProduct("cokis", 10, 3);
        accountservice.createUser("Pekka", "moi", false);
        accountdao.findAll();
        Account account = new Account(1, "Pekka", "hei", false);
        accountdao.update(account);

        productService.listProducts();
        productService.buyProduct(1);
        productService.listProducts();

    }

    @Override
    public void stop() {
        database.removeDatabase();
    }

    @Override
    public void start(Stage primaryStage)  {

        primaryStage.setTitle("itsepalveluPOS");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text title = new Text("Tervetuloa!");
        title.setFont(Font.font("Verdana", FontWeight.NORMAL, 30));
        grid.add(title, 0, 0, 2, 1);

        Label userName = new Label("Käyttäjätunnus:");
        grid.add(userName, 0, 1);

        JFXTextField userTextField = new JFXTextField();
        userTextField.setLabelFloat(true);
        grid.add(userTextField, 1, 1);

        Label password = new Label("Salasana:");
        grid.add(password, 0, 2);

        JFXPasswordField passwordBox = new JFXPasswordField();
        grid.add(passwordBox, 1, 2);

        JFXButton button = new JFXButton("Lisää käyttäjä");
        button.getStyleClass().add("button-raised");

        HBox hotBoxButton = new HBox(10);
        hotBoxButton.setAlignment(Pos.BOTTOM_RIGHT);
        hotBoxButton.getChildren().add(button);
        grid.add(hotBoxButton, 1, 4);

        final Text actionTarget = new Text();
        grid.add(actionTarget, 1, 6);

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actionTarget.setFill(Color.FIREBRICK);
                if ((!userTextField.getText().isEmpty() && !passwordBox.getText().isEmpty())) {
                    actionTarget.setText("Käyttäjä tunnus: " + userTextField.getText() + " Salasana:" + passwordBox.getText());
                } else {
                    actionTarget.setText("Käyttäjätunnus tai salasana puuttuu");
                }
            }
        });

        Scene scene = new Scene(grid, 600, 350);
        scene.getStylesheets().add("itsepalvelupos/ui/stylesheet.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
        launch(PosUI.class);
    }
}