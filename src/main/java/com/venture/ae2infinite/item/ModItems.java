package com.venture.ae2infinite.item;

import com.venture.ae2infinite.item.boosters.DimensionCardItem;
import com.venture.ae2infinite.item.boosters.InfiniteCardItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import com.venture.ae2infinite.AE2Infinite;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item INFINITE_CARD = registerItem(new InfiniteCardItem(new FabricItemSettings()), "infinite_card");
    public static final Item DIMENSION_CARD = registerItem(new DimensionCardItem(new FabricItemSettings()), "dimension_card");

    public static Item registerItem(Item item, String id) {
        Identifier itemId = new Identifier(AE2Infinite.MOD_ID, id);
        Item registeredItem;
        registeredItem = Registry.register(Registries.ITEM, itemId, item);
        return registeredItem;
    }

    public static final RegistryKey<ItemGroup> INFINITE_BOOSTER_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(),
            Identifier.of(AE2Infinite.MOD_ID, "infinite_booster"));

    public static final ItemGroup INFINITE_BOOSTER_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.INFINITE_CARD))
            .displayName(Text.translatable("item_group.ae2infinite.tab"))
            .build();

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, INFINITE_BOOSTER_GROUP_KEY, INFINITE_BOOSTER_GROUP);

        ItemGroupEvents.modifyEntriesEvent(INFINITE_BOOSTER_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(INFINITE_CARD);
            itemGroup.add(DIMENSION_CARD);
        });
    }
}
