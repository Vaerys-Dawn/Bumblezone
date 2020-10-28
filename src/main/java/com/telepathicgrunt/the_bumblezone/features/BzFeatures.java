package com.telepathicgrunt.the_bumblezone.features;

import java.util.function.Supplier;

import com.telepathicgrunt.the_bumblezone.Bumblezone;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BzFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Bumblezone.MODID);
	
    public static final RegistryObject<Feature<NoFeatureConfig>> HONEYCOMB_HOLE = createFeature("honeycomb_holes", () -> new HoneycombHole(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> HONEYCOMB_CAVES = createFeature("honeycomb_caves", () -> new HoneycombCaves(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> CAVE_SUGAR_WATERFALL = createFeature("cave_sugar_waterfall", () -> new CaveSugarWaterfall(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> BEE_DUNGEON = createFeature("bee_dungeon", () -> new BeeDungeon(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> SPIDER_INFESTED_BEE_DUNGEON = createFeature("spider_infested_bee_dungeon", () -> new SpiderInfestedBeeDungeon(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> HONEY_CRYSTAL_FEATURE = createFeature("honey_crystal_feature", () -> new HoneyCrystalFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<Feature<BzBEOreFeatureConfig>> BZ_BE_ORE_FEATURE = createFeature("bz_be_ore_feature", () -> new BzBEOreFeature(BzBEOreFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoFeatureConfig>> BZ_BEES_WAX_PILLAR_FEATURE = createFeature("bz_bees_wax_pillar_feature", () -> new BzBeesWaxPillarFeature(NoFeatureConfig.CODEC));

    private static <F extends Feature<?>> RegistryObject<F> createFeature(String name, Supplier<F> feature)
    {
		return FEATURES.register(name, feature);
	}
}
