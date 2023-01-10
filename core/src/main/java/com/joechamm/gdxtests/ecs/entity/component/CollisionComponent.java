package com.joechamm.gdxtests.ecs.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool;

/**
 * File:    CollisionComponent
 * Package: com.joechamm.gdxtests.ecs.entity.component
 * Project: TestECS
 *
 * @author joechamm
 * Created  1/9/2023 at 2:53 PM
 */
public class CollisionComponent implements
                                Component,
                                Pool.Poolable {
    public Entity collisionEntity;

    /** Resets the object for reuse. Object references should be nulled and fields may be set to default values. */
    @Override
    public void reset () {
        collisionEntity = null;
    }
}
