package net.telepathicgrunt.bumblezone.biome.surfacebuilders;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.telepathicgrunt.bumblezone.blocks.BzBlocksInit;


public class HoneySurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig>
{
	public HoneySurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> p_i51310_1_)
	{
		super(p_i51310_1_);
	}

	private static final BlockState STONE = Blocks.STONE.getDefaultState();
	private static final BlockState FILLED_POROUS_HONEYCOMB = BzBlocksInit.FILLED_POROUS_HONEYCOMB.get().getDefaultState();
	private static final BlockState POROUS_HONEYCOMB = BzBlocksInit.POROUS_HONEYCOMB.get().getDefaultState();
	private static final BlockState HONEYCOMB_BLOCK = Blocks.field_226908_md_.getDefaultState();


	public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config)
	{
		//creates grass surface normally
		SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, config);

		int xpos = x & 15;
		int zpos = z & 15;
		BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable();

		//makes stone below sea level into end stone
		for (int ypos = 255; ypos >= 0; --ypos)
		{
			blockpos$Mutable.setPos(xpos, ypos, zpos);
			BlockState currentBlockState = chunkIn.getBlockState(blockpos$Mutable);

			if (currentBlockState.getBlock() != null && currentBlockState.getMaterial() != Material.AIR)
			{
				if (currentBlockState == STONE)
				{
					chunkIn.setBlockState(blockpos$Mutable, HONEYCOMB_BLOCK, false);
				}
				else if (currentBlockState == POROUS_HONEYCOMB)
				{
					if (ypos <= seaLevel + 2 + Math.max(noise, 0) + random.nextInt(2))
					{
						chunkIn.setBlockState(blockpos$Mutable, FILLED_POROUS_HONEYCOMB, false);
					}
				}
				else if (currentBlockState == AIR)
				{
					if (ypos < seaLevel)
					{
						chunkIn.setBlockState(blockpos$Mutable, defaultFluid, false);
					}
				}
			}
		}

	}
}