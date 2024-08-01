package net.raia.starcana.client.particle;

import net.minecraft.util.math.Vec3d;

public interface MotionProvider {
    Vec3d getMotion(Vec3d position);

}
