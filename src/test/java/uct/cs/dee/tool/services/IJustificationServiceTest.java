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
public class IJustificationServiceTest {
    
    public IJustificationServiceTest() {
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
     * Test of computeJustification method, of class IJustificationService.
     */
    @Test
    public void testComputeJustification() {
        System.out.println("computeJustification");
        IJustificationService instance = new IJustificationServiceImpl();
        ValidationResultModel<String> expResult = null;
        ValidationResultModel<String> result = instance.computeJustification();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEntailmentService method, of class IJustificationService.
     */
    @Test
    public void testGetEntailmentService() {
        System.out.println("getEntailmentService");
        IJustificationService instance = new IJustificationServiceImpl();
        IEntailmentService expResult = null;
        IEntailmentService result = instance.getEntailmentService();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDisplayMessage method, of class IJustificationService.
     */
    @Test
    public void testGetDisplayMessage() {
        System.out.println("getDisplayMessage");
        IJustificationService instance = new IJustificationServiceImpl();
        String expResult = "";
        String result = instance.getDisplayMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getExplanationMessage method, of class IJustificationService.
     */
    @Test
    public void testGetExplanationMessage() {
        System.out.println("getExplanationMessage");
        IJustificationService instance = new IJustificationServiceImpl();
        String expResult = "";
        String result = instance.getExplanationMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IJustificationServiceImpl implements IJustificationService {

        public ValidationResultModel<String> computeJustification() {
            return null;
        }

        public IEntailmentService getEntailmentService() {
            return null;
        }

        public String getDisplayMessage() {
            return "";
        }

        public String getExplanationMessage() {
            return "";
        }
    }
    
}
