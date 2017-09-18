package chwy.tutmod.init;

import chwy.tutmod.corecomponents.EasyRegistry;
import chwy.tutmod.items.ItemIngots;
import net.minecraft.item.Item;

public class ModItems {
	
	
	public static final String META_PLACEHOLDER = "PLACEHOLDER_ITEM";
	public static Item INGOTS;
	
	public static void init() throws InstantiationException, IllegalAccessException{
		INGOTS = new ItemIngots();
		registerItem(INGOTS, "ingot");
	}
	
	public static void registerItem(Item item, String name){
		item.setRegistryName(name);
		EasyRegistry.registerItem(item);
	}
}
