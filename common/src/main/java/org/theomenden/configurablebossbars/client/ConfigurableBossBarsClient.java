package org.theomenden.configurablebossbars.client;

import com.mojang.blaze3d.platform.InputConstants;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import io.github.jamalam360.jamlib.JamLib;
import io.github.jamalam360.jamlib.JamLibPlatform;
import io.github.jamalam360.jamlib.config.ConfigManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConfigurableBossBarsClient {
    public static final String MOD_ID = "configurablebossbars";
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final ConfigManager<ConfigurableBossBarsConfig> CONFIG = new ConfigManager<>(MOD_ID, ConfigurableBossBarsConfig.class);
    public static KeyMapping toggleBarsKey;
    public static ConfigurableBossBarsConfig configuration;

    private static void onToggleBarsKeyPressed(Minecraft minecraft) {
        if(toggleBarsKey.consumeClick()) {
            configuration.shouldDisableBars = !configuration.shouldDisableBars;
        }
    }

    public static void init() {
        JamLib.checkForJarRenaming(ConfigurableBossBarsClient.class);
        configuration = CONFIG.get();
        toggleBarsKey = new KeyMapping(
                "key.cbb.launcher",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_F7,
                "category.cbb"
        );

        KeyMappingRegistry.register(toggleBarsKey);

        ClientTickEvent.CLIENT_POST.register(ConfigurableBossBarsClient::onToggleBarsKeyPressed);
        LOGGER.info("We're opening up your Boss Bars for configuration on '{}'!", JamLibPlatform.getPlatform().name());
    }
}
