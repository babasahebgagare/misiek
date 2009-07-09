package ppine.main;

import ppine.logicmodel.controllers.DataHandle;
import ppine.viewmodel.controllers.CytoDataHandle;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
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
        System.out.println("refresh");
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
