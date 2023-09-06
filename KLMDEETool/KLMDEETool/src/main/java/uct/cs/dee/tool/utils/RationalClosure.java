package uct.cs.dee.tool.utils;

import java.util.ArrayList;
import java.util.List;
import uct.cs.dee.tool.models.*;
import org.tweetyproject.logics.pl.reasoner.SatReasoner;
import org.tweetyproject.logics.pl.sat.Sat4jSolver;
import org.tweetyproject.logics.pl.sat.SatSolver;
import org.tweetyproject.logics.pl.syntax.Negation;
import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import org.tweetyproject.logics.pl.syntax.PlFormula;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class RationalClosure 
{
    
    public static RationalClosureResults computeRationalClosure(PlBeliefSet knowledgeBase, PlFormula query ) throws Exception
    {
        SatSolver.setDefaultSolver(new Sat4jSolver());
        SatReasoner reasoner = new SatReasoner();
        
        MinimalRankedFormulas minimalRankedFormulas = computeMinimalRanking(knowledgeBase, reasoner);
        System.out.println("=====Minimal Ranked Formulas=====");
        System.out.println(minimalRankedFormulas);
        
        PlBeliefSet R = new PlBeliefSet(Utils.materialise(minimalRankedFormulas.getAllFormulas()));
        System.out.println("=====R=====");
        System.out.println(R);
        
        int i = 0;
        
        while(reasoner.query(R, new Negation(((DefeasibleImplication) query).getFirstFormula())) && !R.isEmpty())
        {
            R = removeRankedFormulas(minimalRankedFormulas.getFinitlyRankedFormula(i), R);
            i ++;
        }
        
        return new RationalClosureResults(reasoner.query(R, Utils.materialise((DefeasibleImplication)query)), i, minimalRankedFormulas, R);
    }
    
    private static MinimalRankedFormulas computeMinimalRanking(PlBeliefSet knowledgeBase, SatReasoner reasoner) throws Exception
    {
        
        List<PlFormula> classicalFormulas = Utils.getClassicalFormulas(knowledgeBase);
        //System.out.println("=====Classical Formulas=====");
        //Utils.print(classicalFormulas);
        
        List<PlFormula> currentFormulas = getDefeasibleFormulas(knowledgeBase, classicalFormulas);
        List<PlFormula> prevFormulas = new ArrayList<PlFormula>();
        List<List<PlFormula>> rankedFormulas = new ArrayList<List<PlFormula>>();
        int i = 0;
        
        while(!prevFormulas.equals(currentFormulas))
        {
            //System.out.println("=====E_"+i+"=====");
            //Utils.print(currentFormulas);
            prevFormulas = currentFormulas;
            currentFormulas = computeExceptionalFormulas(currentFormulas, classicalFormulas, reasoner);
            
            //System.out.println("=====R_"+i+"=====");
            List<PlFormula> currentRank = remove(prevFormulas, currentFormulas);
            //Utils.print(currentRank);
            rankedFormulas.add(currentRank);
            //Utils.print(currentRank);
            i++;
        }
        
        List<PlFormula> infinitRank = rankedFormulas.remove(i-1);
        MinimalRankedFormulas result = new MinimalRankedFormulas(classicalFormulas, rankedFormulas);
        result.addInfinitlyRankedFormula(infinitRank);
        
        return result;
    }
    
    // This method compute the following set: E_(i+1) := { alpha => beta in E_{i} | materialsied(E_{i}) Union C \entails not alpha }
    private static List<PlFormula> computeExceptionalFormulas(List<PlFormula> formulaList, List<PlFormula> classicalFormulas, SatReasoner reasoner ) throws Exception
    {
        List<PlFormula> exceptionals = new ArrayList<PlFormula>();
        List<PlFormula> materialised = Utils.materialise(formulaList);
        List<PlFormula> unionedList = Utils.union(materialised, classicalFormulas);
        PlBeliefSet beliefSet = new PlBeliefSet(unionedList);
        
        
        for (PlFormula formula : formulaList)
        {
            if (formula instanceof DefeasibleImplication)
            {
                PlFormula alpha = ((DefeasibleImplication) formula).getFirstFormula();
                if (reasoner.query(beliefSet, new Negation(alpha)))
                {
                    exceptionals.add(formula);
                }
            }
            else
            {
                throw new Exception("Only defeasible implications can be ranked");
            }
        }
        return exceptionals;
    }
    
    // This method computes the following set: R_{i} := E_{i} \ E_{i+1}
    private static List<PlFormula> remove(List<PlFormula> from, List<PlFormula> removeFormulas) throws Exception
    {
        from.removeAll(removeFormulas);
        return from;
    }
    
    private static PlBeliefSet removeRankedFormulas(List<PlFormula> removeList, PlBeliefSet R) throws Exception
    {
        R.removeAll(Utils.materialise(removeList));
        return R;
    }
    
    private static List<PlFormula> getDefeasibleFormulas(PlBeliefSet knowledgeBase, List<PlFormula> classicalFormulas)
    {
        List<PlFormula> defeasibleFormulas = new ArrayList<PlFormula>();
        for (PlFormula plFormula: knowledgeBase)
        {
            if (!classicalFormulas.contains(plFormula))
                defeasibleFormulas.add(plFormula);
        }
        return defeasibleFormulas;
    }
    
    private static Boolean equals(PlFormula x, PlFormula y) throws Exception
    {
        if (!(x instanceof DefeasibleImplication ) || !(y instanceof DefeasibleImplication) )
            throw new Exception("This equals method only compared defeasible implications");
        
        if (((DefeasibleImplication)x).getFormula().equals(((DefeasibleImplication)y).getFormula()))
            return true;
        else
            return false;
        
    }
}
