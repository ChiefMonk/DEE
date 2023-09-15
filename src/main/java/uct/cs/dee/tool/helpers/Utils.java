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
 * <h1>IExplanationService<\h1>
 * The IExplanationService interface has methods that should be implemented for a full entailment explanation.
 * 
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 * @version 1.0.1
 * @since 2023-07-03
 */
public class Utils 
{
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
    
    public static List<PlFormula> materialise(List<PlFormula> formulaList) throws Exception
    {
        List<PlFormula> materialised = new ArrayList<>();
        for (PlFormula formula : formulaList)
        {
            materialised.add(materialise(formula));
        }
        return materialised;
    }
    
    public static PlBeliefSet materialise(PlBeliefSet knowledgeBase)
    {
        PlBeliefSet result = new PlBeliefSet();
        
        for (PlFormula formula : knowledgeBase)
            result.add(materialise(formula));
        
        return result;
        
    }
    
    public static PlFormula materialise(PlFormula formula)
    {
        if (formula instanceof DefeasibleImplication)
        {
            Implication implication = new Implication(((DefeasibleImplication) formula).getFormula());
            return implication;
        }
        else
        {
            return formula;
        }
    }
    
    public static List<PlFormula> dematerialise(List<PlFormula> justification, List<PlFormula> classicalFormulas )
    {
        List<PlFormula> result = new ArrayList<>();
        
        for( PlFormula formula : justification)
        {
            if (!classicalFormulas.contains(formula) && formula instanceof Implication)
            {
                PlFormula dematerialisedFormula = new DefeasibleImplication(((Implication)formula).getFirstFormula(), ((Implication)formula).getSecondFormula());
                result.add(dematerialisedFormula);
            }
            else
            {
                result.add(formula);
            }
        }
        return result;
    }
    
    public static List<PlFormula> getClassicalFormulas(PlBeliefSet knowledgeBase)
    {
        List<PlFormula> classicalFormulas = new ArrayList<>();
        for (PlFormula plFormula : knowledgeBase)
        {
            if (!(plFormula instanceof DefeasibleImplication))
                classicalFormulas.add(plFormula);
        }
        return classicalFormulas;
    }
    
    public static PlBeliefSet remove(PlBeliefSet knowledgeBase, List<PlFormula> formulas)
    {
        knowledgeBase.removeAll(formulas);
        return knowledgeBase;
    }
    
    public static PlBeliefSet remove(PlBeliefSet knowledgeBase, PlFormula formulas)
    {
        PlBeliefSet newKB = clone(knowledgeBase);
        newKB.remove(formulas);
        return newKB;
    }
    
    public static PlBeliefSet clone (PlBeliefSet knowledgeBase)
    {
        PlBeliefSet newKB = new PlBeliefSet();
        for (PlFormula formula : knowledgeBase)
        {
            newKB.add(formula);
        }
        return newKB;
    }
    
    public static List<PlFormula> remove(List<PlFormula> knowledgeBase, PlFormula formula)
    {
        knowledgeBase.remove(formula);
        return knowledgeBase;
    }
    
    public static void print(List<PlFormula> list)
    {
        if (list == null || list.isEmpty())
            System.out.println("null");
        for(var plFormula: list)
            System.out.println(plFormula);
    }
    
    public static void printPropositions(List<Proposition> list)
    {
        if (list == null || list.isEmpty())
            System.out.println("null");
        for(var proposition: list)
            System.out.println(proposition);
    }
    
    public static void printJustifiactions(List<List<PlFormula>> justifications)
    {
        System.out.println("Print Justifications: \n");
        int i = 0;
        for (List<PlFormula> justification : justifications)
        {
            StringBuilder stringBuilder = new StringBuilder(i)
                    .append("\t: ")
                    .append(printJustificationAsCSV(justification)).append("\n");
            System.out.println(stringBuilder.toString());
            i++;
        }
    }
    
    public static String printJustificationAsCSV(List<PlFormula> justification)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (PlFormula formula : justification)
        {
            stringBuilder.append(formula).append(", ");
        }
        String result = stringBuilder.toString();
        if (result != null && result != "" && result.length() > 2)
            return result.substring(0, result.length()-2);
        else
            return "empty";
    }
    
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
