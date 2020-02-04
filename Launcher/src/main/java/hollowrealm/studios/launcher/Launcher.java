package hollowrealm.studios.launcher;

import com.jfoenix.controls.JFXTabPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

public class Launcher extends Application {

    public void start(Stage primaryStage) {
        JFXTabPane pane = new JFXTabPane();
        pane.getTabs().addAll(
                new Tab("Game") {{
                    setClosable(false);
                }}, new Tab("Mods") {{
                    setClosable(false);
                }}, new Tab("Settings") {{
                    setClosable(false);
                }}
        );
        primaryStage.setTitle("Ages Launcher");
        primaryStage.setScene(new Scene(pane, 800, 600));
        primaryStage.show();
    }
}
