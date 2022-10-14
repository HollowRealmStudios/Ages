package hollowrealm.studios.launcher;

import hollowrealm.studios.game.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class PluginManager {

    private final ArrayList<Plugin> plugins = new ArrayList<>();

    public PluginManager(File folder) {
        loadPlugins(folder);
    }

    private void loadPlugins(File folder) {
        File[] pluginFolders = PluginUtils.listFolders(folder);
        if (!areValidFolders(pluginFolders)) throw new IllegalArgumentException("One of the Plugin folders is invalid");
        loadAllPlugins(pluginFolders);
    }

    private void loadAllPlugins(File[] folders) {
        Arrays.stream(folders).forEach(file -> plugins.add(new Plugin(file)));
    }

    private boolean areValidFolders(File[] folders) {
        AtomicBoolean b = new AtomicBoolean(true);
        Arrays.stream(folders).forEach(file -> {
            if (!PluginUtils.isValidPluginFolder(file)) b.set(false);
        });
        return b.get();
    }

    public void startPlugins() {
        plugins.forEach(Plugin::start);
    }

    public void stopPlugins() {
        plugins.forEach(Plugin::stop);
    }

    public ArrayList<Plugin> getPlugins() {
        return plugins;
    }
}
