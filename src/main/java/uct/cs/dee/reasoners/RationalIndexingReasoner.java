package uct.cs.dee.reasoners;

import java.util.*;

import org.tweetyproject.logics.pl.syntax.*;

import uct.cs.dee.Exceptions.*;
import uct.cs.dee.utils.*;

import org.tweetyproject.logics.pl.sat.Sat4jSolver;
import org.tweetyproject.logics.pl.sat.SatSolver;
import org.tweetyproject.logics.pl.reasoner.*;

import uct.cs.dee.builders.*;
import uct.cs.dee.services.*;


public class RationalIndexingReasoner  implements IDefeasibleReasoner 
{
	// Used to store the rank at which a given query is no longer exceptional with the knowledge base
    private HashMap<PlFormula, Integer> antecedentNegationRanksToRemoveFrom;
    private ArrayList<PlBeliefSet> baseRank;
    private IRankBuilderService<ArrayList<PlBeliefSet>> constructor;

    /**
     * Default constructor
     */
    public RationalIndexingReasoner()
    {
        this(new BaseRankBuilder());
    }

    /**
     * Parameterised constructor
     * @param constructor
     */
    public RationalIndexingReasoner(IRankBuilderService<ArrayList<PlBeliefSet>> constructor)
    {
        this.antecedentNegationRanksToRemoveFrom = new HashMap<PlFormula, Integer>();
        this.constructor = constructor;
    }

    /**
     * Parameterised constructor
     * @param baseRank
     */
    public RationalIndexingReasoner(ArrayList<PlBeliefSet> baseRank)
    {
        this.antecedentNegationRanksToRemoveFrom = new HashMap<PlFormula, Integer>();
        this.baseRank = baseRank;
    }

    /**
     * Gets the base ranking of the knowledge base using BaseRank implementation
     * @param knowledge - defeasible knowledge base
     */
    @Override
    public void build(DefeasibleKnowledgeBase knowledge)
    {
        if(this.constructor == null) 
        	throw new MissingRankBuilderException("Cannot construct base rank without RankConstructor.");
        
        this.baseRank = this.constructor.construct(knowledge);
    }


    /**
     * Answers defeasible query using modified RationalClosure algorithm that indexes ranks of previously found antecedents.
     * Code from SCADR (2021).
     * @param defeasibleImplication - defeasible query
     * @return entailment true/false answer
     */
    @Override
    public boolean queryDefeasible(Implication defeasibleImplication)
    {
        if(this.baseRank == null) 
        	throw new MissingRankingException("Cannot perform query without both base rank and knowledge base.");
        
        SatSolver.setDefaultSolver(new Sat4jSolver());
        SatReasoner classicalReasoner = new SatReasoner();
        PlFormula negationOfAntecedent = new Negation(defeasibleImplication.getFormulas().getFirst());
        ArrayList<PlBeliefSet> rankedKB = (ArrayList<PlBeliefSet>) this.baseRank.clone();
        PlBeliefSet combinedRankedKB = ExtensionUtils.combineFormulaRanks(rankedKB);
        
        if (antecedentNegationRanksToRemoveFrom.get(negationOfAntecedent) != null) 
        {
            for (int i = 0; i < (antecedentNegationRanksToRemoveFrom.get(negationOfAntecedent)); i++) 
            {
                rankedKB.remove(rankedKB.get(0));
            }
        } 
        else 
        {
            while (combinedRankedKB.size() != 0) 
            {
                if (classicalReasoner.query(combinedRankedKB, negationOfAntecedent)) 
                {
                    combinedRankedKB.removeAll(rankedKB.get(0));
                    rankedKB.remove(rankedKB.get(0));
                } 
                else 
                {
                    antecedentNegationRanksToRemoveFrom.put(negationOfAntecedent, (this.baseRank.size() - rankedKB.size()));
                    break;
                }
            }
        }

        if (combinedRankedKB.size() != 0) 
        {
            if (classicalReasoner.query(combinedRankedKB, defeasibleImplication)) 
            {
                return true;
            } 
            else 
            {
                return false;
            }
        } 
        else 
        {
            return true;
        }
    }

    /**
     * Query a propositional formula
     *
     * @param formula The formula to query
     * @return Whether the query is entailed
     */
    @Override
    public boolean queryPropositional(PlFormula formula)
    {
        if(this.baseRank == null) 
        	throw new MissingRankingException("Base rank has not been constructed.");
        
        return queryDefeasible(ExtensionUtils.normalizePropositionalFormula(formula));
    }
}
