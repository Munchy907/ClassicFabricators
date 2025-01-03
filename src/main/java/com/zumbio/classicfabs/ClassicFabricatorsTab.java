package com.zumbio.classicfabs;

import com.zumbio.classicfabs.init.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClassicFabricatorsTab extends CreativeTabs {

    public ClassicFabricatorsTab(){
        super(ClassicFabricators.MODID);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Item.getItemFromBlock(ModBlocks.LAVA_FABRICATOR));
    }
}
