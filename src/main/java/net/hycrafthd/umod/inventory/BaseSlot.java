package net.hycrafthd.umod.inventory;

import net.hycrafthd.umod.render.RGBA;
import net.hycrafthd.umod.utils.StringReturnment;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class BaseSlot extends Slot {
	
	private boolean visible = true;
	private RGBA nstart = null;
	private RGBA nend = null;
	private RGBA start = null;
	private RGBA end = null;
	private StringReturnment ret = null;

	public BaseSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}

	public boolean isVisible(){
		return this.visible;
	}
	
	public void setVisible(boolean vis){
		this.visible = vis;
	}
	
	public void setHoverColor(RGBA nstart,RGBA nend,RGBA start,RGBA end){
		this.start = start;
		this.end = end;
		this.nstart = nstart;
		this.nend = nend;
	}
	
	public RGBA getHoverColor(int b){
		switch(b){
		case 0:
			return nstart;
		case 1:
			return nend;
		case 2:
			return start;
		case 3:
			return end;
		}
		return null;
	}
	
	public boolean hasColor(){
		return start != null && end != null;
	}
	
	public boolean hasString(){
		return ret != null && ret.getString() != null;
	}
	
	public String getString(){
		return ret.getString();
	}
	
	public void setStringRet(StringReturnment returnm){
		ret = returnm;
	}
	
	public int getWidth(){
		int size;
        size = ret.getString().length()*5; 
        if(hasMoreLines()){
        	size = 0;
        	String[] str = ret.getString().split("\n");
        	for(int i = 0;i < str.length;i++){
        		if(str[i].length()*5 > size){
        			size = str[i].length()*5;
        		}
        	}
        }
		return size + 10;
	}
	
	public int getHeight(){
		int size = 16;
		if(hasMoreLines()){
		    String[] str = ret.getString().split("\n");
		    size = size * str.length;
		}
		return size;
	}
	
	public boolean hasMoreLines(){
		return ret.getString().contains("\n");
	}
	
	public int getFontColor(){
		return 0xFFFFFF;
	}
}