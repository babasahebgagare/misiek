package algorithm.smart;

import algorithm.abs.AffinityPropagationAlgorithm;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

/** You have to set parameters and do init() befor clustering. */
public class SmartPropagationAlgorithm extends AffinityPropagationAlgorithm<String> {

    private ExamplarsCollection examplars = null;
    private double INF = 1000000;
    private int iteration;
    private boolean convergence;

    public ExamplarsCollection getExamplars() {
        return examplars;
    }

    public void setExamplars(final ExamplarsCollection examplars) {
        this.examplars = examplars;
    }

    private void avgAvailabilities() {
        for (Examplar examplar : examplars.getExamplars().values()) {
            Collection<SiblingData> siblings = examplar.getSiblingMap().values();
            for (SiblingData sibling : siblings) {
                sibling.setA(sibling.getA() * (1 - getLambda()) + getLambda() * sibling.getAold());
            }
        }
    }

    private void avgResponsibilies() {
        for (Examplar examplar : examplars.getExamplars().values()) {
            Collection<SiblingData> siblings = examplar.getSiblingMap().values();
            for (SiblingData sibling : siblings) {
                sibling.setR(sibling.getR() * (1 - getLambda()) + getLambda() * sibling.getRold());
            }
        }
    }

    private boolean checkConvergence() {
        if (convits == null) {
            return false;
        }
        boolean res = true;

        for (Examplar examplar : examplars.getExamplars().values()) {
            if (examplar.changed()) {
                res = false;
                break;
            }
        }

        return res;
    }

    private Map<String, Cluster<String>> computeAssigments(final Collection<Examplar> centers) {
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
                    sibling.setA(computeNotEqPom(examplar.getName(), sibling.getExamplarName()));
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
                examplar.setImCenter(true, iteration);
            } else {
                examplar.setImCenter(false, iteration);
            }
        }

        return ret;
    }

    private double computeEqPom(final String name) {
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

    private double computeMaxPom(final Collection<SiblingData> siblings, final String examplarName) {
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

    private double computeNotEqPom(final String examplarName, final String siblingName) {
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
    public void setSimilarities(final String from, final String to, final Double sim) {
        examplars.setSimilarity(from, to, sim);
    }

    @Override
    public Map<String, String> doCluster() {
        final Map<String, Cluster<String>> help = doClusterAssoc();
        final Map<String, String> res = new HashMap<String, String>();

        for (Entry<String, Cluster<String>> entry : help.entrySet()) {
            for (String obj : entry.getValue().getElements()) {
                res.put(obj, entry.getKey());
            }
        }

        return res;
    }

    @Override
    public Map<String, Cluster<String>> doClusterAssoc() {
        int iterations = getIterations();
        if (iteractionListenerOrNull != null) {
            iteractionListenerOrNull.actionPerformed(new ActionEvent(new IterationData(1, examplars.size()), 0, "ITERATION"));
        }
        System.out.println("SIM: " + examplars.similaritiesToString());

        for (iteration = 0; iteration < iterations; iteration++) {

            copyResponsibilies();
            computeResponsibilities();
            System.out.println("RESP: " + examplars.responsiblitiesToString());
            avgResponsibilies();

            copyAvailabilities();
            computeAvailabilities();
            System.out.println("AVA: " + examplars.availibilitiesToString());
            avgAvailabilities();

            if (iteration + 1 != iterations && iteractionListenerOrNull != null) {
                Collection<Examplar> centers = computeCenters();
                iteractionListenerOrNull.actionPerformed(new ActionEvent(new IterationData(iteration + 2, centers.size()), 0, "ITERATION"));
            }
            convergence = checkConvergence();
            if (convergence) {
                break;
            }
        }


        Collection<Examplar> centers = computeCenters();

        System.out.println("CENTERS: ");
        for (Examplar ex : centers) {
            System.out.println(ex.getName());
        }
        Map<String, Cluster<String>> assigments = computeAssigments(centers);
        // centers = refine(assigments);

        return assigments;
    }

    @Override
    public void init() {
        examplars = new ExamplarsCollection(convits);
    }

    @Override
    public void halt() {
    }

    @Override
    public void setN(final int N) {
    }

    public void setSimilarities(double[][] sim) {
        int N = sim.length;
        if (N == 0) {
            return;
        }
        if (N != sim[0].length) {
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                String from = String.valueOf(i);
                String to = String.valueOf(j);
                Double prob = Double.valueOf(sim[i][j]);
                this.setSimilarities(from, to, prob);
            }
        }

    }
}