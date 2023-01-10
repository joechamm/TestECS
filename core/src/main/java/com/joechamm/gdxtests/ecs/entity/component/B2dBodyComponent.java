package com.joechamm.gdxtests.ecs.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool;

/**
 * File:    B2dBodyComponent
 * Package: com.joechamm.gdxtests.ecs.entity.component
 * Project: TestECS
 *
 * @author joechamm
 * Created  1/9/2023 at 3:19 PM
 */
public class B2dBodyComponent implements
                              Component,
                              Pool.Poolable {
    public Body body;
    public boolean isDead = false;

    @Override
    public void reset() {
        body = null;
        isDead = false;
    }
}
