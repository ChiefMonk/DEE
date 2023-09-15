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
import org.tweetyproject.commons.ParserException;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class IValidationServiceTest {
    
    public IValidationServiceTest() {
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
     * Test of IsDefeasibleStatement method, of class IValidationService.
     */
    @Test
    public void testIsDefeasibleStatement() {
        System.out.println("IsDefeasibleStatement");
        String statement = "";
        IValidationService instance = new IValidationServiceImpl();
        boolean expResult = false;
        boolean result = instance.IsDefeasibleStatement(statement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasValidConnective method, of class IValidationService.
     */
    @Test
    public void testHasValidConnective() {
        System.out.println("hasValidConnective");
        String statement = "";
        IValidationService instance = new IValidationServiceImpl();
        boolean expResult = false;
        boolean result = instance.hasValidConnective(statement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isInputQueryValid method, of class IValidationService.
     */
    @Test
    public void testIsInputQueryValid() throws Exception {
        System.out.println("isInputQueryValid");
        String statement = "";
        IValidationService instance = new IValidationServiceImpl();
        boolean expResult = false;
        boolean result = instance.isInputQueryValid(statement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isInputKnowledgeBaseValid method, of class IValidationService.
     */
    @Test
    public void testIsInputKnowledgeBaseValid() throws Exception {
        System.out.println("isInputKnowledgeBaseValid");
        String knowledgeBase = "";
        IValidationService instance = new IValidationServiceImpl();
        boolean expResult = false;
        boolean result = instance.isInputKnowledgeBaseValid(knowledgeBase);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IValidationServiceImpl implements IValidationService {

        public boolean IsDefeasibleStatement(String statement) {
            return false;
        }

        public boolean hasValidConnective(String statement) {
            return false;
        }

        public boolean isInputQueryValid(String statement) throws ParserException, Exception {
            return false;
        }

        public boolean isInputKnowledgeBaseValid(String knowledgeBase) throws ParserException, Exception {
            return false;
        }
    }
    
}
