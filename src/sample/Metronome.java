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
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Timer;
import java.util.TimerTask;

public class Metronome extends Application {
    boolean ledIsOn = false;
    @Override
    public void start(Stage stage) {
        AudioClip submarineSound = new AudioClip("https://github.com/opendream/ODOpenAlarm/blob/master/sounds/Morse.aiff?raw=true");
        Label ledLabel = new Label("●");

        Timer timer = new Timer();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                timer.cancel();
            }
        });
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ledIsOn = !ledIsOn;
                if (ledIsOn) {
                    ledLabel.setTextFill(Color.RED);
                    submarineSound.play();
                } else {
                    ledLabel.setTextFill(Color.GREEN);
                    submarineSound.play();
                }
            }
        }, 0, 1000);

        TilePane root = new TilePane(Orientation.VERTICAL);
        root.getChildren().add(ledLabel);

        Label programLabel = new Label("METRONOME");
        Label tikskeLabel = new Label("Tiks per beat:");
        TextField tiksTextfield = new TextField();
        Label rateLabel = new Label("Rate:");
        TextField rateTextfield = new TextField();
        Button button1 = new Button("Set");
        Button button2 = new Button("Pause");
        Button button3 = new Button("Restart");
        Label setingLabel = new Label("");

        button1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String handshake = tiksTextfield.getText();
                String name = rateTextfield.getText();
                String seting = handshake + ", " + name + "!";
                setingLabel.setText(seting);

            }
        });
        TilePane rootPane = new TilePane(Orientation.VERTICAL);
        rootPane.getChildren().add(programLabel);
        rootPane.getChildren().add(tikskeLabel);
        rootPane.getChildren().add(tiksTextfield);
        rootPane.getChildren().add(rateLabel);
        rootPane.getChildren().add(rateTextfield);
        rootPane.getChildren().add(button1);
        rootPane.getChildren().add(button2);
        rootPane.getChildren().add(button3);
        rootPane.getChildren().add(setingLabel);

        stage.setScene(new Scene(rootPane, 300, 300));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}