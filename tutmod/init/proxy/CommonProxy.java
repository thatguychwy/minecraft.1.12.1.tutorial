package chwy.tutmod.init.proxy;

import chwy.tutmod.init.compat.ICompatModule;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommonProxy implements ICompatModule{
	@Override
	public void preInit(FMLPreInitializationEvent event) {}
	@Override
	public void init(FMLInitializationEvent event) {}
	@Override
	public void postInit(FMLPostInitializationEvent event) {}
	@Override
	public void serverStarting(FMLServerStartingEvent event) {}
}
