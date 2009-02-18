package algorithm;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        double[][] sim = {{0, -3, -3, - 1}, {-3, 0, -1, -1}, {-1, -2, -3, -4}, {-3, -1, -1, -2}};
        AffinityPropagationAlgorithm instance = new MatrixPropagationAlgorithm();
        instance.setLambda(0.5);
        instance.setN(4);
        instance.init();

        instance.setSimilarities(sim);
        instance.doCluster();
    }
}