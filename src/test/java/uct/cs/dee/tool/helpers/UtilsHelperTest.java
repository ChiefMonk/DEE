/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package uct.cs.dee.tool.helpers;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import org.tweetyproject.logics.pl.syntax.PlFormula;
import org.tweetyproject.logics.pl.syntax.Proposition;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class UtilsHelperTest {
    
    public UtilsHelperTest() {
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
     * Test of union method, of class UtilsHelper.
     */
    @Test
    public void testUnion() {
        System.out.println("union");
        List<PlFormula> list1 = null;
        List<PlFormula> list2 = null;
        List<PlFormula> expResult = null;
        List<PlFormula> result = UtilsHelper.union(list1, list2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of materialise method, of class UtilsHelper.
     * @throws java.lang.Exception
     */
    @Test
    public void testMaterialise_List() throws Exception {
        System.out.println("materialise");
        List<PlFormula> formulaList = null;
        List<PlFormula> expResult = null;
        List<PlFormula> result = UtilsHelper.materialise(formulaList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of materialise method, of class UtilsHelper.   
     */
    @Test
    public void testMaterialise_PlBeliefSet() {
        System.out.println("materialise");
        PlBeliefSet knowledgeBase = null;
        PlBeliefSet expResult = null;
        PlBeliefSet result = UtilsHelper.materialise(knowledgeBase);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of materialise method, of class UtilsHelper.
     */
    @Test
    public void testMaterialise_PlFormula() {
        System.out.println("materialise");
        PlFormula formula = null;
        PlFormula expResult = null;
        PlFormula result = UtilsHelper.materialise(formula);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dematerialise method, of class UtilsHelper.
     */
    @Test
    public void testDematerialise() {
        System.out.println("dematerialise");
        List<PlFormula> justification = null;
        List<PlFormula> classicalFormulas = null;
        List<PlFormula> expResult = null;
        List<PlFormula> result = UtilsHelper.dematerialise(justification, classicalFormulas);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClassicalFormulas method, of class UtilsHelper.
     */
    @Test
    public void testGetClassicalFormulas() {
        System.out.println("getClassicalFormulas");
        PlBeliefSet knowledgeBase = null;
        List<PlFormula> expResult = null;
        List<PlFormula> result = UtilsHelper.getClassicalFormulas(knowledgeBase);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class UtilsHelper.
     */
    @Test
    public void testRemove_PlBeliefSet_List() {
        System.out.println("remove");
        PlBeliefSet knowledgeBase = null;
        List<PlFormula> formulas = null;
        PlBeliefSet expResult = null;
        PlBeliefSet result = UtilsHelper.remove(knowledgeBase, formulas);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class UtilsHelper.
     */
    @Test
    public void testRemove_PlBeliefSet_PlFormula() {
        System.out.println("remove");
        PlBeliefSet knowledgeBase = null;
        PlFormula formulas = null;
        PlBeliefSet expResult = null;
        PlBeliefSet result = UtilsHelper.remove(knowledgeBase, formulas);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class UtilsHelper.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        PlBeliefSet knowledgeBase = null;
        PlBeliefSet expResult = null;
        PlBeliefSet result = UtilsHelper.clone(knowledgeBase);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class UtilsHelper.
     */
    @Test
    public void testRemove_List_PlFormula() {
        System.out.println("remove");
        List<PlFormula> knowledgeBase = null;
        PlFormula formula = null;
        List<PlFormula> expResult = null;
        List<PlFormula> result = UtilsHelper.remove(knowledgeBase, formula);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of print method, of class UtilsHelper.
     */
    @Test
    public void testPrint() {
        System.out.println("print");
        List<PlFormula> list = null;
        UtilsHelper.print(list);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printPropositions method, of class UtilsHelper.
     */
    @Test
    public void testPrintPropositions() {
        System.out.println("printPropositions");
        List<Proposition> list = null;
        UtilsHelper.printPropositions(list);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printJustifications method, of class UtilsHelper.
     */
    @Test
    public void testprintJustifications() {
        System.out.println("printJustifiactions");
        List<List<PlFormula>> justifications = null;
        UtilsHelper.printJustifications(justifications);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printJustificationAsCSV method, of class UtilsHelper.
     */
    @Test
    public void testPrintJustificationAsCSV() {
        System.out.println("printJustificationAsCSV");
        List<PlFormula> justification = null;
        String expResult = "";
        String result = UtilsHelper.printJustificationAsCSV(justification);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of justificationToString method, of class UtilsHelper.
     */
    @Test
    public void testJustificationToString() {
        System.out.println("justificationToString");
        List<PlFormula> formulas = null;
        String expResult = "";
        String result = UtilsHelper.justificationToString(formulas);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareFormulaList method, of class UtilsHelper.
     */
    @Test
    public void testCompareFormulaList() {
        System.out.println("compareFormulaList");
        List<PlFormula> left = null;
        List<PlFormula> right = null;
        Boolean expResult = null;
        Boolean result = UtilsHelper.compareFormulaList(left, right);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
