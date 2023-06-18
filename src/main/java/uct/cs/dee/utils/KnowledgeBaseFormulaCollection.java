package uct.cs.dee.utils;

import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import org.tweetyproject.logics.pl.syntax.PlFormula;

public class KnowledgeBaseFormulaCollection 
{
	 private PlBeliefSet _defeasibleFormulas;
	 private PlBeliefSet _propositionalFormulas;
	 
	 	/**
	    * Default constructor
	    */
	    public KnowledgeBaseFormulaCollection() 
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
	    public KnowledgeBaseFormulaCollection(PlBeliefSet propositionalFormulas, PlBeliefSet defeasibleFormulas) 
	    {
	        _propositionalFormulas = propositionalFormulas;
	        _defeasibleFormulas = defeasibleFormulas;
	    }
	    
	    /**
	     * Add a propositional formula
	     * 
	     * @param propositionalFormula The propositional formula
	     */
	    public void addPropositionalFormula(PlFormula propositionalFormula) 
	    {
	    	_propositionalFormulas.add(propositionalFormula);
	    }
	    
	    /**
	     * Add a defeasible formula
	     * 
	     * @param defeasibleFormula The defeasible formula
	     */
	    public void addDefeasibleFormula(PlFormula defeasibleFormula) 
	    {
	    	_defeasibleFormulas.add(defeasibleFormula);
	    }	 
}
