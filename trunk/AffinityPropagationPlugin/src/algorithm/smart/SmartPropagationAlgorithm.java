/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm.smart;

import algorithm.AffinityPropagationAlgorithm;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SmartPropagationAlgorithm extends AffinityPropagationAlgorithm {

    private ExamplarsCollection examplars = new ExamplarsCollection();
    private double INF = 1000000;

    public void setSimilarity(String from, String to, double sim, double a) {
        examplars.setSimilarity(from, to, sim, a);
    }

    public ExamplarsCollection getExamplars() {
        return examplars;
    }

    public void setExamplars(ExamplarsCollection examplars) {
        this.examplars = examplars;
    }

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void halt() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setN(int N) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSimilarities(double[][] sim) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer[] doCluster() {
        for (int iter = 0; iter < 10; iter++) {
            copyResponsibilies();
            computeResponsibilities();
            avgResponsibilies();
            copyAvailabilities();
            computeAvailabilities();
            avgAvailabilities();
        }
        Collection<Examplar> centers = computeCenters();
        Map<String, Collection<String>> assigments = computeAssigments(centers);

        System.out.println("CENTERS: " + centers.toString() + "ENDS");
        System.out.println("ASSIGMENTS: ");
        for (String key : assigments.keySet()) {
            System.out.println(key + "\n");
            for (String ex : assigments.get(key)) {
                System.out.println(ex);
            }
            System.out.println("\n");
        }
        System.out.println("ENDS ASSIGMENTS: ");
        return null;
    }

    private void avgAvailabilities() {
        for (Examplar examplar : examplars.getExamplars().values()) {
            Collection<SiblingData> siblings = examplar.getSiblingMap().values();
            for (SiblingData sibling : siblings) {
                sibling.setA(sibling.getA() * getLambda() + (1 - getLambda()) * sibling.getAold());
            }
        }
    }

    private void avgResponsibilies() {
        for (Examplar examplar : examplars.getExamplars().values()) {
            Collection<SiblingData> siblings = examplar.getSiblingMap().values();
            for (SiblingData sibling : siblings) {
                sibling.setR(sibling.getR() * getLambda() + (1 - getLambda()) * sibling.getRold());
            }
        }
    }

    private Map<String, Collection<String>> computeAssigments(Collection<Examplar> centers) {
        Map<String, Collection<String>> ret = new HashMap<String, Collection<String>>();
        for (Examplar center : centers) {
            ret.put(center.getName(), new HashSet<String>());
        }
        for (Examplar examplar : examplars.getExamplars().values()) {
            String maxid = null;
            double max = -INF;

            for (Examplar center : centers) {
                SiblingData sibling = examplar.getSiblingMap().get(center.getName());
                if (sibling != null) {
                    double sim = sibling.getS();
                    if (sim > max) {
                        max = sim;
                        maxid = center.getName();
                    }
                }
            }
            ret.get(maxid).add(examplar.getName());
        }

        return ret;
    }

    private void computeAvailabilities() {
        for (Examplar examplar : examplars.getExamplars().values()) {
            Collection<SiblingData> siblings = examplar.getSiblingMap().values();
            for (SiblingData sibling : siblings) {
                if (sibling.getExamplarName().equals(examplar.getName())) {
                    sibling.setA(computeEqPom(sibling.getExamplarName()));
                } else {
                    sibling.setA(computeNotEqPom(examplar.getName(), examplar.getName()));
                }
            }
        }
    }

    private Collection<Examplar> computeCenters() {
        Collection<Examplar> ret = new HashSet<Examplar>();
        for (Examplar examplar : examplars.getExamplars().values()) {
            SiblingData sibling = examplar.getSiblingMap().get(examplar.getName());
            double e = sibling.getA() + sibling.getR();
            if (e > 0) {
                ret.add(examplar);
            }
        }

        return ret;
    }

    private double computeEqPom(String name) {
        double sum = 0;
        for (Examplar examplar : examplars.getExamplars().values()) {
            if (!examplar.getName().equals(name)) {
                SiblingData sibling = examplar.getSiblingMap().get(name);
                if (sibling != null) {
                    double r = sibling.getR();
                    sum += Math.max(0, r);
                }
            }
        }

        return sum;
    }

    private double computeMaxPom(Collection<SiblingData> siblings, String examplarName) {
        double max = -INF;
        for (SiblingData sibling : siblings) {
            if (!sibling.getExamplarName().equals(examplarName)) {
                double pom = sibling.getA() + sibling.getS();
                if (pom > max) {
                    max = pom;
                }
            }
        }
        return max;
    }

    private double computeNotEqPom(String examplarName, String siblingName) {
        double rkk = examplars.getExamplars().get(examplarName).getSiblingMap().get(siblingName).getR();

        double sum = 0;

        for (Examplar examplar : examplars.getExamplars().values()) {
            if (!examplar.getName().equals(examplarName)) {
                SiblingData sibling = examplar.getSiblingMap().get(siblingName);
                if (sibling != null) {
                    double rik = sibling.getR();
                    sum += Math.max(0, rik);
                }
            }
        }
        double ret = Math.min(0, rkk + sum);
        return ret;
    }

    private void computeResponsibilities() {
        for (Examplar examplar : examplars.getExamplars().values()) {
            Collection<SiblingData> siblings = examplar.getSiblingMap().values();
            for (SiblingData sibling : siblings) {
                double maxpom = computeMaxPom(siblings, sibling.getExamplarName());
                sibling.setR(sibling.getS() - maxpom);
            }

        }

    }

    private void copyAvailabilities() {
        for (Examplar examplar : examplars.getExamplars().values()) {
            Collection<SiblingData> siblings = examplar.getSiblingMap().values();
            for (SiblingData sibling : siblings) {
                sibling.setAold(sibling.getA());
            }
        }
    }

    private void copyResponsibilies() {
        for (Examplar examplar : examplars.getExamplars().values()) {
            Collection<SiblingData> siblings = examplar.getSiblingMap().values();
            for (SiblingData sibling : siblings) {
                sibling.setRold(sibling.getR());
            }
        }
    }
}