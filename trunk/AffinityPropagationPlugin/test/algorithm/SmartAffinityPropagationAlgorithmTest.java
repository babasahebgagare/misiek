package algorithm;

import algorithm.abs.AffinityPropagationAlgorithm;
import algorithm.abs.Cluster;
import algorithm.smart.SmartPropagationAlgorithm;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SmartAffinityPropagationAlgorithmTest {

    public SmartAffinityPropagationAlgorithmTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @SuppressWarnings("unchecked")
    public void setSimilarities(double[][] sim, AffinityPropagationAlgorithm instance, Double inf) {
        int N = sim.length;
        if (N == 0) {
            return;
        }
        if (N != sim[0].length) {
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Integer from = Integer.valueOf(i);
                Integer to = Integer.valueOf(j);
                Double prob = Double.valueOf(sim[i][j]);
                if (!prob.equals(inf)) {
                    instance.setSimilarityInt(from, to, prob);
                }
            }
        }

    }

    @Test
    public void testDoCluster() {
        double p = Math.log(0.2);
        double inf = -100;
        double[][] sim = {{p, 0, inf}, {0, p, 0}, {inf, 0, p}};
//        double[][] sim = {{p, inf, inf, 0}, {inf, p, 0, inf}, {inf, 0, p, 0}, {0, inf, 0, p}};
        AffinityPropagationAlgorithm instance = new SmartPropagationAlgorithm();
        instance.setLambda(0.5);
        instance.setN(3);
        instance.setIterations(9);
        instance.setConvits(null);
        instance.init();

        setSimilarities(sim, instance, inf);
        @SuppressWarnings("unchecked")
        Map<Integer, Integer> ass = instance.doClusterInt();
        for (Integer val : ass.values()) {
            System.out.println(val);
        }
    }
}