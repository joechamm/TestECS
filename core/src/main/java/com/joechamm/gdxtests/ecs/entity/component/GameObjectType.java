package com.joechamm.gdxtests.ecs.entity.component;

/**
 * File:    GameObjectType
 * Package: com.joechamm.gdxtests.ecs.entity.component
 * Project: TestECS
 *
 * @author joechamm
 * Created  1/9/2023 at 3:03 PM
 */
public enum GameObjectType {
    PLAYER (0),
    ENEMY (1),
    SCENERY (2),
    BULLET (3),
    OTHER (4);

    private final int type;

    GameObjectType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }
}
