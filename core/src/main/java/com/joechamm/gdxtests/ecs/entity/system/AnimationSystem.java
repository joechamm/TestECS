package com.joechamm.gdxtests.ecs.entity.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.joechamm.gdxtests.ecs.entity.component.AnimationComponent;
import com.joechamm.gdxtests.ecs.entity.component.Mappers;
import com.joechamm.gdxtests.ecs.entity.component.StateComponent;
import com.joechamm.gdxtests.ecs.entity.component.TextureComponent;

import java.util.Map;

/**
 * File:    AnimationSystem
 * Package: com.joechamm.gdxtests.ecs.entity.system
 * Project: TestECS
 *
 * @author joechamm
 * Created  1/9/2023 at 8:33 PM
 */
public class AnimationSystem extends IteratingSystem {

    public static final String TAG = AnimationSystem.class.getName ();

    public AnimationSystem() {
        this(Family.all ( TextureComponent.class, AnimationComponent.class, StateComponent.class ).get ());
    }

    /**
     * Instantiates a system that will iterate over the entities described by the Family.
     *
     * @param family The family of entities iterated over in this System
     */
    AnimationSystem ( Family family ) {
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
        AnimationComponent animCom = Mappers.animationCM.get ( entity );
        StateComponent stateCom = Mappers.stateCM.get ( entity );

        if ( animCom.animations.containsKey ( stateCom.get () ) ) {
            TextureComponent texCom = Mappers.textureCM.get ( entity );
            texCom.region = (TextureRegion) animCom.animations.get ( stateCom.get () ).getKeyFrame ( stateCom.time, stateCom.isLooping );
        }

        stateCom.time += deltaTime;
    }
}
