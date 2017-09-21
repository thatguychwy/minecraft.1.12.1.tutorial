package chwy.tutmod.init.entities;

import chwy.tutmod.corecomponents.ExplosionCore;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityNuke extends EntityTNTPrimed{

	public int fuse = 80;
	public int radius = 50;
	
	public EntityNuke(World world){
		super(world);
	}
	
	public EntityNuke(World world, double x, double y, double z, EntityLivingBase tntPlacedBy){
		super(world, x, y, z , tntPlacedBy);
	}
	
	@Override
	public void onUpdate(){
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.motionY -= 0.03999999910593033D;
		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.9800000190734863D;
		this.motionY *= 0.9800000190734863D;
		this.motionZ *= 0.9800000190734863D;
		if (this.onGround) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
			this.motionY *= -0.5D;
		}
		if (this.fuse-- <= 0) {
			this.setDead();
			if (!this.world.isRemote) {
				this.explodeNuke();
			}
		} else {
			this.handleWaterMovement();
			this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX, this.posY + 0.5D, this.posZ, 0.0D,
				0.0D, 0.0D, new int[0]);
		}
	}
	
	public void explodeNuke() {
		ExplosionCore nukeExplosion = new ExplosionCore(new BlockPos(this.posX, this.posY, this.posZ), world,
				radius);
		nukeExplosion.setLivingBase(getTntPlacedBy());
		nukeExplosion.explode();
	}
	
	
}
	

