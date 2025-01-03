package com.zumbio.classicfabs.block;

import com.zumbio.classicfabs.ClassicFabricators;
import com.zumbio.classicfabs.tile.TileOilFabricator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockOilFabricator extends BlockFabricator{
    public BlockOilFabricator() {
        super(ClassicFabricators.FabBlocks.OIL_FABRICATOR.getUnlocalizedName(),
                ClassicFabricators.FabBlocks.OIL_FABRICATOR.getRegistryName());

    }

    @Override
    protected TileOilFabricator getTE(World world, BlockPos pos) {
        return (TileOilFabricator) world.getTileEntity(pos);
    }
}
