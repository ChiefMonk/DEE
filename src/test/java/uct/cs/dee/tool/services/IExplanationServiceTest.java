/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package uct.cs.dee.tool.services;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uct.cs.dee.tool.models.ValidationResultModel;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class IExplanationServiceTest {
    
    public IExplanationServiceTest() {
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
     * Test of computeExplanation method, of class IExplanationService.
     */
    @Test
    public void testComputeExplanation() {
        System.out.println("computeExplanation");
        IExplanationService instance = new IExplanationServiceImpl();
        ValidationResultModel<String> expResult = null;
        ValidationResultModel<String> result = instance.computeExplanation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getExplanationMessage method, of class IExplanationService.
     */
    @Test
    public void testGetExplanationMessage() {
        System.out.println("getExplanationMessage");
        IExplanationService instance = new IExplanationServiceImpl();
        String expResult = "";
        String result = instance.getExplanationMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IExplanationServiceImpl implements IExplanationService {

        public ValidationResultModel<String> computeExplanation() {
            return null;
        }

        public String getExplanationMessage() {
            return "";
        }
    }
    
}
