package chwy.tutmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockCore extends Block{

	public BlockCore(Material materialIn) {
		super(materialIn);
	}
	public int getRenderType(){
		return 3;
	}

}
