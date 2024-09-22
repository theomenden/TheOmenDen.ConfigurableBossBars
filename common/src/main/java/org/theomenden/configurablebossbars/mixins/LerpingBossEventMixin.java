package org.theomenden.configurablebossbars.mixins;

import org.theomenden.configurablebossbars.client.ConfigurableBossBarsClient;
import net.minecraft.world.BossEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BossEvent.class)
public abstract class LerpingBossEventMixin {

    @Inject(
            method = "getColor",
            at = @At("RETURN"),
            cancellable = true
    )
    public void onColorRetrieval(CallbackInfoReturnable<BossEvent.BossBarColor> cir) {
        if(ConfigurableBossBarsClient.configuration.bossBarColor != null) {
            cir.setReturnValue(ConfigurableBossBarsClient.configuration.bossBarColor);
        }
    }
}
