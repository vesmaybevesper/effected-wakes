package uwu.serenya.effectedwakes;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import org.jetbrains.annotations.Nullable;
import org.ladysnake.effective.Effective;
import org.ladysnake.effective.EffectiveConfig;
import org.ladysnake.effective.particle.contracts.SplashParticleInitialData;
import org.ladysnake.effective.particle.types.SplashParticleType;

public class EffectedWakesUtils {

	public static void spawnSplashAccurate(World world, Vec3d pos, double velocityX, double velocityY, double velocityZ, @Nullable SplashParticleInitialData data) {
		SplashParticleType splash = Effective.SPLASH;
		BlockPos blockPos = BlockPos.create(pos.getX(), pos.getY(), pos.getZ());
		if (EffectiveConfig.glowingPlankton && isNightTime(world) && world.getBiome(blockPos).isRegistryKey(Biomes.WARM_OCEAN)) {
			splash = Effective.GLOW_SPLASH;
		}

		world.addParticle(splash.setData(data), pos.getX(), pos.getY() + .9f, pos.getZ(), velocityX, velocityY, velocityZ);
	}

	public static boolean isNightTime(World world) {
		return world.getSkyAngle(world.getTimeOfDay()) >= 0.25965086 && world.getSkyAngle(world.getTimeOfDay()) <= 0.7403491;
	}


}
