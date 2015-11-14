package net.hycrafthd.umod.container;

import net.hycrafthd.umod.UMod;
import net.hycrafthd.umod.inventory.BaseOreInputSlot;
import net.hycrafthd.umod.inventory.BaseSlotOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ContainerPulverizer extends ContainerBase{

	private static Slot input;
	private static Slot[] output = new Slot[3];
	
	public ContainerPulverizer(IInventory inv, EntityPlayer pl, World wo) {
		super(inv, pl, ((TileEntity) inv).getPos(), wo);
		output[0] = this.addSlotToContainer(new BaseSlotOutput(inv, 0, 116, 24));
		output[1] = this.addSlotToContainer(new BaseSlotOutput(inv, 1, 98, 54));
		output[2] = this.addSlotToContainer(new BaseSlotOutput(inv, 2, 126, 54));
		input = this.addSlotToContainer(new BaseOreInputSlot(inv, 3, 30, 23));
				
	}
	
	@Override
	public ItemStack slotClick(int slotId, int clickedButton, int mode, EntityPlayer playerIn) {
		UMod.log.info("Slot Id:" + slotId + " Clicked Button:" + clickedButton + " Mode:"+mode);
		return super.slotClick(slotId, clickedButton, mode, playerIn);
	}
	
}
