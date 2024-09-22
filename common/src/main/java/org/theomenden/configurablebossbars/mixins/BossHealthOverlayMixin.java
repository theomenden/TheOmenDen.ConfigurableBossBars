package org.theomenden.configurablebossbars.mixins;

import org.theomenden.configurablebossbars.client.ConfigurableBossBarsClient;
import it.unimi.dsi.fastutil.objects.Reference2IntLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.BossHealthOverlay;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

@Mixin(BossHealthOverlay.class)
public abstract class BossHealthOverlayMixin {

    @Unique
    private static final Reference2IntMap<LerpingBossEvent> theOmenDen_ConfigurableBossBars$bossBars = new Reference2IntLinkedOpenHashMap<>();

    @Inject(
            method = "render",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onInitialRendering(CallbackInfo ci) {
        if(ConfigurableBossBarsClient.configuration.shouldDisableBars) {
            ci.cancel();
        }
    }

    @ModifyConstant(method = "render", constant = @Constant(intValue = 16777215))
    private int changeColor(int original) {
        if(ConfigurableBossBarsClient.configuration.bossTextColor != original){
            return ConfigurableBossBarsClient.configuration.bossTextColor;
        }
        return original;
    }
    @Redirect(method = "render",
    at = @At(
            value = "INVOKE",
            target = "Ljava/util/Collection;iterator()Ljava/util/Iterator;"
    ))
    private Iterator<LerpingBossEvent> onBarRendering(Collection<LerpingBossEvent> collection) {
        HashMap<String, LerpingBossEvent> chosenLerpingMap = new HashMap<>();

        collection.iterator()
                .forEachRemaining(bar -> {
                    var name = bar.getName().getString();
                    if(chosenLerpingMap.containsKey(name)) {
                        var bossMapping = chosenLerpingMap.get(name);
                        theOmenDen_ConfigurableBossBars$bossBars.computeInt(bossMapping, (bossMap, integer) -> integer == null ? 2 : integer + 1);
                    } else {
                        chosenLerpingMap.put(name, bar);
                    }
                });
        return chosenLerpingMap.values().iterator();
    }

    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
            target="Lnet/minecraft/client/gui/components/LerpingBossEvent;getName()Lnet/minecraft/network/chat/Component;")
    )
    private Component onFormatBossTextString(LerpingBossEvent lerpingBossEvent) {
        var count = theOmenDen_ConfigurableBossBars$bossBars.getOrDefault(lerpingBossEvent, 1);

        if(count == 1) {
            return lerpingBossEvent.getName();
        }

        theOmenDen_ConfigurableBossBars$bossBars.removeInt(lerpingBossEvent);
        var name = lerpingBossEvent.getName().copy();
        var bossCount = Component.literal(String.format(" x%d", count)).copy();

        bossCount.setStyle(bossCount
                .getStyle()
                .withColor(ChatFormatting.GRAY)
                .withItalic(true));
        name.append(bossCount);
        return name;
    }

 @Inject(
         method = "reset",
         at = @At("TAIL")
 )
    private void onReset(CallbackInfo ci) {
        theOmenDen_ConfigurableBossBars$bossBars.clear();
 }

}
