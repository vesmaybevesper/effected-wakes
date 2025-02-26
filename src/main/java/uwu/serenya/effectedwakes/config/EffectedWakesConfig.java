package uwu.serenya.effectedwakes.config;


import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.minecraft.text.Text;
import org.quiltmc.loader.api.QuiltLoader;
import uwu.serenya.effectedwakes.Constants;
import uwu.serenya.effectedwakes.EffectedWakes;

public class EffectedWakesConfig {


    public static ConfigClassHandler<EffectedWakesConfig> HANDLER =
            ConfigClassHandler.createBuilder(EffectedWakesConfig.class)
                    .id(EffectedWakes.id("config"))
                    .serializer(config -> GsonConfigSerializerBuilder.create(config)
                            .setPath(QuiltLoader.getConfigDir().resolve(Constants.MOD_ID + ".json5"))
                            .setJson5(true)
                            .build())
                    .build();

    public static void init() {

    }

}
