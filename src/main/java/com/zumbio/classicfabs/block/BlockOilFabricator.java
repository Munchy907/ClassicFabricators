package com.zumbio.classicfabs.block;

import com.zumbio.classicfabs.ClassicFabricators;
import com.zumbio.classicfabs.config.ClassicFabricatorsConfig;
import com.zumbio.classicfabs.tile.TileOilFabricator;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BlockOilFabricator extends BlockFabricator{
    public BlockOilFabricator() {
        super(ClassicFabricators.FabBlocks.OIL_FABRICATOR.getUnlocalizedName(),
                ClassicFabricators.FabBlocks.OIL_FABRICATOR.getRegistryName());

    }

    @Override
    protected TileOilFabricator getTE(World world, BlockPos pos) {
        return (TileOilFabricator) world.getTileEntity(pos);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add("Cost: " + ClassicFabricatorsConfig.oilCostInEu + " EU/mB");
        super.addInformation(stack, player, tooltip, advanced);
    }
}
