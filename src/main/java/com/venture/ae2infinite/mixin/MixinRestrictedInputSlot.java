package com.venture.ae2infinite.mixin;

import appeng.menu.slot.RestrictedInputSlot;
import com.venture.ae2infinite.item.ModItems;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RestrictedInputSlot.class)
public class MixinRestrictedInputSlot {
    @Inject(method = "canInsert", at = @At("HEAD"), cancellable = true)
    private void mayPlace(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if(stack.isOf(ModItems.INFINITE_CARD) || stack.isOf(ModItems.DIMENSION_CARD)) {
            cir.setReturnValue(true);
        }
    }
}
