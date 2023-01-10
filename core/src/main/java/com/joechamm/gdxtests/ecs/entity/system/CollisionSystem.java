package com.joechamm.gdxtests.ecs.entity.system;

import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.joechamm.gdxtests.ecs.entity.component.GameObjectType;
import com.joechamm.gdxtests.ecs.entity.component.Mappers;
import com.joechamm.gdxtests.ecs.entity.component.CollisionComponent;
import com.joechamm.gdxtests.ecs.entity.component.TypeComponent;

/**
 * File:    CollisionSystem
 * Package: com.joechamm.gdxtests.ecs.entity.system
 * Project: TestECS
 *
 * @author joechamm
 * Created  1/9/2023 at 11:09 PM
 */
public class CollisionSystem extends IteratingSystem {
    public static final String TAG = CollisionSystem.class.getName ();

    public CollisionSystem() {
        this(Family.all ( CollisionComponent.class ).get ());
    }

    /**
     * Instantiates a system that will iterate over the entities described by the Family.
     *
     * @param family The family of entities iterated over in this System
     */
    CollisionSystem ( Family family ) {
        super ( family );
    }

    /**
     * This method is called on every entity on every update call of the EntitySystem. Override this to implement your system's
     * specific processing.
     *
     * @param entity    The current Entity being processed
     * @param deltaTime The delta time between the last and current frame
     */
    @Override
    protected void processEntity ( Entity entity, float deltaTime ) {
        // get collision component for this entity
        CollisionComponent colCom = Mappers.collisionCM.get ( entity );
        // get collided entity
        Entity collidedEntity = colCom.collisionEntity;

        TypeComponent thisType = entity.getComponent ( TypeComponent.class );

        // Do player collisions
        if ( thisType.gameObjectType == GameObjectType.PLAYER ) {

        }
    }
}
