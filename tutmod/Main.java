package chwy.tutmod;

import chwy.tutmod.init.ModItems;
import chwy.tutmod.init.lib.ModInfo;
import chwy.tutmod.init.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class Main {

	@Instance
	public static Main instance;
	@SidedProxy(clientSide = ModInfo.CLIENT_PROXY_CLASS, serverSide = ModInfo.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) throws InstantiationException, IllegalAccessException{
		//Register Mod Blocks
		
		//Register Mod Items
		ModItems.init();
		
		proxy.preInit(event);
	}
	@EventHandler
	public void Init(FMLInitializationEvent event){
		//Recipes
		
		//Client only init
		proxy.init(event);
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){

	}
	
	//Creative Tab
	public static CreativeTabs tutmodtab = new CreativeTabs("tab_tutmod"){
		@Override
		public ItemStack getTabIconItem(){
			return new ItemStack(Items.COAL);
		}
	};
	
}//End of Class
