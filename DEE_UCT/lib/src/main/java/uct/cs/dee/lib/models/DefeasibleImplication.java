/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uct.cs.dee.lib.models;

import java.util.HashSet;
import java.util.Set;
import org.tweetyproject.commons.util.Pair;
import org.tweetyproject.logics.pl.semantics.PossibleWorld;
import org.tweetyproject.logics.pl.syntax.Conjunction;
import org.tweetyproject.logics.pl.syntax.PlFormula;
import org.tweetyproject.logics.pl.syntax.PlPredicate;
import org.tweetyproject.logics.pl.syntax.PlSignature;
import org.tweetyproject.logics.pl.syntax.Proposition;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class DefeasibleImplication extends PlFormula
{
    private Pair<PlFormula, PlFormula> formulas;
    
    public DefeasibleImplication(PlFormula a, PlFormula b)
    {
        this.formulas = new Pair<PlFormula, PlFormula> (a, b); 
    }
    
    public DefeasibleImplication(Pair<PlFormula, PlFormula> formulas)
    {
        this.formulas = formulas;
    }
    
    public Pair<PlFormula, PlFormula> getFormula()
    {
        return this.formulas;
    }
    
    public void setFormulas(Pair<PlFormula, PlFormula> formulas)
    {
        this.formulas = formulas;
    }
    
    public void setFormulas(PlFormula left, PlFormula right)
    {
        this.formulas = new Pair<PlFormula, PlFormula>(left, right);
    }
    
    public void setFirstFormula(PlFormula formula)
    {
        this.formulas.setFirst(formula);
    }
    
    public void setSecondFormula(PlFormula formula)
    {
        this.formulas.setSecond(formula);
    }
            
    public PlFormula getFirstFormula()
    {
        return this.formulas.getFirst();
    }
    
    public PlFormula getSecondFormula()
    {
        return this.formulas.getSecond();
    }
    
    @Override
    public Set<Proposition> getAtoms() 
    {
        Set<Proposition> result = new HashSet<Proposition>();
        result.addAll(this.formulas.getFirst().getAtoms());
        result.addAll(this.formulas.getSecond().getAtoms());
        return result;
    }

    @Override
    public Set<PlFormula> getLiterals() 
    {
        Set<PlFormula> result = new HashSet<PlFormula>();
        result.addAll(this.formulas.getFirst().getLiterals());
        result.addAll(this.formulas.getSecond().getLiterals());
        return result;
    }

    @Override
    public PlFormula collapseAssociativeFormulas() 
    {
        PlFormula left = this.formulas.getFirst().collapseAssociativeFormulas();
        PlFormula right = this.formulas.getSecond().collapseAssociativeFormulas();
        return new DefeasibleImplication(left, right);
    }
    
    @Override
    public Set<PlPredicate> getPredicates() 
    {
        Set<PlPredicate> result = new HashSet<PlPredicate>();
        result.addAll(this.formulas.getFirst().getPredicates());
        result.addAll(this.formulas.getSecond().getPredicates());
        return result;
    }

    @Override
    public PlFormula trim() 
    {
        PlFormula f1 = this.formulas.getFirst().trim();
        PlFormula f2 = this.formulas.getSecond().trim();
        return new DefeasibleImplication(f1, f2);
    }

    @Override
    public PlFormula toNnf() 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Conjunction toCnf() 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<PossibleWorld> getModels(PlSignature ps) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int numberOfOccurrences(Proposition prpstn) 
    {
        int result = 0;
        result += formulas.getFirst().numberOfOccurrences(prpstn);
        result += formulas.getSecond().numberOfOccurrences(prpstn);
        return result;
    }

    @Override
    public PlFormula replace(Proposition prpstn, PlFormula pf, int i) 
    {
        DefeasibleImplication result = this.clone();
        PlFormula left = formulas.getFirst();
        if (left.numberOfOccurrences(prpstn) >= i)
        {
            result.setFirstFormula(left.replace(prpstn, pf, i));
        }
        else
        {
            int num = left.numberOfOccurrences(prpstn);
            PlFormula right = formulas.getSecond();
            if (num + right.numberOfOccurrences(prpstn) >= i)
            {
                result.setSecondFormula(right.replace(prpstn, pf, i-num));
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DefeasibleImplication other = (DefeasibleImplication) obj;
        if (formulas == null)
        {
            if (other.formulas != null)
                return false;
        }
        else if (!formulas.equals(other.formulas))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() 
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((formulas == null) ? 0 : formulas.hashCode());
        return result;
    }

    @Override
    public DefeasibleImplication clone() 
    {
        return new DefeasibleImplication(formulas);
    }
    
    @Override
    public String toString()
    {
        return "(" + this.formulas.getFirst().toString() + DefeasibleLogicSymbol.DEFEASIBLEIMPLICATION() + this.formulas.getSecond().toString() +")";
    }
    
}
