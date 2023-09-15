/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package uct.cs.dee.tool.ui;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uct.cs.dee.tool.models.ValidationResultModel;
import uct.cs.dee.tool.services.IEntailmentService;
import uct.cs.dee.tool.services.IExplanationService;
import uct.cs.dee.tool.services.IJustificationService;
import uct.cs.dee.tool.services.IKnowledgeBaseService;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class UIManagerTest {
    
    public UIManagerTest() {
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
     * Test of validateKnowledgeBase method, of class UIManager.
     */
    @Test
    public void testValidateKnowledgeBase_String() {
        System.out.println("validateKnowledgeBase");
        String kbString = "";
        ValidationResultModel<String> expResult = null;
        ValidationResultModel<String> result = UIManager.validateKnowledgeBase(kbString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateKnowledgeBase method, of class UIManager.
     */
    @Test
    public void testValidateKnowledgeBase_List() {
        System.out.println("validateKnowledgeBase");
        List<String> kbStatements = null;
        ValidationResultModel<String> expResult = null;
        ValidationResultModel<String> result = UIManager.validateKnowledgeBase(kbStatements);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateKnowledgeBaseFile method, of class UIManager.
     */
    @Test
    public void testValidateKnowledgeBaseFile() {
        System.out.println("validateKnowledgeBaseFile");
        String filePath = "";
        ValidationResultModel<String> expResult = null;
        ValidationResultModel<String> result = UIManager.validateKnowledgeBaseFile(filePath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateQuery method, of class UIManager.
     */
    @Test
    public void testValidateQuery() {
        System.out.println("validateQuery");
        String query = "";
        ValidationResultModel<String> expResult = null;
        ValidationResultModel<String> result = UIManager.validateQuery(query);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of computeEntailmentAndExplanation method, of class UIManager.
     */
    @Test
    public void testComputeEntailmentAndExplanation_String_String() {
        System.out.println("computeEntailmentAndExplanation");
        String kbString = "";
        String query = "";
        ValidationResultModel<String> expResult = null;
        ValidationResultModel<String> result = UIManager.computeEntailmentAndExplanation(kbString, query);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of computeEntailmentAndExplanation method, of class UIManager.
     */
    @Test
    public void testComputeEntailmentAndExplanation_List_String() {
        System.out.println("computeEntailmentAndExplanation");
        List<String> kbStatements = null;
        String query = "";
        ValidationResultModel<String> expResult = null;
        ValidationResultModel<String> result = UIManager.computeEntailmentAndExplanation(kbStatements, query);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of KnowledgeBaseService method, of class UIManager.
     */
    @Test
    public void testKnowledgeBaseService() {
        System.out.println("KnowledgeBaseService");
        IKnowledgeBaseService expResult = null;
        IKnowledgeBaseService result = UIManager.KnowledgeBaseService();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of EntailmentService method, of class UIManager.
     */
    @Test
    public void testEntailmentService() {
        System.out.println("EntailmentService");
        IEntailmentService expResult = null;
        IEntailmentService result = UIManager.EntailmentService();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of JustificationService method, of class UIManager.
     */
    @Test
    public void testJustificationService() {
        System.out.println("JustificationService");
        IJustificationService expResult = null;
        IJustificationService result = UIManager.JustificationService();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ExplanationService method, of class UIManager.
     */
    @Test
    public void testExplanationService() {
        System.out.println("ExplanationService");
        IExplanationService expResult = null;
        IExplanationService result = UIManager.ExplanationService();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
