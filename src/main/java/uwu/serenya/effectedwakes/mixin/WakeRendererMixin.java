package uwu.serenya.effectedwakes.mixin;

import com.goby56.wakes.render.WakeTextureRenderer;
import com.goby56.wakes.utils.WakeNode;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.biome.Biomes;
import org.joml.Matrix4f;
import org.ladysnake.effective.EffectiveConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static uwu.serenya.effectedwakes.EffectedWakesUtils.isNightTime;

@Mixin(value = WakeTextureRenderer.class, remap = false)
public abstract class WakeRendererMixin {

    @Redirect(
            method = "afterTranslucent",
            at = @At(
            value = "INVOKE",
            target = "Lcom/goby56/wakes/render/WakeTextureRenderer;renderTexture(IIJLorg/joml/Matrix4f;FFFFFFFI)V"
            )
    )
    public void render(int resolution, int textureID, long texture, Matrix4f matrix,
                       float x, float y, float z, float r, float g, float b, float a,
                       int light, @Local WakeNode node) {
        ClientWorld world = MinecraftClient.getInstance().world;
        BlockPos blockPos = node.blockPos();

        if (r == 1.0f && g == 1.0f && b == 1.0f && EffectiveConfig.glowingPlankton && isNightTime(world) &&
                world.getBiome(blockPos).isRegistryKey(Biomes.WARM_OCEAN)) {
            final int foamLight = 15728880;
            //float rg = (float) (Math.random() / 5f);
            float rgRender = Math.min(0.3f, node.age / 40f) + world.getLightLevel(LightType.BLOCK, blockPos) / 15f; // Math.min(1, rg + world.getLightLevel(LightType.BLOCK, blockPos) / 15f);

            WakeRendererAccessor.invokeRenderTexture(resolution, textureID, texture, matrix, x, y, z, rgRender, rgRender, 1.0f, 1.0f, foamLight);

        } else {
            WakeRendererAccessor.invokeRenderTexture(resolution, textureID, texture, matrix, x, y, z, r, g, b, a, light);
        }
    }
}
