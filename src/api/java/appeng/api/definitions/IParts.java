/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 AlgorithmX2
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package appeng.api.definitions;


import appeng.api.util.AEColoredItemDefinition;


/**
 * A list of all parts in AE
 */
public interface IParts {
    AEColoredItemDefinition cableSmart();

    AEColoredItemDefinition cableCovered();

    AEColoredItemDefinition cableGlass();

    AEColoredItemDefinition cableDenseCovered();

    AEColoredItemDefinition cableDenseSmart();
    //AEColoredItemDefinition cableCreative();


    AEColoredItemDefinition lumenCableSmart();

    AEColoredItemDefinition lumenCableCovered();

    AEColoredItemDefinition lumenCableGlass();

    AEColoredItemDefinition lumenDenseCableSmart();

    IItemDefinition quartzFiber();

    IItemDefinition toggleBus();

    IItemDefinition invertedToggleBus();

    IItemDefinition storageBus();

    IItemDefinition oreDictStorageBus();

    IItemDefinition importBus();
    IItemDefinition importBusImp();

    IItemDefinition exportBus();
    IItemDefinition exportBusImp();

    IItemDefinition iface();
    IItemDefinition ifaceImp();
    IItemDefinition ifaceAdv();
    IItemDefinition ifacePer();
    IItemDefinition ifacePatt();

    IItemDefinition fluidIface();

    IItemDefinition levelEmitter();

    IItemDefinition annihilationPlane();

    IItemDefinition identityAnnihilationPlane();

    IItemDefinition formationPlane();

    IItemDefinition p2PTunnelME();

    IItemDefinition p2PTunnelRedstone();

    IItemDefinition p2PTunnelItems();

    IItemDefinition p2PTunnelFluids();

    IItemDefinition p2PTunnelEU();

    IItemDefinition p2PTunnelFE();

    IItemDefinition p2PTunnelGTEU();

    IItemDefinition p2PTunnelLight();

    // IItemDefinition p2PTunnelOpenComputers();

    IItemDefinition cableAnchor();

    IItemDefinition monitor();

    IItemDefinition semiDarkMonitor();

    IItemDefinition darkMonitor();

    IItemDefinition interfaceTerminal();

    IItemDefinition patternTerminal();

    IItemDefinition expandedProcessingPatternTerminal();

    IItemDefinition interfaceConfigurationTerminal();

    IItemDefinition fluidInterfaceConfigurationTerminal();

    IItemDefinition craftingTerminal();

    IItemDefinition terminal();

    IItemDefinition storageMonitor();

    IItemDefinition conversionMonitor();

    IItemDefinition fluidTerminal();

    IItemDefinition fluidImportBus();
    IItemDefinition fluidImportBusImp();

    IItemDefinition fluidExportBus();
    IItemDefinition fluidExportBusImp();

    IItemDefinition fluidStorageBus();

    IItemDefinition fluidLevelEmitter();

    IItemDefinition fluidAnnihilationPlane();

    IItemDefinition fluidFormationnPlane();
}
