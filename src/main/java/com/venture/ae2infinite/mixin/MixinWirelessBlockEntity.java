package com.venture.ae2infinite.mixin;

import appeng.api.inventories.InternalInventory;
import appeng.blockentity.networking.WirelessAccessPointBlockEntity;
import appeng.util.inv.AppEngInternalInventory;
import appeng.util.inv.filter.IAEItemFilter;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = WirelessAccessPointBlockEntity.class, remap = false)
public class MixinWirelessBlockEntity {
    @Final private AppEngInternalInventory inv;
    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void init(BlockEntityType blockEntityType, BlockPos pos, BlockState blockState, CallbackInfo ci) {
        inv.setFilter(new IAEItemFilter() {
            @Override
            public boolean allowExtract(InternalInventory inv, int slot, int amount) {
                return true;
            }

            @Override
            public boolean allowInsert(InternalInventory inv, int slot, ItemStack stack) {
                return true;
            }
        });
    }
}
