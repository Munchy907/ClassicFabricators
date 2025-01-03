package com.zumbio.classicfabs.block;

import com.zumbio.classicfabs.ClassicFabricators;
import com.zumbio.classicfabs.tile.TileLavaFabricator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLavaFabricator extends BlockFabricator {
    public BlockLavaFabricator() {
        super(ClassicFabricators.FabBlocks.LAVA_FABRICATOR.getUnlocalizedName(),
                ClassicFabricators.FabBlocks.LAVA_FABRICATOR.getRegistryName());

    }

    @Override
    protected TileLavaFabricator getTE(World world, BlockPos pos) {
        return (TileLavaFabricator) world.getTileEntity(pos);
    }

//    @Override
//    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
//                                    EnumFacing side, float hitX, float hitY, float hitZ) {
//        if (!world.isRemote) {
//
//            // We only count on the server side.
////            int fluidAmount = getTE(world, pos).getFluidAmount();
////            String fluidName = getTE(world, pos).getFluid().getName();
////            //ClassicFabs.getLogger().info();
////            String message = fluidName + " " + fluidAmount + "ml";
////            TextComponentTranslation component = new TextComponentTranslation("message.classicfabs.tank_contents",
////                    message);
////            component.getStyle().setColor(TextFormatting.GREEN);
////            player.sendStatusMessage(component, false);
//        }
//        // Return true also on the client to make sure that MC knows we handled this and will not try to place
//        // a block on the client
//        return true;
//    }
}
