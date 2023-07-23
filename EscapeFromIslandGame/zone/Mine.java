package location;

import enemy.Enemy;
import enemy.Snake;

public class Mine extends EnemyZone{
    public Mine() {
        super("Mine", new Snake(), null);
    }
}
