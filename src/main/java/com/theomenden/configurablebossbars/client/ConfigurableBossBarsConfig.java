package com.theomenden.configurablebossbars.client;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraft.world.BossEvent;

@Config(name = ConfigurableBossBarsClient.MODID)
public class ConfigurableBossBarsConfig implements ConfigData {
    @ConfigEntry.Category("title")
    @ConfigEntry.Gui.Tooltip()
    public boolean shouldDisableBars = false;

    @ConfigEntry.Category("title")
    @ConfigEntry.ColorPicker(allowAlpha = true)
    @ConfigEntry.Gui.Tooltip()
    public int bossTextColor = 16777215; //default gray text

    @ConfigEntry.Category("title")
    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public BossEvent.BossBarColor bossBarColor = BossEvent.BossBarColor.PURPLE; //default color
}
