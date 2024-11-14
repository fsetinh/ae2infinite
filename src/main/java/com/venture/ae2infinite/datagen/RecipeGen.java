package com.venture.ae2infinite.datagen;

import appeng.core.definitions.AEItems;
import com.venture.ae2infinite.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class RecipeGen extends FabricRecipeProvider {
    public RecipeGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> consumer) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.INFINITE_CARD, 1)
                .pattern("#W#")
                .pattern("WsW")
                .pattern("nnn")
                .input('W', AEItems.WIRELESS_BOOSTER)
                .input('#', Items.ENDER_EYE)
                .input('n', Items.NETHERITE_INGOT)
                .input('s', Items.NETHER_STAR)
                .criterion(hasItem(AEItems.WIRELESS_BOOSTER), conditionsFromItem(AEItems.WIRELESS_BOOSTER))
                .offerTo(consumer, new Identifier(getRecipeName(ModItems.INFINITE_CARD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DIMENSION_CARD, 1)
                .pattern("IsI")
                .pattern("s#s")
                .pattern("IsI")
                .input('I', ModItems.INFINITE_CARD)
                .input('s', Items.NETHER_STAR)
                .input('#', Items.ENDER_EYE)
                .criterion(hasItem(ModItems.INFINITE_CARD), conditionsFromItem(ModItems.INFINITE_CARD))
                .offerTo(consumer, new Identifier(getRecipeName(ModItems.DIMENSION_CARD)));
    }
}