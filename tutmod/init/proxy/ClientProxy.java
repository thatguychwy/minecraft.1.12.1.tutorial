package chwy.tutmod.init.proxy;

import chwy.tutmod.init.client.RegisterItemJsons;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{

	public static ResourceLocation getItemLocation(Item item) {
		Object o = item.getRegistryName();
		if (o == null) {
			return null;
		}
		return (ResourceLocation) o;
	}

	private static ResourceLocation registerIt(Item item, final ResourceLocation location) {
		ModelLoader.setCustomMeshDefinition(item, new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return new ModelResourceLocation(location, "inventory");
			}
		});
		ModelLoader.registerItemVariants(item, location);

		return location;
	}
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		
		//Registering Item Jsons
		RegisterItemJsons.registerModels();
	}
	public void init(FMLInitializationEvent event) {
		super.init(event);
		
	}
	
	protected void registerItemModel(ItemStack item, String name) {
		ModelLoader.registerItemVariants(item.getItem(), new ResourceLocation(name));
		ModelLoader.setCustomModelResourceLocation(item.getItem(), item
			.getMetadata(), new ModelResourceLocation(name, "inventory"));
	}
	public ResourceLocation registerItemModel(Item item) {
		ResourceLocation itemLocation = getItemLocation(item);
		if (itemLocation == null) {
			return null;
		}
		return registerIt(item, itemLocation);
	}
	
	
	
	
	
}
