package net.hycrafthd.umod;

import java.util.ArrayList;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.EventBus;

public class UEvent {

	private EventBus bus;
	private ArrayList<Object> events;
	
	public UEvent() {
		init();
	}

	private void init() {
		this.bus = MinecraftForge.EVENT_BUS;
		this.events = new ArrayList<Object>();
	}
	
	public boolean addEvent(Object event){
		if(events.contains(event)) return false;
		events.add(event);
		Logger.debug(UEvent.class, "addEvent(e)", "Add Mod Event to List");
		return true;
	}
	
	public boolean removeEvent(Object event){
		if(!events.contains(event)) return false;
		events.remove(event);
		Logger.debug(UEvent.class, "removeEvent(e)", "Remove Mod Event from List");
		return true;
	}
	
	public void register() {
		for(Object event : events) {
			bus.register(event);
			FMLCommonHandler.instance().bus().register(event);
		}
		Logger.debug(UEvent.class, "register()", "All Events in the List registered");
	}

	public void setEvents(ArrayList<Object> events) {
		this.events = events;
	}
	
	public ArrayList<Object> getEvents() {
		return events;
	}
	
}
