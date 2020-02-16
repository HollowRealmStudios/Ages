package hollowrealm.studios.launcher;

import com.jfoenix.controls.JFXButton;
import hollowrealm.studios.game.Starter;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;

import java.io.File;

import static javafx.scene.paint.Color.RED;

public class GameTab extends Tab {

    public final JFXButton launchButton = LauncherUtils.usePref(new JFXButton("Launch"), 800, 600);

    public GameTab(Stage stage) {
        setClosable(false);
        setText("Game");
        launchButton.setOnAction(actionEvent -> {
            boolean errored = false;
            if (LauncherUtils.isInvalidInput(Launcher.SETTINGS_TAB.widthField.getText())) {
                Launcher.TAB_PANE.getSelectionModel().select(Launcher.SETTINGS_TAB);
                Launcher.SETTINGS_TAB.widthField.setBackground(new Background(new BackgroundFill(RED, CornerRadii.EMPTY, Insets.EMPTY)));
                errored = true;
            } else Launcher.CONFIG.setWidth(Integer.parseInt(Launcher.SETTINGS_TAB.widthField.getText()));
            if (LauncherUtils.isInvalidInput(Launcher.SETTINGS_TAB.heightField.getText())) {
                Launcher.TAB_PANE.getSelectionModel().select(Launcher.SETTINGS_TAB);
                Launcher.SETTINGS_TAB.heightField.setBackground(new Background(new BackgroundFill(RED, CornerRadii.EMPTY, Insets.EMPTY)));
                errored = true;
            } else Launcher.CONFIG.setHeight(Integer.parseInt(Launcher.SETTINGS_TAB.heightField.getText()));
            if (!errored) {
                Launcher.CONFIG.setDefaultVolume((int) Launcher.SETTINGS_TAB.volumeSlider.getValue());
                ConfigManager.safe(Launcher.CONFIG, new File("Game.config"));
                stage.close();
                System.gc();
                Starter.start(Launcher.CONFIG, Launcher.PLUGIN_MANAGER != null ? Launcher.PLUGIN_MANAGER.getPlugins() : null);
            }
        });
        setContent(launchButton);
    }

}
