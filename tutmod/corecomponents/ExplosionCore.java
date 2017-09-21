package chwy.tutmod.corecomponents;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.time.StopWatch;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class ExplosionCore extends Explosion{
	@Nonnull
	BlockPos center;
	
	@Nonnull
	World world;
	
	@Nonnull
	int radius;
	
	@Nonnull
	EntityLivingBase livingBase;
	
	public ExplosionCore(@Nonnull BlockPos center, @Nonnull World world, @Nonnull int radius){
		super(world, null, center.getX(), center.getY(), center.getZ(), radius, false, true);
		this.center = center;
		this.world = world;
		this.radius = radius;
	}
	
	public void setLivingBase(@Nullable EntityLivingBase livingBase){
		this.livingBase = livingBase;
	}
	
	public @Nullable EntityLivingBase getLivingBase(){
		return livingBase;
	}
	
	public void explode(){
		StopWatch watch = new StopWatch();
		watch.start();
		for(int tx = -radius; tx < radius + 1; tx++){
			for(int ty = -radius; ty < radius + 1; ty++){
				for(int tz = -radius; tz < radius + 1; tz++){
					if(Math.sqrt(Math.pow(tx, 2) + Math.pow(ty, 2) + Math.pow(tz, 2)) <= radius - 2){
						BlockPos pos = center.add(tx, ty, tz);
						IBlockState state = world.getBlockState(pos);
						Block block = state.getBlock();
						if(block != Blocks.BEDROCK && block != Blocks.AIR){
							block.onBlockDestroyedByExplosion(world, pos, this);
							world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
						}
					}
				}
			}
		}
	}
	
	@Override
	public void doExplosionA(){
		explode();
	}
	public void doExplosionB(boolean spawnParticles){
		explode();
	}
	
	public @Nullable EntityLivingBase getExplosionPlacedBy(){
		return livingBase;
	}
	
	@Override
	public List<BlockPos> getAffectedBlockPositions(){
		List<BlockPos> positions = new ArrayList<>();
		for(int tx = -radius; tx < radius + 1; tx++){
			for(int ty = -radius; ty < radius + 1; ty++){
				for(int tz = -radius; tz < radius + 1; tz++){
					if(Math.sqrt(Math.pow(tx, 2) + Math.pow(ty, 2) + Math.pow(tz, 2)) <= radius - 2){
						BlockPos pos = center.add(tx, ty, tz);
						IBlockState state = world.getBlockState(pos);
						Block block = state.getBlock();
						if(block != Blocks.BEDROCK && block != Blocks.AIR){
							positions.add(pos);
						}
					}
				}
			}
		}
		return positions;
	}

	
}
