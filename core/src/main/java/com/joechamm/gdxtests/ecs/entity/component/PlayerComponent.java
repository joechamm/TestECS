package com.joechamm.gdxtests.ecs.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * File:    PlayerComponent
 * Package: com.joechamm.gdxtests.ecs.entity.component
 * Project: TestECS
 *
 * @author joechamm
 * Created  1/10/2023 at 1:33 AM
 */
public class PlayerComponent implements
                             Component,
                             Pool.Poolable {

    public OrthographicCamera camera = null;
    public boolean isDead = false;
    public float shootDelay = 0.5f;
    public float timeSinceLastShot = 0f;

    
}
