package algorithm;

import algorithm.matrix.MatrixPropagationAlgorithm;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AffinityPropagationAlgorithmTest {

    public AffinityPropagationAlgorithmTest() {
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

    @Test
    public void testDoCluster() {
        double[][] sim = {{-2, -3, -3, - 1}, {-3, -1, -1, -1}, {-300, -2, 0, -4}, {-300, -1, -1, 0}};
        MatrixPropagationAlgorithm instance = new MatrixPropagationAlgorithm();
        instance.setLambda(0.5);
        instance.setN(4);
        instance.init();

        instance.setSimilarities(sim);
        instance.doCluster();
    }
}