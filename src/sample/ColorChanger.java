package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

public class ColorChanger extends Application {
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

        stage.setScene(new Scene(root, 300, 275));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
