package uct.cs.dee.utils;

import org.tweetyproject.logics.pl.syntax.PlBeliefSet;

public class DefeasibleKnowledgeBase extends KnowledgeBaseFormulaSet
{

	 /**
     * Default constructor
     */
    public DefeasibleKnowledgeBase()
    {
        super();
    }

    /**
     * Parameterized constructor
     * 
     * @param collection The defeasible formula collection
     */
    public DefeasibleKnowledgeBase(KnowledgeBaseFormulaSet collection)
    {
        super(collection);
    }

    /**
     * Parameterized constructor
     * 
     * @param defeasibleKnowledge The defeasible knowledge
     * @param propositionalKnowledge The propositional knowledge
     */
    public DefeasibleKnowledgeBase(PlBeliefSet propositionalFormulas, PlBeliefSet defeasibleFormulas)
    {
        super(propositionalFormulas, defeasibleFormulas);
    }
}
