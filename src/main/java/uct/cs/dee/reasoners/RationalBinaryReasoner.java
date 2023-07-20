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


public class RationalBinaryReasoner implements IDefeasibleReasoner 
{
	 	private ArrayList<PlBeliefSet> baseRank;
	    private IRankBuilderService<ArrayList<PlBeliefSet>> constructor;

	    /**
	     * Default constructor
	     */
	    public RationalBinaryReasoner()
	    {
	        this(new BaseRankBuilder());
	    }

	    /**
	     * Parameterised constructor
	     * @param constructor
	     */
	    public RationalBinaryReasoner(IRankBuilderService<ArrayList<PlBeliefSet>> constructor)
	    {
	        this.constructor = constructor;
	    }

	    /**
	     * Parameterised constructor
	     * @param baseRank
	     */
	    public RationalBinaryReasoner(ArrayList<PlBeliefSet> baseRank)
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
	     * Answers defeasible query using rational closure with a binary search optimisation.
	     * Code from SCADR (2021).
	     * @param defeasibleImplication - defeasible query
	     * @return entailment true/false answer
	     */
	    public boolean queryDefeasible(Implication defeasibleImplication)
	    {
	        if(this.baseRank == null) 
	        	throw new MissingRankingException("Cannot perform query without both base rank and knowledge base.");
	        
	        SatSolver.setDefaultSolver(new Sat4jSolver());
	        SatReasoner classicalReasoner = new SatReasoner();

	        PlFormula negationOfAntecedent = new Negation(defeasibleImplication.getFormulas().getFirst());

	        int low = 0;
	        int n = this.baseRank.size();
	        int high = n;

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
