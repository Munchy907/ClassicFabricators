package com.zumbio.classicfabs.handlers;

import com.zumbio.classicfabs.init.ModBlocks;
import com.zumbio.classicfabs.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegisterHandler {

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event){
        for (Block block : ModBlocks.blocks){
            event.getRegistry().register(block);
        }

    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event){
        for (Item item : ModItems.items) {
            event.getRegistry().register(item);
        }

        for (Block block : ModBlocks.blocks){
            event.getRegistry().register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        }

    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModBlocks.registerRenders();
        ModItems.registerRenders();
    }
}
