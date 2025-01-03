package com.zumbio.classicfabs.block;

import com.zumbio.classicfabs.ClassicFabricators;
import com.zumbio.classicfabs.tile.TileLavaFabricator;
import com.zumbio.classicfabs.tile.TileOilFabricator;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockFabricator extends Block implements ITileEntityProvider {

    public BlockFabricator(String unlocalizedName, String registryName){
        super(Material.ANVIL);
        setHardness(5);
        setUnlocalizedName(unlocalizedName);
        setRegistryName(registryName);
        setCreativeTab(ClassicFabricators.CLASSIC_FAB_TAB);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        if (this instanceof BlockOilFabricator){return new TileOilFabricator();}

        return new TileLavaFabricator();
    }

    protected abstract TileEntity getTE(World world, BlockPos pos);

}
