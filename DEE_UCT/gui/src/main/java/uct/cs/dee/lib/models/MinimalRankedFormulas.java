package uct.cs.dee.lib.models;

import java.util.ArrayList;
import java.util.List;
import org.tweetyproject.logics.pl.syntax.PlFormula;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class MinimalRankedFormulas 
{
    List<List<PlFormula>> finitlyRankedFormulas = new ArrayList<>();
    List<PlFormula> infinitlyRankedFormulas = new ArrayList<>();
    
    public MinimalRankedFormulas()
    {
        this.finitlyRankedFormulas = new ArrayList<>();
        this.infinitlyRankedFormulas = new ArrayList<>();
    }
    
    public MinimalRankedFormulas(List<PlFormula> classicalFormulas)
    {
        this.finitlyRankedFormulas = new ArrayList<>();
        this.infinitlyRankedFormulas = classicalFormulas;
    }
    
    public MinimalRankedFormulas(List<PlFormula> classicalFormulas, List<List<PlFormula>> rankedFormulas)
    {
        this.finitlyRankedFormulas = rankedFormulas;
        this.infinitlyRankedFormulas = classicalFormulas;
    }
    
    public void addFinitlyRankedFormula(int rank, PlFormula formula)
    {
        List<PlFormula> formulaList = finitlyRankedFormulas.get(rank);
        
        if (formulaList == null)
        {
            formulaList = new ArrayList<PlFormula>();
            formulaList.add(formula);
            this.finitlyRankedFormulas.add(rank, formulaList);
        }
        else
        {
            formulaList.add(formula);
            this.finitlyRankedFormulas.add(rank, formulaList);
        }
    }
    
    public void addFinitlyRankedFormula(int rank, List<PlFormula> formulas)
    {
        if (rank > this.finitlyRankedFormulas.size())
        {
            this.finitlyRankedFormulas.set(rank, new ArrayList<PlFormula>());
        }
        
        List<PlFormula> formulaList = finitlyRankedFormulas.get(rank);
        
        if (formulaList == null)
        {
            this.finitlyRankedFormulas.add(rank, formulas);
        }
        else
        {
            formulaList.addAll(formulas);
            this.finitlyRankedFormulas.add(rank, formulaList);
        }
    }
    
    public void addInfinitlyRankedFormula(PlFormula formula)
    {
        this.infinitlyRankedFormulas.add(formula);
    }
    
    public void addInfinitlyRankedFormula(List<PlFormula> formulas)
    {
        this.infinitlyRankedFormulas.addAll(formulas);
    }
    
    public List<PlFormula> getFinitlyRankedFormula(int rank)
    {
        return this.finitlyRankedFormulas.get(rank);
    }
    
    public List<PlFormula> getInfinitlyRankedFromula()
    {
        return this.infinitlyRankedFormulas;
    }
    
    public List<PlFormula> getFinitlyRankedFormulas()
    {
        List<PlFormula> result = new ArrayList<PlFormula>();
        for(List<PlFormula> rankedFormulas: this.finitlyRankedFormulas)
        {
            result.addAll(rankedFormulas);
        }
        return result;
    }
    
    public List<PlFormula> getAllFormulas()
    {
        List<PlFormula> result = new ArrayList<PlFormula>();
        result.addAll(getFinitlyRankedFormulas());
        result.addAll(this.infinitlyRankedFormulas);
        return result;
    }
    
    private String formatFormulaList(List<PlFormula> formulaList)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (PlFormula formula : formulaList)
        {
            stringBuilder.append(formula).append(",");
        }
        return stringBuilder.substring(0, stringBuilder.length()-1);
    }
    
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("R_{INFINITY}")
                .append("\t|")
                .append(formatFormulaList(this.infinitlyRankedFormulas))
                .append("\n");
        
        for (int i = this.finitlyRankedFormulas.size()-1; i >= 0; i --)
        {
            stringBuilder.append("R_{" + i + "}")
                    .append("\t\t|")
                    .append(formatFormulaList(this.finitlyRankedFormulas.get(i)))
                    .append("\n");
        }
        
        return stringBuilder.toString();
    }
}
