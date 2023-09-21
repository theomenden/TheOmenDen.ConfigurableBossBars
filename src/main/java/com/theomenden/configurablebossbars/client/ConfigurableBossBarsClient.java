package com.theomenden.configurablebossbars.client;

import com.mojang.blaze3d.platform.InputConstants;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurableBossBarsClient implements ClientModInitializer {
    public static final String MODID = "configurablebossbars";
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurableBossBarsClient.class);

    public static KeyMapping toggleBarsKey;
    public static ConfigurableBossBarsConfig configuration;

    private static void onToggleBarsKeyPressed(Minecraft minecraft) {
        if(toggleBarsKey.consumeClick()) {
            configuration.shouldDisableBars = !configuration.shouldDisableBars;
        }
    }

    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        AutoConfig.register(ConfigurableBossBarsConfig.class, GsonConfigSerializer::new);

        configuration = AutoConfig
                .getConfigHolder(ConfigurableBossBarsConfig.class)
                .getConfig();

        toggleBarsKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.cbb.launcher",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_F7,
                "category.cbb"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(ConfigurableBossBarsClient::onToggleBarsKeyPressed);
        LOGGER.info("Configure your boss bars!");
    }
}
