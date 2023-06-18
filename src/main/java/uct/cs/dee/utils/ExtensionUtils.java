package uct.cs.dee.utils;

import org.tweetyproject.commons.ParserException;
import org.tweetyproject.logics.pl.syntax.Contradiction;
import org.tweetyproject.logics.pl.syntax.Implication;
import org.tweetyproject.logics.pl.syntax.Negation;
import org.tweetyproject.logics.pl.syntax.PlFormula;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;

@Extension
public class ExtensionUtils 
{		
	public static boolean isEmptyOrNull(@This String inpuString)
	{
		if(inpuString != null && !inpuString.trim().isEmpty())
		{
			return false;
		}
		
		return true;
	}
	
	public static boolean isEmptyOrNullWithThrow(@This String inpuString) throws ParserException 
	{
		if(isEmptyOrNull(inpuString))
		{
			throw new ParserException(Constants.ErrorMessages.InValidFormula);
		}
		
		return true;
	}
	
	/**
     * Determines whether a string formula is defeasible or not
     * @param formula
     * @return boolean indicating whether a formula is defeasible (true) or not (false)
	 * @throws Exception 
     */
	public static boolean isDefeasible(@This String formula) throws ParserException 
	{
		if(ExtensionUtils.isEmptyOrNull(formula))
		{
			throw new ParserException(Constants.ErrorMessages.InValidFormula);
		}
		
		return formula.contains(Constants.Symbols.Twiddle);		
	}
	
	 /**
     * Materialises given defeasible implication (changes twiddle to material implication)
     * 
     * @param defeasibleFormula
     * @return The materialized implication
     */
	public static String materialiseDefeasibleImplication(@This String defeasibleFormula) throws ParserException 
	{
		if(ExtensionUtils.isEmptyOrNull(defeasibleFormula))
		{
			throw new ParserException(Constants.ErrorMessages.InValidFormula);
		}
		
		return defeasibleFormula.replace(Constants.Symbols.Twiddle, Constants.Symbols.PropositionalImplication);
	}
	
	/**
     * Normalises a purely classical propositional as a defeasible implication
     * @param formula - classical propositional formula
     * @return defeasible implication
     */
    public static Implication normalizePropositionalFormula(PlFormula formula) throws ParserException 
    {
    	if(formula == null)
		{
			throw new ParserException(Constants.ErrorMessages.InValidFormula);
		}
    	
        return new Implication(new Negation(formula), new Contradiction());
    }
}
