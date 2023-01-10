package com.joechamm.gdxtests.ecs.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool;
import com.joechamm.gdxtests.ecs.Constants;

/**
 * File:    SteeringComponent
 * Package: com.joechamm.gdxtests.ecs.entity.component
 * Project: TestECS
 *
 * @author joechamm
 * Created  1/10/2023 at 12:42 AM
 */
public class SteeringComponent implements
                               Steerable<Vector2>,
                               Component,
                               Pool.Poolable {

    public static final String TAG = SteeringComponent.class.getName ();

    // list of behavior
    public static enum SteeringState {
        WANDER,
        SEEK,
        FLEE,
        ARRIVE,
        NONE
    }

    public SteeringState currentMode = SteeringState.WANDER; // stores which state the entity is currently in
    public Body body; // stores a reference to our Box2D body

    // Steering data
    float maxLinearSpeed = Constants.MAX_SPEED_LINEAR;
    float maxLinearAcceleration = Constants.MAX_ACCELERATION_LINEAR;
    float maxAngularSpeed = Constants.MAX_SPEED_ANGULAR;
    float maxAngularAcceleration = Constants.MAX_ACCELERATION_ANGULAR;
    float zeroThreshold = Constants.ZERO_THRESHOLD;

    public SteeringBehavior<Vector2> steeringBehavior; // stores the action behavior
    private static final SteeringAcceleration<Vector2> steeringOutput = new SteeringAcceleration<Vector2> ( new Vector2 () ); // the actual steering vector for our unit

    private float boundingRadius = Constants.BOUNDING_RADIUS;
    private boolean tagged = true; // this is a generic flag utilized in a variety of ways.
    private boolean independentFacing = false; // defines if the entity can move in a direction other than the way it faces

    /** Returns the vector indicating the linear velocity of this Steerable. */
    @Override
    public Vector2 getLinearVelocity () {
        return body.getLinearVelocity ();
    }

    /** Returns the float value indicating the the angular velocity in radians of this Steerable. */
    @Override
    public float getAngularVelocity () {
        return body.getAngularVelocity ();
    }

    /** Returns the bounding radius of this Steerable. */
    @Override
    public float getBoundingRadius () {
        return this.boundingRadius;
    }

    /** Returns {@code true} if this Steerable is tagged; {@code false} otherwise. */
    @Override
    public boolean isTagged () {
        return this.tagged;
    }

    /**
     * Tag/untag this Steerable. This is a generic flag utilized in a variety of ways.
     *
     * @param tagged the boolean value to set
     */
    @Override
    public void setTagged ( boolean tagged ) {
        this.tagged = tagged;
    }

    /**
     * Returns the threshold below which the linear speed can be considered zero. It must be a small positive value near to zero.
     * Usually it is used to avoid updating the orientation when the velocity vector has a negligible length.
     */
    @Override
    public float getZeroLinearSpeedThreshold () {
        return zeroThreshold;
    }

    /**
     * Sets the threshold below which the linear speed can be considered zero. It must be a small positive value near to zero.
     * Usually it is used to avoid updating the orientation when the velocity vector has a negligible length.
     *
     * @param value
     */
    @Override
    public void setZeroLinearSpeedThreshold ( float value ) {
        this.zeroThreshold = value;
    }

    /** Returns the maximum linear speed. */
    @Override
    public float getMaxLinearSpeed () {
        return this.maxLinearSpeed;
    }

    /**
     * Sets the maximum linear speed.
     *
     * @param maxLinearSpeed
     */
    @Override
    public void setMaxLinearSpeed ( float maxLinearSpeed ) {
        this.maxLinearSpeed = maxLinearSpeed;
    }

    /** Returns the maximum linear acceleration. */
    @Override
    public float getMaxLinearAcceleration () {
        return this.maxLinearAcceleration;
    }

    /**
     * Sets the maximum linear acceleration.
     *
     * @param maxLinearAcceleration
     */
    @Override
    public void setMaxLinearAcceleration ( float maxLinearAcceleration ) {
        this.maxLinearAcceleration = maxLinearAcceleration;
    }

    /** Returns the maximum angular speed. */
    @Override
    public float getMaxAngularSpeed () {
        return this.maxAngularSpeed;
    }

    /**
     * Sets the maximum angular speed.
     *
     * @param maxAngularSpeed
     */
    @Override
    public void setMaxAngularSpeed ( float maxAngularSpeed ) {
        this.maxAngularSpeed = maxAngularSpeed;
    }

    /** Returns the maximum angular acceleration. */
    @Override
    public float getMaxAngularAcceleration () {
        return this.maxAngularAcceleration;
    }

    /**
     * Sets the maximum angular acceleration.
     *
     * @param maxAngularAcceleration
     */
    @Override
    public void setMaxAngularAcceleration ( float maxAngularAcceleration ) {
        this.maxAngularSpeed = maxAngularAcceleration;
    }

    /** Returns the vector indicating the position of this location. */
    @Override
    public Vector2 getPosition () {
        return body.getPosition ();
    }

    /**
     * Returns the float value indicating the orientation of this location. The orientation is the angle in radians representing
     * the direction that this location is facing.
     */
    @Override
    public float getOrientation () {
        return body.getAngle ();
    }

    /**
     * Sets the orientation of this location, i.e. the angle in radians representing the direction that this location is facing.
     *
     * @param orientation the orientation in radians
     */
    @Override
    public void setOrientation ( float orientation ) {
        body.setTransform ( getPosition (), orientation );
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

    /** Resets the object for reuse. Object references should be nulled and fields may be set to default values. */
    @Override
    public void reset () {
        currentMode = SteeringState.NONE;
        body = null;
        steeringBehavior = null;
    }

    boolean isIndependentFacing () {
        return independentFacing;
    }

    void setIndependentFacing ( boolean independentFacing ) {
        this.independentFacing = independentFacing;
    }

    public void update(float dt) {
        if(steeringBehavior != null) {
            steeringBehavior.calculateSteering ( steeringOutput );
            applySteering ( steeringOutput, dt );
        }
    }

    protected void applySteering ( SteeringAcceleration<Vector2> steering, float deltaTime ) {
        boolean anyAccelerations = false;

        // Update position and linear velocity
        if ( ! steeringOutput.linear.isZero () ) {
            // this method internally scales the force by deltaTime
            body.applyForceToCenter ( steeringOutput.linear, true );
            anyAccelerations = true;
        }

        // Update orientation and angular velocity
        if ( isIndependentFacing () ) {
            if ( steeringOutput.angular != 0 ) {
                // this method internally scales the torque by deltaTime
                body.applyTorque ( steeringOutput.angular, true );
                anyAccelerations = true;
            }
        } else {
            // If we haven't got any velocity, then we can do nothing
            Vector2 linVel = getLinearVelocity ();
            if ( ! linVel.isZero ( getZeroLinearSpeedThreshold () ) ) {
                float newOrientation = vectorToAngle ( linVel );
                body.setAngularVelocity ( (newOrientation - getAngularVelocity ()) * deltaTime ); // this is superfluous if independentFacing is always true
                body.setTransform ( body.getPosition (), newOrientation );
            }
        }

        if ( anyAccelerations ) {
            // cap the lienar speed
            Vector2 velocity = body.getLinearVelocity ();
            float currentSpeedSquare = velocity.len2 ();
            float maxLinearSpeed = getMaxLinearSpeed ();
            if(currentSpeedSquare > (maxLinearSpeed * maxLinearSpeed)) {
                body.setLinearVelocity ( velocity.scl ( maxLinearSpeed / (float) Math.sqrt ( currentSpeedSquare ) ) );
            }

            // cap the angular speed
            float maxAngVelocity = getMaxAngularSpeed ();
            if ( body.getAngularVelocity () > maxAngVelocity ) {
                body.setAngularVelocity ( maxAngVelocity );
            }
        }
    }
}
