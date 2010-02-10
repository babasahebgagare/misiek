/* ===========================================================
 * APGraphClusteringPlugin : Java implementation of Affinity Propagation
 * algorithm as Cytoscape plugin.
 * ===========================================================
 *
 *
 * Project Info:  http://bioputer.mimuw.edu.pl/veppin/
 * Sources: http://code.google.com/p/misiek/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
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
 * APGraphClusteringPlugin  Copyright (C) 2008-2009
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */
package panel;

public class ClusteringHistoryData {

    private String network;
    private Integer iterations;
    private Integer convits;
    private Double preferences;
    private Integer madeIterations;
    private Double lambda;
    private Integer clusters;
    private String clusterID;
    private String centerID;
    private Boolean takeLog;
    private Boolean noise;

    public Integer getMadeIterations() {
        return madeIterations;
    }

    public void setMadeIterations(Integer madeIterations) {
        this.madeIterations = madeIterations;
    }

    public Boolean getNoise() {
        return noise;
    }

    public void setNoise(Boolean noise) {
        this.noise = noise;
    }

    public Boolean getTakeLog() {
        return takeLog;
    }

    public void setTakeLog(Boolean takeLog) {
        this.takeLog = takeLog;
    }

    public Integer getConvits() {
        return convits;
    }

    public void setConvits(Integer convits) {
        this.convits = convits;
    }

    public String getCenterID() {
        return centerID;
    }

    public void setCenterID(String centerID) {
        this.centerID = centerID;
    }

    public String getClusterID() {
        return clusterID;
    }

    public void setClusterID(String clusterID) {
        this.clusterID = clusterID;
    }

    public Integer getClusters() {
        return clusters;
    }

    public void setClusters(Integer clusters) {
        this.clusters = clusters;
    }

    public Integer getIterations() {
        return iterations;
    }

    public void setIterations(Integer iterations) {
        this.iterations = iterations;
    }

    public Double getLambda() {
        return lambda;
    }

    public void setLambda(Double lambda) {
        this.lambda = lambda;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public Double getPreferences() {
        return preferences;
    }

    public void setPreferences(Double preferences) {
        this.preferences = preferences;
    }
}
