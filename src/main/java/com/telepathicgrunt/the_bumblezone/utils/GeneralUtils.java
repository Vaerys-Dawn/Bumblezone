package com.telepathicgrunt.the_bumblezone.utils;

import com.telepathicgrunt.the_bumblezone.mixin.blocks.DefaultDispenseItemBehaviorInvoker;
import com.telepathicgrunt.the_bumblezone.mixin.world.BiomeGenerationSettingsAccessor;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IDispenseItemBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class GeneralUtils {

    /**
     * Helper method to make WB biomes mutable to add stuff to it later
     */
    public static void makeBiomeMutable(Biome biome){
        // Make the structure and features list mutable for modification late
        List<List<Supplier<ConfiguredFeature<?, ?>>>> tempFeature = ((BiomeGenerationSettingsAccessor)biome.getGenerationSettings()).bz_getFeatures();
        List<List<Supplier<ConfiguredFeature<?, ?>>>> mutableGenerationStages = new ArrayList<>();

        // Fill in generation stages so there are at least 10 or else Minecraft crashes.
        // (we need all stages for adding features/structures to the right stage too)
        for(int currentStageIndex = 0; currentStageIndex < Math.max(GenerationStage.Decoration.values().length, tempFeature.size()); currentStageIndex++){
            if(currentStageIndex >= tempFeature.size()){
                mutableGenerationStages.add(new ArrayList<>());
            }else{
                mutableGenerationStages.add(new ArrayList<>(tempFeature.get(currentStageIndex)));
            }
        }

        // Make the Structure and GenerationStages (features) list mutable for modification later
        ((BiomeGenerationSettingsAccessor)biome.getGenerationSettings()).bz_setFeatures(mutableGenerationStages);
    }

    // If it instanceof DefaultDispenseItemBehavior, call dispenseStack directly to avoid
    // playing particles and sound twice due to dispense method having that by default.
    public static ItemStack dispenseStackProperly(IBlockSource source, ItemStack stack, IDispenseItemBehavior defaultDispenseBehavior) {

        if (defaultDispenseBehavior instanceof DefaultDispenseItemBehavior) {
            return ((DefaultDispenseItemBehaviorInvoker) defaultDispenseBehavior).bz_invokeDispenseStack(source, stack);
        }
        else {
            // Fallback to dispense as someone chose to make a custom class without dispenseStack.
            return defaultDispenseBehavior.dispense(source, stack);
        }
    }
}
