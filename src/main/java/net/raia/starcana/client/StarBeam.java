package net.raia.starcana.client;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.raia.starcana.Starcana;
import net.raia.starcana.entity.mobs.FallenStarEntity;
import org.joml.Matrix4f;
import team.lodestar.lodestone.handlers.RenderHandler;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypeRegistry;
import team.lodestar.lodestone.systems.rendering.LodestoneRenderType;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;
import team.lodestar.lodestone.systems.rendering.rendeertype.RenderTypeToken;

import java.awt.*;

public class StarBeam {

    private static RenderTypeToken getRenderTypeToken() {
        return RenderTypeToken.createToken(new Identifier(Starcana.MOD_ID, "textures/vfx/laser.png"));
    }

    private static final LodestoneRenderType RENDER_LAYER = LodestoneRenderTypeRegistry.TRANSPARENT_TEXTURE.applyAndCache(
            getRenderTypeToken());

    public static void renderBeam(MatrixStack matrixStack, float x, float y, float z, float size, FallenStarEntity entity) {
        VFXBuilders.WorldVFXBuilder builder = VFXBuilders.createWorld();
        builder.replaceBufferSource(RenderHandler.LATE_DELAYED_RENDER.getTarget())
                .setRenderType(RENDER_LAYER)
                .setColor(new Color(255, 255, 255))
                .setAlpha(1.0f);
        float halfSize = size / 2.0f;
        matrixStack.push();
        matrixStack.translate(-entity.getX(), -entity.getY(), -entity.getZ());

        Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();

        Vec3d startPos = new Vec3d( (x+entity.getX() - halfSize), y+entity.getY(), (z + entity.getZ() - halfSize));
        Vec3d endPos = new Vec3d( (x +entity.getX() + halfSize),y +entity.getY() + 20, (z + entity.getZ()+ halfSize));

        builder.renderBeam(matrix4f, startPos, endPos, 1);

        matrixStack.pop();
    }
}