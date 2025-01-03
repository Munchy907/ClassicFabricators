package com.zumbio.classicfabs.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ModItems {
    public static List<Item> items = new ArrayList<>();

    public static void init(){}

    public static void registerRenders(){
        Iterator<Item> itemIterator = items.iterator();
        int meta = 0;
        while (itemIterator.hasNext()){
            registerRender(itemIterator.next(), meta);
            //meta += 1;
        }
    }

    private static void registerRender(Item item, int meta) {
        System.out.println(item.getRegistryName());
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta,
                new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
