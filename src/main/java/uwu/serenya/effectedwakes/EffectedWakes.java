package uwu.serenya.effectedwakes;

import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import uwu.serenya.effectedwakes.config.EffectedWakesConfig;

public class EffectedWakes implements ClientModInitializer {
    @Override
    public void onInitializeClient(ModContainer mod) {
        Constants.LOGGER.info("Loading EffectedWakes...");
        EffectedWakesConfig.init();
    }

    public static Identifier id(String id) {
        return new Identifier(Constants.MOD_ID, id);
    }
}
