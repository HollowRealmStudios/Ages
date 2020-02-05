package hollowrealm.studios.launcher;

import simple.engine.util.FileAccessor;
import simple.engine.util.GameConfig;

import java.io.File;

public class ConfigManager {

    public static GameConfig load(File file) {
        GameConfig config = new FileAccessor<GameConfig>(file).load();
        return config != null ? config : new GameConfig();
    }

    public static void safe(GameConfig config, File file) {
        new FileAccessor<GameConfig>(file).save(config);
    }

}
