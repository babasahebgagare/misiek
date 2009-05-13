package algorithm.smart;

import algorithm.abs.AffinityPropagationAlgorithm;
import algorithm.abs.ConvitsVector;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

/** You have to set parameters and do init() befor clustering. */
public class SmartPropagationAlgorithm extends AffinityPropagationAlgorithm {

    private ExamplarsCollection examplars = null;
    private double INF = 1000000;
    protected Collection<Integer> centers;

    public ExamplarsCollection getExamplars() {
        return examplars;
    }

    public void setExamplars(final ExamplarsCollection examplars) {
        this.examplars = examplars;
    }

    protected void avgAvailabilities() {
        for (Examplar examplar : examplars.getExamplars().values()) {
            Collection<SiblingData> siblings = examplar.getSiblingMap().values();
            for (SiblingData sibling : siblings) {
                sibling.setA(sibling.getA() * (1 - getLambda()) + getLambda() * sibling.getAold());
            }
        }
    }

    protected void avgResponsibilies() {
        for (Examplar examplar : examplars.getExamplars().values()) {
            Collection<SiblingData> siblings = examplar.getSiblingMap().values();
            for (SiblingData sibling : siblings) {
                sibling.setR(sibling.getR() * (1 - getLambda()) + getLambda() * sibling.getRold());
            }
        }
    }

    protected void computeAvailabilities() {
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

    protected void computeCenters() {
        Collection<Integer> ret = new HashSet<Integer>();
        for (Examplar examplar : examplars.getExamplars().values()) {
            SiblingData sibling = examplar.getSiblingMap().get(examplar.getName());
            double e = sibling.getA() + sibling.getR();
            if (e > 0) {
                ret.add(examplar.getName());
            //         examplar.setImCenter(true, iteration);
            }// else {
        //   examplar.setImCenter(false, iteration);
        //}

        }

        centers = ret;
    }

    private double computeEqPom(final Integer name) {
        double sum = 0;
        for (Examplar examplar : examplars.getExamplars().values()) {
            if (!examplar.getName().equals(name)) {
                SiblingData sibling = examplar.getSiblingMap().get(name);
                if (sibling != null) {
                    double r = sibling.getR();
                    sum +=
                            Math.max(0, r);
                }

            }
        }

        return sum;
    }

    private double computeMaxPom(final Collection<SiblingData> siblings, final Integer examplarName) {
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

    private double computeNotEqPom(final Integer examplarName,
            final Integer siblingName) {
        SiblingData sib = examplars.getExamplars().get(siblingName).getSiblingMap().get(siblingName);
        double rkk = sib.getR();

        double sum = 0;

        for (Examplar examplar : examplars.getExamplars().values()) {
            if (!examplar.getName().equals(examplarName) && !examplar.getName().equals(siblingName)) {
                SiblingData sibling = examplar.getSiblingMap().get(siblingName);
                if (sibling != null) {
                    double rik = sibling.getR();
                    sum +=
                            Math.max(0, rik);
                }

            }
        }
        double ret = Math.min(0, rkk + sum);
        return ret;
    }

    protected void computeResponsibilities() {
        for (Examplar examplar : examplars.getExamplars().values()) {
            Collection<SiblingData> siblings = examplar.getSiblingMap().values();
            for (SiblingData sibling : siblings) {
                double maxpom = computeMaxPom(siblings, sibling.getExamplarName());
                sibling.setR(sibling.getS() - maxpom);
            }

        }

    }

    protected void copyAvailabilities() {
        for (Examplar examplar : examplars.getExamplars().values()) {
            Collection<SiblingData> siblings = examplar.getSiblingMap().values();
            for (SiblingData sibling : siblings) {
                sibling.setAold(sibling.getA());
            }

        }
    }

    protected void copyResponsibilies() {
        for (Examplar examplar : examplars.getExamplars().values()) {
            Collection<SiblingData> siblings = examplar.getSiblingMap().values();
            for (SiblingData sibling : siblings) {
                sibling.setRold(sibling.getR());
            }

        }
    }

    /**
     *
     * @param from
     * @param to
     * @param sim
     */
    @Override
    public void setSimilarityInt(final Integer from,
            final Integer to,
            final Double sim) {

        if (graphMode == AffinityGraphMode.DIRECTED) {
            examplars.setSimilarity(from, to, sim);
        } else {
            examplars.setSimilarity(from, to, sim);
            examplars.setSimilarity(to, from, sim);
        }
    }

    @Override
    public void setSimilarity(final String from,
            final String to,
            final Double sim) {
        Integer i = getExamplarID(from);
        Integer j = getExamplarID(to);
        if (graphMode == AffinityGraphMode.DIRECTED) {
            examplars.setSimilarity(i, j, sim);
        } else {
            examplars.setSimilarity(i, j, sim);
            examplars.setSimilarity(j, i, sim);
        }
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
                this.setSimilarityInt(i, j, sim[i][j]);
            }

        }

    }

    @Override
    protected int getClustersNumber() {
        return centers.size();
    }

    @Override
    public void setConstPreferences(Double preferences) {
        Collection<Integer> examplarsNames = examplars.getExamplars().keySet();
        for (Integer exName : examplarsNames) {
            setSimilarityInt(exName, exName, preferences);
        }
    }

    @Override
    protected Collection<Integer> getCenters() {
        return new TreeSet<Integer>(centers);
    }

    @Override
    protected Collection<Integer> getAllExamplars() {
        return new TreeSet<Integer>(examplars.getExamplars().keySet());
    }

    protected Double tryGetSimilarityInt(Integer i, Integer j) {

        Examplar ix = examplars.getExamplars().get(i);
        SiblingData sibling = ix.getSiblingMap().get(j);
        if (sibling == null) {
            return null;
        } else {
            return sibling.getS();
        }
    }

    @Override
    protected Double tryGetSimilarity(String from, String to) {

        Integer i = idMapper.get(from);
        Integer j = idMapper.get(to);

        Examplar ix = examplars.getExamplars().get(i);
        SiblingData sibling = ix.getSiblingMap().get(j);
        if (sibling == null) {
            return null;
        } else {
            return sibling.getS();
        }
    }

    @Override
    protected void calculateCovergence() {
        if (convits != null) {
            for (Integer ex : examplars.getExamplars().keySet()) {
                if (centers.contains(ex)) {
                    convitsVectors.get(ex).addCovits(true);
                } else {
                    convitsVectors.get(ex).addCovits(false);
                }
            }
        }
    }

    @Override
    protected void initConvergence() {
        if (convits != null) {

            for (Integer ex : examplars.getExamplars().keySet()) {
                ConvitsVector vec = new ConvitsVector(convits.intValue());
                vec.init();
                convitsVectors.put(ex, vec);
            }
        }
    }
}