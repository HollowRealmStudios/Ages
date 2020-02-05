package hollowrealm.studios.launcher;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import hollowrealm.studios.game.Starter;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import simple.engine.util.GameConfig;

import java.io.File;

import static javafx.scene.layout.Region.USE_PREF_SIZE;
import static javafx.scene.paint.Color.RED;

public class Builder {

    private final JFXButton launchButton = new JFXButton();
    private final JFXTextField widthField = new JFXTextField();
    private final JFXTextField heightField = new JFXTextField();
    private final JFXSlider volumeSlider = new JFXSlider();
    private final Tab gameTab = new Tab("Game");
    private final Tab settingsTab = new Tab("Settings");
    private final Tab modTab = new Tab("Mods");
    private GameConfig config;

    public Builder(GameConfig config) {
        this.config = config;
    }

    public void buildGameTab(Stage stage, JFXTabPane pane) {
        gameTab.setText("Game");
        gameTab.setClosable(false);
        launchButton.setText("Launch");
        launchButton.setMinWidth(USE_PREF_SIZE);
        launchButton.setMinHeight(USE_PREF_SIZE);
        launchButton.setMaxWidth(USE_PREF_SIZE);
        launchButton.setMaxHeight(USE_PREF_SIZE);
        launchButton.setPrefWidth(800);
        launchButton.setPrefHeight(600);
        launchButton.setOnAction(actionEvent -> {
            boolean errored = false;
            if (LauncherUtils.isInvalidInput(widthField.getText())) {
                pane.getSelectionModel().select(settingsTab);
                widthField.setBackground(new Background(new BackgroundFill(RED, CornerRadii.EMPTY, Insets.EMPTY)));
                errored = true;
            } else config.setWidth(Integer.parseInt(widthField.getText()));
            if (LauncherUtils.isInvalidInput(heightField.getText())) {
                pane.getSelectionModel().select(settingsTab);
                heightField.setBackground(new Background(new BackgroundFill(RED, CornerRadii.EMPTY, Insets.EMPTY)));
                errored = true;
            } else config.setHeight(Integer.parseInt(heightField.getText()));
            if (!errored) {
                config.setDefaultVolume((int) volumeSlider.getValue());
                ConfigManager.safe(config, new File("Game.config"));
                stage.close();
                Starter.start(config);
            }
        });
        gameTab.setContent(launchButton);
    }

    public Tab getGameTab() {
        return gameTab;
    }

    public Tab getSettingsTab() {
        return settingsTab;
    }

    public void buildSettingsTab() {
        volumeSlider.setMin(0);
        volumeSlider.setMax(100);
        volumeSlider.setValue(config.getDefaultVolume());
        widthField.setText(String.valueOf(config.getWidth()));
        heightField.setText(String.valueOf(config.getHeight()));
        System.out.println(config.getWidth());
        System.out.println(config.getHeight());
        settingsTab.setClosable(false);
        VBox vBox = new VBox();
        vBox.setMinWidth(USE_PREF_SIZE);
        vBox.setMinHeight(USE_PREF_SIZE);
        vBox.setMaxWidth(USE_PREF_SIZE);
        vBox.setMaxHeight(USE_PREF_SIZE);
        vBox.setPrefWidth(800);
        vBox.setPrefHeight(500);
        vBox.getChildren().addAll(new Label("Volume"),
                volumeSlider,
                new Label("Width"),
                widthField,
                new Label("Height"),
                heightField);
        settingsTab.setContent(vBox);
    }

    public void buildModTab() {
        modTab.setClosable(false);
    }

    public Tab getModTab() {
        return modTab;
    }
}
