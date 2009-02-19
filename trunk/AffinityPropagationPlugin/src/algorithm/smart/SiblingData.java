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

    public SiblingData(double a, double s, String examplarName) {
        this.a = a;
        this.s = s;
        this.r = 0;
        this.rold = 0;
        this.aold = 0;
        this.examplarName = examplarName;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public String getExamplarName() {
        return examplarName;
    }

    public void setExamplarName(String examplarName) {
        this.examplarName = examplarName;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getS() {
        return s;
    }

    public void setS(double s) {
        this.s = s;
    }

    public double getAold() {
        return aold;
    }

    public void setAold(double aold) {
        this.aold = aold;
    }

    public double getRold() {
        return rold;
    }

    public void setRold(double rold) {
        this.rold = rold;
    }

    @Override
    public String toString() {
        String ret = "[" + examplarName + ": " + "r: " + r + " a: " + a + " s: " + s + "]";
        return ret;
    }
}
