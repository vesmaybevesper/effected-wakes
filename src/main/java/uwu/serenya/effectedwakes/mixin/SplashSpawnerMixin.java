package uwu.serenya.effectedwakes.mixin;


import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import org.ladysnake.effective.particle.contracts.SplashParticleInitialData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import uwu.serenya.effectedwakes.EffectedWakesUtils;


@Mixin(value = Entity.class, priority = 1500)
public abstract class SplashSpawnerMixin {


    @Shadow
    public World world;
    @Shadow
    @Final
    protected RandomGenerator random;

    @Shadow
    public abstract double getX();

    @Shadow
    public abstract double getZ();

    @Shadow
    public abstract double getY();

    @TargetHandler(
            mixin = "org.ladysnake.effective.mixin.water.SplashSpawner",
            name = "onSwimmingStart"
    )
    @Redirect(
            method = "@MixinSquared:Handler",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/ladysnake/effective/EffectiveUtils;spawnSplash(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;DDDLorg/ladysnake/effective/particle/contracts/SplashParticleInitialData;)V"
            )
    )

    public void uwu(World world, BlockPos pos, double velocityX, double velocityY, double velocityZ, SplashParticleInitialData data) {
        EffectedWakesUtils.spawnSplashAccurate(this.world,
                new Vec3d(this.getX(), pos.getY(), this.getZ()),
                0, 
                0,
                0, 
                data);
    }
}
