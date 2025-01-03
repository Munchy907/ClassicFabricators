package com.zumbio.classicfabs.tile;

import net.minecraftforge.fluids.FluidRegistry;

import static com.zumbio.classicfabs.config.ClassicFabricatorsConfig.lavaCostInEU;
import static com.zumbio.classicfabs.config.ClassicFabricatorsConfig.lavaFabricatorInternalTankSize;

public class TileLavaFabricator extends TileFabricator{
    public TileLavaFabricator() {
        super(FluidRegistry.LAVA, lavaCostInEU, lavaCostInEU * 5, lavaFabricatorInternalTankSize);
    }
}
