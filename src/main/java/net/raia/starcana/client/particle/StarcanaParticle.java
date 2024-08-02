package net.raia.starcana.client.particle;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.BillboardParticle;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SweepAttackParticle;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.raia.starcana.Starcana;
import net.raia.starcana.client.particle.prong.SmashingParticleBulder;
import team.lodestar.lodestone.systems.particle.world.type.LodestoneWorldParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class StarcanaParticle{

    public static LazyRegistrar<ParticleType<?>> PARTICLES = LazyRegistrar.create(Registries.PARTICLE_TYPE, Starcana.MOD_ID);

    public static RegistryObject<LodestoneWorldParticleType> HEXAGON = PARTICLES.register("hexagon", LodestoneWorldParticleType::new);
    public static RegistryObject<LodestoneWorldParticleType> CIRCLE = PARTICLES.register("circle", LodestoneWorldParticleType::new);


    public static RegistryObject<DefaultParticleType> GUST = PARTICLES.register("gust", FabricParticleTypes::simple);



    public static void registerParticleFactory()
    {
        ParticleFactoryRegistry.getInstance().register(HEXAGON.get(), LodestoneWorldParticleType.Factory::new);
        ParticleFactoryRegistry.getInstance().register(CIRCLE.get(), LodestoneWorldParticleType.Factory::new);

        ParticleBuilderFactoryRegistry.registerFactory("smashing", new SmashingParticleBulder());
        ParticleBuilderFactoryRegistry.registerFactory("default", new DefaultParticleBuilderFactory());

        ParticleFactoryRegistry.getInstance().register(GUST.get(), SweepAttackParticle.Factory::new);

    }





}
