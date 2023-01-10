package com.joechamm.gdxtests.ecs;

/**
 * File:    Constants
 * Package: com.joechamm.gdxtests.ecs
 * Project: TestECS
 *
 * @author joechamm
 * Created  1/9/2023 at 4:33 PM
 */
public class Constants {

    public static final float MAX_SPEED_LINEAR = 2.0f; // max speed an entity can go
    public static final float MAX_ACCELERATION_LINEAR = 5.0f; // max acceleration
    public static final float MAX_SPEED_ANGULAR = 50f; // max turning speed
    public static final float MAX_ACCELERATION_ANGULAR = 5f; // max turning acceleration
    public static final float ZERO_THRESHOLD = 0.1f; // how accurate should checks be for target location checks
    public static final float BOUNDING_RADIUS = 1.0f; // minimum radius size for a circle required to cover whole object
    public static final float WORLD_WIDTH = 5.0f;
    public static final float WORLD_HEIGHT = 5.0f;
    public static final float PIXELS_PER_METER = 16.0f;
    public static final float PIXELS_TO_METERS = 1.0f / PIXELS_PER_METER;

}
