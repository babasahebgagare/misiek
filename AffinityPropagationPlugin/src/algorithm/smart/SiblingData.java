/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm.smart;

/**
 *
 * @author misiek
 */
public class SiblingData {

    private double r;
    private double rold;
    private double a;
    private double aold;
    private double s;
    private String examplarName;

    public SiblingData(final double s, final String examplarName) {
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

    public String getExamplarName() {
        return examplarName;
    }

    public void setExamplarName(final String examplarName) {
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
