package hollowrealm.studios.launcher;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class PluginManager {

    private final File folder;
    private final ArrayList<Plugin> plugins = new ArrayList<>();

    public PluginManager(File folder) {
        this.folder = folder;
        loadPlugins();
    }

    private void loadPlugins() {
        Arrays.stream(PluginUtils.listFolders(folder)).forEach(plugin -> plugins.add(new Plugin(plugin)));
    }

    public ArrayList<Plugin> getPlugins() {
        return plugins;
    }
}
