/*
 * This file is part of Applied Energistics 2.
 * Copyright (c) 2013 - 2014, AlgorithmX2, All rights reserved.
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

package appeng.core.settings;


import appeng.core.AEConfig;


public enum TickRates {

    Interface(1, 1),

    ImportBus(4, 35),

    FluidImportBus(4, 35),

    ExportBus(4, 35),

    FluidExportBus(4, 35),

    AnnihilationPlane(3, 80),

    METunnel(5, 20),

    Inscriber(1, 1),

    Charger(1, 10),

    IOPort(1, 1),

    VibrationChamber(10, 40),

    StorageBus(4, 35),

    FluidStorageBus(4, 35),

    ItemTunnel(3, 60),

    LightTunnel(3, 60),

    OpenComputersTunnel(1, 5),

    PressureTunnel(1, 120);

    private int min;
    private int max;

    TickRates(final int min, final int max) {
        this.setMin(min);
        this.setMax(max);
    }

    public void Load(final AEConfig config) {
        config.addCustomCategoryComment("TickRates",
                " Min / Max Tickrates for dynamic ticking, most of these components also use sleeping, to prevent constant ticking, adjust with care, non standard rates are not supported or tested.");
        this.setMin(config.get("TickRates", this.name() + ".min", this.getMin()).getInt(this.getMin()));
        this.setMax(config.get("TickRates", this.name() + ".max", this.getMax()).getInt(this.getMax()));
    }

    public int getMax() {
        return this.max;
    }

    public void setMax(final int max) {
        this.max = max;
    }

    public int getMin() {
        return this.min;
    }

    public void setMin(final int min) {
        this.min = min;
    }

}
