package chwy.tutmod.corecomponents;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.GameData;

import java.lang.reflect.InvocationTargetException;

public class EasyRegistry {

	public static void registerItem(Item item) {
		GameData.register_impl(item);
	}
	
	public static void registerItem(Item item, ResourceLocation name) {
		item.setRegistryName(name);
		GameData.register_impl(item);
	}

	@SideOnly(Side.CLIENT)
	public static void registerItemModel(Item i, int meta) {
		ResourceLocation loc = i.getRegistryName();
		ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(loc, "inventory"));
	}

	@SideOnly(Side.CLIENT)
	public static void registerItemModel(Block b, int meta) {
		registerItemModel(Item.getItemFromBlock(b), meta);
	}

	@SideOnly(Side.CLIENT)
	public static void registerItemModel(Item i, int meta, String variant) {
		ResourceLocation loc = i.getRegistryName();
		ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(loc, "type=" + variant));
	}
}
