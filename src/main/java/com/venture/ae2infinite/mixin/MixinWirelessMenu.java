package com.venture.ae2infinite.mixin;

import appeng.menu.implementations.WirelessAccessPointMenu;
import appeng.menu.slot.RestrictedInputSlot;
import com.venture.ae2infinite.item.ModItems;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = WirelessAccessPointMenu.class)
public abstract class MixinWirelessMenu {
    @Shadow protected abstract void setRange(long range);
    @Shadow @Final private RestrictedInputSlot boosterSlot;
    @Inject(method = "sendContentUpdates", at = @At(value = "INVOKE", target = "Lappeng/menu/AEBaseMenu;sendContentUpdates()V", shift = At.Shift.BEFORE))
    private void sendContentUpdates(CallbackInfo ci) {
        if(this.boosterSlot.getStack().isOf(ModItems.INFINITE_CARD) || this.boosterSlot.getStack().isOf(ModItems.DIMENSION_CARD)) {
            this.setRange(Long.MAX_VALUE);
        }
    }
}
