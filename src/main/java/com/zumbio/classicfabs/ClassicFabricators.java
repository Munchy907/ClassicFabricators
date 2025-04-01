package com.zumbio.classicfabs;

import com.zumbio.classicfabs.init.ModBlocks;
import com.zumbio.classicfabs.init.ModItems;
import com.zumbio.classicfabs.proxy.ICommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import static com.zumbio.classicfabs.config.ClassicFabricatorsConfig.lavaCostInEU;

@Mod(
        modid = ClassicFabricators.MODID,
        name = ClassicFabricators.NAME,
        version = ClassicFabricators.VERSION,
        dependencies = ClassicFabricators.DEPENDENCIES,
        acceptedMinecraftVersions = "[1.12]"
)
public class ClassicFabricators {
    public static final String MODID = "classicfabs";
    public static final String NAME = "Classic Fabricators";
    public static final String VERSION = "1.0";
    public static final String DEPENDENCIES = "required-after:ic2;required-after:ic2-classic-spmod;" +
            "required-after:buildcraftenergy";

    @SidedProxy(
            clientSide = "com.zumbio.classicfabs.proxy.ClientProxy",
            serverSide = "com.zumbio.classicfabs.proxy.ServerProxy"
    )
    public static ICommonProxy proxy;
    @Instance("classicfabs")
    public static ClassicFabricators instance;
    public static final CreativeTabs CLASSIC_FAB_TAB = new ClassicFabricatorsTab();

    private static Logger logger;

    public ClassicFabricators(){instance = this;}

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        ModBlocks.init();
        ModItems.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        //logger.info(lavaCostInEU);
    }

    public static Logger getLogger() {
        return logger;
    }

//    public static enum FabItems {
//        LAVA_FABRICATOR("lavafab", "LavaFab"),
//        OIL_FABRICATOR("oilfab", "OilFab");
//
//        private String unlocalizedName;
//        private String registryName;
//        FabItems(String unlocalizedName, String registerName){
//            this.unlocalizedName = unlocalizedName;
//            this.registryName = registerName;
//        }
//
//        public String getUnlocalizedName() {
//            return unlocalizedName;
//        }
//
//        public String getRegistryName() {
//            return registryName;
//        }
//    }

    public enum FabBlocks {
        LAVA_FABRICATOR("lavafab", "LavaFab"),
        OIL_FABRICATOR("oilfab", "OilFab");

        private String unlocalizedName;
        private String registryName;
        FabBlocks(String unlocalizedName, String registerName){
            this.unlocalizedName = unlocalizedName;
            this.registryName = registerName;
        }

        public String getUnlocalizedName() {
            return unlocalizedName;
        }

        public String getRegistryName() {
            return registryName;
        }
    }
}
