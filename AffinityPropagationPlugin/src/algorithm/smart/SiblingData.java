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
 * APGraphClusteringPlugin  Copyright (C) 2008-2009
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */


package algorithm.smart;

public class SiblingData {

    private double r;
    private double rold;
    private double a;
    private double aold;
    private double s;
    private Integer examplarName;

    public SiblingData(final double s, final Integer examplarName) {
        this.a = 0;
        this.s = s;
        this.r = 0;
        this.rold = 0;
        this.aold = 0;
        this.examplarName = examplarName;
    }

    public double getA() {
        return a;
    }

    public void setA(final double a) {
        this.a = a;
    }

    public Integer getExamplarName() {
        return examplarName;
    }

    public void setExamplarName(final Integer examplarName) {
        this.examplarName = examplarName;
    }

    public double getR() {
        return r;
    }

    public void setR(final double r) {
        this.r = r;
    }

    public double getS() {
        return s;
    }

    public void setS(final double s) {
        this.s = s;
    }

    public double getAold() {
        return aold;
    }

    public void setAold(final double aold) {
        this.aold = aold;
    }

    public double getRold() {
        return rold;
    }

    public void setRold(final double rold) {
        this.rold = rold;
    }

    @Override
    public String toString() {
        String ret = "[" + examplarName + ": " + "r: " + r + " a: " + a + " s: " + s + "]";
        return ret;
    }
}
