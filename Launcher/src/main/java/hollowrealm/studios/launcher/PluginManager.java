package hollowrealm.studios.launcher;

import org.pf4j.DefaultPluginManager;

import java.io.File;

public class PluginManager {

    public PluginManager(File file) {
        DefaultPluginManager pluginManager = new DefaultPluginManager(file.toPath());
        pluginManager.loadPlugins();
        pluginManager.startPlugins();
    }
}
