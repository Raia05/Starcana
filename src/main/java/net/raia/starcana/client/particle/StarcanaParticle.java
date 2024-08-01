package net.raia.starcana.client.particle;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.raia.starcana.Starcana;
import team.lodestar.lodestone.systems.particle.world.type.LodestoneWorldParticleType;


public class StarcanaParticle{

    public static LazyRegistrar<ParticleType<?>> PARTICLES = LazyRegistrar.create(Registries.PARTICLE_TYPE, Starcana.MOD_ID);

    public static RegistryObject<LodestoneWorldParticleType> HEXAGON = PARTICLES.register("hexagon", LodestoneWorldParticleType::new);

    public static void registerParticleFactory()
    {
        ParticleFactoryRegistry.getInstance().register(HEXAGON.get(), LodestoneWorldParticleType.Factory::new);

    }





}
