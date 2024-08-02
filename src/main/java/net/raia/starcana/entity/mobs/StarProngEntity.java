package net.raia.starcana.entity.mobs;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.raia.starcana.client.network.packets.ParticlePacket;
import net.raia.starcana.client.network.packets.StarcanaPackets;
import net.raia.starcana.client.particle.CompositeParticleBuilderFactory;
import net.raia.starcana.client.particle.ParticleBuilderFactory;
import net.raia.starcana.client.particle.prong.SmashingParticleBulder;
import net.raia.starcana.client.particle.prong.SmashingParticleFollowBulder;
import net.raia.starcana.entity.StarcanaEntities;
import net.raia.starcana.item.Starcanaitems;
import net.raia.starcana.sounds.StarcanaSounds;
import net.raia.starcana.utils.StarcanaColors;
import net.raia.starcana.utils.helper.StarcanaEnchantmentHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class StarProngEntity extends PersistentProjectileEntity {
    private static final TrackedData<Boolean> ENCHANTED = DataTracker.registerData(StarProngEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private ItemStack ProngStack = new ItemStack(Starcanaitems.STAR_PRONG);
    private boolean dealtDamage;

    public StarProngEntity(EntityType<? extends StarProngEntity> entityType, World world) {
        super(entityType, world);
    }

    public StarProngEntity(World world, LivingEntity owner, ItemStack stack) {
        super(StarcanaEntities.STAR_PRONG, owner, world);
        this.ProngStack = stack.copy();
        //this.dataTracker.set(LOYALTY, (byte) EnchantmentHelper.getLoyalty(stack));
        this.dataTracker.set(ENCHANTED, stack.hasGlint());
    }



    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ENCHANTED, false);

    }

    @Override
    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        super.tick();
    }

    private boolean isOwnerAlive() {
        Entity entity = this.getOwner();
        return entity == null || !entity.isAlive() ? false : !(entity instanceof ServerPlayerEntity) || !entity.isSpectator();
    }

    @Override
    protected ItemStack asItemStack() {
        return this.ProngStack.copy();
    }

    public boolean isEnchanted() {
        return this.dataTracker.get(ENCHANTED);
    }

    @Nullable
    @Override
    protected EntityHitResult getEntityCollision(Vec3d currentPosition, Vec3d nextPosition) {
        return this.dealtDamage ? null : super.getEntityCollision(currentPosition, nextPosition);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        float f = 8.0F;
        if (entity instanceof LivingEntity livingEntity) {
            f += EnchantmentHelper.getAttackDamage(this.ProngStack, livingEntity.getGroup());
        }

        Entity entity2 = this.getOwner();
        DamageSource damageSource = this.getDamageSources().trident(this, (Entity)(entity2 == null ? this : entity2));
        this.dealtDamage = true;
        SoundEvent soundEvent = SoundEvents.ITEM_TRIDENT_HIT;
        if (entity.damage(damageSource, f)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (entity instanceof LivingEntity livingEntity2) {
                if (entity2 instanceof LivingEntity) {
                    EnchantmentHelper.onUserDamaged(livingEntity2, entity2);
                    EnchantmentHelper.onTargetDamaged((LivingEntity)entity2, livingEntity2);
                }

                this.onHit(livingEntity2);
            }
        }

        this.setVelocity(this.getVelocity().multiply(-0.01, -0.1, -0.01));
        float g = 1.0F;
        if (this.getWorld() instanceof ServerWorld && this.getWorld().isThundering() && this.hasChanneling()) {
            BlockPos blockPos = entity.getBlockPos();
            if (this.getWorld().isSkyVisible(blockPos)) {
                LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(this.getWorld());
                if (lightningEntity != null) {
                    lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos));
                    lightningEntity.setChanneler(entity2 instanceof ServerPlayerEntity ? (ServerPlayerEntity)entity2 : null);
                    this.getWorld().spawnEntity(lightningEntity);
                    soundEvent = SoundEvents.ITEM_TRIDENT_THUNDER;
                    g = 5.0F;
                }
            }
        }

        this.playSound(soundEvent, g, 1.0F);
    }

    public boolean hasChanneling() {
        return EnchantmentHelper.hasChanneling(this.ProngStack);
    }

    @Override
    protected boolean tryPickup(PlayerEntity player) {
        return super.tryPickup(player) || this.isNoClip() && this.isOwner(player) && player.getInventory().insertStack(this.asItemStack());
    }

    @Override
    protected SoundEvent getHitSound() {
        return StarcanaSounds.PRONG_HIT_GROUND;
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        if (this.isOwner(player) || this.getOwner() == null) {
            super.onPlayerCollision(player);
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("Trident", NbtElement.COMPOUND_TYPE)) {
            this.ProngStack = ItemStack.fromNbt(nbt.getCompound("Trident"));
        }

        this.dealtDamage = nbt.getBoolean("DealtDamage");
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.put("Trident", this.ProngStack.writeNbt(new NbtCompound()));
        nbt.putBoolean("DealtDamage", this.dealtDamage);
    }

    @Override
    public void age() {
        if (this.pickupType != PersistentProjectileEntity.PickupPermission.ALLOWED) {
            super.age();
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if (StarcanaEnchantmentHelper.getSmashing(ProngStack) > 0)
        {
            knockbackNearbyEntities(this.getWorld(), this);
            String factoryId = "smashing"; // The identifier for SmashingParticleBuilder
            ParticlePacket packet = new ParticlePacket(this.getPos(), StarcanaColors.Magenta[4].getRGB(), StarcanaColors.Magenta[7].getRGB(), factoryId);
            PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
            packet.toBytes(buf);
            for (Entity player : getPlayers(this))
            {
                ServerPlayNetworking.send((ServerPlayerEntity) player, StarcanaPackets.PARTICLE_SPAWN_ID, buf);

            }
        }
        super.onBlockHit(blockHitResult);
    }

    @Override
    protected float getDragInWater() {
        return 0.99F;
    }

    @Override
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        return true;
    }

    private List<Entity> getPlayers(Entity prong)
    {
        return prong.getWorld().getOtherEntities(this ,prong.getBoundingBox().expand(30), particlePredicate());
    }

    private void knockbackNearbyEntities(World world, Entity prong) {
        world.getEntitiesByClass(LivingEntity.class, prong.getBoundingBox().expand(5), getKnockbackPredicate(this.getOwner(), prong)).forEach(entity -> {
            Vec3d vec3d = entity.getPos().subtract(prong.getPos());
            double d = getKnockback(entity, vec3d);
            Vec3d vec3d2 = vec3d.normalize().multiply(d);
            if (d > 0.0) {
                entity.addVelocity(vec3d2.x, 0.7F, vec3d2.z);

            }
        });
    }
    private static Predicate<? super Entity> particlePredicate()
    {
        return Entity::isPlayer;
    }

    private static Predicate<LivingEntity> getKnockbackPredicate(Entity player, Entity attacked) {
        return entity -> {
            boolean bl;
            boolean bl2;
            boolean bl3;
            boolean var10000;
            label62: {
                bl = !entity.isSpectator();
                bl2 = entity != attacked;
                bl3 = !player.isTeammate(entity);
                if (entity instanceof TameableEntity tameableEntity && tameableEntity.isTamed() && player.getUuid().equals(tameableEntity.getOwnerUuid())) {
                    var10000 = true;
                    break label62;
                }

                var10000 = false;
            }

            boolean bl4;
            label55: {
                bl4 = !var10000;
                if (entity instanceof ArmorStandEntity armorStandEntity && armorStandEntity.isMarker()) {
                    var10000 = false;
                    break label55;
                }

                var10000 = true;
            }

            boolean bl5 = var10000;
            boolean bl6 = attacked.squaredDistanceTo(entity) <= Math.pow(3.5, 2.0);
            return bl && bl2 && bl3 && bl4 && bl5 && bl6;
        };
    }

    private double getKnockback(LivingEntity attacked, Vec3d distance) {
        return (3.5 - distance.length())
                * 0.7F
                * StarcanaEnchantmentHelper.getSmashing(ProngStack)
                * (1.0 - attacked.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
    }

}
