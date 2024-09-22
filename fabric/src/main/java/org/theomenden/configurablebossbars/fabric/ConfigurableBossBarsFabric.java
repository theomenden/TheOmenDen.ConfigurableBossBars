package org.theomenden.configurablebossbars.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.theomenden.configurablebossbars.client.ConfigurableBossBarsClient;

@Environment(EnvType.CLIENT)
public final class ConfigurableBossBarsFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ConfigurableBossBarsClient.init();
    }
}
