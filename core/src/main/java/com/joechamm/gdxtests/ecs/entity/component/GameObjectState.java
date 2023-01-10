package com.joechamm.gdxtests.ecs.entity.component;

/**
 * File:    GameObjectState
 * Package: com.joechamm.gdxtests.ecs.entity.component
 * Project: TestECS
 *
 * @author joechamm
 * Created  1/9/2023 at 10:25 PM
 */
public enum GameObjectState {
    NORMAL (0),
    HIT (1),
    EXPLODING (2),
    THRUSTING (3);

    private final int state;

    GameObjectState(int state) {
        this.state = state;
    }

    public int getState() {
        return this.state;
    }
}
