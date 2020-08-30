package net.telepathicgrunt.bumblezone;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.telepathicgrunt.bumblezone.blocks.BzBlocks;
import net.telepathicgrunt.bumblezone.capabilities.CapabilityPlayerPosAndDim;
import net.telepathicgrunt.bumblezone.client.BumblezoneClient;
import net.telepathicgrunt.bumblezone.configs.*;
import net.telepathicgrunt.bumblezone.dimension.BzDimension;
import net.telepathicgrunt.bumblezone.effects.BzEffects;
import net.telepathicgrunt.bumblezone.entities.BzEntities;
import net.telepathicgrunt.bumblezone.features.BzConfiguredFeatures;
import net.telepathicgrunt.bumblezone.features.BzFeatures;
import net.telepathicgrunt.bumblezone.features.decorators.BzPlacements;
import net.telepathicgrunt.bumblezone.items.BzItems;
import net.telepathicgrunt.bumblezone.items.DispenserItemSetup;
import net.telepathicgrunt.bumblezone.modCompat.ModChecker;
import net.telepathicgrunt.bumblezone.surfacebuilders.BzSurfaceBuilders;
import net.telepathicgrunt.bumblezone.utils.ConfigHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Bumblezone.MODID)
public class Bumblezone{

    public static final String MODID = "the_bumblezone";
    public static final ResourceLocation MOD_DIMENSION_ID = new ResourceLocation(Bumblezone.MODID, Bumblezone.MODID);
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static BzBeeAggressionConfigs.BzBeeAggressionConfigValues BzBeeAggressionConfig = null;
    public static BzBlockMechanicsConfigs.BzBlockMechanicsConfigValues BzBlockMechanicsConfig = null;
    public static BzDimensionConfigs.BzDimensionConfigValues BzDimensionConfig = null;
    public static BzDungeonsConfigs.BzDungeonsConfigValues BzDungeonsConfig = null;
    public static BzModCompatibilityConfigs.BzModCompatibilityConfigValues BzModCompatibilityConfig = null;

    public Bumblezone() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        modEventBus.addListener(this::setup);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> BumblezoneClient.subscribeClientEvents(modEventBus, forgeBus));

        // generates/handles config
        BzModCompatibilityConfig = ConfigHelper.register(ModConfig.Type.SERVER, BzModCompatibilityConfigs.BzModCompatibilityConfigValues::new);
        BzBlockMechanicsConfig = ConfigHelper.register(ModConfig.Type.SERVER, BzBlockMechanicsConfigs.BzBlockMechanicsConfigValues::new);
        BzBeeAggressionConfig = ConfigHelper.register(ModConfig.Type.SERVER, BzBeeAggressionConfigs.BzBeeAggressionConfigValues::new);
        BzDimensionConfig = ConfigHelper.register(ModConfig.Type.SERVER, BzDimensionConfigs.BzDimensionConfigValues::new);
        BzDungeonsConfig = ConfigHelper.register(ModConfig.Type.SERVER, BzDungeonsConfigs.BzDungeonsConfigValues::new);


    }



    private void setup(final FMLCommonSetupEvent event)
    {
        CapabilityPlayerPosAndDim.register();
        BzDimension.setupDimension();
        DispenserItemSetup.setupDispenserBehaviors();
        DeferredWorkQueue.runLater(Bumblezone::lateSetup);
    }


    // should run after most other mods just in case
    private static void lateSetup()
    {
        ModChecker.setupModCompat();
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event)
        {
            BzBlocks.registerBlocks();
        }

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event)
        {
            BzItems.registerItems();
        }

        @SubscribeEvent
        public static void registerEntity(final RegistryEvent.Register<EntityType<?>> event)
        {
            BzEntities.registerEntities();
        }

        /**
         * This method will be called by Forge when it is time for the mod to register features.
         */
        @SubscribeEvent
        public static void onRegisterFeatures(final RegistryEvent.Register<Feature<?>> event)
        {
            BzFeatures.registerFeatures();
            BzConfiguredFeatures.registerConfiguredFeatures();
        }

        /**
         * This method will be called by Forge when it is time for the mod to register effects.
         */
        @SubscribeEvent
        public static void onRegisterEffects(final RegistryEvent.Register<Effect> event)
        {
            BzEffects.registerEffects();
        }

        /**
         * This method will be called by Forge when it is time for the mod to register placement.
         */
        @SubscribeEvent
        public static void onRegisterPlacements(final RegistryEvent.Register<Placement<?>> event)
        {
            BzPlacements.registerPlacements();
        }

        /**
         * This method will be called by Forge when it is time for the mod to register surface builders.
         */
        @SubscribeEvent
        public static void onRegisterSurfacebuilders(final RegistryEvent.Register<SurfaceBuilder<?>> event)
        {
            BzSurfaceBuilders.registerSurfaceBuilders();
        }


        @SubscribeEvent
        public static void onRegisterSerializers(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
            BzItems.registerCustomRecipes(event);
        }
    }

}
