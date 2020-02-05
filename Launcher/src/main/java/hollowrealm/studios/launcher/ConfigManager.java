package hollowrealm.studios.launcher;

import simple.engine.util.FileAccessor;
import simple.engine.util.GameConfig;

import java.io.File;

public class ConfigManager {

    public static GameConfig load(File file) {
        GameConfig config = new FileAccessor<GameConfig>(file).load();
        System.out.println(file.toPath().toAbsolutePath().toFile().toString());
        return config;
    }

    public static void safe(GameConfig config, File file) {
        System.out.println(file.toPath().toAbsolutePath().toFile().toString());
        new FileAccessor<GameConfig>(file).save(config);
    }

}
