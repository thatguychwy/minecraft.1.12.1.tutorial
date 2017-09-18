package chwy.tutmod.init;

import chwy.tutmod.Main;
import net.minecraft.item.Item;

public class ItemCore extends Item{
	public ItemCore(){
		setNoRepair();
		setCreativeTab(Main.tutmodtab);
	}
}
