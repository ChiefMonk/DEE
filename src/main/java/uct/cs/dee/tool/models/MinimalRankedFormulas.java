package uct.cs.dee.tool.models;

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
            formulaList = new ArrayList<>();
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
            this.finitlyRankedFormulas.set(rank, new ArrayList<>());
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
        List<PlFormula> result = new ArrayList<>();
        for(List<PlFormula> rankedFormulas: this.finitlyRankedFormulas)
        {
            result.addAll(rankedFormulas);
        }
        return result;
    }
    
    public List<PlFormula> getAllFormulas()
    {
        List<PlFormula> result = new ArrayList<>();
        result.addAll(getFinitlyRankedFormulas());
        result.addAll(this.infinitlyRankedFormulas);
        return result;
    }
    
    private String formatFormulaList(List<PlFormula> formulaList)
    {
        StringBuilder stringBuilder = new StringBuilder();
        int counter = 1;
        for (PlFormula formula : formulaList)
        {
            stringBuilder.append(formula);
            if(counter < formulaList.size())
                 stringBuilder.append(", ");
            counter++;            
        }
        return stringBuilder.toString();
    }
    
    public List<String> getAllFormulasList()
    {
        List<String> result = new ArrayList<>();
        
       if(this.finitlyRankedFormulas.isEmpty()) {
            result.add(String.format("Rank 0 : { empty }"));
        }
         else {
            for (int i = 0; i < this.finitlyRankedFormulas.size(); i++)
            {
                 result.add(String.format("Rank %s : { %s }", i,this.finitlyRankedFormulas.get(i).toString().replace("[", "").replace("]", "")));                                 
            }
        }
        
        if(this.infinitlyRankedFormulas.isEmpty()) {
            result.add(String.format("Rank ∞ : { empty }"));          
        }
        else {
             result.add(String.format("Rank ∞ : { %s }", this.infinitlyRankedFormulas.toString().replace("[", "").replace("]", "")));                              
        }
        return result;
    }
    
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();        
        
        if(this.finitlyRankedFormulas.isEmpty()) {
            stringBuilder.append("Rank 0").append(" : { empty }\n");
        }
         else {
            for (int i = 0; i < this.finitlyRankedFormulas.size(); i++)
            {
                stringBuilder.append("Rank ").append(i).append(" : { ")                   
                        .append(formatFormulaList(this.finitlyRankedFormulas.get(i)))
                        .append(" }\n");
            }
        }
        
        if(this.infinitlyRankedFormulas.isEmpty()) {
            stringBuilder.append("Rank ∞").append(" : { empty }\n");
        }
        else {
            stringBuilder.append("Rank ∞").append(" : {  ")                       
                .append(formatFormulaList(this.infinitlyRankedFormulas))
                .append(" }\n");
        }
            
        
        return stringBuilder.toString();
    }
    
    public String toString2()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("R_{INFINITY}")
                .append("\t|")
                .append(formatFormulaList(this.infinitlyRankedFormulas))
                .append("\n");
        
        for (int i = this.finitlyRankedFormulas.size()-1; i >= 0; i --)
        {
            stringBuilder.append("R_{").append(i).append("}")
                    .append("\t\t|")
                    .append(formatFormulaList(this.finitlyRankedFormulas.get(i)))
                    .append("\n");
        }
        
        return stringBuilder.toString();
    }
}
