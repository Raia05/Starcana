package net.raia.starcana.entity.client.trinkets;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class StarProngTrinket extends BipedEntityModel<LivingEntity> {
	private final ModelPart sword;

	public StarProngTrinket(ModelPart root) {
        super(root);
        this.sword = root.getChild("sword");
	}

    public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0f);
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData sword = modelPartData.addChild("sword", ModelPartBuilder.create(), ModelTransform.of(-2.125F, 0.25F, 3.875F, 0.0F, 0.0F, 2.3126F));

		ModelPartData handle = sword.addChild("handle", ModelPartBuilder.create().uv(-4, 56).cuboid(-3.0F, -5.5F, -1.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F))
				.uv(39, 46).cuboid(-2.0F, -3.0F, 0.0F, 2.0F, 16.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 0.0F, -1.0F));

		ModelPartData cube_r1 = handle.addChild("cube_r1", ModelPartBuilder.create().uv(20, 42).cuboid(-1.8787F, -4.2929F, 0.0F, 4.0F, 5.0F, 2.0F, new Dilation(-0.1F)), ModelTransform.of(-1.0F, -3.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData cube_r2 = handle.addChild("cube_r2", ModelPartBuilder.create().uv(20, 49).cuboid(-1.9787F, -4.4929F, 0.0F, 4.0F, 5.0F, 2.0F, new Dilation(-0.1F)), ModelTransform.of(-1.0F, -3.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData cube_r3 = handle.addChild("cube_r3", ModelPartBuilder.create().uv(44, 26).mirrored().cuboid(-1.5F, -2.0F, 0.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.01F)).mirrored(false), ModelTransform.of(-6.0F, -5.0F, -1.0F, 0.0F, 0.0F, 0.3927F));

		ModelPartData cube_r4 = handle.addChild("cube_r4", ModelPartBuilder.create().uv(44, 26).cuboid(-2.5F, -2.0F, 0.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.01F)), ModelTransform.of(4.0F, -5.0F, -1.0F, 0.0F, 0.0F, -0.3927F));

		ModelPartData hilt_r1 = handle.addChild("hilt_r1", ModelPartBuilder.create().uv(0, 10).cuboid(-1.6F, -3.0F, -0.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 15.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData fork = sword.addChild("fork", ModelPartBuilder.create().uv(52, 0).mirrored().cuboid(1.7F, -20.0F, -0.5F, 3.0F, 22.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
				.uv(52, 0).cuboid(-4.7F, -20.0F, -0.5F, 3.0F, 22.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -8.0F, 0.0F));

		ModelPartData tips = fork.addChild("tips", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r5 = tips.addChild("cube_r5", ModelPartBuilder.create().uv(44, 0).mirrored().cuboid(-1.0F, -5.0F, -0.5F, 3.0F, 5.0F, 1.0F, new Dilation(-0.1F)).mirrored(false), ModelTransform.of(2.7F, -19.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		ModelPartData cube_r6 = tips.addChild("cube_r6", ModelPartBuilder.create().uv(44, 0).cuboid(-2.0F, -5.0F, -0.5F, 3.0F, 5.0F, 1.0F, new Dilation(-0.1F)), ModelTransform.of(-2.7F, -19.0F, 0.0F, 0.0F, 0.0F, -0.3927F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		sword.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

}