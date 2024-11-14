package com.venture.ae2infinite.mixin;

import appeng.api.implementations.blockentities.IWirelessAccessPoint;
import appeng.api.implementations.menuobjects.ItemMenuHost;
import appeng.blockentity.networking.WirelessAccessPointBlockEntity;
import appeng.helpers.WirelessTerminalMenuHost;
import com.venture.ae2infinite.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(value = WirelessTerminalMenuHost.class, remap = false)
public class MixinWirelessTerminalMenuHost extends ItemMenuHost {
    public MixinWirelessTerminalMenuHost(PlayerEntity player, int slot, ItemStack stack) {
        super(player, slot, stack);
    }
    @Inject(method = "getWapSqDistance", at = @At("HEAD"), cancellable = true)
    private void testWap(IWirelessAccessPoint wirelessAccessPoint, CallbackInfoReturnable<Double> cir) {
        Objects.requireNonNull(wirelessAccessPoint.getGrid()).getMachines(WirelessAccessPointBlockEntity.class).forEach(wirelessBlockEntity -> {
            if(wirelessBlockEntity.getInternalInventory().getStackInSlot(0).isOf(ModItems.DIMENSION_CARD)) {
                cir.setReturnValue(1024.0D);
            }

            if(!this.getPlayer().getWorld().getRegistryKey().getValue().toString().equals(wirelessAccessPoint.getLocation().getLevel().getRegistryKey().getValue().toString())) {
                return;
            }

            if(wirelessBlockEntity.getInternalInventory().getStackInSlot(0).isOf(ModItems.INFINITE_CARD)) {
                cir.setReturnValue(256.0D);
            }
        });
    }

}
