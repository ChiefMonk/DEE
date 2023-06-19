package uct.cs.dee.utils;

import java.security.InvalidParameterException;

import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import org.tweetyproject.logics.pl.syntax.PlFormula;

import uct.cs.dee.enums.FormularType;

public class KnowledgeBaseFormulaSet 
{
	 private PlBeliefSet _defeasibleFormulas;
	 private PlBeliefSet _propositionalFormulas;
	 
	 	/**
	    * Default constructor
	    */
	    public KnowledgeBaseFormulaSet() 
	    {
	    	_propositionalFormulas = new PlBeliefSet();
	    	_defeasibleFormulas = new PlBeliefSet();	    	
	    }
	    
	    /**
	     * Parameterized constructor
	     * 
	     * @param propositionalFormulas The propositional formulas
	     * @param defeasibleFormulas The defeasible formulas	    
	     */
	    public KnowledgeBaseFormulaSet(KnowledgeBaseFormulaSet formulaSet) 
	    {
	    	if(formulaSet == null)
	    		return;
	    		    	    
	        _propositionalFormulas = formulaSet.getKnowledgeBaseCopy(FormularType.Propositional);
	        _defeasibleFormulas = formulaSet.getKnowledgeBaseCopy(FormularType.Defeasible);
	    }
	    
	    /**
	     * Parameterized constructor
	     * 
	     * @param propositionalFormulas The propositional formulas
	     * @param defeasibleFormulas The defeasible formulas	    
	     */
	    public KnowledgeBaseFormulaSet (PlBeliefSet propositionalFormulas, PlBeliefSet defeasibleFormulas) 
	    {
	        _propositionalFormulas = propositionalFormulas;
	        _defeasibleFormulas = defeasibleFormulas;
	    }
	    
	    /**
	     * Add a propositional or defeasible formula
	     * 
	     * @param formularType The formular type
	     * @param formula The propositional or defeasible formula
	     */
	    public void addFormula(FormularType formularType, PlFormula formula) throws InvalidParameterException
	    {
	    	System.out.println(String.format("FormularType: %s, PlFormula: %s", formularType, formula));
	    	
	    	if(formularType == FormularType.Propositional)
	    	{	    		
	    		_propositionalFormulas.add(formula);	    	
	    	}	    	
	    	else if (formularType == FormularType.Defeasible)
	    	{
	    		_defeasibleFormulas.add(formula);
	    	}
	    	else
	    	{
	    		throw new InvalidParameterException(Constants.ErrorMessages.InValidFormularType + ": '" + formularType + "'");
	    	}
	    		    
	    }
	    
	    /**
	     * Get copy of the propositional or defeasible knowledge base
	     * 
	     * @return The defeasible knowledge
	     */
	    public PlBeliefSet getKnowledgeBaseCopy(FormularType formularType) 
	    {
	    	if(formularType == FormularType.Propositional)
	    		return new PlBeliefSet(_propositionalFormulas);
	    	
	    	if(formularType == FormularType.Defeasible)
	    		return new PlBeliefSet(_defeasibleFormulas);
	    	
	    	throw new InvalidParameterException(Constants.ErrorMessages.InValidFormularType + ": '" + formularType + "'");
	    }
	    
	    
	    /**
	     * Get union of propositional and materialized defeasible knowledge
	     * 
	     * @param formulaSet The Formula Set
	     * @return The union of the knowledge
	     */
	    public static PlBeliefSet union(KnowledgeBaseFormulaSet formulaSet) 
	    {
	    	if(formulaSet == null)
	    		return new PlBeliefSet();
	    	
	    	 return union(formulaSet.getKnowledgeBaseCopy(FormularType.Propositional), formulaSet.getKnowledgeBaseCopy(FormularType.Defeasible));
	    }
	    

	    /**
	     * Get the union of two formula sets
	     * 
	     * @param a The first set of formulas
	     * @param b The second set of formulas
	     * @return The union
	     */
	    public static PlBeliefSet union(PlBeliefSet a, PlBeliefSet b) 
	    {
	        PlBeliefSet temp = new PlBeliefSet();
	        
	        temp.addAll(a);
	        temp.addAll(b);
	        
	        return temp;
	    }
	    
	    /**
	     * Overridden toString method
	     */
	    @Override
	    public String toString() {
	        return "=> : " + _propositionalFormulas + "\n" +
	                "~> : " + _defeasibleFormulas;
	    }
}
