/*
 * This file is part of Applied Energistics 2.
 * Copyright (c) 2013 - 2015, AlgorithmX2, All rights reserved.
 *
 * Applied Energistics 2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Applied Energistics 2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Applied Energistics 2.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */

package appeng.core.api.definitions;


import appeng.api.definitions.IBlockDefinition;
import appeng.api.definitions.IBlocks;
import appeng.api.definitions.IItemDefinition;
import appeng.api.definitions.ITileDefinition;
import appeng.block.AEBaseItemBlockChargeable;
import appeng.block.crafting.*;
import appeng.block.crafting.BlockCraftingUnit.CraftingUnitType;
import appeng.block.grindstone.*;
import appeng.block.misc.*;
import appeng.block.networking.*;
import appeng.block.paint.BlockPaint;
import appeng.block.paint.PaintRendering;
import appeng.block.qnb.BlockQuantumLinkChamber;
import appeng.block.qnb.BlockQuantumRing;
import appeng.block.qnb.QuantumBridgeRendering;
import appeng.block.spatial.BlockMatrixFrame;
import appeng.block.spatial.BlockSpatialIOPort;
import appeng.block.spatial.BlockSpatialPylon;
import appeng.block.storage.*;
import appeng.block.storage.BlockSkyChest.SkyChestType;
import appeng.bootstrap.BlockRenderingCustomizer;
import appeng.bootstrap.FeatureFactory;
import appeng.bootstrap.IBlockRendering;
import appeng.bootstrap.IItemRendering;
import appeng.bootstrap.components.IEntityRegistrationComponent;
import appeng.bootstrap.components.IOreDictComponent;
import appeng.bootstrap.components.IPostInitComponent;
import appeng.bootstrap.components.IPreInitComponent;
import appeng.bootstrap.definitions.TileEntityDefinition;
import appeng.client.render.crafting.CraftingCubeRendering;
import appeng.client.render.model.GlassModel;
import appeng.client.render.spatial.SpatialPylonRendering;
import appeng.core.AppEng;
import appeng.core.features.AEFeature;
import appeng.core.features.BlockDefinition;
import appeng.core.features.registries.PartModels;
import appeng.debug.*;
import appeng.decorative.slab.BlockSlabCommon;
import appeng.decorative.solid.*;
import appeng.decorative.solid.BlockSkyStone.SkystoneType;
import appeng.decorative.stair.BlockStairCommon;
import appeng.entity.EntityIds;
import appeng.entity.EntityTinyTNTPrimed;
import appeng.fluids.block.BlockFluidInterface;
import appeng.fluids.tile.TileFluidInterface;
import appeng.hooks.DispenserBehaviorTinyTNT;
import appeng.tile.crafting.*;
import appeng.tile.grindstone.TileCrank;
import appeng.tile.grindstone.TileCrankImp;
import appeng.tile.grindstone.TileGrinder;
import appeng.tile.misc.*;
import appeng.tile.networking.*;
import appeng.tile.qnb.TileQuantumBridge;
import appeng.tile.spatial.TileSpatialIOPort;
import appeng.tile.spatial.TileSpatialPylon;
import appeng.tile.storage.*;
import com.google.common.base.Verify;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockSlab;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;


/**
 * Internal implementation for the API blocks
 */
public final class ApiBlocks implements IBlocks {
    private final IBlockDefinition quartzOre;
    private final IBlockDefinition quartzOreCharged;
    private final IBlockDefinition matrixFrame;
    private final IBlockDefinition quartzBlock;
    private final IBlockDefinition quartzPillar;
    private final IBlockDefinition chiseledQuartzBlock;
    private final IBlockDefinition quartzGlass;
    private final IBlockDefinition quartzVibrantGlass;
    private final IBlockDefinition quartzFixture;
    private final IBlockDefinition fluixBlock;
    private final IBlockDefinition skyStoneBlock;
    private final IBlockDefinition smoothSkyStoneBlock;
    private final IBlockDefinition skyStoneBrick;
    private final IBlockDefinition skyStoneSmallBrick;
    private final IBlockDefinition skyStoneChest;
    private final IBlockDefinition smoothSkyStoneChest;
    private final IBlockDefinition skyCompass;
    private final ITileDefinition grindstone;
    private final ITileDefinition crank;
    private final ITileDefinition crankImp;
    private final ITileDefinition inscriber;
    private final ITileDefinition wirelessAccessPoint;
    private final ITileDefinition charger;
    private final IBlockDefinition tinyTNT;
    private final ITileDefinition securityStation;
    private final ITileDefinition quantumRing;
    private final ITileDefinition quantumLink;
    private final ITileDefinition spatialPylon;
    private final ITileDefinition spatialIOPort;
    private final ITileDefinition multiPart;
    private final ITileDefinition controller;
    private final ITileDefinition drive;
    private final ITileDefinition driveImp;
    private final ITileDefinition chest;
    private final ITileDefinition iface;
    private final ITileDefinition ifaceImp;
    private final ITileDefinition ifaceAdv;
    private final ITileDefinition ifacePer;
    private final ITileDefinition ifacePatterns;
    private final ITileDefinition fluidIface;
    private final ITileDefinition cellWorkbench;
    private final ITileDefinition iOPort;
    private final ITileDefinition iOPortImp;
    private final ITileDefinition condenser;
    private final ITileDefinition energyAcceptor;
    private final ITileDefinition vibrationChamber;
    private final ITileDefinition quartzGrowthAccelerator;
    private final ITileDefinition energyCell;
    private final ITileDefinition energyCellDense;

    private final ITileDefinition energyCellImproved;
    private final ITileDefinition energyCellAdvanced;
    private final ITileDefinition energyCellPerfect;


    private final ITileDefinition energyCellCreative;
    private final ITileDefinition craftingUnit;
    private final ITileDefinition craftingAccelerator;
    private final ITileDefinition craftingAccelerator4x;
    private final ITileDefinition craftingAccelerator16x;
    private final ITileDefinition craftingAccelerator64x;
    private final ITileDefinition craftingAccelerator128x;
    private final ITileDefinition craftingAccelerator256x;
    private final ITileDefinition craftingAccelerator512x;
    private final ITileDefinition craftingAccelerator1024x;
    private final ITileDefinition craftingAcceleratorCreative;

    private final ITileDefinition craftingStorage1k;
    private final ITileDefinition craftingStorage4k;
    private final ITileDefinition craftingStorage16k;
    private final ITileDefinition craftingStorage64k;
    private final ITileDefinition craftingStorage1mb;
    private final ITileDefinition craftingStorage4mb;
    private final ITileDefinition craftingStorage16mb;
    private final ITileDefinition craftingStorage64mb;
    private final ITileDefinition craftingStorage256mb;
    private final ITileDefinition craftingStorage1gb;
    private final ITileDefinition craftingStorage15gb;

    private final ITileDefinition craftingMonitor;
    private final ITileDefinition molecularAssembler;
    private final ITileDefinition molecularAssemblerImp;
    private final ITileDefinition molecularAssemblerCreative;
    private final ITileDefinition lightDetector;
    private final ITileDefinition paint;
    private final IBlockDefinition skyStoneStairs;
    private final IBlockDefinition smoothSkyStoneStairs;
    private final IBlockDefinition skyStoneBrickStairs;
    private final IBlockDefinition skyStoneSmallBrickStairs;
    private final IBlockDefinition fluixStairs;
    private final IBlockDefinition quartzStairs;
    private final IBlockDefinition chiseledQuartzStairs;
    private final IBlockDefinition quartzPillarStairs;

    private final IBlockDefinition skyStoneSlab;
    private final IBlockDefinition smoothSkyStoneSlab;
    private final IBlockDefinition skyStoneBrickSlab;
    private final IBlockDefinition skyStoneSmallBrickSlab;
    private final IBlockDefinition fluixSlab;
    private final IBlockDefinition quartzSlab;
    private final IBlockDefinition chiseledQuartzSlab;
    private final IBlockDefinition quartzPillarSlab;

    private final IBlockDefinition itemGen;
    private final IBlockDefinition chunkLoader;
    private final IBlockDefinition phantomNode;
    private final IBlockDefinition cubeGenerator;
    private final IBlockDefinition energyGenerator;

    public ApiBlocks(FeatureFactory registry, PartModels partModels) {
        // this.quartzOre = new BlockDefinition( "ore.quartz", new OreQuartz() );
        this.quartzOre = registry.block("quartz_ore", BlockQuartzOre::new)
                .features(AEFeature.CERTUS_ORE)
                .bootstrap((block, item) -> (IOreDictComponent) side -> OreDictionary.registerOre("oreCertusQuartz", new ItemStack(block)))
                .build();
        this.quartzOreCharged = registry.block("charged_quartz_ore", BlockChargedQuartzOre::new)
                .features(AEFeature.CERTUS_ORE, AEFeature.CHARGED_CERTUS_ORE)
                .useCustomItemModel()
                .bootstrap((block, item) -> (IOreDictComponent) side ->
                {
                    OreDictionary.registerOre("oreCertusQuartz", new ItemStack(block));
                    OreDictionary.registerOre("oreChargedCertusQuartz", new ItemStack(block));
                })
                .build();
        this.matrixFrame = registry.block("matrix_frame", BlockMatrixFrame::new).features(AEFeature.SPATIAL_IO).build();

        FeatureFactory deco = registry.features(AEFeature.DECORATIVE_BLOCKS);
        this.quartzBlock = deco.block("quartz_block", BlockQuartz::new).build();
        this.quartzPillar = deco.block("quartz_pillar", BlockQuartzPillar::new).build();
        this.chiseledQuartzBlock = deco.block("chiseled_quartz_block", BlockChiseledQuartz::new).build();

        this.quartzGlass = registry.features(AEFeature.QUARTZ_GLASS)
                .block("quartz_glass", BlockQuartzGlass::new)
                .useCustomItemModel()
                .rendering(new BlockRenderingCustomizer() {
                    @Override
                    @SideOnly(Side.CLIENT)
                    public void customize(IBlockRendering rendering, IItemRendering itemRendering) {
                        rendering.builtInModel("models/block/builtin/quartz_glass", new GlassModel());
                    }
                })
                .build();
        this.quartzVibrantGlass = deco.block("quartz_vibrant_glass", BlockQuartzLamp::new)
                .addFeatures(AEFeature.DECORATIVE_LIGHTS, AEFeature.QUARTZ_GLASS)
                .useCustomItemModel()
                .build();
        this.quartzFixture = registry.block("quartz_fixture", BlockQuartzFixture::new)
                .features(AEFeature.DECORATIVE_LIGHTS)
                .useCustomItemModel()
                .build();

        this.fluixBlock = registry.features(AEFeature.FLUIX).block("fluix_block", BlockFluix::new).build();

        this.skyStoneBlock = registry.features(AEFeature.SKY_STONE)
                .block("sky_stone_block", () -> new BlockSkyStone(SkystoneType.STONE))
                .build();
        this.smoothSkyStoneBlock = registry.features(AEFeature.SKY_STONE)
                .block("smooth_sky_stone_block", () -> new BlockSkyStone(SkystoneType.BLOCK))
                .build();
        this.skyStoneBrick = deco.block("sky_stone_brick", () -> new BlockSkyStone(SkystoneType.BRICK))
                .addFeatures(AEFeature.SKY_STONE)
                .build();
        this.skyStoneSmallBrick = deco.block("sky_stone_small_brick", () -> new BlockSkyStone(SkystoneType.SMALL_BRICK))
                .addFeatures(AEFeature.SKY_STONE)
                .build();

        this.skyStoneChest = registry.block("sky_stone_chest", () -> new BlockSkyChest(SkyChestType.STONE))
                .features(AEFeature.SKY_STONE, AEFeature.SKY_STONE_CHESTS)
                .tileEntity(new TileEntityDefinition(TileSkyChest.class, "sky_stone_chest"))
                .rendering(new SkyChestRenderingCustomizer(SkyChestType.STONE))
                .build();
        this.smoothSkyStoneChest = registry.block("smooth_sky_stone_chest", () -> new BlockSkyChest(SkyChestType.BLOCK))
                .features(AEFeature.SKY_STONE, AEFeature.SKY_STONE_CHESTS)
                .tileEntity(new TileEntityDefinition(TileSkyChest.class, "sky_stone_chest"))
                .rendering(new SkyChestRenderingCustomizer(SkyChestType.BLOCK))
                .build();

        this.skyCompass = registry.block("sky_compass", BlockSkyCompass::new)
                .features(AEFeature.METEORITE_COMPASS)
                .tileEntity(new TileEntityDefinition(TileSkyCompass.class))
                .rendering(new SkyCompassRendering())
                .build();
        this.grindstone = registry.block("grindstone", BlockGrinder::new)
                .features(AEFeature.GRIND_STONE)
                .tileEntity(new TileEntityDefinition(TileGrinder.class))
                .build();

        this.crank = registry.block("crank", BlockCrank::new)
                .features(AEFeature.GRIND_STONE)
                .tileEntity(new TileEntityDefinition(TileCrank.class))
                .rendering(new CrankRendering())
                .useCustomItemModel()
                .build();
        this.crankImp = registry.block("crank_imp", BlockCrankImp::new)
                .features(AEFeature.GRIND_STONE)
                .tileEntity(new TileEntityDefinition(TileCrankImp.class))
                .rendering(new CrankRenderingImp())
                .useCustomItemModel()
                .build();
        this.inscriber = registry.block("inscriber", BlockInscriber::new)
                .features(AEFeature.INSCRIBER)
                .tileEntity(new TileEntityDefinition(TileInscriber.class))
                .rendering(new InscriberRendering())
                .build();
        this.wirelessAccessPoint = registry.block("wireless_access_point", BlockWireless::new)
                .features(AEFeature.WIRELESS_ACCESS_TERMINAL)
                .tileEntity(new TileEntityDefinition(TileWireless.class))
                .rendering(new WirelessRendering())
                .build();
        this.charger = registry.block("charger", BlockCharger::new)
                .features(AEFeature.CHARGER)
                .tileEntity(new TileEntityDefinition(TileCharger.class))
                .rendering(new BlockRenderingCustomizer() {
                    @Override
                    @SideOnly(Side.CLIENT)
                    public void customize(IBlockRendering rendering, IItemRendering itemRendering) {
                        rendering.tesr(BlockCharger.createTesr());
                    }
                })
                .build();
        this.tinyTNT = registry.block("tiny_tnt", BlockTinyTNT::new)
                .features(AEFeature.TINY_TNT)
                .bootstrap((block, item) -> (IPreInitComponent) side -> BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(item,
                        new DispenserBehaviorTinyTNT()))
                .bootstrap((block, item) -> (IEntityRegistrationComponent) r ->
                {
                    r.register(EntityEntryBuilder.create()
                            .entity(EntityTinyTNTPrimed.class)
                            .id(new ResourceLocation("appliedenergistics2", EntityTinyTNTPrimed.class.getName()),
                                    EntityIds.get(EntityTinyTNTPrimed.class))
                            .name("EntityTinyTNTPrimed")
                            .tracker(16, 4, true)
                            .build());
                })
                .build();
        this.securityStation = registry.block("security_station", BlockSecurityStation::new)
                .features(AEFeature.SECURITY)
                .tileEntity(new TileEntityDefinition(TileSecurityStation.class))
                .rendering(new SecurityStationRendering())
                .build();
        this.quantumRing = registry.block("quantum_ring", BlockQuantumRing::new)
                .features(AEFeature.QUANTUM_NETWORK_BRIDGE)
                .tileEntity(new TileEntityDefinition(TileQuantumBridge.class, "quantum_ring"))
                .rendering(new QuantumBridgeRendering())
                .build();
        this.quantumLink = registry.block("quantum_link", BlockQuantumLinkChamber::new)
                .features(AEFeature.QUANTUM_NETWORK_BRIDGE)
                .tileEntity(new TileEntityDefinition(TileQuantumBridge.class, "quantum_ring"))
                .rendering(new QuantumBridgeRendering())
                .build();
        this.spatialPylon = registry.block("spatial_pylon", BlockSpatialPylon::new)
                .features(AEFeature.SPATIAL_IO)
                .tileEntity(new TileEntityDefinition(TileSpatialPylon.class))
                .useCustomItemModel()
                .rendering(new SpatialPylonRendering())
                .build();
        this.spatialIOPort = registry.block("spatial_io_port", BlockSpatialIOPort::new)
                .features(AEFeature.SPATIAL_IO)
                .tileEntity(new TileEntityDefinition(TileSpatialIOPort.class))
                .build();
        this.controller = registry.block("controller", BlockController::new)
                .tileEntity(new TileEntityDefinition(TileController.class))
                .useCustomItemModel()
                .rendering(new ControllerRendering())
                .build();
        this.drive = registry.block("drive", BlockDrive::new)
                .features(AEFeature.STORAGE_CELLS, AEFeature.ME_DRIVE)
                .tileEntity(new TileEntityDefinition(TileDrive.class))
                .useCustomItemModel()
                .rendering(new DriveRendering())
                .build();

        this.driveImp = registry.block("driveImp", BlockDriveImproved::new)
                .features(AEFeature.STORAGE_CELLS, AEFeature.ME_DRIVE_IMP)
                .tileEntity(new TileEntityDefinition(TileDriveImproved.class))
                .useCustomItemModel()
                .rendering(new DriveRendering())
                .build();

        this.chest = registry.block("chest", BlockChest::new)
                .features(AEFeature.STORAGE_CELLS, AEFeature.ME_CHEST)
                .tileEntity(new TileEntityDefinition(TileChest.class))
                .useCustomItemModel()
                .rendering(new ChestRendering())
                .build();
        this.iface = registry.block("interface", BlockInterface::new)
                .features(AEFeature.INTERFACE)
                .tileEntity(new TileEntityDefinition(TileInterface.class))
                .build();
        this.ifaceImp = registry.block("interfaceimp", BlockInterfaceImp::new)
                .features(AEFeature.INTERFACE)
                .tileEntity(new TileEntityDefinition(TileInterfaceImp.class))
                .build();
        this.ifaceAdv = registry.block("interfaceadv", BlockInterfaceAdv::new)
                .features(AEFeature.INTERFACE)
                .tileEntity(new TileEntityDefinition(TileInterfaceAdv.class))
                .build();
        this.ifacePer = registry.block("interfaceper", BlockInterfacePer::new)
                .features(AEFeature.INTERFACE)
                .tileEntity(new TileEntityDefinition(TileInterfacePer.class))
                .build();
        this.ifacePatterns = registry.block("interface_patterns", BlockInterfacePatterns::new)
                .features(AEFeature.INTERFACE)
                .tileEntity(new TileEntityDefinition(TileInterfacePatt.class))
                .build();

        this.fluidIface = registry.block("fluid_interface", BlockFluidInterface::new)
                .features(AEFeature.FLUID_INTERFACE)
                .tileEntity(new TileEntityDefinition(TileFluidInterface.class))
                .build();
        this.cellWorkbench = registry.block("cell_workbench", BlockCellWorkbench::new)
                .features(AEFeature.STORAGE_CELLS)
                .tileEntity(new TileEntityDefinition(TileCellWorkbench.class))
                .build();
        this.iOPort = registry.block("io_port", BlockIOPort::new)
                .features(AEFeature.STORAGE_CELLS, AEFeature.IO_PORT)
                .tileEntity(new TileEntityDefinition(TileIOPort.class))
                .build();
        this.iOPortImp = registry.block("io_portimp", BlockIOPortImp::new)
                .features(AEFeature.STORAGE_CELLS, AEFeature.IO_PORTIMP)
                .tileEntity(new TileEntityDefinition(TileIOPortImp.class))
                .build();
        this.condenser = registry.block("condenser", BlockCondenser::new)
                .features(AEFeature.CONDENSER)
                .tileEntity(new TileEntityDefinition(TileCondenser.class))
                .build();
        this.energyAcceptor = registry.block("energy_acceptor", BlockEnergyAcceptor::new)
                .features(AEFeature.ENERGY_ACCEPTOR)
                .tileEntity(new TileEntityDefinition(TileEnergyAcceptor.class))
                .build();
        this.vibrationChamber = registry.block("vibration_chamber", BlockVibrationChamber::new)
                .features(AEFeature.POWER_GEN)
                .tileEntity(new TileEntityDefinition(TileVibrationChamber.class))
                .build();
        this.quartzGrowthAccelerator = registry.block("quartz_growth_accelerator", BlockQuartzGrowthAccelerator::new)
                .tileEntity(new TileEntityDefinition(TileQuartzGrowthAccelerator.class))
                .features(AEFeature.CRYSTAL_GROWTH_ACCELERATOR)
                .build();
        this.energyCell = registry.block("energy_cell", BlockEnergyCell::new)
                .features(AEFeature.ENERGY_CELLS)
                .item(AEBaseItemBlockChargeable::new)
                .tileEntity(new TileEntityDefinition(TileEnergyCell.class))
                .rendering(new BlockEnergyCellRendering(new ResourceLocation(AppEng.MOD_ID, "energy_cell")))
                .build();
        this.energyCellDense = registry.block("dense_energy_cell", BlockDenseEnergyCell::new)
                .features(AEFeature.ENERGY_CELLS, AEFeature.DENSE_ENERGY_CELLS)
                .item(AEBaseItemBlockChargeable::new)
                .tileEntity(new TileEntityDefinition(TileDenseEnergyCell.class))
                .rendering(new BlockEnergyCellRendering(new ResourceLocation(AppEng.MOD_ID, "dense_energy_cell")))
                .build();

        this.energyCellImproved = registry.block("improved_energy_cell", BlockImprovedEnergyCell::new)
                .features(AEFeature.ENERGY_CELLS, AEFeature.DENSE_ENERGY_CELLS)
                .item(AEBaseItemBlockChargeable::new)
                .tileEntity(new TileEntityDefinition(TileImprovedEnergyCell.class))
                .rendering(new BlockEnergyCellRendering(new ResourceLocation(AppEng.MOD_ID, "improved_energy_cell")))
                .build();
        this.energyCellAdvanced = registry.block("advanced_energy_cell", BlockAdvancedEnergyCell::new)
                .features(AEFeature.ENERGY_CELLS, AEFeature.DENSE_ENERGY_CELLS)
                .item(AEBaseItemBlockChargeable::new)
                .tileEntity(new TileEntityDefinition(TileAdvancedEnergyCell.class))
                .rendering(new BlockEnergyCellRendering(new ResourceLocation(AppEng.MOD_ID, "advanced_energy_cell")))
                .build();
        this.energyCellPerfect = registry.block("perfect_energy_cell", BlockPerfectEnergyCell::new)
                .features(AEFeature.ENERGY_CELLS, AEFeature.DENSE_ENERGY_CELLS)
                .item(AEBaseItemBlockChargeable::new)
                .tileEntity(new TileEntityDefinition(TilePerfectEnergyCell.class))
                .rendering(new BlockEnergyCellRendering(new ResourceLocation(AppEng.MOD_ID, "perfect_energy_cell")))
                .build();


        this.energyCellCreative = registry.block("creative_energy_cell", BlockCreativeEnergyCell::new)
                .features(AEFeature.CREATIVE)
                .tileEntity(new TileEntityDefinition(TileCreativeEnergyCell.class))
                .build();

        FeatureFactory crafting = registry.features(AEFeature.CRAFTING_CPU);
        this.craftingUnit = crafting.block("crafting_unit", () -> new BlockCraftingUnit(CraftingUnitType.UNIT))
                .rendering(new CraftingCubeRendering("crafting_unit", CraftingUnitType.UNIT))
                .tileEntity(new TileEntityDefinition(TileCraftingTile.class, "crafting_unit"))
                .useCustomItemModel()
                .build();
        this.craftingAccelerator = crafting.block("crafting_accelerator", () -> new BlockCraftingUnit(CraftingUnitType.ACCELERATOR))
                .rendering(new CraftingCubeRendering("crafting_accelerator", CraftingUnitType.ACCELERATOR))
                .tileEntity(new TileEntityDefinition(TileCraftingTile.class, "crafting_unit"))
                .useCustomItemModel()
                .build();
        this.craftingAccelerator4x = crafting.block("crafting_accelerator_4x", () -> new BlockCraftingUnit(CraftingUnitType.ACCELERATOR_4X))
                .rendering(new CraftingCubeRendering("crafting_accelerator_4x", CraftingUnitType.ACCELERATOR_4X))
                .tileEntity(new TileEntityDefinition(TileCraftingTile.class, "crafting_unit"))
                .useCustomItemModel()
                .build();
        this.craftingAccelerator16x = crafting.block("crafting_accelerator_16x", () -> new BlockCraftingUnit(CraftingUnitType.ACCELERATOR_16X))
                .rendering(new CraftingCubeRendering("crafting_accelerator_16x", CraftingUnitType.ACCELERATOR_16X))
                .tileEntity(new TileEntityDefinition(TileCraftingTile.class, "crafting_unit"))
                .useCustomItemModel()
                .build();
        this.craftingAccelerator64x = crafting.block("crafting_accelerator_64x", () -> new BlockCraftingUnit(CraftingUnitType.ACCELERATOR_64X))
                .rendering(new CraftingCubeRendering("crafting_accelerator_64x", CraftingUnitType.ACCELERATOR_64X))
                .tileEntity(new TileEntityDefinition(TileCraftingTile.class, "crafting_unit"))
                .useCustomItemModel()
                .build();
        this.craftingAccelerator128x = crafting.block("crafting_accelerator_128x", () -> new BlockCraftingUnit(CraftingUnitType.ACCELERATOR_128X))
                .rendering(new CraftingCubeRendering("crafting_accelerator_128x", CraftingUnitType.ACCELERATOR_128X))
                .tileEntity(new TileEntityDefinition(TileCraftingTile.class, "crafting_unit"))
                .useCustomItemModel()
                .build();
        this.craftingAccelerator256x = crafting.block("crafting_accelerator_256x", () -> new BlockCraftingUnit(CraftingUnitType.ACCELERATOR_256X))
                .rendering(new CraftingCubeRendering("crafting_accelerator_256x", CraftingUnitType.ACCELERATOR_256X))
                .tileEntity(new TileEntityDefinition(TileCraftingTile.class, "crafting_unit"))
                .useCustomItemModel()
                .build();
        this.craftingAccelerator512x = crafting.block("crafting_accelerator_512x", () -> new BlockCraftingUnit(CraftingUnitType.ACCELERATOR_512X))
                .rendering(new CraftingCubeRendering("crafting_accelerator_512x", CraftingUnitType.ACCELERATOR_512X))
                .tileEntity(new TileEntityDefinition(TileCraftingTile.class, "crafting_unit"))
                .useCustomItemModel()
                .build();
        this.craftingAccelerator1024x = crafting.block("crafting_accelerator_1024x", () -> new BlockCraftingUnit(CraftingUnitType.ACCELERATOR_1024X))
                .rendering(new CraftingCubeRendering("crafting_accelerator_1024x", CraftingUnitType.ACCELERATOR_1024X))
                .tileEntity(new TileEntityDefinition(TileCraftingTile.class, "crafting_unit"))
                .useCustomItemModel()
                .build();
        this.craftingAcceleratorCreative = crafting.block("crafting_accelerator_creative", () -> new BlockCraftingUnit(CraftingUnitType.ACCELERATOR_CREATIVE))
                .rendering(new CraftingCubeRendering("crafting_accelerator_creative", CraftingUnitType.ACCELERATOR_CREATIVE))
                .tileEntity(new TileEntityDefinition(TileCraftingTile.class, "crafting_unit"))
                .useCustomItemModel()
                .build();


        this.craftingStorage1k = crafting.block("crafting_storage_1k", () -> new BlockCraftingStorage(CraftingUnitType.STORAGE_1K))
                .item(ItemCraftingStorage::new)
                .tileEntity(new TileEntityDefinition(TileCraftingStorageTile.class, "crafting_storage"))
                .rendering(new CraftingCubeRendering("crafting_storage_1k", CraftingUnitType.STORAGE_1K))
                .useCustomItemModel()
                .build();
        this.craftingStorage4k = crafting.block("crafting_storage_4k", () -> new BlockCraftingStorage(CraftingUnitType.STORAGE_4K))
                .item(ItemCraftingStorage::new)
                .tileEntity(new TileEntityDefinition(TileCraftingStorageTile.class, "crafting_storage"))
                .rendering(new CraftingCubeRendering("crafting_storage_4k", CraftingUnitType.STORAGE_4K))
                .useCustomItemModel()
                .build();
        this.craftingStorage16k = crafting.block("crafting_storage_16k", () -> new BlockCraftingStorage(CraftingUnitType.STORAGE_16K))
                .item(ItemCraftingStorage::new)
                .tileEntity(new TileEntityDefinition(TileCraftingStorageTile.class, "crafting_storage"))
                .rendering(new CraftingCubeRendering("crafting_storage_16k", CraftingUnitType.STORAGE_16K))
                .useCustomItemModel()
                .build();
        this.craftingStorage64k = crafting.block("crafting_storage_64k", () -> new BlockCraftingStorage(CraftingUnitType.STORAGE_64K))
                .item(ItemCraftingStorage::new)
                .tileEntity(new TileEntityDefinition(TileCraftingStorageTile.class, "crafting_storage"))
                .rendering(new CraftingCubeRendering("crafting_storage_64k", CraftingUnitType.STORAGE_64K))
                .useCustomItemModel()
                .build();
        this.craftingStorage1mb = crafting.block("crafting_storage_1mb", () -> new BlockCraftingStorage(CraftingUnitType.STORAGE_1MB))
                .item(ItemCraftingStorage::new)
                .tileEntity(new TileEntityDefinition(TileCraftingStorageTile.class, "crafting_storage"))
                .rendering(new CraftingCubeRendering("crafting_storage_1mb", CraftingUnitType.STORAGE_1MB))
                .useCustomItemModel()
                .build();
        this.craftingStorage4mb = crafting.block("crafting_storage_4mb", () -> new BlockCraftingStorage(CraftingUnitType.STORAGE_4MB))
                .item(ItemCraftingStorage::new)
                .tileEntity(new TileEntityDefinition(TileCraftingStorageTile.class, "crafting_storage"))
                .rendering(new CraftingCubeRendering("crafting_storage_4mb", CraftingUnitType.STORAGE_4MB))
                .useCustomItemModel()
                .build();
        this.craftingStorage16mb = crafting.block("crafting_storage_16mb", () -> new BlockCraftingStorage(CraftingUnitType.STORAGE_16MB))
                .item(ItemCraftingStorage::new)
                .tileEntity(new TileEntityDefinition(TileCraftingStorageTile.class, "crafting_storage"))
                .rendering(new CraftingCubeRendering("crafting_storage_16mb", CraftingUnitType.STORAGE_16MB))
                .useCustomItemModel()
                .build();
        this.craftingStorage64mb = crafting.block("crafting_storage_64mb", () -> new BlockCraftingStorage(CraftingUnitType.STORAGE_64MB))
                .item(ItemCraftingStorage::new)
                .tileEntity(new TileEntityDefinition(TileCraftingStorageTile.class, "crafting_storage"))
                .rendering(new CraftingCubeRendering("crafting_storage_64mb", CraftingUnitType.STORAGE_64MB))
                .useCustomItemModel()
                .build();
        this.craftingStorage256mb = crafting.block("crafting_storage_256mb", () -> new BlockCraftingStorage(CraftingUnitType.STORAGE_256MB))
                .item(ItemCraftingStorage::new)
                .tileEntity(new TileEntityDefinition(TileCraftingStorageTile.class, "crafting_storage"))
                .rendering(new CraftingCubeRendering("crafting_storage_256mb", CraftingUnitType.STORAGE_256MB))
                .useCustomItemModel()
                .build();
        this.craftingStorage1gb = crafting.block("crafting_storage_1gb", () -> new BlockCraftingStorage(CraftingUnitType.STORAGE_1GB))
                .item(ItemCraftingStorage::new)
                .tileEntity(new TileEntityDefinition(TileCraftingStorageTile.class, "crafting_storage"))
                .rendering(new CraftingCubeRendering("crafting_storage_1gb", CraftingUnitType.STORAGE_1GB))
                .useCustomItemModel()
                .build();
        this.craftingStorage15gb = crafting.block("crafting_storage_15gb", () -> new BlockCraftingStorage(CraftingUnitType.STORAGE_15GB))
                .item(ItemCraftingStorage::new)
                .tileEntity(new TileEntityDefinition(TileCraftingStorageTile.class, "crafting_storage"))
                .rendering(new CraftingCubeRendering("crafting_storage_15gb", CraftingUnitType.STORAGE_15GB))
                .useCustomItemModel()
                .build();

        this.craftingMonitor = crafting.block("crafting_monitor", BlockCraftingMonitor::new)
                .tileEntity(new TileEntityDefinition(TileCraftingMonitorTile.class))
                .rendering(new CraftingCubeRendering("crafting_monitor", CraftingUnitType.MONITOR))
                .useCustomItemModel()
                .build();

        this.molecularAssembler = registry.block("molecular_assembler", BlockMolecularAssembler::new)
                .features(AEFeature.MOLECULAR_ASSEMBLER)
                .tileEntity(new TileEntityDefinition(TileMolecularAssembler.class))
                .build();
        this.molecularAssemblerImp = registry.block("molecular_assemblerimp", BlockMolecularAssemblerImp::new)
                .features(AEFeature.MOLECULAR_ASSEMBLERIMP)
                .tileEntity(new TileEntityDefinition(TileMolecularAssemblerImp.class))
                .build();
        this.molecularAssemblerCreative = registry.block("molecular_assembler_creative", BlockMolecularAssemblerCreative::new)
                .features(AEFeature.MOLECULAR_ASSEMBLERCREATIVE)
                .tileEntity(new TileEntityDefinition(TileMolecularAssemblerCreative.class))
                .build();


        this.lightDetector = registry.block("light_detector", BlockLightDetector::new)
                .features(AEFeature.LIGHT_DETECTOR)
                .tileEntity(new TileEntityDefinition(TileLightDetector.class))
                .useCustomItemModel()
                .build();
        this.paint = registry.block("paint", BlockPaint::new)
                .features(AEFeature.PAINT_BALLS)
                .tileEntity(new TileEntityDefinition(TilePaint.class))
                .rendering(new PaintRendering())
                .build();

        this.skyStoneStairs = makeStairs("sky_stone_stairs", registry, this.skyStoneBlock());
        this.smoothSkyStoneStairs = makeStairs("smooth_sky_stone_stairs", registry, this.smoothSkyStoneBlock());
        this.skyStoneBrickStairs = makeStairs("sky_stone_brick_stairs", registry, this.skyStoneBrick());
        this.skyStoneSmallBrickStairs = makeStairs("sky_stone_small_brick_stairs", registry, this.skyStoneSmallBrick());
        this.fluixStairs = makeStairs("fluix_stairs", registry, this.fluixBlock());
        this.quartzStairs = makeStairs("quartz_stairs", registry, this.quartzBlock());
        this.chiseledQuartzStairs = makeStairs("chiseled_quartz_stairs", registry, this.chiseledQuartzBlock());
        this.quartzPillarStairs = makeStairs("quartz_pillar_stairs", registry, this.quartzPillar());

        this.multiPart = registry.block("cable_bus", BlockCableBus::new)
                .rendering(new CableBusRendering(partModels))
                // (handled in BlockCableBus.java and its setupTile())
                // .tileEntity( TileCableBus.class )
                // TODO: why the custom registration?
                .bootstrap((block, item) -> (IPostInitComponent) side -> ((BlockCableBus) block).setupTile())
                .build();

        this.skyStoneSlab = makeSlab("sky_stone_slab", "sky_stone_double_slab", registry, this.skyStoneBlock());
        this.smoothSkyStoneSlab = makeSlab("smooth_sky_stone_slab", "smooth_sky_stone_double_slab", registry, this.smoothSkyStoneBlock());
        this.skyStoneBrickSlab = makeSlab("sky_stone_brick_slab", "sky_stone_brick_double_slab", registry, this.skyStoneBrick());
        this.skyStoneSmallBrickSlab = makeSlab("sky_stone_small_brick_slab", "sky_stone_small_brick_double_slab", registry, this.skyStoneSmallBrick());
        this.fluixSlab = makeSlab("fluix_slab", "fluix_double_slab", registry, this.fluixBlock());
        this.quartzSlab = makeSlab("quartz_slab", "quartz_double_slab", registry, this.quartzBlock());
        this.chiseledQuartzSlab = makeSlab("chiseled_quartz_slab", "chiseled_quartz_double_slab", registry, this.chiseledQuartzBlock());
        this.quartzPillarSlab = makeSlab("quartz_pillar_slab", "quartz_pillar_double_slab", registry, this.quartzPillar());

        this.itemGen = registry.block("debug_item_gen", BlockItemGen::new)
                .features(AEFeature.UNSUPPORTED_DEVELOPER_TOOLS, AEFeature.CREATIVE)
                .tileEntity(new TileEntityDefinition(TileItemGen.class))
                .useCustomItemModel()
                .build();
        this.chunkLoader = registry.block("debug_chunk_loader", BlockChunkloader::new)
                .features(AEFeature.UNSUPPORTED_DEVELOPER_TOOLS, AEFeature.CREATIVE)
                .tileEntity(new TileEntityDefinition(TileChunkLoader.class))
                .useCustomItemModel()
                .build();
        this.phantomNode = registry.block("debug_phantom_node", BlockPhantomNode::new)
                .features(AEFeature.UNSUPPORTED_DEVELOPER_TOOLS, AEFeature.CREATIVE)
                .tileEntity(new TileEntityDefinition(TilePhantomNode.class))
                .useCustomItemModel()
                .build();
        this.cubeGenerator = registry.block("debug_cube_gen", BlockCubeGenerator::new)
                .features(AEFeature.UNSUPPORTED_DEVELOPER_TOOLS, AEFeature.CREATIVE)
                .tileEntity(new TileEntityDefinition(TileCubeGenerator.class))
                .useCustomItemModel()
                .build();
        this.energyGenerator = registry.block("debug_energy_gen", BlockEnergyGenerator::new)
                .features(AEFeature.UNSUPPORTED_DEVELOPER_TOOLS, AEFeature.CREATIVE)
                .tileEntity(new TileEntityDefinition(TileEnergyGenerator.class))
                .useCustomItemModel()
                .build();
    }

    private static IBlockDefinition makeSlab(String slabId, String doubleSlabId, FeatureFactory registry, IBlockDefinition blockDef) {
        if (!blockDef.maybeBlock().isPresent()) {
            return new BlockDefinition(slabId, null, null);
        }

        Block block = blockDef.maybeBlock().get();

        IBlockDefinition slabDef = registry.block(slabId, () -> new BlockSlabCommon.Half(block))
                .features(AEFeature.DECORATIVE_BLOCKS)
                .disableItem()
                .build();

        if (!slabDef.maybeBlock().isPresent()) {
            return new BlockDefinition(slabId, null, null);
        }

        BlockSlab slabBlock = (BlockSlab) slabDef.maybeBlock().get();

        // Reigster the double slab variant as well
        IBlockDefinition doubleSlabDef = registry.block(doubleSlabId, () -> new BlockSlabCommon.Double(slabBlock, block))
                .features(AEFeature.DECORATIVE_BLOCKS)
                .disableItem()
                .build();

        if (!doubleSlabDef.maybeBlock().isPresent()) {
            return new BlockDefinition(slabId, null, null);
        }

        Verify.verify(doubleSlabDef.maybeBlock().isPresent());

        BlockSlab doubleSlabBlock = (BlockSlab) doubleSlabDef.maybeBlock().get();

        // Make the slab item
        IItemDefinition itemDef = registry.item(slabId, () -> new ItemSlab(slabBlock, slabBlock, doubleSlabBlock))
                .features(AEFeature.DECORATIVE_BLOCKS)
                .build();

        if (!itemDef.maybeItem().isPresent()) {
            return new BlockDefinition(slabId, null, null);
        }

        Verify.verify(itemDef.maybeItem().isPresent());

        // Return a new composite block definition that combines the single slab block with the slab item
        return new BlockDefinition(slabId, slabBlock, (ItemBlock) itemDef.maybeItem().get());
    }

    private static IBlockDefinition makeStairs(String registryName, FeatureFactory registry, IBlockDefinition block) {
        if (!block.maybeBlock().isPresent()) {
            return new BlockDefinition(registryName, null, null);
        }

        IBlockDefinition stairs = registry.block(registryName, () -> new BlockStairCommon(block.maybeBlock().get(), block.identifier()))
                .features(AEFeature.DECORATIVE_BLOCKS)
                .rendering(new BlockRenderingCustomizer() {
                    @Override
                    @SideOnly(Side.CLIENT)
                    public void customize(IBlockRendering rendering, IItemRendering itemRendering) {
                        ModelResourceLocation model = new ModelResourceLocation(new ResourceLocation(AppEng.MOD_ID, registryName), "facing=east,half=bottom,shape=straight");
                        itemRendering.model(model);
                    }
                })
                .build();

        if (!stairs.maybeBlock().isPresent()) {
            return new BlockDefinition(registryName, null, null);
        }

        Verify.verify(stairs.maybeBlock().isPresent());

        return stairs;
    }

    @Override
    public IBlockDefinition quartzOre() {
        return this.quartzOre;
    }

    @Override
    public IBlockDefinition quartzOreCharged() {
        return this.quartzOreCharged;
    }

    @Override
    public IBlockDefinition matrixFrame() {
        return this.matrixFrame;
    }

    @Override
    public IBlockDefinition quartzBlock() {
        return this.quartzBlock;
    }

    @Override
    public IBlockDefinition quartzPillar() {
        return this.quartzPillar;
    }

    @Override
    public IBlockDefinition chiseledQuartzBlock() {
        return this.chiseledQuartzBlock;
    }

    @Override
    public IBlockDefinition quartzGlass() {
        return this.quartzGlass;
    }

    @Override
    public IBlockDefinition quartzVibrantGlass() {
        return this.quartzVibrantGlass;
    }

    @Override
    public IBlockDefinition quartzFixture() {
        return this.quartzFixture;
    }

    @Override
    public IBlockDefinition fluixBlock() {
        return this.fluixBlock;
    }

    @Override
    public IBlockDefinition skyStoneBlock() {
        return this.skyStoneBlock;
    }

    @Override
    public IBlockDefinition smoothSkyStoneBlock() {
        return this.smoothSkyStoneBlock;
    }

    @Override
    public IBlockDefinition skyStoneBrick() {
        return this.skyStoneBrick;
    }

    @Override
    public IBlockDefinition skyStoneSmallBrick() {
        return this.skyStoneSmallBrick;
    }

    @Override
    public IBlockDefinition skyStoneChest() {
        return this.skyStoneChest;
    }

    @Override
    public IBlockDefinition smoothSkyStoneChest() {
        return this.smoothSkyStoneChest;
    }

    @Override
    public IBlockDefinition skyCompass() {
        return this.skyCompass;
    }

    @Override
    public IBlockDefinition skyStoneStairs() {
        return this.skyStoneStairs;
    }

    @Override
    public IBlockDefinition smoothSkyStoneStairs() {
        return this.smoothSkyStoneStairs;
    }

    @Override
    public IBlockDefinition skyStoneBrickStairs() {
        return this.skyStoneBrickStairs;
    }

    @Override
    public IBlockDefinition skyStoneSmallBrickStairs() {
        return this.skyStoneSmallBrickStairs;
    }

    @Override
    public IBlockDefinition fluixStairs() {
        return this.fluixStairs;
    }

    @Override
    public IBlockDefinition quartzStairs() {
        return this.quartzStairs;
    }

    @Override
    public IBlockDefinition chiseledQuartzStairs() {
        return this.chiseledQuartzStairs;
    }

    @Override
    public IBlockDefinition quartzPillarStairs() {
        return this.quartzPillarStairs;
    }

    @Override
    public IBlockDefinition skyStoneSlab() {
        return this.skyStoneSlab;
    }

    @Override
    public IBlockDefinition smoothSkyStoneSlab() {
        return this.smoothSkyStoneSlab;
    }

    @Override
    public IBlockDefinition skyStoneBrickSlab() {
        return this.skyStoneBrickSlab;
    }

    @Override
    public IBlockDefinition skyStoneSmallBrickSlab() {
        return this.skyStoneSmallBrickSlab;
    }

    @Override
    public IBlockDefinition fluixSlab() {
        return this.fluixSlab;
    }

    @Override
    public IBlockDefinition quartzSlab() {
        return this.quartzSlab;
    }

    @Override
    public IBlockDefinition chiseledQuartzSlab() {
        return this.chiseledQuartzSlab;
    }

    @Override
    public IBlockDefinition quartzPillarSlab() {
        return this.quartzPillarSlab;
    }

    @Override
    public ITileDefinition grindstone() {
        return this.grindstone;
    }


    @Override
    public ITileDefinition crank() {
        return this.crank;
    }
    @Override
    public ITileDefinition crankImp() {
        return this.crankImp;
    }


    @Override
    public ITileDefinition inscriber() {
        return this.inscriber;
    }

    @Override
    public ITileDefinition wirelessAccessPoint() {
        return this.wirelessAccessPoint;
    }

    @Override
    public ITileDefinition charger() {
        return this.charger;
    }

    @Override
    public IBlockDefinition tinyTNT() {
        return this.tinyTNT;
    }

    @Override
    public ITileDefinition securityStation() {
        return this.securityStation;
    }

    @Override
    public ITileDefinition quantumRing() {
        return this.quantumRing;
    }

    @Override
    public ITileDefinition quantumLink() {
        return this.quantumLink;
    }

    @Override
    public ITileDefinition spatialPylon() {
        return this.spatialPylon;
    }

    @Override
    public ITileDefinition spatialIOPort() {
        return this.spatialIOPort;
    }

    @Override
    public ITileDefinition multiPart() {
        return this.multiPart;
    }

    @Override
    public ITileDefinition controller() {
        return this.controller;
    }

    @Override
    public ITileDefinition drive() {
        return this.drive;
    }

    @Override
    public ITileDefinition driveImp() {
        return this.driveImp;
    }


    @Override
    public ITileDefinition chest() {
        return this.chest;
    }

    @Override
    public ITileDefinition iface() {
        return this.iface;
    }
    @Override
    public ITileDefinition ifaceImp() {
        return this.ifaceImp;
    }
    @Override
    public ITileDefinition ifaceAdv() {
        return this.ifaceAdv;
    }
    @Override
    public ITileDefinition ifacePer() {
        return this.ifacePer;
    }
    @Override
    public ITileDefinition ifacePatterns() {
        return this.ifacePatterns;
    }


    @Override
    public ITileDefinition fluidIface() {
        return this.fluidIface;
    }

    @Override
    public ITileDefinition cellWorkbench() {
        return this.cellWorkbench;
    }

    @Override
    public ITileDefinition iOPort() {
        return this.iOPort;
    }
    @Override
    public ITileDefinition iOPortImp() {
        return this.iOPortImp;
    }

    @Override
    public ITileDefinition condenser() {
        return this.condenser;
    }

    @Override
    public ITileDefinition energyAcceptor() {
        return this.energyAcceptor;
    }

    @Override
    public ITileDefinition vibrationChamber() {
        return this.vibrationChamber;
    }

    @Override
    public ITileDefinition quartzGrowthAccelerator() {
        return this.quartzGrowthAccelerator;
    }

    @Override
    public ITileDefinition energyCell() {
        return this.energyCell;
    }

    @Override
    public ITileDefinition energyCellDense() {
        return this.energyCellDense;
    }

    @Override
    public ITileDefinition energyCellImproved() {
        return this.energyCellImproved;
    }
    @Override
    public ITileDefinition energyCellAdvanced() {
        return this.energyCellAdvanced;
    }
    @Override
    public ITileDefinition energyCellPerfect() {
        return this.energyCellPerfect;
    }


    @Override
    public ITileDefinition energyCellCreative() {
        return this.energyCellCreative;
    }

    @Override
    public ITileDefinition craftingUnit() {
        return this.craftingUnit;
    }

    @Override
    public ITileDefinition craftingAccelerator() {
        return this.craftingAccelerator;
    }
    @Override
    public ITileDefinition craftingAcceleratorx4() {
        return this.craftingAccelerator4x;
    }
    @Override
    public ITileDefinition craftingAcceleratorx16() {
        return this.craftingAccelerator16x;
    }
    @Override
    public ITileDefinition craftingAcceleratorx64() {
        return this.craftingAccelerator64x;
    }

    @Override
    public ITileDefinition craftingAcceleratorx128() {
        return this.craftingAccelerator128x;
    }
    @Override
    public ITileDefinition craftingAcceleratorx256() {
        return this.craftingAccelerator256x;
    }
    @Override
    public ITileDefinition craftingAcceleratorx512() {
        return this.craftingAccelerator512x;
    }
    @Override
    public ITileDefinition craftingAcceleratorx1024() {
        return this.craftingAccelerator1024x;
    }
    @Override
    public ITileDefinition craftingAcceleratorCreative() {
        return this.craftingAcceleratorCreative;
    }


    @Override
    public ITileDefinition craftingStorage1k() {
        return this.craftingStorage1k;
    }

    @Override
    public ITileDefinition craftingStorage4k() {
        return this.craftingStorage4k;
    }

    @Override
    public ITileDefinition craftingStorage16k() {
        return this.craftingStorage16k;
    }

    @Override
    public ITileDefinition craftingStorage64k() {
        return this.craftingStorage64k;
    }
    @Override
    public ITileDefinition craftingStorage1mb() {
        return this.craftingStorage1mb;
    }
    @Override
    public ITileDefinition craftingStorage4mb() {
        return this.craftingStorage4mb;
    }
    @Override
    public ITileDefinition craftingStorage16mb() {
        return this.craftingStorage16mb;
    }
    @Override
    public ITileDefinition craftingStorage64mb() {
        return this.craftingStorage64mb;
    }
    @Override
    public ITileDefinition craftingStorage256mb() {
        return this.craftingStorage256mb;
    }
    @Override
    public ITileDefinition craftingStorage1gb() {
        return this.craftingStorage1gb;
    }
    @Override
    public ITileDefinition craftingStorage15gb() {
        return this.craftingStorage15gb;
    }

    @Override
    public ITileDefinition craftingMonitor() {
        return this.craftingMonitor;
    }

    @Override
    public ITileDefinition molecularAssembler() {
        return this.molecularAssembler;
    }
    @Override
    public ITileDefinition molecularAssemblerImp() {
        return this.molecularAssemblerImp;
    }

    @Override
    public ITileDefinition molecularAssemblerCreative() {
        return this.molecularAssemblerCreative;
    }


    @Override
    public ITileDefinition lightDetector() {
        return this.lightDetector;
    }

    @Override
    public ITileDefinition paint() {
        return this.paint;
    }

    public IBlockDefinition chunkLoader() {
        return this.chunkLoader;
    }

    public IBlockDefinition itemGen() {
        return this.itemGen;
    }

    public IBlockDefinition phantomNode() {
        return this.phantomNode;
    }

    public IBlockDefinition cubeGenerator() {
        return this.cubeGenerator;
    }

    public IBlockDefinition energyGenerator() {
        return this.energyGenerator;
    }
}
