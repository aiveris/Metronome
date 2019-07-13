package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class KeyboardInput extends Application {
    @Override
    public void start(Stage stage) {
        AudioClip submarineSound = new AudioClip("https://github.com/opendream/ODOpenAlarm/blob/master/sounds/Bottle.aiff?raw=true");

        TilePane rootPane = new TilePane(Orientation.VERTICAL);
        Scene scene = new Scene(rootPane);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                submarineSound.play();
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
