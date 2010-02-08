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

import algorithm.abs.AffinityPropagationAlgorithm;
import algorithm.abs.ConvitsVector;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

/** You have to set parameters and do init() befor clustering. */
public class SmartPropagationAlgorithm extends AffinityPropagationAlgorithm {

    private ExamplarsCollection examplars = null;
    private double INF = 100000000;
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
            //    System.out.println(examplar.getName() + " "+e);
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
        //     if (graphMode == AffinityGraphMode.DIRECTED) {
        examplars.setSimilarity(from, to, sim);
        //    } else {
        //        examplars.setSimilarity(from, to, sim);
        //        examplars.setSimilarity(to, from, sim);
        //    }
    }

    @Override
    public void setSimilarity(final String from,
            final String to,
            final Double sim) {
        Integer i = getExamplarID(from);
        Integer j = getExamplarID(to);
        //      if (graphMode == AffinityGraphMode.DIRECTED) {
        examplars.setSimilarity(i, j, sim);
        //    } else {
        //        examplars.setSimilarity(i, j, sim);
        //        examplars.setSimilarity(j, i, sim);
        //    }
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
    public int getClustersNumber() {
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
    public Collection<Integer> getCentersAlg() {
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

    @Override
    protected void generateNoise() {
        for (Examplar examplar : examplars.getExamplars().values()) {
            Collection<SiblingData> siblings = examplar.getSiblingMap().values();
            for (SiblingData sibling : siblings) {
                double s = sibling.getS();
                s = generateNoiseHelp(s);
                sibling.setS(s);
            }
        }
    }

    @Override
    protected void showInfo() {
        System.out.println("SSSSS");
        for (Examplar examplar : examplars.getExamplars().values()) {
            Collection<SiblingData> siblings = examplar.getSiblingMap().values();
            for (SiblingData sibling : siblings) {
                if (sibling.getExamplarName().equals(examplar.getName())) {
                    System.out.println(sibling.getS());
                }
            }
        }
        /*      System.out.println("EEEE");
        for (Examplar examplar : examplars.getExamplars().values()) {
        Collection<SiblingData> siblings = examplar.getSiblingMap().values();
        for (SiblingData sibling : siblings) {
        if (sibling.getExamplarName().equals(examplar.getName())) {
        System.out.println(sibling.getA() + sibling.getR());
        }
        }
        }
        for (Examplar examplar : examplars.getExamplars().values()) {
        Collection<SiblingData> siblings = examplar.getSiblingMap().values();
        for (SiblingData sibling : siblings) {
        if (sibling.getExamplarName().equals(examplar.getName())) {
        System.out.println(sibling.getA());
        }
        }
        }
        System.out.println("RRRRRRRRRRRRRRR");
        for (Examplar examplar : examplars.getExamplars().values()) {
        Collection<SiblingData> siblings = examplar.getSiblingMap().values();
        for (SiblingData sibling : siblings) {
        if (sibling.getExamplarName().equals(examplar.getName())) {
        System.out.println(sibling.getR());
        }
        }
        }*/
    }
}
