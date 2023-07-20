package uct.cs.dee.reasoners;

import java.util.*;

import org.tweetyproject.logics.pl.syntax.*;

import uct.cs.dee.utils.*;

import org.tweetyproject.logics.pl.sat.Sat4jSolver;
import org.tweetyproject.logics.pl.sat.SatSolver;
import org.tweetyproject.logics.pl.semantics.NicePossibleWorld;
import org.tweetyproject.logics.pl.reasoner.*;

import uct.cs.dee.builders.*;
import uct.cs.dee.exceptions.*;
import uct.cs.dee.services.*;


public class MinimalRankedEntailmentReasoner implements IDefeasibleReasoner 
{

	// Private reference to a RankConstructor that produces RankedInterpretations
    private IRankBuilderService<RankedInterpretation> constructor;
    // Private RankedInterpretation model of a given knowledge base - used to answer entailment queries
    private RankedInterpretation model;

    /**
     * Constructor to produce reasoner with a pre-existing RankedInterpretation model
     * @param model
     */
    public MinimalRankedEntailmentReasoner(RankedInterpretation model) 
    {
        this.model = model;
    }

    /**
     * Constructor to produce reasoner with a RankConstructor capable of generating the necessary RankedInterpretation
     * model needed for reasoning
     * @param constructor - RankConstructor<RankedInterpretation>
     */
    public MinimalRankedEntailmentReasoner(IRankBuilderService<RankedInterpretation> constructor) 
    {
        this.constructor = constructor;
    }

    /**
     * Setter method to set the RankedInterpretation model used for reasoning
     * @param model - RankedInterpretation
     */
    public void setModel(RankedInterpretation model) 
    {
        this.model = model;
    }

    /**
     * Setter method to set the RankConstructor used for generating RankedInterpretation models
     * @param constructor - RankConstructor<RankedInterpretation>
     */
    public void setModelConstructor(IRankBuilderService<RankedInterpretation> constructor) 
    {
        this.constructor = constructor;
    }

    /**
     * Method that uses the built-in reference to a RankConstructor, if it exists, to build a ranked model of a given
     * knowledge base
     * @param knowledge - DefeasibleKnowledgeBase
     */
    @Override
    public void build(DefeasibleKnowledgeBase knowledge) 
    {
        if (this.constructor == null)
            throw new MissingRankBuilderException("Cannot build model without a RankConstructor.");
        
        this.model = constructor.construct(knowledge);
    }

    /**
     * Method to answer a given defeasible query using the RankedInterpretation model.
     * This method essentially checks whether the ranked model satisfies the given defeasible query.
     * @param defeasibleFormula - materialised defeasible query
     * @return - true or false
     */
    private boolean checkMinimalWorlds(Implication defeasibleFormula) 
    {
        boolean foundMinRank = false;
        
        for (int i = 0; i < this.model.getRankCount(); ++i) 
        {
            for (NicePossibleWorld world : this.model.getRank(i)) 
            {
                if (world.satisfies(defeasibleFormula.getFirstFormula())) 
                {
                    foundMinRank = true;
                    
                    if (!world.satisfies(defeasibleFormula.getSecondFormula())) 
                    {
                        return false;
                    }
                }
            }
            if (foundMinRank) 
            {
                return true;
            }
        }
        return true;
    }

    /**
     * Method to answer a given propositional query using the RankedInterpretation model
     * @param propositionalFormula - propositional query
     * @return - true or false
     */
    private boolean checkAllWorlds(PlFormula propositionalFormula) 
    {
        for (int i = 0; i < this.model.getRankCount(); ++i) 
        {
            for (NicePossibleWorld world : this.model.getRank(i)) 
            {
                if (!world.satisfies(propositionalFormula)) 
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method to answer a given defeasible query using the RankedInterpretation model
     * @param defeasibleImplication - materialised defeasible query
     * @return true or false
     */
    @Override
    public boolean queryDefeasible(Implication defeasibleImplication) 
    {
        if (this.model == null)
            throw new MissingRankingException("Ranked model has not been constructed.");
        
        return checkMinimalWorlds(defeasibleImplication);
    }

    /**
     * Method to answer a purely propositional query using the RankedInterpretation model
     * @param formula - propositional query
     * @return true or false
     */
    @Override
    public boolean queryPropositional(PlFormula formula) 
    {
        if (this.model == null)
            throw new MissingRankingException("Base rank has not been constructed.");
        
        return queryDefeasible(ExtensionUtils.normalizePropositionalFormula(formula));
    }

}
