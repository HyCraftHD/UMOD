package net.hycrafthd.umod;

import net.hycrafthd.umod.block.BlockBlocks;
import net.hycrafthd.umod.block.BlockOres;
import net.hycrafthd.umod.block.BlockPulverizer;
import net.hycrafthd.umod.item.ItemBlockBlocks;
import net.hycrafthd.umod.item.ItemBlockOres;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class UBlocks {

	// Ores
	public static Block ores;
	// Blocks
	public static Block blocks;
	//Pulverizer
	public static Block pulver;

	public UBlocks() {
		init();
		register();
		oredirectionary();
	}

	private void init() {
		// Ore
		ores = new BlockOres().setUnlocalizedName("ores").setCreativeTab(UReference.tab);
		// Blocks
		blocks = new BlockBlocks().setUnlocalizedName("blocks").setCreativeTab(UReference.tab);
		//Pulverizer
		pulver = new BlockPulverizer();
	}

	private void register() {
		// Ore
		UUtils.registerBlock(ores, ItemBlockOres.class);
		// Blocks
		UUtils.registerBlock(blocks, ItemBlockBlocks.class);
		//Pulverizer
        UUtils.registerBlock(pulver,ItemBlock.class);
	}

	private void oredirectionary() {
		// Ore
		UUtils.registerOredirectionary(ores);
		// Blocks
		UUtils.registerOredirectionary(blocks);
		//Pulverizer
        UUtils.registerOredirectionary(pulver);
	}

}
