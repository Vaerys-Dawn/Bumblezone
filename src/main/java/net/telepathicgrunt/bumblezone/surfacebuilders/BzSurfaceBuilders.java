package net.telepathicgrunt.bumblezone.surfacebuilders;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.telepathicgrunt.bumblezone.Bumblezone;

public class BzSurfaceBuilders {
    public static final SurfaceBuilder<SurfaceBuilderConfig> HONEY_SURFACE_BUILDER = new HoneySurfaceBuilder(SurfaceBuilderConfig.CODEC);

    public static void registerSurfaceBuilders() {
        Registry.register(Registry.SURFACE_BUILDER, new ResourceLocation(Bumblezone.MODID, "honey_surface_builder"), HONEY_SURFACE_BUILDER);
    }
}
