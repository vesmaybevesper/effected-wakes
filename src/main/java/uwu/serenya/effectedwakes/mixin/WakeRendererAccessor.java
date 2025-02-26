package uwu.serenya.effectedwakes.mixin;

import com.goby56.wakes.render.WakeTextureRenderer;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = WakeTextureRenderer.class, remap = false)
public interface WakeRendererAccessor {

    @Invoker("renderTexture")
    static void invokeRenderTexture(int resolution, int textureID, long texture, Matrix4f matrix, float x, float y, float z, float r, float g, float b, float a, int light) {
        throw new AssertionError();
    }

}
