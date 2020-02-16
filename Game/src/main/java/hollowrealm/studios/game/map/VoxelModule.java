package hollowrealm.studios.game.map;

import hollowrealm.studios.game.ages.Age;
import simple.engine.modules.Module;
import simple.engine.util.GameConfig;

import java.awt.*;

/**
 * Created by matteoschmider on 11.02.20.
 */
public class VoxelModule extends Module {

    private Age age;
    private float zoom = 1f;
    private Point translation = new Point();

    public VoxelModule(GameConfig config, Age age) {
        super(config);
        this.age = age;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public void render(Graphics2D g) {
        g.drawImage(age.getVoxelMap().getTexture(), 0, 0, config.getWidth(), config.getHeight(), null);
    }
}
