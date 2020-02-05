package hollowrealm.studios.launcher;

import com.jfoenix.controls.JFXTabPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Launcher extends Application {

    private final Builder builder = new Builder(ConfigManager.load(new File("Game.config")));

    @Override
    public void start(Stage primaryStage) {
        JFXTabPane pane = new JFXTabPane();
        builder.buildGameTab(primaryStage, pane);
        builder.buildModTab(primaryStage);
        builder.buildSettingsTab();
        pane.getTabs().addAll(builder.getGameTab(), builder.getModTab(), builder.getSettingsTab());
        primaryStage.setTitle("Ages Launcher");
        primaryStage.setScene(new Scene(pane, 800, 600));
        primaryStage.show();
    }

}
