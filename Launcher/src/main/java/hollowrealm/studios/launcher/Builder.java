package hollowrealm.studios.launcher;

import com.jfoenix.controls.*;
import hollowrealm.studios.game.Starter;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import simple.engine.util.GameConfig;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

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
        JFXScrollPane scrollPane = new JFXScrollPane();
        GridPane gridPane = new GridPane();
        JFXButton button = LauncherUtils.usePref(new JFXButton("Select mod folder"), 190);
        JFXTextField pathField = LauncherUtils.usePref(new JFXTextField(), 590);
        gridPane.add(new HBox(pathField, button), 0, 0);
        gridPane.setVgap(15d);
        button.setOnAction(actionEvent -> {
            File f = new DirectoryChooser().showDialog(stage);
            File[] files = f.listFiles();
            if (files != null) {
                try {
                    new PluginManager(f);
                } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < files.length; i++) {
                    gridPane.add(new HBox(new JFXCheckBox(), new Label(files[i].getAbsolutePath())), 0, i + 1);
                }
            }
        });
        scrollPane.setContent(gridPane);
        modTab.setContent(scrollPane);
    }

    public Tab getModTab() {
        return modTab;
    }
}
