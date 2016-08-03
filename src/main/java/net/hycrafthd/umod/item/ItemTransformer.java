package net.hycrafthd.umod.item;

import java.util.List;

import net.hycrafthd.umod.enumtype.EnumTypeTransformer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemTransformer extends ItemBase{
	
	public ItemTransformer() {
		super();
		this.hasSubtypes = true;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		EnumTypeTransformer type = EnumTypeTransformer.byMetadata(stack.getMetadata());
		return "item.transformer" + type.getName();
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(Item item, CreativeTabs creativetab, List list) {
		for (int i = 0; i < EnumTypeTransformer.values().length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
}