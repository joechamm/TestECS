package com.joechamm.gdxtests.ecs.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * File:    ParticleEffectComponent
 * Package: com.joechamm.gdxtests.ecs.entity.component
 * Project: TestECS
 *
 * @author joechamm
 * Created  1/9/2023 at 7:45 PM
 */
public class ParticleEffectComponent implements
                                     Component,
                                     Pool.Poolable  {

    public PooledEffect particleEffect;
    public boolean isAttached = false;
    public float xOffset = 0f;
    public float yOffset = 0f;
    public float timeUntilDeath = 0.5f;
    public boolean isDead = false;
    public Body attachedBody;

    @Override
    public void reset() {
        particleEffect.free ();
        particleEffect = null;
        xOffset = 0.0f;
        yOffset = 0.0f;
        isAttached = false;
        isDead = false;
        attachedBody = null;
        timeUntilDeath = 0.5f;
    }
}
