/* ===========================================================
 * NetworkEvolutionPlugin : Cytoscape plugin for visualizing stages of
 * protein networks evolution.
 * ===========================================================
 *
 *
 * Project Info:  http://bioputer.mimuw.edu.pl/modevo/
 * Sources: http://code.google.com/p/misiek/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 *
 * NetworkEvolutionPlugin  Copyright (C) 2008-2009
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea, data) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */

package ppine.main;

import ppine.logicmodel.controllers.DataHandle;
import ppine.viewmodel.controllers.CytoDataHandle;

public class PluginDataHandle {

    private static DataHandle dataHandle = null;
    private static CytoDataHandle cytoDataHandle = null;
    private static LoadingDataHandle loadingDataHandle = new LoadingDataHandle();
    private static LoadedDataHandle loadedDataHandle = new LoadedDataHandle();

    public static LoadedDataHandle getLoadedDataHandle() {
        return loadedDataHandle;
    }

    public static void setLoadedDataHandle(LoadedDataHandle loadedDataHandle) {
        PluginDataHandle.loadedDataHandle = loadedDataHandle;
    }

    public static LoadingDataHandle getLoadingDataHandle() {
        return loadingDataHandle;
    }

    public static void setLoadingDataHandle(LoadingDataHandle loadingDataHandle) {
        PluginDataHandle.loadingDataHandle = loadingDataHandle;
    }

    public static void refreshPluginDataHandle() {
        //System.out.println("refresh");
        dataHandle = new DataHandle();
        loadedDataHandle = new LoadedDataHandle();
        loadingDataHandle = new LoadingDataHandle();
        cytoDataHandle = new CytoDataHandle();
    }

    public static CytoDataHandle getCytoDataHandle() {
        return cytoDataHandle;
    }

    public static void setCytoDataHandle(CytoDataHandle cytoDataHandle) {
        PluginDataHandle.cytoDataHandle = cytoDataHandle;
    }

    public static DataHandle getDataHandle() {
        return dataHandle;
    }

    public static void setDataHandle(DataHandle dataHandle) {
        PluginDataHandle.dataHandle = dataHandle;
    }
}
