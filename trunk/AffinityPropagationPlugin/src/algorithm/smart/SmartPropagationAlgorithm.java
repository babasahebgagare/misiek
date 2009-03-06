package algorithm.smart;

import algorithm.abs.AffinityPropagationAlgorithm;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SmartPropagationAlgorithm extends AffinityPropagationAlgorithm<String> {

    private ExamplarsCollection examplars = new ExamplarsCollection();
    private double INF = 1000000;

    public ExamplarsCollection getExamplars() {
        return examplars;
    }

    public void setExamplars(ExamplarsCollection examplars) {
        this.examplars = examplars;
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

    private Map<String, String> computeAssigments(Collection<Examplar> centers) {
        Map<String, String> ret = new HashMap<String, String>();

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
            if (maxid != null) {
                ret.put(examplar.getName(), maxid);
            }
        }

        return ret;
    }

    private Map<String, Cluster<String>> computeAssigments2(Collection<Examplar> centers) {
        Map<String, Cluster<String>> ret = new HashMap<String, Cluster<String>>();

        for (Examplar center : centers) {
            Cluster<String> clust = new Cluster<String>(center.getName());
            clust.add(center.getName());
            ret.put(center.getName(), clust);
        }

        for (Examplar examplar : examplars.getExamplars().values()) {
            if (!ret.containsKey(examplar.getName())) {

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
                if (maxid != null) {
                    Cluster<String> cluster = ret.get(maxid);
                    cluster.add(examplar.getName());
                }
            }
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
        SiblingData sib = examplars.getExamplars().get(siblingName).getSiblingMap().get(siblingName);
        double rkk = sib.getR();

        double sum = 0;

        for (Examplar examplar : examplars.getExamplars().values()) {
            if (!examplar.getName().equals(examplarName) && !examplar.getName().equals(siblingName)) {
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

    @Override
    public void setSimilarities(String from, String to, Double sim) {
        examplars.setSimilarity(from, to, sim);
    }

    @Override
    public Map<String, String> doCluster() {
        Map<String, Cluster<String>> help = doClusterAssoc();
        Map<String, String> res = new HashMap<String, String>();

        for (String examplar : help.keySet()) {
            for (String obj : help.get(examplar).getElements()) {
                res.put(obj, examplar);
            }
        }

        return res;
    }

    @Override
    public Map<String, Cluster<String>> doClusterAssoc() {
        int iterations = getIterations();
        for (int iter = 0; iter < iterations; iter++) {

            copyResponsibilies();
            computeResponsibilities();
            avgResponsibilies();
            copyAvailabilities();
            computeAvailabilities();
            avgAvailabilities();

            Collection<Examplar> centers = computeCenters();
            iteractionListener.actionPerformed(new ActionEvent(new IterationData(iter + 1, centers.size()), 0, "ITERATION"));
        }
        Collection<Examplar> centers = computeCenters();
        Map<String, Cluster<String>> assigments = computeAssigments2(centers);

        return assigments;
    }

    @Override
    public void init() {
    }

    @Override
    public void halt() {
    }

    @Override
    public void setN(int N) {
    }
}