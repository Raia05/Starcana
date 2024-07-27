package net.raia.starcana.entity.mobs;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.raia.starcana.entity.StarcanaEntities;

public class FallenStarProjectile extends ThrownEntity {



    public FallenStarProjectile(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
    }

    public FallenStarProjectile(LivingEntity livingEntity, World world)
    {
        super(StarcanaEntities.FALLEN_STAR_PROJ, livingEntity, world);
    }


    @Override
    public void initDataTracker() {

    }


    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {

        return new EntitySpawnS2CPacket(this);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if(!this.getWorld().isClient())
        {
            FallenStarEntity spawn = new FallenStarEntity(StarcanaEntities.FALLEN_STAR, this.getWorld());
            Vec3d SpawnPos = new Vec3d(blockHitResult.getPos().x, blockHitResult.getPos().y+2.25, blockHitResult.getPos().z);
            spawn.setPosition(SpawnPos);
            this.getWorld().spawnEntity(spawn);
        }

        this.discard();
        super.onBlockHit(blockHitResult);
    }
}
