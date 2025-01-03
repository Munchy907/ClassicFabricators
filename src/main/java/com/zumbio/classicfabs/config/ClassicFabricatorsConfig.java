package com.zumbio.classicfabs.config;

import com.zumbio.classicfabs.ClassicFabricators;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;

@Config(modid = ClassicFabricators.MODID)
public class ClassicFabricatorsConfig {
    @Comment({"Enable the oil fabricator"})
    public static Boolean oilFabricatorEnabled = true;
    @Comment({"Enable the lava fabricator"})
    public static Boolean lavaFabricatorEnable = true;
    @Comment({"The cost in EU, per mB of lava fabricated"})
    public static Integer lavaCostInEU = 50;
    @Comment({"The cost in EU, per mB of (BuildCraft) oil fabricated Note: Classic version is 50, but due to Liquid Fuel generator now existing, it is too low"})
    public static Integer oilCostInEu = 250;
    @Comment("The size of the internal tank in the oil fabricator in mB. Set to 32000mB as it takes two (BuildCraft) tanks " +
            "in the crafting recipe")
    public static Integer oilFabricatorInternalTankSize = 32000;
    @Comment("The size of the internal tank in the lava fabricator in mB. Set to 32000mB as it takes two (BuildCraft) tanks " +
            "in the crafting recipe")
    public static Integer lavaFabricatorInternalTankSize = 32000;
    @Comment("Should the fabricators output if a fluid tank/pipe is attached? (Uses the same method as the buildcraft pump to achieve this)")
    public static Boolean outputFluidAutomatically = true;

}
