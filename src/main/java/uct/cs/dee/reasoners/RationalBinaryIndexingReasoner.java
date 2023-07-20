package uct.cs.dee.reasoners;

import java.util.*;

import org.tweetyproject.logics.pl.syntax.*;

import uct.cs.dee.utils.*;

import org.tweetyproject.logics.pl.sat.Sat4jSolver;
import org.tweetyproject.logics.pl.sat.SatSolver;
import org.tweetyproject.logics.pl.reasoner.*;

import uct.cs.dee.builders.*;
import uct.cs.dee.exceptions.*;
import uct.cs.dee.services.*;


public class RationalBinaryIndexingReasoner implements IDefeasibleReasoner 
{

	 // (For use with Joel's indexing algorithms) Used to store the rank at which a
    // given query is no longer exceptional with the knowledge base
    private HashMap<PlFormula, Integer> antecedentNegationRanksToRemoveFrom = new HashMap<PlFormula, Integer>();
    private ArrayList<PlBeliefSet> baseRank;
    private IRankBuilderService<ArrayList<PlBeliefSet>> constructor;

    /**
     * Default constructor
     */
    public RationalBinaryIndexingReasoner()
    {
        this(new BaseRankBuilder());
    }

    /**
     * Parameterised constructor
     * @param constructor
     */
    public RationalBinaryIndexingReasoner(IRankBuilderService<ArrayList<PlBeliefSet>> constructor)
    {
        this.antecedentNegationRanksToRemoveFrom = new HashMap<PlFormula, Integer>();
        this.constructor = constructor;
    }

    /**
     * Parameterised constructor
     * @param baseRank
     */
    public RationalBinaryIndexingReasoner(ArrayList<PlBeliefSet> baseRank)
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
        this.antecedentNegationRanksToRemoveFrom = new HashMap<PlFormula, Integer>();
    }

    /**
     * Answers defeasible query using rational closure with binary search and antecedent indexing optimisations.
     * Code from SCADR (2021).
     * @param defeasibleImplication - defeasible query
     * @return entailment true/false answer
     */
    public boolean queryDefeasible(Implication defeasibleImplication)
    {
        if(this.baseRank == null) 
        	throw new MissingRankingException("Base rank has not been constructed.");
        
        SatSolver.setDefaultSolver(new Sat4jSolver());
        SatReasoner classicalReasoner = new SatReasoner();

        PlFormula negationOfAntecedent = new Negation(defeasibleImplication.getFormulas().getFirst());

        int low = 0;
        int n = this.baseRank.size();
        int high = n;

        Integer removeFrom = antecedentNegationRanksToRemoveFrom.get(negationOfAntecedent);

        if (removeFrom != null) 
        {

            List<PlBeliefSet> R = this.baseRank.subList(removeFrom, n);
            PlBeliefSet combinedRankedKBArray = ExtensionUtils.combineFormulaRanks(R);
            return classicalReasoner.query(combinedRankedKBArray, defeasibleImplication);

        } 
        else 
        {

            while (high > low) 
            {
                int mid = low + (high - low) / 2;
                List<PlBeliefSet> R = this.baseRank.subList(mid + 1, n);
                PlBeliefSet combinedRankedKBArray = ExtensionUtils.combineFormulaRanks(R);
                
                if (classicalReasoner.query(combinedRankedKBArray, negationOfAntecedent)) 
                {
                    low = mid + 1;
                } 
                else 
                {
                    R = this.baseRank.subList(mid, n);
                    combinedRankedKBArray = ExtensionUtils.combineFormulaRanks(R);
                    
                    if (classicalReasoner.query(combinedRankedKBArray, negationOfAntecedent)) 
                    {
                        R = this.baseRank.subList(mid + 1, n);
                        combinedRankedKBArray = ExtensionUtils.combineFormulaRanks(R);
                        return classicalReasoner.query(combinedRankedKBArray, defeasibleImplication);
                    } 
                    else 
                    {
                        high = mid;
                    }
                }
            }

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
