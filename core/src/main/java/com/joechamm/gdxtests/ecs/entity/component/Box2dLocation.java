package com.joechamm.gdxtests.ecs.entity.component;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * File:    Box2dLocation
 * Package: com.joechamm.gdxtests.ecs.entity.component
 * Project: TestECS
 *
 * @author joechamm
 * Created  1/9/2023 at 11:34 PM
 */

public class Box2dLocation implements Location<Vector2> {
    public static final String TAG = Box2dLocation.class.getName ();

    Vector2 position;
    float orientation;

    public Box2dLocation() {
        this ( new Vector2 (), 0f );
    }

    Box2dLocation ( Vector2 position, float orientation ) {
        this.position = position;
        this.orientation = orientation;
    }

    /** Returns the vector indicating the position of this location. */
    @Override
    public Vector2 getPosition () {
        return position;
    }

    /**
     * Returns the float value indicating the orientation of this location. The orientation is the angle in radians representing
     * the direction that this location is facing.
     */
    @Override
    public float getOrientation () {
        return orientation;
    }

    /**
     * Sets the orientation of this location, i.e. the angle in radians representing the direction that this location is facing.
     *
     * @param orientation the orientation in radians
     */
    @Override
    public void setOrientation ( float orientation ) {
        this.orientation = orientation;
    }

    /**
     * Returns the angle in radians pointing along the specified vector.
     *
     * @param vector the vector
     */
    @Override
    public float vectorToAngle ( Vector2 vector ) {
        return MathUtils.atan2 ( - vector.x, vector.y );
    }

    /**
     * Returns the unit vector in the direction of the specified angle expressed in radians.
     *
     * @param outVector the output vector.
     * @param angle     the angle in radians.
     *
     * @return the output vector for chaining.
     */
    @Override
    public Vector2 angleToVector ( Vector2 outVector, float angle ) {
        outVector.x = - MathUtils.sin ( angle );
        outVector.y = MathUtils.cos ( angle );
        return outVector;
    }

    /**
     * Creates a new location.
     * <p>
     * This method is used internally to instantiate locations of the correct type parameter {@code T}. This technique keeps the API
     * simple and makes the API easier to use with the GWT backend because avoids the use of reflection.
     *
     * @return the newly created location.
     */
    @Override
    public Location<Vector2> newLocation () {
        return new Box2dLocation ();
    }
}
