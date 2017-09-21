package chwy.tutmod.blocks;

import chwy.tutmod.Main;
import chwy.tutmod.init.entities.EntityNuke;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockNuke extends BlockCore{
	
	public static final PropertyBool OVERLAY = PropertyBool.create("overlay");
	
	public BlockNuke() {
		super(Material.TNT);
		setUnlocalizedName("tutmod.nuke");
		setCreativeTab(Main.tutmodtab);
	}
	
	public void explode(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase igniter){
		if(!worldIn.isRemote){
			EntityNuke entitynuke = new EntityNuke(worldIn, (double) ((float) pos.getX() + 0.5f), (double) pos.getY(), (double) ((float) pos.getZ() + 0.5F), igniter);
			worldIn.spawnEntity(entitynuke);
		}
	}
	
	@Override
	public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn) {
		if (!worldIn.isRemote) {
			EntityNuke entitynuke = new EntityNuke(worldIn, (double) ((float) pos.getX() + 0.5F),
				(double) pos.getY(), (double) ((float) pos.getZ() + 0.5F), explosionIn.getExplosivePlacedBy());
			entitynuke.fuse = worldIn.rand.nextInt(entitynuke.fuse / 4) + entitynuke.fuse / 8;
			worldIn.spawnEntity(entitynuke);
		}
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (!worldIn.isRemote && entityIn instanceof EntityArrow) {
			EntityArrow entityarrow = (EntityArrow) entityIn;

			if (entityarrow.isBurning()) {
				this.explode(worldIn, pos, state, entityarrow.shootingEntity instanceof EntityLivingBase
				                                  ? (EntityLivingBase) entityarrow.shootingEntity : null);
				worldIn.setBlockToAir(pos);
			}
		}
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
		if (worldIn.isBlockPowered(pos)) {
			this.explode(worldIn, pos, state, null);
			worldIn.setBlockToAir(pos);
		}
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos p_189540_5_) {
		if (worldIn.isBlockPowered(pos)) {
			this.explode(worldIn, pos, state, null);
			worldIn.setBlockToAir(pos);
		}
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
	                                EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		return false;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(OVERLAY) ? 1 : 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(OVERLAY, (meta & 1) > 0);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, OVERLAY);
	}

}
