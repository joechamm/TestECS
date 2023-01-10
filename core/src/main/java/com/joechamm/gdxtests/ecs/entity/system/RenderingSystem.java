package com.joechamm.gdxtests.ecs.entity.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.joechamm.gdxtests.ecs.entity.component.AnimationComponent;
import com.joechamm.gdxtests.ecs.entity.component.InvisibleComponent;
import com.joechamm.gdxtests.ecs.entity.component.Mappers;
import com.joechamm.gdxtests.ecs.entity.component.ParticleEffectComponent;
import com.joechamm.gdxtests.ecs.entity.component.TextureComponent;
import com.joechamm.gdxtests.ecs.entity.component.TransformComponent;

import java.util.Comparator;

import static com.joechamm.gdxtests.ecs.Constants.PIXELS_PER_METER;
import static com.joechamm.gdxtests.ecs.Constants.PIXELS_TO_METERS;

/**
 * File:    RenderingSystem
 * Package: com.joechamm.gdxtests.ecs.entity.system
 * Project: TestECS
 *
 * @author joechamm
 * Created  1/9/2023 at 5:02 PM
 */
public class RenderingSystem extends SortedIteratingSystem {

    public static final String TAG = RenderingSystem.class.getName ();

    private boolean shouldRender = true;

    // camera frustum based off the width and height of the screen and pixels per meter ratio
    static final float FRUSTUM_WIDTH = Gdx.graphics.getWidth () / PIXELS_PER_METER;
    static final float FRUSTUM_HEIGHT = Gdx.graphics.getHeight () / PIXELS_PER_METER;

    // static Vector2's for convenience methods
    private static Vector2 meterDimensions = new Vector2 ();
    private static Vector2 pixelDimensions = new Vector2 ();

    /**
     * Convenience method to get screen size in meters
     * @return screen size in meters
     */
    public static Vector2 getScreenSizeInMeters() {
        meterDimensions.set ( Gdx.graphics.getWidth () * PIXELS_TO_METERS,
                              Gdx.graphics.getHeight () * PIXELS_TO_METERS);
        return meterDimensions;
    }

    /**
     * Convenience method to get screen size in pixels
     * @return screen size in pixels
     */
    public static Vector2 getScreenSizeInPixels() {
        pixelDimensions.set ( Gdx.graphics.getWidth (), Gdx.graphics.getHeight () );
        return pixelDimensions;
    }

    /**
     * Convenience method to convert pixels to meters
     * @param pixelValue dimension value in terms of pixels
     * @return dimension value in terms of meters
     */
    public static float PixelsToMeters(float pixelValue) {
        return pixelValue * PIXELS_TO_METERS;
    }

   /* // Renderable family
    private Family renderableFamily = Family.all ( TransformComponent.class )
            .one ( TextureComponent.class, AnimationComponent.class, ParticleEffectComponent.class )
            .exclude ( InvisibleComponent.class )
            .get ();*/

    /// MEMBER VARIABLES
    private SpriteBatch batch; // reference to our spritebatch
    private Array<Entity> renderQueue; // array to allow sorting of images
    private ZComparator zComparator;
    private OrthographicCamera camera; // reference to our camera

    public RenderingSystem ( SpriteBatch batch ) {
        this ( Family.all ( TransformComponent.class, TextureComponent.class ).get (), new ZComparator (), batch  );
    }

    /**
     * Instantiates a system that will iterate over the entities described by the Family, with a specific priority.
     *
     * @param family     The family of entities iterated over in this System
     * @param comparator The comparator to sort the entities
     */
    public RenderingSystem (
            Family family,
            Comparator<Entity> comparator,
            SpriteBatch batch
    ) {
        super ( family, comparator );

        this.zComparator = (ZComparator)comparator;

        this.batch = batch;

        // create the array for sorting entities
        this.renderQueue = new Array<> ();

        // set up the camera to match our screen size
        camera = new OrthographicCamera ( FRUSTUM_WIDTH, FRUSTUM_HEIGHT );
        camera.position.set ( FRUSTUM_WIDTH / 2f, FRUSTUM_HEIGHT / 2f, 0f );
    }

    @Override
    public void update ( float deltaTime ) {
        super.update ( deltaTime );

        // sort the renderQueue based on z-index
        renderQueue.sort (zComparator);

        // update camera and sprite batch
        camera.update ();
        batch.setProjectionMatrix ( camera.combined );
        batch.enableBlending ();

        if ( shouldRender ) {
            batch.begin ();

            // loop through each entity in our render queue
            for ( Entity entity : renderQueue ) {
                TextureComponent texCom = Mappers.textureCM.get ( entity );
                TransformComponent xfrmCom = Mappers.transformCM.get ( entity );

                if ( null == texCom.region || xfrmCom.isHidden ) continue;

                float width = texCom.region.getRegionWidth ();
                float height = texCom.region.getRegionHeight ();

                float originX = width / 2f;
                float originY = height / 2f;

                float tx = xfrmCom.position.x - originX + texCom.offsetX;
                float ty = xfrmCom.position.y - originY + texCom.offsetY;

                float sclX = PixelsToMeters ( xfrmCom.scale.x );
                float sclY = PixelsToMeters ( xfrmCom.scale.y );

                batch.draw ( texCom.region,
                             tx, ty,
                             originX, originY,
                             width, height,
                             sclX, sclY, xfrmCom.rotation );
            }

            batch.end ();
        }

        renderQueue.clear ();
    }

    /**
     * This method is called on every entity on every update call of the EntitySystem. Override this to implement your system's
     * specific processing.
     *
     * @param entity    The current Entity being processed
     * @param deltaTime The delta time between the last and current frame
     */
    @Override
    protected void processEntity ( Entity entity, float deltaTime ) {
        renderQueue.add ( entity );
    }

    /**
     * Convenience method to get camera
     * @return current camera
     */
    public OrthographicCamera getCamera() {
        return camera;
    }

    private static class ZComparator implements Comparator<Entity> {
        private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor ( TransformComponent.class );

        @Override
        public int compare(Entity e1, Entity e2) {
            return (int)Math.signum ( tm.get ( e1 ).position.z - tm.get ( e2 ).position.z );
        }
    }
}
