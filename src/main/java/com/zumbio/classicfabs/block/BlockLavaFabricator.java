package com.zumbio.classicfabs.block;

import com.zumbio.classicfabs.ClassicFabricators;
import com.zumbio.classicfabs.config.ClassicFabricatorsConfig;
import com.zumbio.classicfabs.tile.TileLavaFabricator;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BlockLavaFabricator extends BlockFabricator {
    public BlockLavaFabricator() {
        super(ClassicFabricators.FabBlocks.LAVA_FABRICATOR.getUnlocalizedName(),
                ClassicFabricators.FabBlocks.LAVA_FABRICATOR.getRegistryName());

    }

    @Override
    protected TileLavaFabricator getTE(World world, BlockPos pos) {
        return (TileLavaFabricator) world.getTileEntity(pos);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add("Cost: " + ClassicFabricatorsConfig.lavaCostInEU + " EU/mB");
        super.addInformation(stack, player, tooltip, advanced);
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
