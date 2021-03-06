package com.telepathicgrunt.the_bumblezone.dimension.layer;

import com.telepathicgrunt.the_bumblezone.dimension.BzBiomeProvider;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;


public enum BzBiomePillarLayer implements ICastleTransformer {
    INSTANCE;

    public int apply(INoiseRandom context, int n, int e, int s, int w, int center) {

        if (context.random(12) == 0 && n == center && e == center && s == center && w == center) {
            return BzBiomeProvider.LAYERS_BIOME_REGISTRY.getId(
                    BzBiomeProvider.LAYERS_BIOME_REGISTRY.getOrDefault(BzBiomeProvider.HIVE_PILLAR));
        }

        return center;
    }

}