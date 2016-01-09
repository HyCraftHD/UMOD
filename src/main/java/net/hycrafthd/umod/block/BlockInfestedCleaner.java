package net.hycrafthd.umod.block;

import net.hycrafthd.umod.interfaces.IInfectedBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class BlockInfestedCleaner extends BlockBase {

	public BlockInfestedCleaner() {
		super(Material.iron);
		this.setHarvestLevel("pickaxe", 2);
		this.setHardness(1F);
		this.setStepSound(soundTypeMetal);
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		clean(worldIn, pos);
		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
	}
	
	private void clean(final World world, BlockPos blockPos){
		if(!world.isRemote){
			Chunk chunk = world.getChunkFromBlockCoords(blockPos);
			final int x1 = chunk.xPosition * 16;
			final int y1 = 1;
			final int z1 = chunk.zPosition * 16;
			
			final int x2 = chunk.xPosition * 16 + 15;
			final int y2 = 255;
			final int z2 = chunk.zPosition * 16 + 15;
			
//			System.out.println(x1 + " " + z1);
//			System.out.println(x2 + " " + z2);
			
//			world.setBlockState(new BlockPos(x1, blockPos.getY(), z1), Blocks.diamond_block.getDefaultState());
//			world.setBlockState(new BlockPos(x2, blockPos.getY(), z2), Blocks.diamond_block.getDefaultState());
			
			new Thread(){
				@Override
				public void run() {
					for(int i = x1; i<=x2; i++)
						for(int k = z1; k<=z2; k++)
							for(int j = y1; j<=y2; j++){
								if(world.getBlockState(new BlockPos(i, j, k)).getBlock() instanceof IInfectedBlock){
									IInfectedBlock infectedBlock = (IInfectedBlock) world.getBlockState(new BlockPos(i, j, k)).getBlock();
									if(infectedBlock.getNormalBlock() == null) continue;
									world.setBlockState(new BlockPos(i, j, k), infectedBlock.getNormalBlock().getDefaultState());
								}
							}
				};
			}.start();
		}
	}
	
}
