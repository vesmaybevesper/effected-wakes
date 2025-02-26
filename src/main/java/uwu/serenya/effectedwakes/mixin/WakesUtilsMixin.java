package uwu.serenya.effectedwakes.mixin;

import com.goby56.wakes.utils.WakesUtils;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import org.ladysnake.effective.EffectiveUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;



@Mixin(WakesUtils.class)
public abstract class WakesUtilsMixin {

    @Redirect(
            method = "spawnPaddleSplashCloudParticle",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V")
    )
    private static void effectiveSplash(World instance, ParticleEffect parameters,
                                        double x, double y, double z,
                                        double velocityX, double velocityY, double velocityZ,
                                        @Local(argsOnly = true) BoatEntity boat) {
        RandomGenerator random = instance.getRandom();
        int count = random.range(5, 8);

        for(int i = 0; i < count; i++) {
            EffectiveUtils.spawnWaterEffect(instance,
                    new Vec3d(x, y, z),
                    random.nextGaussian() / 20f,
                    random.nextFloat() / 4f,
                    random.nextGaussian() / 20f,
                    EffectiveUtils.WaterEffectType.DROPLET);
        }
    }
}
