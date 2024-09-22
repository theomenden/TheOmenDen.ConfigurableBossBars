package org.theomenden.configurablebossbars.neoforge;

import net.neoforged.api.distmarker.Dist;
import org.theomenden.configurablebossbars.client.ConfigurableBossBarsClient;
import net.neoforged.fml.common.Mod;

@Mod(value = ConfigurableBossBarsClient.MOD_ID, dist = Dist.CLIENT)
public final class ConfigurableBossBarsNeoForge {
    public ConfigurableBossBarsNeoForge() {
        // Run our common setup.
        ConfigurableBossBarsClient.init();
    }
}