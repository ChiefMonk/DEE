/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package uct.cs.dee.tool.helpers;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import org.tweetyproject.logics.pl.syntax.PlFormula;
import uct.cs.dee.tool.models.NodeModel;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class ClassicalJustificationHelperTest {
    
    public ClassicalJustificationHelperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of computeJustification method, of class ClassicalJustificationHelper.
     */
    @Test
    public void testComputeJustification() {
        System.out.println("computeJustification");
        PlBeliefSet knowledgeBase = null;
        PlFormula query = null;
        NodeModel expResult = null;
        NodeModel result = ClassicalJustificationHelper.computeJustification(knowledgeBase, query);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
