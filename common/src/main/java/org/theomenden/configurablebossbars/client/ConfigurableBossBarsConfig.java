package org.theomenden.configurablebossbars.client;

import blue.endless.jankson.Comment;
import io.github.jamalam360.jamlib.config.ConfigExtensions;
import net.minecraft.network.chat.Component;
import net.minecraft.world.BossEvent;

import java.util.List;

public final class ConfigurableBossBarsConfig implements ConfigExtensions<ConfigurableBossBarsConfig> {
    @Comment("Determines if the bars should be enabled or disabled, defaults to false")
    public boolean shouldDisableBars = false;

    @Comment("Changing the colours text for boss bars, default is the standard gray text (argb: 16777215).")
    public int bossTextColor = 16777215; //default gray text

    @Comment("Changes the actual boss bar colour, defaults to Purple")
    public BossEvent.BossBarColor bossBarColor = BossEvent.BossBarColor.PURPLE; //default color

    @Override
    public List<Link> getLinks() {
        return List.of(
                new Link(Link.DISCORD, "https://discord.gg/MXCdCWeYGb", Component.translatable("config.configurablebossbars.discord")),
                new Link(Link.GITHUB, "https://github.com/theomenden", Component.translatable("config.configurablebossbars.github")),
                new Link(Link.GENERIC_LINK, "https://modrinth.com/project/configurable-boss-bars", Component.translatable("config.configurablebossbars.modrinth"))
        );
    }
}
