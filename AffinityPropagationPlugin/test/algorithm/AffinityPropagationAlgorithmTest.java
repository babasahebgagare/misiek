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
        double[][] sim = {{3, 3}};
        System.out.println("doCluster");
        AffinityPropagationAlgorithm instance = new AffinityPropagationAlgorithm();
        instance.setLambda(0.5);
        instance.setSimilarities(sim);
        instance.doCluster();
    }
}