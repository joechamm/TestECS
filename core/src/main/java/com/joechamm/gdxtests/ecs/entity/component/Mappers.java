package com.joechamm.gdxtests.ecs.entity.component;

import com.badlogic.ashley.core.ComponentMapper;

/**
 * File:    Mappers
 * Package: com.joechamm.gdxtests.ecs.entity.component
 * Project: TestECS
 *
 * @author joechamm
 * Created  1/9/2023 at 5:48 PM
 */
public class Mappers {

    public static final ComponentMapper<AnimationComponent> animationCM = ComponentMapper.getFor ( AnimationComponent.class );
    public static final ComponentMapper<B2dBodyComponent> b2dbodyCM = ComponentMapper.getFor ( B2dBodyComponent.class );
    public static final ComponentMapper<CollisionComponent> collisionCM = ComponentMapper.getFor ( CollisionComponent.class );
//    public static final ComponentMapper<MovementComponent> movementCM = ComponentMapper.getFor ( MovementComponent.class );
    public static final ComponentMapper<TextureComponent> textureCM = ComponentMapper.getFor ( TextureComponent.class );
    public static final ComponentMapper<TransformComponent> transformCM = ComponentMapper.getFor ( TransformComponent.class );
    public static final ComponentMapper<TypeComponent> typeCM = ComponentMapper.getFor ( TypeComponent.class );
    public static final ComponentMapper<StateComponent> stateCM = ComponentMapper.getFor ( StateComponent.class );
    public static final ComponentMapper<InvisibleComponent> invisibleCM = ComponentMapper.getFor ( InvisibleComponent.class );
    public static final ComponentMapper<ParticleEffectComponent> particleEffectCM = ComponentMapper.getFor (
            ParticleEffectComponent.class );
}
