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
import uct.cs.dee.tool.models.EntailmentResultModel;
import uct.cs.dee.tool.models.MinimalRankedFormulaModel;
import uct.cs.dee.tool.models.ValidationResultModel;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class IEntailmentServiceTest {
    
    public IEntailmentServiceTest() {
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
     * Test of computeEntailment method, of class IEntailmentService.
     */
    @Test
    public void testComputeEntailment() {
        System.out.println("computeEntailment");
        IEntailmentService instance = new IEntailmentServiceImpl();
        ValidationResultModel<String> expResult = null;
        ValidationResultModel<String> result = instance.computeEntailment();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getKnowledgeBaseService method, of class IEntailmentService.
     */
    @Test
    public void testGetKnowledgeBaseService() {
        System.out.println("getKnowledgeBaseService");
        IEntailmentService instance = new IEntailmentServiceImpl();
        IKnowledgeBaseService expResult = null;
        IKnowledgeBaseService result = instance.getKnowledgeBaseService();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doesKbEntailQuery method, of class IEntailmentService.
     */
    @Test
    public void testDoesKbEntailQuery() {
        System.out.println("doesKbEntailQuery");
        IEntailmentService instance = new IEntailmentServiceImpl();
        boolean expResult = false;
        boolean result = instance.doesKbEntailQuery();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfDiscardedRanks method, of class IEntailmentService.
     */
    @Test
    public void testGetNumberOfDiscardedRanks() {
        System.out.println("getNumberOfDiscardedRanks");
        IEntailmentService instance = new IEntailmentServiceImpl();
        int expResult = 0;
        int result = instance.getNumberOfDiscardedRanks();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBaseRankingFormulas method, of class IEntailmentService.
     */
    @Test
    public void testGetBaseRankingFormulas() {
        System.out.println("getBaseRankingFormulas");
        IEntailmentService instance = new IEntailmentServiceImpl();
        MinimalRankedFormulaModel expResult = null;
        MinimalRankedFormulaModel result = instance.getBaseRankingFormulas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEntailmentResults method, of class IEntailmentService.
     */
    @Test
    public void testGetEntailmentResults() {
        System.out.println("getEntailmentResults");
        IEntailmentService instance = new IEntailmentServiceImpl();
        EntailmentResultModel expResult = null;
        EntailmentResultModel result = instance.getEntailmentResults();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBaseRankingFormulasMessage method, of class IEntailmentService.
     */
    @Test
    public void testGetBaseRankingFormulasMessage() {
        System.out.println("getBaseRankingFormulasMessage");
        IEntailmentService instance = new IEntailmentServiceImpl();
        String expResult = "";
        String result = instance.getBaseRankingFormulasMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDiscardedFormulaListMessage method, of class IEntailmentService.
     */
    @Test
    public void testGetDiscardedFormulaListMessage() {
        System.out.println("getDiscardedFormulaListMessage");
        IEntailmentService instance = new IEntailmentServiceImpl();
        String expResult = "";
        String result = instance.getDiscardedFormulaListMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRemainingFormulaListMessage method, of class IEntailmentService.
     */
    @Test
    public void testGetRemainingFormulaListMessage() {
        System.out.println("getRemainingFormulaListMessage");
        boolean addEndline = false;
        IEntailmentService instance = new IEntailmentServiceImpl();
        String expResult = "";
        String result = instance.getRemainingFormulaListMessage(addEndline);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDisplayMessage method, of class IEntailmentService.
     */
    @Test
    public void testGetDisplayMessage() {
        System.out.println("getDisplayMessage");
        IEntailmentService instance = new IEntailmentServiceImpl();
        String expResult = "";
        String result = instance.getDisplayMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getExplanationMessage method, of class IEntailmentService.
     */
    @Test
    public void testGetExplanationMessage() {
        System.out.println("getExplanationMessage");
        IEntailmentService instance = new IEntailmentServiceImpl();
        String expResult = "";
        String result = instance.getExplanationMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IEntailmentServiceImpl implements IEntailmentService {

        public ValidationResultModel<String> computeEntailment() {
            return null;
        }

        public IKnowledgeBaseService getKnowledgeBaseService() {
            return null;
        }

        public boolean doesKbEntailQuery() {
            return false;
        }

        public int getNumberOfDiscardedRanks() {
            return 0;
        }

        public MinimalRankedFormulaModel getBaseRankingFormulas() {
            return null;
        }

        public EntailmentResultModel getEntailmentResults() {
            return null;
        }

        public String getBaseRankingFormulasMessage() {
            return "";
        }

        public String getDiscardedFormulaListMessage() {
            return "";
        }

        public String getRemainingFormulaListMessage(boolean addEndline) {
            return "";
        }

        public String getDisplayMessage() {
            return "";
        }

        public String getExplanationMessage() {
            return "";
        }
    }
    
}
