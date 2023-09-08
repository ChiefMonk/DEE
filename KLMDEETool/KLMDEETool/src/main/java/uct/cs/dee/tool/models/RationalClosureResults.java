package uct.cs.dee.tool.models;

import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import java.util.ArrayList;
import java.util.List;
import org.tweetyproject.logics.pl.syntax.PlFormula;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class RationalClosureResults 
{
    private final boolean entailment;
    private final int ranksRemoved;
    private final MinimalRankedFormulas rankedFormulas;
    private final PlBeliefSet remainingFormulas;
    
    private List<String> _rankedFormulaList;   
    
    public RationalClosureResults(boolean entailment, int ranksRemoved, MinimalRankedFormulas rankedFormulas, PlBeliefSet remainingFormulas)
    {
        this.entailment = entailment;
        this.ranksRemoved = ranksRemoved;
        this.rankedFormulas = rankedFormulas;
        this.remainingFormulas = remainingFormulas;
        
        _rankedFormulaList = rankedFormulas.getAllFormulasList();       
    }
    
    public boolean entailmentsHolds()
    {
        return this.entailment;
    }
    
    public int getRanksRemoved()
    {
        return this.ranksRemoved;
    }
    
    /**
     *
     * @return
     */
    public MinimalRankedFormulas getMinimalRanking()
    {
        return this.rankedFormulas;
    }
    
    /**
     *
     * @return
     */
    public PlBeliefSet getRemainingFormulas()
    {
        return this.remainingFormulas;
    }
    
    /**
     *
     * @return
     */
    public MinimalRankedFormulas getDiscardedFormulas()
    {
        return this.rankedFormulas;
    }
    
    public List<String> getRemainingFormulaList() {
        List<String> result = new ArrayList<>();
         
        for (int i = getRanksRemoved(); i < _rankedFormulaList.size(); i++)
        {
            result.add(_rankedFormulaList.get(i));                                       
        }
        
        return result;
    }
    
    public List<String> getDiscardedFormulaList() {
        List<String> result = new ArrayList<>();
         
        for (int i = 0; i < getRanksRemoved(); i++)
        {
            result.add(_rankedFormulaList.get(i));                                       
        }
        
        return result;
    }
    
    public String getEntailmentMessage()
    {
         StringBuilder sb = new StringBuilder();   
         sb.append("Does K entail α? : ");
         if(entailmentsHolds())
            sb.append("YES");
         else
            sb.append("NO");    
         
         return sb.toString();
    }
    
    public String getRemainingFormulaListMessage()
    {       
        if(getRemainingFormulaList().isEmpty())
        {
            return "{ empty }";           
        }
        
        StringBuilder stringBuilder = new StringBuilder();
        int counter = 0;
        for (String formula : getRemainingFormulaList())
        {
            stringBuilder.append(formula);
            if(counter < getRemainingFormulaList().size())
                  stringBuilder.append("\n");
            counter++;            
        }
        return stringBuilder.toString();
    }
    
    public String getDiscardedFormulaListMessage()
    {          
        if(getDiscardedFormulaList().isEmpty())
        {
            return "{ empty }"; 
        }
        StringBuilder stringBuilder = new StringBuilder();      
        int counter = 0;
        for (String formula : getDiscardedFormulaList())
        {
            stringBuilder.append(formula);
            if(counter < getDiscardedFormulaList().size())
                 stringBuilder.append("\n");
            counter++;            
        }
        return stringBuilder.toString();
    }
    
    public String getRemainingFormulasMessage()
    {
        StringBuilder sb = new StringBuilder();            
        sb.append(String.format("Remaining formulas in K : %s", getRemainingFormulas()));               
         
         return sb.toString();
    }
    
    
    
    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<< Rational Closure Result - BEGIN>>\n")
                .append("Entailment holds :\t").append(this.entailment).append("\n")
                .append("Ranked removed :\t").append(this.ranksRemoved).append("\n")
                .append("Minimal rank:\n").append(this.rankedFormulas)
                .append("<< Rational Closure Result - END >>");
        return stringBuilder.toString();
    }
    
}
