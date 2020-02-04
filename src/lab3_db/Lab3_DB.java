/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3_db;

import javafx.application.Application;
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

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setWidth(600);
        primaryStage.setHeight(500);
        
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(5);
        gridPane.setPadding(new Insets(20));

        addLabelsToGridPane(gridPane);

        TextField idField = new TextField();
        idField.setDisable(true);

        TextField fNameField = new TextField();
        fNameField.setPromptText("First Name");

        TextField lNameField = new TextField();
        lNameField.setPromptText("Last Name");

        TextField mNameField = new TextField();
        mNameField.setPromptText("Middle Name");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField phoneField = new TextField();
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
        Button nextButton = new Button("next");
        Button lasButton = new Button("Last");

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
    
        
        vBox.getChildren().addAll(gridPane,hBox);
        vBox.setPadding(new Insets(25));
        
       FlowPane p = new FlowPane();
       p.setPadding(new Insets(200));
       p.getChildren().add(vBox);
       p.setAlignment(Pos.CENTER);
        

        // Border border = BorderFactory.createLineBorder(Color.yellow)
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
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

}
