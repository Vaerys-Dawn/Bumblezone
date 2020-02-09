package net.telepathicgrunt.bumblezone.generation.layer;

import org.apache.logging.log4j.Level;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.PerlinNoiseGenerator;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;
import net.telepathicgrunt.bumblezone.Bumblezone;
import net.telepathicgrunt.bumblezone.world.biome.BiomeInit;


@SuppressWarnings("deprecation")
public enum BiomeLayer implements IAreaTransformer0
{
	INSTANCE;

	private static final int SUGAR_WATER = Registry.BIOME.getId(BiomeInit.SUGAR_WATER);
	private static final int HIVE_WALL = Registry.BIOME.getId(BiomeInit.HIVE_WALL);

	private static PerlinNoiseGenerator perlinGen;
//	private double max = 0;
//	private double min = 1;
	

	public int apply(INoiseRandom noise, int x, int z)
	{
		double perlinNoise = perlinGen.noiseAt((double) x * 0.1D, (double)z * 0.00001D, false) * 0.5D + 0.5D;
		
//		max = Math.max(max, perlinNoise);
//		min = Math.min(min, perlinNoise);
//		Bumblezone.LOGGER.log(Level.DEBUG, "Max: " + max +", Min: "+min + ", perlin: "+perlinNoise);

		if(Math.abs(perlinNoise) < 0.7)
		{
			return SUGAR_WATER;
		}
		else
		{
			return HIVE_WALL;
		}
	}


	public static void setSeed(long seed)
	{
		if (perlinGen == null)
		{
			SharedSeedRandom sharedseedrandom = new SharedSeedRandom(seed);
			perlinGen = new PerlinNoiseGenerator(sharedseedrandom, 0, 0);
		}
	}
}