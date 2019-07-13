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

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Metronome2 extends Application {
    boolean running = false;
    int nbOfTicks = 4;
    int beatsPerMinute = 20;
    Timer timer;
    int currentTick = -1;
    List<Label> tickLabels;

    @Override
    public void start(Stage stage) {
        AudioClip submarineSound = new AudioClip("https://github.com/opendream/ODOpenAlarm/blob/master/sounds/Morse.aiff?raw=true");
        AudioClip bottleSound = new AudioClip("https://github.com/opendream/ODOpenAlarm/blob/master/sounds/Bottle.aiff?raw=true");

        Label nbOfTicksLabel = new Label("Number of ticks: ");
        TextField nbOfTicksTextField = new TextField(String.valueOf(nbOfTicks));
        Label beatsPerMinuteLabel = new Label("Beats per minute: ");
        TextField beatsPerMinuteTextField = new TextField(String.valueOf(beatsPerMinute));
        Button controlButton = new Button("Start/Stop");

        TilePane ticksPane = new TilePane(Orientation.HORIZONTAL);

        controlButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!running) {
                    String nbOfTicksText = nbOfTicksTextField.getText();
                    nbOfTicks = Integer.parseInt(nbOfTicksText);

                    String beatsPerMinuteText = beatsPerMinuteTextField.getText();
                    beatsPerMinute = Integer.parseInt(beatsPerMinuteText);

                    tickLabels = new ArrayList<>();
                    for (int i = 0; i < nbOfTicks; i++) {
                        tickLabels.add(new Label("●"));
                    }
                    ticksPane.getChildren().setAll(tickLabels);

                    timer = new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            currentTick = (currentTick + 1) % nbOfTicks;

                            for (int i = 0; i < nbOfTicks; i++) {
                                Label tickLabel = tickLabels.get(i);
                                if (i <= currentTick) {
                                    tickLabel.setTextFill(Color.RED);
                                } else {
                                    tickLabel.setTextFill(Color.GRAY);
                                }
                            }

                            if (currentTick == 0) {
                                submarineSound.play();
                            } else {
                                bottleSound.play();
                            }
                        }
                    }, 0, 60_000 / (beatsPerMinute * nbOfTicks));
                } else {
                    timer.cancel();
                }

                running = !running;
            }
        });

        TilePane rootPane = new TilePane(Orientation.VERTICAL);
        rootPane.getChildren().addAll(nbOfTicksLabel, nbOfTicksTextField);
        rootPane.getChildren().addAll(beatsPerMinuteLabel, beatsPerMinuteTextField);
        rootPane.getChildren().add(controlButton);
        rootPane.getChildren().add(ticksPane);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                if (timer != null) {
                    timer.cancel();
                }
            }
        });
        stage.setTitle("Metronome");
        stage.setScene(new Scene(rootPane, 500, 500));
        stage.show();
    }
    void parseIntFromTextField (TextField textField) {
        String beatsPerMinuteText = textField.getText();
        beatsPerMinute = Integer.parseInt(beatsPerMinuteText);

    }


    public static void main(String[] args) {
        launch();
    }
}