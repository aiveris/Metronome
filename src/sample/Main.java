package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Label handshakeLabel = new Label("Handshake:");
        TextField handshakeTextField = new TextField();
        Label nameLabel = new Label("Name:");
        TextField nameTextField = new TextField();
        Button greetButton = new Button("Greet!");
        Label greetingLabel = new Label("");

        greetButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String handshake = handshakeTextField.getText();
                String name = nameTextField.getText();

                String greeting = handshake + ", " + name + "!";

                greetingLabel.setText(greeting);
            }
        });

        TilePane rootPane = new TilePane(Orientation.VERTICAL);
        rootPane.getChildren().add(handshakeLabel);
        rootPane.getChildren().add(handshakeTextField);
        rootPane.getChildren().add(nameLabel);
        rootPane.getChildren().add(nameTextField);
        rootPane.getChildren().add(greetButton);
        rootPane.getChildren().add(greetingLabel);

        stage.setScene(new Scene(rootPane, 300, 300));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}