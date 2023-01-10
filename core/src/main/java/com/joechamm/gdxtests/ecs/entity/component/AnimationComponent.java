package com.joechamm.gdxtests.ecs.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * File:    AnimationComponent
 * Package: com.joechamm.gdxtests.ecs.entity.component
 * Project: TestECS
 *
 * @author joechamm
 * Created  1/9/2023 at 2:51 PM
 */
public class AnimationComponent implements
                                Component,
                                Pool.Poolable {
    public IntMap<Animation> animations = new IntMap<>();

    @Override
    public void reset() {
        animations = new IntMap<> ();
    }
}
