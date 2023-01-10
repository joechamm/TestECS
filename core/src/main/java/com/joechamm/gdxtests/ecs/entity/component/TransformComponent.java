package com.joechamm.gdxtests.ecs.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * File:    TransformComponent
 * Package: com.joechamm.gdxtests.ecs.entity.component
 * Project: TestECS
 *
 * @author joechamm
 * Created  1/7/2023 at 1:44 AM
 */
public class TransformComponent implements
                                Component,
                                Poolable {

    public final Vector3 position = new Vector3 (); // use Vector3 for Z-comparator
    public final Vector2 scale = new Vector2 ( 1f, 1f );
    public float rotation = 0f;
    public boolean isHidden = false;

    @Override
    public void reset() {
        position.set ( 0.0f, 0.0f, 0.0f );
        scale.set ( 1.0f, 1.0f );
        rotation = 0.0f;
        isHidden = false;
    }
}
