package enemy;

import enemy.Enemy;

public class Snake extends Enemy {
    public Snake() {
        super("Snake", (Math.random() > .5? 3 : 6), 12, 0, 5);
    }
}
