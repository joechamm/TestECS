package com.joechamm.gdxtests.ecs.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * File:    StateComponent
 * Package: com.joechamm.gdxtests.ecs.entity.component
 * Project: TestECS
 *
 * @author joechamm
 * Created  1/9/2023 at 10:27 PM
 */
public class StateComponent implements
                            Component,
                            Pool.Poolable {

    private GameObjectState state = GameObjectState.NORMAL;
    public float time = 0.0f;
    public boolean isLooping = true;

    public void setState(GameObjectState state) {
        this.state = state;
        this.time = 0.0f;
    }

    public GameObjectState getState() {
        return state;
    }

    public int get() {
        return state.getState ();
    }

    /** Resets the object for reuse. Object references should be nulled and fields may be set to default values. */
    @Override
    public void reset () {
        state = GameObjectState.NORMAL;
        time = 0.0f;
        isLooping = true;
    }
}
