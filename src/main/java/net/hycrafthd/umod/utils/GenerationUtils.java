package net.hycrafthd.umod.utils;

import java.util.Random;

import net.hycrafthd.umod.entity.EntityInfectedCow;
import net.hycrafthd.umod.enumtype.EnumTypeChestLooting;
import net.hycrafthd.umod.schematic.Schematic;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GenerationUtils {

	public static void generateOre(IBlockState state, Random random, int x, int z, World world, int chance, int minY, int maxY, int minVienSize, int maxVienSize) {
		GenerationUtils.generateOre(state, Blocks.stone, random, x, z, world, chance, minY, maxY, minVienSize, maxVienSize);
	}

	public static void generateOre(IBlockState state, Block blockin, Random random, int x, int z, World world, int chance, int minY, int maxY, int minVienSize, int maxVienSize) {

		int vienSize = minVienSize + random.nextInt(maxVienSize - minVienSize);
		int hightRange = maxY - minY;

		for (int i = 0; i < chance; i++) {
			int posX = x + random.nextInt(16);
			int posY = random.nextInt(hightRange) + minY;
			int posZ = z + random.nextInt(16);
			new WorldGenMinable(state, vienSize, BlockHelper.forBlock(blockin)).generate(world, random, new BlockPos(posX, posY, posZ));
		}
	}

	public static void generateSchematic(Class<? extends Schematic> clazz, Random random, int x, int z, World world, int chance, BiomeGenBase biome) {
		if (random.nextInt(chance) == 0) {
			int posX = x + random.nextInt(16);
			int posZ = z + random.nextInt(16);
			int posY = getWorldHeightAt(world, x, z);
			try {
				if (biome == null) {
					clazz.newInstance().generate(world, posX, posY, posZ);
					System.out.println("Schematic generiert: " + posX + " " + posY + " " + posZ);

				} else {
					if (world.getBiomeGenForCoords(new BlockPos(posX, posY, posZ)) == biome) {
						clazz.newInstance().generate(world, posX, posY, posZ);
						System.out.println("Schematic generiert: " + posX + " " + posY + " " + posZ);

					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static TileEntityChest generateChestLoot(TileEntityChest chest, EnumTypeChestLooting type, int minitems, int maxitems) {
		int itemslots = MathHelper.getRandomIntegerInRange(new Random(), minitems, maxitems);

		while (itemslots > 0) {
			itemslots--;
			int slot = MathHelper.getRandomIntegerInRange(new Random(), 0, chest.getSizeInventory() - 1);
			ItemStack stack = ChestLootUtils.getStack(type);
			chest.setInventorySlotContents(slot, stack);
		}

		return chest;

	}

	public static int getWorldHeightAt(World world, int x, int z) {
		int height = 0;
		for (int i = (int) (63 - 20); i < world.getActualHeight() - 1; i++) {
			BlockPos pos = new BlockPos(x, i, z);
			if (world.getBlockState(pos).getBlock().isSolidFullCube()) {
				height = pos.getY();
			}
		}
		return height;
	}

}
