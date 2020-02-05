package hollowrealm.studios.launcher;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import hollowrealm.studios.game.Starter;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import simple.engine.util.GameConfig;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

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

    public void buildModTab(Stage stage) {
        modTab.setClosable(false);
        FlowPane flowPane = new FlowPane();
        ScrollPane scrollPane = LauncherUtils.usePref(new ScrollPane(), 790);
        FlowPane scrollingFlowPane = LauncherUtils.usePref(new FlowPane(), 790);
        JFXButton button = LauncherUtils.usePref(new JFXButton("Select mod folder"), 200,30);
        JFXTextField path = LauncherUtils.usePref(new JFXTextField(), 600,30);
        button.setOnAction(actionEvent -> {
            File f = new DirectoryChooser().showDialog(stage);
            if (f != null) {
                path.setText(f.toString());
                Arrays.stream(Objects.requireNonNull(f.list())).forEach(s -> scrollingFlowPane.getChildren().add(new Label(s)));
            }
        });
        scrollingFlowPane.setVgap(10d);
        flowPane.setVgap(10d);
        scrollPane.setContent(scrollingFlowPane);
        flowPane.getChildren().add(new HBox(path, button));
        flowPane.getChildren().add(scrollPane);
        modTab.setContent(flowPane);
    }

    public Tab getModTab() {
        return modTab;
    }
}
