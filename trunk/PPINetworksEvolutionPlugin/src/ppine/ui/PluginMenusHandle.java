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
package ppine.ui;

import javax.swing.JCheckBox;
import ppine.ui.familycolors.SpeciesFamilyColorPanel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTree;

public class PluginMenusHandle {

    private static JTextArea memo = null;
    private static JTree tree = null;
    private static JButton doProjectionButton = null;
    private static JButton showNetworkButton = null;
    private static JButton showLoadedInteractionsButton = null;
    private static JButton newDataButton = null;
    private static JButton deleteDataButton = null;
    private static JButton updateDataButton = null;
    private static JCheckBox mapWithAttrCheckbox = null;
    private static JPanel ppinePanel = null;
    private static SpeciesFamilyColorPanel familiesColorListPanel = null;

    public static SpeciesFamilyColorPanel getFamiliesColorListPanel() {
        return familiesColorListPanel;
    }

    public static JButton getDeleteDataButton() {
        return deleteDataButton;
    }

    public static void setDeleteDataButton(JButton deleteDataButton) {
        PluginMenusHandle.deleteDataButton = deleteDataButton;
    }

    public static JTree getTree() {
        return tree;
    }

    public static void setTree(JTree tree) {
        PluginMenusHandle.tree = tree;
    }

    public static JTextArea getMemo() {
        return memo;
    }

    public static void setMemo(JTextArea memo) {
        PluginMenusHandle.memo = memo;
    }

    public static JButton getDoProjectionButton() {
        return doProjectionButton;
    }

    public static void setDoProjectionButton(JButton doProjectionButton) {
        PluginMenusHandle.doProjectionButton = doProjectionButton;
    }

    public static JButton getShowNetworkButton() {
        return showNetworkButton;
    }

    public static void setShowNetowrkButton(JButton showNetworkButton) {
        PluginMenusHandle.showNetworkButton = showNetworkButton;
    }

    public static JButton getShowLoadedInteractionsButton() {
        return showLoadedInteractionsButton;
    }

    public static void setShowLoadedInteractionsButton(JButton showLoadedInteractionsButton) {
        PluginMenusHandle.showLoadedInteractionsButton = showLoadedInteractionsButton;
    }

    public static JButton getNewDataButton() {
        return newDataButton;
    }

    public static void setNewDataButton(JButton newDataButton) {
        PluginMenusHandle.newDataButton = newDataButton;
    }

    public static JButton getUpdateDataButton() {
        return updateDataButton;
    }

    public static void setUpdateDataButton(JButton updateDataButton) {
        PluginMenusHandle.updateDataButton = updateDataButton;
    }

    public static JPanel getPPINEPanel() {
        return ppinePanel;
    }

    public static void setPPINEPanel(JPanel ppinePanel) {
        PluginMenusHandle.ppinePanel = ppinePanel;
    }

    public static void setFamiliesColorListPanel(SpeciesFamilyColorPanel familiesColorListPanel) {
        PluginMenusHandle.familiesColorListPanel = familiesColorListPanel;
    }

    public static void initButtonsState() {
        getShowNetworkButton().setEnabled(false);
        getDoProjectionButton().setEnabled(false);
        getShowLoadedInteractionsButton().setEnabled(false);
        getDoProjectionButton().setEnabled(false);
        getNewDataButton().setEnabled(true);
        getUpdateDataButton().setEnabled(false);
    }

    public static void refreshUIafterDeleteData() {
        PluginMenusHandle.getUpdateDataButton().setEnabled(false);
        PluginMenusHandle.getShowNetworkButton().setEnabled(false);
        PluginMenusHandle.getNewDataButton().setEnabled(true);
        PluginMenusHandle.getDoProjectionButton().setEnabled(false);
        PluginMenusHandle.getShowLoadedInteractionsButton().setEnabled(false);
        PluginMenusHandle.getDeleteDataButton().setEnabled(false);
    }

    public static void refreshUIafterProteinsLoading() {
        PluginMenusHandle.getUpdateDataButton().setEnabled(true);
        PluginMenusHandle.getShowNetworkButton().setEnabled(true);
        PluginMenusHandle.getNewDataButton().setEnabled(true);
        PluginMenusHandle.getShowLoadedInteractionsButton().setEnabled(true);
    }

    public static void refreshUIafterSpeciesLoading() {
        PluginMenusHandle.getNewDataButton().setEnabled(true);
        PluginMenusHandle.getUpdateDataButton().setEnabled(true);
        PluginMenusHandle.getDeleteDataButton().setEnabled(true);
    }

    static void setMapWithAttributesCkeckBox(JCheckBox checkbox) {
        mapWithAttrCheckbox = checkbox;
    }

    public static JCheckBox getMapWithAttrCheckbox() {
        return mapWithAttrCheckbox;
    }
}
