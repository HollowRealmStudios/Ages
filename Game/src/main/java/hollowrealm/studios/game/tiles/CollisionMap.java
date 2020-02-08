package hollowrealm.studios.game.tiles;

import hollowrealm.studios.game.Rhomb;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class CollisionMap {

    private final Rhomb[] rhombs;

    public CollisionMap(Rhomb... rhombs) {
        this.rhombs = rhombs;
    }

    public boolean collides(int x, int y) {
        AtomicBoolean b = new AtomicBoolean(false);
        Arrays.stream(rhombs).forEach(rhomb -> {
            if (rhomb.contains(x, y)) b.set(true);
        });
        return b.get();
    }

}
