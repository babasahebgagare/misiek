/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm.smart;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author misiek
 */
public class SmartPropagationAlgorithmTest {

    public SmartPropagationAlgorithmTest() {
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
    public void testSetSimilarity() {
        System.out.println("setSimilarity");

        SmartPropagationAlgorithm instance = new SmartPropagationAlgorithm();
        loadData(instance);


        System.out.println(instance.getExamplars().toString());
        instance.doClusterString();
        System.out.println(instance.getExamplars().toString());
    }

    private void loadData(SmartPropagationAlgorithm instance) {
        instance.setSimilarity("1", "1", -2, 0);
        instance.setSimilarity("1", "2", -3, 0);
        instance.setSimilarity("1", "3", -3, 0);
        instance.setSimilarity("1", "4", -1, 0);
        instance.setSimilarity("2", "1", -3, 0);
        instance.setSimilarity("2", "2", -1, 0);
        instance.setSimilarity("2", "3", -1, 0);
        instance.setSimilarity("2", "4", -1, 0);
        instance.setSimilarity("3", "2", -2, 0);
        instance.setSimilarity("3", "3", -0, 0);
        instance.setSimilarity("3", "4", -4, 0);
        instance.setSimilarity("4", "2", -1, 0);
        instance.setSimilarity("4", "3", -1, 0);
        instance.setSimilarity("4", "4", 0, 0);
    }
    /*
    @Test
    public void testDoCluster() {
    System.out.println("doCluster");
    SmartPropagationAlgorithm instance = new SmartPropagationAlgorithm();
    Integer[] expResult = null;
    Integer[] result = instance.doCluster();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
    }*/
}