package hollowrealm.studios.launcher;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class ModTab extends Tab {

    public ModTab(Stage stage) {
        setClosable(false);
        setText("Mods");
        JFXScrollPane scrollPane = new JFXScrollPane();
        GridPane gridPane = new GridPane();
        JFXButton button = LauncherUtils.usePref(new JFXButton("Select mod folder"), 190);
        JFXTextField pathField = LauncherUtils.usePref(new JFXTextField(), 590);
        gridPane.add(new HBox(pathField, button), 0, 0);
        gridPane.setVgap(15d);
        button.setOnAction(actionEvent -> {
            File f = new DirectoryChooser().showDialog(stage);
            Launcher.PLUGIN_MANAGER = new PluginManager(f);
            ArrayList<Plugin> plugins = Launcher.PLUGIN_MANAGER.getPlugins();
            for (int i = 0; i < plugins.size(); i++) {
                gridPane.add(new HBox(new JFXCheckBox(), new Label(plugins.get(i).getName() + "(" + plugins.get(i).getAuthor() + ", " + plugins.get(i).getVersion() + ")")), 0, i + 1);
            }
        });
        scrollPane.setContent(gridPane);
        setContent(scrollPane);
    }

}
