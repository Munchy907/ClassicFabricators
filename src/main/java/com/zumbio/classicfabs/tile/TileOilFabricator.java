package com.zumbio.classicfabs.tile;

import buildcraft.energy.BCEnergyFluids;

import static com.zumbio.classicfabs.config.ClassicFabricatorsConfig.oilCostInEu;
import static com.zumbio.classicfabs.config.ClassicFabricatorsConfig.oilFabricatorInternalTankSize;

public class TileOilFabricator extends TileFabricator{
    public TileOilFabricator() {
        super(BCEnergyFluids.oil, oilCostInEu, oilCostInEu * 5, oilFabricatorInternalTankSize);
    }
}