package itsepalvelupos.ui;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import itsepalvelupos.database.AccountDao;
import itsepalvelupos.database.Database;
import itsepalvelupos.database.ProductDao;
import itsepalvelupos.domain.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class PosUI extends Application {

    private StoreService storeService;
    private Scene loginWindow;
    private Scene mainWindow;
    private Scene userWindow;
    private String currentUser;

    @Override
    public void init() throws Exception {
        storeService = new StoreService("pos.db", 1);
    }

    @Override
    public void stop() throws SQLException {
    }

    @Override
    public void start(Stage primaryStage)  {
        // Sisäänkirjautuminen


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

        JFXButton createButton = new JFXButton("Lisää käyttäjä");
        createButton.getStyleClass().add("button-raised");

        HBox hotBoxButton = new HBox(10);
        hotBoxButton.setAlignment(Pos.BOTTOM_RIGHT);
        hotBoxButton.getChildren().add(createButton);
        grid.add(hotBoxButton, 1, 4);

        JFXButton loginButton = new JFXButton("Kirjaudu sisään");
        loginButton.getStyleClass().add("button-raised");

        HBox loginHotBoxButton = new HBox(10);
        loginHotBoxButton.setAlignment(Pos.BOTTOM_RIGHT);
        loginHotBoxButton.getChildren().add(loginButton);
        grid.add(loginHotBoxButton, 0, 4);

        final Text actionTarget = new Text();
        grid.add(actionTarget, 0, 6);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actionTarget.setFill(Color.FIREBRICK);
                if ((!userTextField.getText().isEmpty() && !passwordBox.getText().isEmpty())) {
                    actionTarget.setText("Käyttäjä tunnus: " + userTextField.getText() + " Salasana:" + passwordBox.getText());
                    try {
                        if (storeService.getAccountService().login(userTextField.getText(), passwordBox.getText())) {
                            primaryStage.setScene(mainWindow);
                        }
                    } catch(Exception ex) {
                        actionTarget.setText("Salasana on väärin");
                    }
                } else {
                    actionTarget.setText("Käyttäjätunnus tai salasana puuttuu");
                }
                System.out.println(storeService.getAccountService().getCurrentUser());
            }
        });

        createButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actionTarget.setFill(Color.FIREBRICK);
                if ((!userTextField.getText().isEmpty() && !passwordBox.getText().isEmpty())) {
                    actionTarget.setText("Käyttäjä tunnus: " + userTextField.getText() + " Salasana:" + passwordBox.getText());
                    try {
                        System.out.println("Lisättiin käyttäjä: " + userTextField.getText());
                        storeService.getAccountService().createUser(userTextField.getText(), passwordBox.getText(), false, 0);
                        storeService.getAccountService().login(userTextField.getText(), passwordBox.getText());
                        currentUser = storeService.getAccountService().getCurrentUser().getUsername();
                        primaryStage.setScene(userWindow);
                    } catch(Exception ex) {
                        System.out.printf("SQL Exception");
                    }
                } else {
                    actionTarget.setText("Käyttäjätunnus tai salasana puuttuu");
                }
            }
        });


        loginWindow = new Scene(grid, 800, 600);
        loginWindow.getStylesheets().add("itsepalvelupos/ui/stylesheet.css");

        // Pääikkuna

        ScrollPane todoScollbar = new ScrollPane();
        BorderPane mainPane = new BorderPane(todoScollbar);
        mainWindow = new Scene(mainPane, 300, 250);

        // Käyttäjä

        GridPane userGrid = new GridPane();
        userGrid.setAlignment(Pos.CENTER);
        userGrid.setHgap(10);
        userGrid.setVgap(10);
        userGrid.setPadding(new Insets(25, 25, 25, 25));

        if (storeService.getAccountService().getCurrentUser()!=null) {
            currentUser = storeService.getAccountService().getCurrentUser().getUsername();
            System.out.println(currentUser);
        }

        Text cashTitle = new Text("Lisää rahaa nykyiselle käyttäjälle:");

        cashTitle.setFont(Font.font("Verdana", FontWeight.NORMAL, 30));
        userGrid.add(cashTitle, 0, 0, 2, 1);

        Label cash = new Label("Summa:");
        userGrid.add(cash, 0, 1);

        JFXTextField cashTextField = new JFXTextField();
        cashTextField.setLabelFloat(true);
        userGrid.add(cashTextField, 1, 1);

        JFXButton cashButton = new JFXButton("Lisää");
        cashButton.getStyleClass().add("button-raised");

        HBox cashHotBoxButton = new HBox(10);
        cashHotBoxButton.setAlignment(Pos.BOTTOM_RIGHT);
        cashHotBoxButton.getChildren().add(cashButton);
        userGrid.add(cashHotBoxButton, 1, 4);

        cashButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actionTarget.setFill(Color.FIREBRICK);
                if ((!cashTextField.getText().isEmpty())) {
                    actionTarget.setText("Summa: " + cashTextField.getText());
                        try {
                            storeService.getAccountService().changeBalance(Integer.parseInt(cashTextField.getText()));
                            primaryStage.setScene(mainWindow);
                    } catch(Exception ex) {
                        System.out.printf("SQL Exception");
                    }
                } else {
                    actionTarget.setText("Käyttäjätunnus tai salasana puuttuu");
                }
            }
        });

        userWindow = new Scene(userGrid, 600, 200);

        // Luodaan primary stage

        primaryStage.setTitle("itsepalveluPOS");
        primaryStage.setScene(loginWindow);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e-> {
            System.out.println("Suljetaan sovellus");
        });
    }


    public static void main(String[] args) throws Exception {
        launch(PosUI.class);
    }
}