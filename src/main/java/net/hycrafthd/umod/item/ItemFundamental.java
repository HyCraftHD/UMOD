package net.hycrafthd.umod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFundamental extends ItemBase {
	
	public ItemFundamental() {
		super();
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		// BlockPos pos = new BlockPos(playerIn.posX, playerIn.posY, playerIn.posZ);
		// new USchematicInfectedRuin().generate(worldIn, pos.getX(), pos.getY(), pos.getZ());
		return super.onItemRightClick(itemStackIn, worldIn, playerIn);
	}
}
