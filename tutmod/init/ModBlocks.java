package chwy.tutmod.init;

import chwy.tutmod.blocks.BlockNuke;
import chwy.tutmod.corecomponents.EasyRegistry;
import chwy.tutmod.init.lib.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

public class ModBlocks {

	//Basic Blocks
	public static Block NUKE;
	
	public static void init(){
		NUKE = new BlockNuke();
		registerBlock(NUKE, "nuke");
	}
	
	public static void registerBlock(Block block, String name){
		name = name.toLowerCase();
		block.setUnlocalizedName(ModInfo.NAME + ":" + name);
		EasyRegistry.registerBlock(block, new ResourceLocation(ModInfo.MOD_ID, name));
	}
	
	public static void registerBlock(Block block, Class<? extends ItemBlock> itemclass, String name){
		name = name.toLowerCase();
		block.setUnlocalizedName(ModInfo.MOD_ID + ":" + name);
		EasyRegistry.registerBlock(block, itemclass, new ResourceLocation(ModInfo.MOD_ID, name));
	}
}
