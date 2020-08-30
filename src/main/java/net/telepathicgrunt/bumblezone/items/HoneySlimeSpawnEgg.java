package net.telepathicgrunt.bumblezone.items;

import net.minecraft.entity.EntityType;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.telepathicgrunt.bumblezone.entities.BzEntities;

public class HoneySlimeSpawnEgg extends SpawnEggItem {
    public HoneySlimeSpawnEgg(EntityType<?> typeIn, int primaryColorIn, int secondaryColorIn, Properties builder) {
        super(null, primaryColorIn, secondaryColorIn, builder);
    }

    @Override
    public EntityType<?> getType(CompoundNBT tag) {
        return BzEntities.HONEY_SLIME;
    }
}