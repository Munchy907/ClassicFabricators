package com.zumbio.classicfabs.tile;

import buildcraft.lib.misc.CapUtil;
import buildcraft.lib.misc.StringUtilBC;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

import javax.annotation.Nullable;

import static com.zumbio.classicfabs.config.ClassicFabricatorsConfig.outputFluidAutomatically;

public class TileFabricator extends TileEntity implements ITickable, IEnergySink, IFluidHandler {
    protected boolean loaded = false;
    protected int capacity = 32000;
    protected int storedEnergy = 0;
    protected int maxStoredEnergy = 1600;
    protected FluidTank tank;
    protected Fluid fluid;
    protected int pricePerMB;

    public TileFabricator(Fluid fluid, int pricePerMB, int maxStoredEnergy, int capacity) {
        super();
        this.fluid = fluid;
        this.pricePerMB = pricePerMB;
        this.maxStoredEnergy = maxStoredEnergy;
        this.capacity = capacity;
        tank = new FluidTank(capacity);
        tank.setCanFill(false);
    }

    @Override
    public void update() {
        if (this.getWorld().isRemote){return;}
        if (!loaded){
            loaded = true;
            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
        }

        if (tank.getFluid() != null){
            if (tank.getFluid().amount == tank.getCapacity()){return;}
        }
        if (storedEnergy >= pricePerMB){
            int fillAmount = storedEnergy / pricePerMB;
            int liquidUsed = tank.fillInternal(new FluidStack(this.fluid, fillAmount), true);
            this.storedEnergy -= liquidUsed * this.pricePerMB;
            markDirty();
        }
        if (!outputFluidAutomatically){return;}

        pushFluidAround(world, pos, tank);
    }

    //copy of buildcraft FluidUtilBC.pushFluidAround but with FluidTank as the tank parameter
    public static void pushFluidAround(IBlockAccess world, BlockPos pos, FluidTank tank) {
        FluidStack potential = tank.drain(tank.getFluidAmount(), false);
        int drained = 0;
        if (potential == null || potential.amount <= 0) {
            return;
        }
        FluidStack working = potential.copy();
        for (EnumFacing side : EnumFacing.VALUES) {
            if (potential.amount <= 0) {
                break;
            }
            TileEntity target = world.getTileEntity(pos.offset(side));
            if (target == null) {
                continue;
            }
            IFluidHandler handler = target.getCapability(CapUtil.CAP_FLUIDS, side.getOpposite());
            if (handler != null) {
                int used = handler.fill(potential.copy(), true);

                if (used > 0) {
                    drained += used;
                    potential.amount -= used;
                }
            }
        }
        if (drained > 0) {
            FluidStack actuallyDrained = tank.drain(drained, true);
            if (actuallyDrained == null || actuallyDrained.amount != drained) {
                String strWorking = StringUtilBC.fluidToString(working);
                String strActual = StringUtilBC.fluidToString(actuallyDrained);
                throw new IllegalStateException("Bad tank! Could drain " + strWorking + " but only drained " + strActual
                        + "( tank " + tank.getClass() + ")");
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        storedEnergy = compound.getInteger("StoredEnergy");
        int storedFluidAmount = compound.getInteger("StoredLiquid");
        if (storedFluidAmount > 0){tank.fillInternal(new FluidStack(this.fluid, storedFluidAmount), true);}
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("StoredEnergy", storedEnergy);
        compound.setInteger("StoredLiquid", tank.getFluidAmount());
        return compound;
    }

    @Override
    public double getDemandedEnergy() {
        return this.maxStoredEnergy - storedEnergy;
    }

    @Override
    public int getSinkTier() {
        return 3; // HV
    }

    @Override
    public double injectEnergy(EnumFacing enumFacing, double amount, double voltage) {
        storedEnergy += (int) Math.floor(amount);
        return 0;
    }

    @Override
    public boolean acceptsEnergyFrom(IEnergyEmitter iEnergyEmitter, EnumFacing enumFacing) {
        return true;
    }

    @Override
    public void onChunkUnload() {
        MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
        super.onChunkUnload();
        loaded = false;
    }

//    public int getFluidAmount(){
//        return tank.getFluidAmount();
//    }

//    public Fluid getFluid(){
//        return this.fluid;
//    }

    @Override
    public void invalidate() {
        MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
        super.invalidate();
        loaded = false;
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return tank.getTankProperties();
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        return tank.fill(resource, doFill);
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        return tank.drain(resource, doDrain);
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return tank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(tank);
        }
        return super.getCapability(capability, facing);
    }
}
