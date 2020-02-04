/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3_db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author mashael
 */
public class Lab3_DB extends Application {

    DBMangement dBMangement;
    TextField idField;
    TextField fNameField;
    TextField lNameField;
    TextField mNameField;
    TextField emailField;
    TextField phoneField;

    @Override
    public void stop() throws Exception {
        super.stop(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init() throws Exception {
        super.init(); //To change body of generated methods, choose Tools | Templates.

        dBMangement = new DBMangement();

    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setWidth(600);
        primaryStage.setHeight(500);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(5);
        gridPane.setPadding(new Insets(20));

        addLabelsToGridPane(gridPane);

        idField = new TextField();
        idField.setDisable(true);

        fNameField = new TextField();
        fNameField.setPromptText("First Name");

        lNameField = new TextField();
        lNameField.setPromptText("Last Name");

        mNameField = new TextField();
        mNameField.setPromptText("Middle Name");

        emailField = new TextField();
        emailField.setPromptText("Email");

        phoneField = new TextField();
        phoneField.setPromptText("Phone Number");

        gridPane.add(idField, 1, 0);
        gridPane.add(fNameField, 1, 1);
        gridPane.add(mNameField, 1, 2);
        gridPane.add(lNameField, 1, 3);
        gridPane.add(emailField, 1, 4);
        gridPane.add(phoneField, 1, 5);

        gridPane.setAlignment(Pos.CENTER);

        HBox hBox = new HBox();

        Button newbButton = new Button("New");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button firsButton = new Button("First");
        Button previousButton = new Button("Previous");
        previousButton.addEventHandler(ActionEvent.ACTION, (event) -> {
            try {
                User user = dBMangement.getPrUser();
                setFields(user);
            } catch (SQLException ex) {
                Logger.getLogger(Lab3_DB.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Button nextButton = new Button("next");
        nextButton.addEventHandler(ActionEvent.ACTION, (event) -> {
            try {
                User user = dBMangement.getUser();

                if (user != null) {
                    setFields(user);
                }

            } catch (SQLException ex) {
                Logger.getLogger(Lab3_DB.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Button lasButton = new Button("Last");
        updateButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                User user = new User();
                user.setId(Integer.parseInt(idField.getText()));
                user.setfName(fNameField.getText());
                user.setlName(lNameField.getText());
                user.setmName(mNameField.getText());
                user.setEmail(emailField.getText());
                user.setPhone(Integer.parseInt(phoneField.getText()));
                try {
                    dBMangement.updateRow(user);
                } catch (SQLException ex) {
                    Logger.getLogger(Lab3_DB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    dBMangement.deletRow(Integer.parseInt(idField.getText()));
                } catch (SQLException ex) {
                    Logger.getLogger(Lab3_DB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        hBox.getChildren()
                .addAll(newbButton,
                        updateButton,
                        deleteButton,
                        firsButton,
                        previousButton,
                        nextButton,
                        lasButton);

        hBox.setSpacing(10);

        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox();
        vBox.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        vBox.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(gridPane, hBox);
        vBox.setPadding(new Insets(25));

        FlowPane p = new FlowPane();
        p.setPadding(new Insets(200));
        p.getChildren().add(vBox);
        p.setAlignment(Pos.CENTER);

        try {
           User user = dBMangement.getUser();
            setFields(user);
        } catch (SQLException ex) {
            Logger.getLogger(Lab3_DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(p, 300, 250);

        primaryStage.setTitle("Database Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addLabelsToGridPane(GridPane gridPane) {
        gridPane.add(new Label("ID"), 0, 0);
        gridPane.add(new Label("First Name"), 0, 1);
        gridPane.add(new Label("Middle Name"), 0, 2);
        gridPane.add(new Label("Last name"), 0, 3);
        gridPane.add(new Label("Email"), 0, 4);
        gridPane.add(new Label("Phone"), 0, 5);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    void setFields(User user) {
        idField.setText(String.valueOf(user.getId()));
        fNameField.setText(user.getfName());
        mNameField.setText(user.getmName());
        lNameField.setText(user.getlName());
        emailField.setText(user.getEmail());
        phoneField.setText(String.valueOf(user.getPhone()));
    }
}
