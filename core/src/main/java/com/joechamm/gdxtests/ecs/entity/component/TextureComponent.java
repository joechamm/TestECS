package com.joechamm.gdxtests.ecs.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * File:    TextureComponent
 * Package: com.joechamm.gdxtests.ecs.entity.component
 * Project: TestECS
 *
 * @author joechamm
 * Created  1/9/2023 at 1:48 PM
 */
public class TextureComponent implements
                              Component,
                              Poolable {

    public TextureRegion region = null;
    public float offsetX = 0f;
    public float offsetY = 0f;

    /** Resets the object for reuse. Object references should be nulled and fields may be set to default values. */
    @Override
    public void reset () {
        region = null;
        offsetX = 0f;
        offsetY = 0f;
    }
}
