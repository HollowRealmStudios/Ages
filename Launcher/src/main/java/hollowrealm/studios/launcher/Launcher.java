package hollowrealm.studios.launcher;

import com.jfoenix.controls.*;
import com.sun.javafx.collections.ObservableListWrapper;
import hollowrealm.studios.game.Starter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import simple.engine.Engine;
import simple.engine.util.GameConfig;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

import static javafx.scene.layout.VBox.setMargin;
import static javafx.scene.paint.Color.RED;

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
