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
import org.tweetyproject.logics.pl.syntax.PlFormula;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class IParsingServiceTest {
    
    public IParsingServiceTest() {
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
     * Test of isInputQueryValid method, of class IParsingService.
     */
    @Test
    public void testIsInputQueryValid() {
        System.out.println("isInputQueryValid");
        String inputQuery = "";
        IParsingService instance = new IParsingServiceImpl();
        boolean expResult = false;
        boolean result = instance.isInputQueryValid(inputQuery);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isInputKnowledgeBaseValid method, of class IParsingService.
     */
    @Test
    public void testIsInputKnowledgeBaseValid() {
        System.out.println("isInputKnowledgeBaseValid");
        String inputKnowledgeBase = "";
        IParsingService instance = new IParsingServiceImpl();
        boolean expResult = false;
        boolean result = instance.isInputKnowledgeBaseValid(inputKnowledgeBase);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseFormula method, of class IParsingService.
     */
    @Test
    public void testParseFormula() throws Exception {
        System.out.println("parseFormula");
        String formula = "";
        IParsingService instance = new IParsingServiceImpl();
        PlFormula expResult = null;
        PlFormula result = instance.parseFormula(formula);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IParsingServiceImpl implements IParsingService {

        public boolean isInputQueryValid(String inputQuery) {
            return false;
        }

        public boolean isInputKnowledgeBaseValid(String inputKnowledgeBase) {
            return false;
        }

        public PlFormula parseFormula(String formula) throws ParserException, Exception {
            return null;
        }
    }
    
}
