package hollowrealm.studios.launcher;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextField;
import hollowrealm.studios.game.Plugin;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class ModTab extends Tab {

    public final JFXScrollPane scrollPane = new JFXScrollPane();

    public ModTab(Stage stage) {
        setClosable(false);
        setText("Mods");
        GridPane gridPane = new GridPane();
        JFXButton button = LauncherUtils.usePref(new JFXButton("Select mod folder"), 190);
        JFXTextField pathField = LauncherUtils.usePref(new JFXTextField(), 590);
        gridPane.add(new HBox(pathField, button), 0, 0);
        gridPane.setVgap(15d);
        button.setOnAction(actionEvent -> {
            File f = new DirectoryChooser().showDialog(stage);
            pathField.setText(f.getAbsolutePath());
            Launcher.PLUGIN_MANAGER = new PluginManager(f);
            ArrayList<Plugin> list = Launcher.PLUGIN_MANAGER.getPlugins();
            for (int i = 0; i < list.size(); i++) {
                gridPane.add(new HBox(new Label(list.get(i).getName() + "(" + list.get(i).getAuthor() + ", " + list.get(i).getVersion() + ")")), 0, i + 1);
            }
        });
        scrollPane.setContent(gridPane);
        setContent(scrollPane);
    }

}
