package chwy.tutmod.items;

import java.security.InvalidParameterException;

import com.google.common.base.CaseFormat;

import chwy.tutmod.Main;
import chwy.tutmod.init.ItemCore;
import chwy.tutmod.init.ModItems;
import chwy.tutmod.init.events.RecipeHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemIngots extends ItemCore{

public static final String[] types = new String[] { "copper", "tin", "steel"};
	
	public ItemIngots(){
		setCreativeTab(Main.tutmodtab);
		setHasSubtypes(true);
		setUnlocalizedName("tutmod.ingot");
		RecipeHandler.hideEntry(this);
	}
	
	public static ItemStack getIngotByName(String name, int count) {
		name = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
		for (int i = 0; i < types.length; i++) {
			if (types[i].equalsIgnoreCase(name)) {
				return new ItemStack(ModItems.INGOTS, count, i);
			}
		}
		if (name.equalsIgnoreCase("iron")) {
			return new ItemStack(Items.IRON_INGOT);
		}
		if (name.equalsIgnoreCase("gold")) {
			return new ItemStack(Items.GOLD_INGOT);
		}
		throw new InvalidParameterException("The ingot " + name + " could not be found.");
	}

	public static ItemStack getIngotByName(String name) {
		return getIngotByName(name, 1);
	}

	@Override
	// gets Unlocalized Name depending on meta data
	public String getUnlocalizedName(ItemStack itemStack) {
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= types.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + types[meta];
	}

	// Adds Dusts SubItems To Creative Tab
	@Override
	public void getSubItems(CreativeTabs creativeTabs, NonNullList<ItemStack> list) {
		if (!isInCreativeTab(creativeTabs)) {
			return;
		}
		for (int meta = 0; meta < types.length; ++meta) {
			if (!types[meta].equals(ModItems.META_PLACEHOLDER)) {
				list.add(new ItemStack(this, 1, meta));
			}
		}
	}
	
}
