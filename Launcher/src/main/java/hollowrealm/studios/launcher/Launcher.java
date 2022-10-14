package hollowrealm.studios.launcher;

import com.jfoenix.controls.JFXTabPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import simple.engine.util.GameConfig;

import java.io.File;

public class Launcher extends Application {

	public static final GameConfig CONFIG = ConfigManager.load(new File("Game.config"));
	public static final JFXTabPane TAB_PANE = new JFXTabPane();
	public static SettingsTab SETTINGS_TAB;
	public static ModTab MOD_TAB;
	public static GameTab GAME_TAB;
	public static PluginManager PLUGIN_MANAGER;

	@Override
	public void start(Stage primaryStage) {
		SETTINGS_TAB = new SettingsTab();
		MOD_TAB = new ModTab(primaryStage);
		GAME_TAB = new GameTab(primaryStage);
		TAB_PANE.getTabs().addAll(GAME_TAB, MOD_TAB, SETTINGS_TAB);
		primaryStage.setTitle("Ages Launcher");
		primaryStage.setScene(new Scene(TAB_PANE, 800, 600));
		primaryStage.show();
	}
}
