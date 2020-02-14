package hollowrealm.studios.game;

import simple.engine.modules.Module;
import simple.engine.util.GameConfig;

public class PlayerModule extends Module {

    public final Player player;

    public PlayerModule(GameConfig config) {
        super(config);
        player = new Player();
    }
}
