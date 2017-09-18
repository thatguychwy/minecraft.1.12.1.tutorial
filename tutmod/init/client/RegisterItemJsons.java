package chwy.tutmod.init.client;

import chwy.tutmod.init.ModItems;
import chwy.tutmod.items.ItemIngots;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class RegisterItemJsons {

	public static void registerModels(){
		registerItems();
	}
	
	private static void registerItems(){
		for (int i = 0; i < ItemIngots.types.length; ++i) {
			String[] name = ItemIngots.types.clone();
			registerBlockstate(ModItems.INGOTS, i, name[i], "items/materials/");
		}
	}
	
	private static void register(Item item, int meta, String name) {
		ModelLoader.setCustomModelResourceLocation(item, meta,
			new ModelResourceLocation("tutmod:" + name, "inventory"));
	}

	private static void register(Item item, String name) {
		register(item, 0, name);
	}

	@SuppressWarnings("unused")
	private static void register(Block block, int meta, String name) {
		register(Item.getItemFromBlock(block), meta, name);
	}

	private static void register(Block block, String name) {
		register(Item.getItemFromBlock(block), 0, name);
	}

	@SuppressWarnings("unused")
	private static void registerBlockstate(Item i, int meta, String variant) {
		registerBlockstate(i, meta, variant, "");
	}

	private static void registerBlockstate(Item i, int meta, String variant, String dir) {
		ResourceLocation loc = new ResourceLocation("tutmod", dir + i.getRegistryName().getResourcePath());
		ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(loc, "type=" + variant));
	}

	@SuppressWarnings("unused")
	private static void registerBlockstate(Block i, int meta, String variant) {
		registerBlockstate(i, meta, variant, "");
	}

	private static void registerBlockstate(Block i, int meta, String variant, String dir) {
		registerBlockstate(Item.getItemFromBlock(i), meta, variant, dir);
	}
}
