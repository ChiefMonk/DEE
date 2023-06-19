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

/**
 * Implementation of standard, unoptimised RationalClosure algorithm from SCADR (2021).
 */
public class RationalRegularReasoner implements IDefeasibleReasoner
{

    private ArrayList<PlBeliefSet> baseRank;
    private IRankBuilderService<ArrayList<PlBeliefSet>> constructor;

    /**
     * Default constructor
     */
    public RationalRegularReasoner()
    {
        this(new BaseRankBuilder());
    }

    /**
     * Parameterised constructor
     * @param constructor
     */
    public RationalRegularReasoner(IRankBuilderService<ArrayList<PlBeliefSet>> constructor)
    {
        this.constructor = constructor;
    }

    /**
     * Parameterised constructor
     * @param baseRank
     */
    public RationalRegularReasoner(ArrayList<PlBeliefSet> baseRank)
    {
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
     * Answers defeasible query using standard, unoptimised RationalClosure algorithm.
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
        
        while (combinedRankedKB.size() != 0) 
        {
            if (classicalReasoner.query(combinedRankedKB, negationOfAntecedent)) 
            {
                combinedRankedKB.removeAll(rankedKB.get(0));
                rankedKB.remove(rankedKB.get(0));
            } 
            else 
            {
                break;
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
