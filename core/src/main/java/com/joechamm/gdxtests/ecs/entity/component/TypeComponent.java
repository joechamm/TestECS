package com.joechamm.gdxtests.ecs.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * File:    TypeComponent
 * Package: com.joechamm.gdxtests.ecs.entity.component
 * Project: TestECS
 *
 * @author joechamm
 * Created  1/9/2023 at 2:55 PM
 */
public class TypeComponent implements
                           Component,
                           Pool.Poolable {

    public GameObjectType gameObjectType = GameObjectType.OTHER;

    @Override
    public void reset() {
        gameObjectType = GameObjectType.OTHER;
    }
}
