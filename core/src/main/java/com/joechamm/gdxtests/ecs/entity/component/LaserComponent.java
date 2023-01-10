package com.joechamm.gdxtests.ecs.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

/**
 * File:    LaserComponent
 * Package: com.joechamm.gdxtests.ecs.entity.component
 * Project: TestECS
 *
 * @author joechamm
 * Created  1/9/2023 at 11:29 PM
 */
public class LaserComponent implements
                            Component,
                            Pool.Poolable {

    public static enum Owner {
        ENEMY,
        PLAYER,
        SCENERY,
        NONE
    }

    public final Vector2 velocity = new Vector2 ();
    public boolean isDead = false;
    public Owner owner = Owner.NONE;
    public Entity particleEffect;

    @Override
    public void reset() {
        owner = Owner.NONE;
        particleEffect = null;
        velocity.set ( 0f, 0f );
        isDead = false;
    }

}
