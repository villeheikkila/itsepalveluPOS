package itsepalvelupos.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import itsepalvelupos.domain.PosService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.util.Optional;


public class PosUI extends Application {

    private PosService posService;
    private Scene loginWindow;
    private Scene mainWindow;
    private Scene dataWindow;
    private Scene storeWindow;
    private Scene userWindow;


    @Override
    public void init() throws Exception {
    }

    @Override
    public void stop() throws SQLException {
    }

    @Override
    public void start(Stage primaryStage)  {
        // Tietokannan luominen

        GridPane dataGrid = new GridPane();
        dataGrid.setAlignment(Pos.CENTER);
        dataGrid.setHgap(10);
        dataGrid.setVgap(10);
        dataGrid.setPadding(new Insets(25, 25, 25, 25));

        Text dataTitle = new Text("Tervetuloa! ");

        dataTitle.setFont(Font.font("Verdana", FontWeight.NORMAL, 30));
        dataGrid.add(dataTitle, 0, 0, 2, 1);

        Label databaseName = new Label("Tietokannan nimi:");
        dataGrid.add(databaseName, 0, 1);

        JFXTextField dataTextField = new JFXTextField();
        dataTextField.setLabelFloat(true);
        dataGrid.add(dataTextField, 1, 1);

        JFXButton dataButton = new JFXButton("Lisää");
        dataButton.getStyleClass().add("button-raised");

        HBox dataHotBoxButton = new HBox(10);
        dataHotBoxButton.setAlignment(Pos.BOTTOM_RIGHT);
        dataHotBoxButton.getChildren().add(dataButton);
        dataGrid.add(dataHotBoxButton, 1, 4);

        final Text dataActionTarget = new Text();
        dataGrid.add(dataActionTarget, 0, 6);

        dataButton.setOnAction(e -> {
            dataActionTarget.setFill(Color.FIREBRICK);
            if ((!dataTextField.getText().isEmpty())) {
                try {
                    posService = new PosService(dataTextField.getText());

                    if (posService.isNewDatabase()) {
                        primaryStage.setScene(storeWindow);
                    } else {
                        primaryStage.setScene(loginWindow);
                    }
                } catch(Exception ex) {
                    System.out.printf("SQL Exception");
                }
            } else {
                dataActionTarget.setText("Tietokannan nimi puuttuu");
            }
        });

        dataWindow = new Scene(dataGrid, 800, 600);
        dataWindow.getStylesheets().add("itsepalvelupos/ui/stylesheet.css");

        // Kaupan luominen

        GridPane storeGrid = new GridPane();
        storeGrid.setAlignment(Pos.CENTER);
        storeGrid.setHgap(10);
        storeGrid.setVgap(10);
        storeGrid.setPadding(new Insets(25, 25, 25, 25));

        Text storeTitle = new Text("Luodaan uusi kauppa!");
        storeTitle.setFont(Font.font("Verdana", FontWeight.NORMAL, 30));
        storeGrid.add(storeTitle, 0, 0, 2, 1);

        Label storeName = new Label("Kaupan nimi");
        storeGrid.add(storeName, 0, 1);

        JFXTextField storeTextField = new JFXTextField();
        storeTextField.setLabelFloat(true);
        storeGrid.add(storeTextField, 1, 1);

        Label storeCash = new Label("Kaupan kassan saldo alussa:");
        storeGrid.add(storeCash, 0, 2);

        JFXTextField cashField = new JFXTextField();
        storeTextField.setLabelFloat(true);
        storeGrid.add(cashField, 1, 2);

        JFXButton createStoreButton = new JFXButton("Lisää kauppa");
        createStoreButton.getStyleClass().add("button-raised");

        HBox storeHotBoxButton = new HBox(10);
        storeHotBoxButton.setAlignment(Pos.BOTTOM_RIGHT);
        storeHotBoxButton.getChildren().add(createStoreButton);
        storeGrid.add(storeHotBoxButton, 1, 4);

        final Text storeActionTarget = new Text();
        storeGrid.add(storeActionTarget, 0, 6);

        createStoreButton.setOnAction(e -> {
            storeActionTarget.setFill(Color.FIREBRICK);
            if ((!storeTextField.getText().isEmpty() && !cashField.getText().isEmpty())) {
                try {
                    if (posService.getStoreService().createStore(storeTextField.getText(), Integer.parseInt(cashField.getText()))) {
                        System.out.println("Lisättiin kauppa: " + storeTextField.getText());
                        primaryStage.setScene(loginWindow);
                    } else {
                        storeActionTarget.setText("Kauppa on jo olemassa tai sen nimi on liian lyhyt");
                    }
                } catch(Exception ex) {
                    storeActionTarget.setText("Kassan saldon täytyy olla kokonaisluku");
                    System.out.println("SQL Exception");
                }
            } else {
                storeActionTarget.setText("Nimi tai kassan alkusumma puuttuu");
            }
        });

        storeWindow = new Scene(storeGrid, 800, 600);
        storeWindow.getStylesheets().add("itsepalvelupos/ui/stylesheet.css");

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

        loginButton.setOnAction(e -> {
            actionTarget.setFill(Color.FIREBRICK);
            if ((!userTextField.getText().isEmpty() && !passwordBox.getText().isEmpty())) {
                try {
                    if (posService.getAccountService().login(userTextField.getText(), passwordBox.getText())) {
                        primaryStage.setScene(userWindow);
                    } else {
                        actionTarget.setText("Käyttäjätunnus tai salasana on väärin");
                    }
                } catch(Exception ex) {
                    actionTarget.setText("Salasana on väärin");
                }
            } else {
                actionTarget.setText("Käyttäjätunnus tai salasana puuttuu");
            }
        });

        createButton.setOnAction(e -> {
            actionTarget.setFill(Color.FIREBRICK);
            if ((!userTextField.getText().isEmpty() && !passwordBox.getText().isEmpty())) {
                try {
                    if (posService.getAccountService().createUser(userTextField.getText(), passwordBox.getText(), false, 0)) {
                        System.out.println("Lisättiin käyttäjä: " + userTextField.getText());
                        actionTarget.setText("Lisättiin käyttäjätunnus: " + userTextField.getText() + ". Voit nyt kirjautua sisään!");
                    } else {
                        actionTarget.setText("Käyttäjätunnus on jo olemassa tai salasana tai käyttäjätunnus on liian lyhyt");
                    }
                } catch(Exception ex) {
                    System.out.printf("SQL Exception");
                }
            } else {
                actionTarget.setText("Käyttäjätunnus tai salasana puuttuu");
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

        cashButton.setOnAction(e -> {
            actionTarget.setFill(Color.FIREBRICK);
            if ((!cashTextField.getText().isEmpty())) {
                actionTarget.setText("Summa: " + cashTextField.getText());
                    try {
                        posService.getAccountService().changeBalance(Integer.parseInt(cashTextField.getText()));
                        primaryStage.setScene(mainWindow);
                } catch(Exception ex) {
                    System.out.printf("SQL Exception");
                }
            } else {
                actionTarget.setText("Käyttäjätunnus tai salasana puuttuu");
            }
        });

        userWindow = new Scene(userGrid, 600, 200);



        // Luodaan primary stage

        primaryStage.setTitle("itsepalveluPOS");
        primaryStage.setScene(dataWindow);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e-> {
            deleteDatabaseDialog();
            System.out.println("Program will be closed");
        });
    }

    private void deleteDatabaseDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Haluatko poistaa tietokannan?");
        alert.setHeaderText("Poistetaanko tietokanta?");

        ButtonType deleteDatabase = new ButtonType("Poista");
        ButtonType retainDatabase = new ButtonType("Säilytä");

        alert.getButtonTypes().setAll(deleteDatabase, retainDatabase);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == deleteDatabase){
            posService.deleteDatabase();
        } else if (result.get() == retainDatabase) {
            System.out.println("Tietokantaa ei poisteta");
        }
    }

    public static void main(String[] args) throws Exception {
        launch(PosUI.class);
    }
}