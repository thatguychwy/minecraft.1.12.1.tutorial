package chwy.tutmod.init.events;

import java.util.List;
import java.util.ArrayList;

import net.minecraftforge.registries.IForgeRegistryEntry;

public class RecipeHandler {

	@SuppressWarnings("rawtypes")
	private static List<IForgeRegistryEntry> hiddenEntrys = new ArrayList<>();

	public static void hideEntry(IForgeRegistryEntry<?> entry) {
		hiddenEntrys.add(entry);
	}
	
}
