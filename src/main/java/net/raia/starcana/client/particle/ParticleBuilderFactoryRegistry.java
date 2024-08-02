package net.raia.starcana.client.particle;

import java.util.HashMap;
import java.util.Map;

public class ParticleBuilderFactoryRegistry {
    private static final Map<String, ParticleBuilderFactory> FACTORIES = new HashMap<>();

    public static void registerFactory(String id, ParticleBuilderFactory factory) {
        FACTORIES.put(id, factory);
    }

    public static ParticleBuilderFactory getFactory(String id) {
        return FACTORIES.get(id);
    }
}