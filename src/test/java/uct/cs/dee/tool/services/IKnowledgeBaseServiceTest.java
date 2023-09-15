/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package uct.cs.dee.tool.services;

import java.util.List;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import org.tweetyproject.logics.pl.syntax.PlFormula;
import uct.cs.dee.tool.models.ValidationResultModel;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class IKnowledgeBaseServiceTest extends TestCase {
    
    private static String _inputKnowledgeBase;
    private static String _inputQuery;
    
    public IKnowledgeBaseServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
         StringBuilder sb = new StringBuilder();
        sb.append("a ~> w\n");
        sb.append( "a ~> h\n" );
        sb.append("p => a\n" );
        sb.append("p ~> !w\n" );
        sb.append("e => p\n" );
        sb.append("e ~> w\n" );
        sb.append("s => e");
        
        _inputKnowledgeBase = sb.toString();        
        _inputQuery = "s ~> w";
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
     * Test of validateQuery method, of class IKnowledgeBaseService.
     */
    @Test
    public void testValidateQuery() {
        System.out.println("validateQuery");       
        IKnowledgeBaseService instance = new IKnowledgeBaseServiceImpl();
        ValidationResultModel<String> expResult = null;
        ValidationResultModel<String> result = instance.validateQuery(_inputQuery);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateKnowledgeBase method, of class IKnowledgeBaseService.
     */
    @Test
    public void testValidateKnowledgeBase_String() {
        System.out.println("validateKnowledgeBase");       
        IKnowledgeBaseService instance = new IKnowledgeBaseServiceImpl();
        ValidationResultModel<String> expResult = null;
        ValidationResultModel<String> result = instance.validateKnowledgeBase(_inputKnowledgeBase);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateKnowledgeBase method, of class IKnowledgeBaseService.
     */
    @Test
    public void testValidateKnowledgeBase_List() {
        System.out.println("validateKnowledgeBase");
        List<String> kbStatements = null;
        IKnowledgeBaseService instance = new IKnowledgeBaseServiceImpl();
        ValidationResultModel<String> expResult = null;
        ValidationResultModel<String> result = instance.validateKnowledgeBase(kbStatements);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateKnowledgeBaseFile method, of class IKnowledgeBaseService.
     */
    @Test
    public void testValidateKnowledgeBaseFile() {
        System.out.println("validateKnowledgeBaseFile");
        String filePath = "kb.txt";
        IKnowledgeBaseService instance = new IKnowledgeBaseServiceImpl();
        ValidationResultModel<String> expResult = null;
        ValidationResultModel<String> result = instance.validateKnowledgeBaseFile(filePath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getKnowledgeBase method, of class IKnowledgeBaseService.
     */
    @Test
    public void testGetKnowledgeBase() {
        System.out.println("getKnowledgeBase");
        IKnowledgeBaseService instance = new IKnowledgeBaseServiceImpl();
        PlBeliefSet expResult = null;
        PlBeliefSet result = instance.getKnowledgeBase();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuery method, of class IKnowledgeBaseService.
     */
    @Test
    public void testGetQuery() {
        System.out.println("getQuery");
        IKnowledgeBaseService instance = new IKnowledgeBaseServiceImpl();
        PlFormula expResult = null;
        PlFormula result = instance.getQuery();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClassicalKbFormulas method, of class IKnowledgeBaseService.
     */
    @Test
    public void testGetClassicalKbFormulas() {
        System.out.println("getClassicalKbFormulas");
        IKnowledgeBaseService instance = new IKnowledgeBaseServiceImpl();
        List<PlFormula> expResult = null;
        List<PlFormula> result = instance.getClassicalKbFormulas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValidatedKnowledgeBaseMessage method, of class IKnowledgeBaseService.
     */
    @Test
    public void testGetValidatedKnowledgeBaseMessage() {
        System.out.println("getValidatedKnowledgeBaseMessage");
        IKnowledgeBaseService instance = new IKnowledgeBaseServiceImpl();
        String expResult = "";
        String result = instance.getValidatedKnowledgeBaseMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDisplayMessage method, of class IKnowledgeBaseService.
     */
    @Test
    public void testGetDisplayMessage() {
        System.out.println("getDisplayMessage");
        IKnowledgeBaseService instance = new IKnowledgeBaseServiceImpl();
        String expResult = "";
        String result = instance.getDisplayMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getExplanationMessage method, of class IKnowledgeBaseService.
     */
    @Test
    public void testGetExplanationMessage() {
        System.out.println("getExplanationMessage");
        IKnowledgeBaseService instance = new IKnowledgeBaseServiceImpl();
        String expResult = "";
        String result = instance.getExplanationMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IKnowledgeBaseServiceImpl implements IKnowledgeBaseService {

        public ValidationResultModel<String> validateQuery(String inputQuery) {
            return null;
        }

        public ValidationResultModel<String> validateKnowledgeBase(String kbString) {
            return null;
        }

        public ValidationResultModel<String> validateKnowledgeBase(List<String> kbStatements) {
            return null;
        }

        public ValidationResultModel<String> validateKnowledgeBaseFile(String filePath) {
            return null;
        }

        public PlBeliefSet getKnowledgeBase() {
            return null;
        }

        public PlFormula getQuery() {
            return null;
        }

        public List<PlFormula> getClassicalKbFormulas() {
            return null;
        }

        public String getValidatedKnowledgeBaseMessage() {
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
