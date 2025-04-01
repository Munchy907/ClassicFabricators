package com.zumbio.classicfabs.init;

import com.zumbio.classicfabs.ClassicFabricators;
import com.zumbio.classicfabs.block.BlockLavaFabricator;
import com.zumbio.classicfabs.block.BlockOilFabricator;
import com.zumbio.classicfabs.tile.TileLavaFabricator;
import com.zumbio.classicfabs.tile.TileOilFabricator;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.zumbio.classicfabs.config.ClassicFabricatorsConfig.lavaFabricatorEnable;
import static com.zumbio.classicfabs.config.ClassicFabricatorsConfig.oilFabricatorEnabled;

public class ModBlocks {

    public static List<Block> blocks = new ArrayList<>();
    public static Block LAVA_FABRICATOR = new BlockLavaFabricator();
    public static Block OIL_FABRICATOR = new BlockOilFabricator();

    public static void init(){
        if (lavaFabricatorEnable){
            blocks.add(LAVA_FABRICATOR);
            GameRegistry.registerTileEntity(TileLavaFabricator.class, new ModelResourceLocation(
                    "lava_fabricator", "inventory"));
        }
        if (oilFabricatorEnabled){
            blocks.add(OIL_FABRICATOR);
            GameRegistry.registerTileEntity(TileOilFabricator.class, new ModelResourceLocation(
                    "oil_fabricator", "inventory"));
        }
    }

    public static void registerRenders(){
        Iterator<Block> blockIterator = blocks.iterator();
        int meta = 0;
        while (blockIterator.hasNext()){
            registerRender(blockIterator.next(), meta);
        }

    }

    private static void registerRender(Block block, int meta){
        System.out.println(block.getRegistryName());
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(block.getRegistryName(), ""));
    }
}
