package uct.cs.dee.tool.helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import uct.cs.dee.tool.models.*;
import org.tweetyproject.logics.pl.syntax.Implication;
import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import org.tweetyproject.logics.pl.syntax.PlFormula;
import org.tweetyproject.logics.pl.syntax.Proposition;

/**
 * <h1>UtilsHelper<\h1>
 * The Utils Helper.
 * 
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 * @version 1.0.1
 * @since 2023-07-03
 */
public class UtilsHelper 
{

    /**
     *
     * @param list1
     * @param list2
     * @return
     */
    public static List<PlFormula> union(List<PlFormula> list1, List<PlFormula> list2)
    {
        if (list1 == null || list1.isEmpty())
            return list2;
        
        if (list2 == null || list2.isEmpty())
            return list1;
        
        Set<PlFormula> set = new HashSet<>();
        set.addAll(list1);
        set.addAll(list2);
        return new ArrayList<>(set);
    }
    
    /**
     * materialise formulas
     * 
     * @param formulaList
     * @return PlFormula List
     * @throws Exception
     */
    public static List<PlFormula> materialise(List<PlFormula> formulaList) throws Exception
    {
        List<PlFormula> materialised = new ArrayList<>();
        for (PlFormula formula : formulaList)
        {
            materialised.add(materialise(formula));
        }
        return materialised;
    }
    
    /**
     * materialise formulas
     * 
     * @param knowledgeBase
     * @return PlBeliefSet    
     */
    public static PlBeliefSet materialise(PlBeliefSet knowledgeBase)
    {
        PlBeliefSet result = new PlBeliefSet();
        
        for (PlFormula formula : knowledgeBase)
            result.add(materialise(formula));
        
        return result;
        
    }
    
     /**
     * materialise formulas
     * 
     * @param formula
     * @return PlFormula    
     */
    public static PlFormula materialise(PlFormula formula)
    {
        if (formula instanceof DefeasibleImplicationModel)
        {
            Implication implication = new Implication(((DefeasibleImplicationModel) formula).getFormula());
            return implication;
        }
        else
        {
            return formula;
        }
    }
    
      /**
     * dematerialise formulas
     * 
     * @param justification
     * @param classicalFormulas
     * @return PlFormula List    
     */
    public static List<PlFormula> dematerialise(List<PlFormula> justification, List<PlFormula> classicalFormulas )
    {
        List<PlFormula> result = new ArrayList<>();
        
        for( PlFormula formula : justification)
        {
            if (!classicalFormulas.contains(formula) && formula instanceof Implication)
            {
                PlFormula dematerialisedFormula = new DefeasibleImplicationModel(((Implication)formula).getFirstFormula(), ((Implication)formula).getSecondFormula());
                result.add(dematerialisedFormula);
            }
            else
            {
                result.add(formula);
            }
        }
        return result;
    }
    
     /**
     * get Classical Formulas
     * 
     * @param knowledgeBase
     * @return PlFormula List    
     */
    public static List<PlFormula> getClassicalFormulas(PlBeliefSet knowledgeBase)
    {
        List<PlFormula> classicalFormulas = new ArrayList<>();
        for (PlFormula plFormula : knowledgeBase)
        {
            if (!(plFormula instanceof DefeasibleImplicationModel))
                classicalFormulas.add(plFormula);
        }
        return classicalFormulas;
    }
    
     /**
     * remove formula from KnowledgeBase
     * 
     * @param knowledgeBase
     * @param formulas
     * @return PlBeliefSet
     */
    public static PlBeliefSet remove(PlBeliefSet knowledgeBase, List<PlFormula> formulas)
    {
        knowledgeBase.removeAll(formulas);
        return knowledgeBase;
    }
    
      /**
     * remove formula from KnowledgeBase
     * 
     * @param knowledgeBase
     * @param formulas
     * @return PlBeliefSet
     */
    public static PlBeliefSet remove(PlBeliefSet knowledgeBase, PlFormula formulas)
    {
        PlBeliefSet newKB = clone(knowledgeBase);
        newKB.remove(formulas);
        return newKB;
    }
    
     /**
     * clone KnowledgeBase
     * 
     * @param knowledgeBase
     * @return PlBeliefSet
     */
    public static PlBeliefSet clone (PlBeliefSet knowledgeBase)
    {
        PlBeliefSet newKB = new PlBeliefSet();
        for (PlFormula formula : knowledgeBase)
        {
            newKB.add(formula);
        }
        return newKB;
    }
    
     /**
     * remove Formula from BnowledgeBase
     * 
     * @param knowledgeBase
     * @param formula
     * @return PlFormula List    
     */
    public static List<PlFormula> remove(List<PlFormula> knowledgeBase, PlFormula formula)
    {
        knowledgeBase.remove(formula);
        return knowledgeBase;
    }
    
     /**
     * print formula list
     * 
     * @param list    
     */
    public static void print(List<PlFormula> list)
    {
        if (list == null || list.isEmpty())
            System.out.println("null");
        for(var plFormula: list)
            System.out.println(plFormula);
    }
    
     /**
     * print Propositions
     * 
     * @param list    
     */
    public static void printPropositions(List<Proposition> list)
    {
        if (list == null || list.isEmpty())
            System.out.println("null");
        for(var proposition: list)
            System.out.println(proposition);
    }
    
     /**
     * print Justifications
     * 
     * @param justifications
     */
    public static void printJustifications(List<List<PlFormula>> justifications)
    {
        System.out.println("Print Justifications: \n");
        int i = 0;
        for (List<PlFormula> justification : justifications)
        {
            String s = "\t: " + printJustificationAsCSV(justification) + "\n";
            System.out.println(s);
            i++;
        }
    }
    
     /**
     * print Justification AsCSV
     * 
     * @param justification
     * @return String
     */
    public static String printJustificationAsCSV(List<PlFormula> justification)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (PlFormula formula : justification)
        {
            stringBuilder.append(formula).append(", ");
        }
        String result = stringBuilder.toString();
        if (result != null && !result.isBlank() && result.length() > 2)
            return result.substring(0, result.length()-2);
        else
            return "empty";
    }
    
     /**
     * get justification String
     * 
     * @param formulas
     * @return String
     */
    public static String justificationToString(List<PlFormula> formulas)
    {
        if (formulas == null || formulas.isEmpty())
            return "No Justification";
        
        StringBuilder stringBuilder = new StringBuilder("{");
        for (PlFormula formula : formulas)
        {
            stringBuilder.append(formula)
                    .append(", ");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
    
     /**
     * compare Formula List
     * @param left
     * @param right
     * @return Boolean    
     */
    public static Boolean compareFormulaList(List<PlFormula> left, List<PlFormula> right) {
        for (PlFormula formula : left) {
            if (!right.contains(formula)) {
                return false;
            }              
        }
        
        for (PlFormula formula : right) {
            if (!left.contains(formula)) {
                return false;
            }            
        }
        
        return true;
    }   
}
