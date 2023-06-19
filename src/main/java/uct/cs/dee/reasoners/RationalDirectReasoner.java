package uct.cs.dee.reasoners;

import java.util.*;

import org.tweetyproject.logics.pl.syntax.*;
import org.tweetyproject.logics.pl.sat.Sat4jSolver;
import org.tweetyproject.logics.pl.sat.SatSolver;
import org.tweetyproject.logics.pl.reasoner.*;

import uct.cs.dee.builders.*;
import uct.cs.dee.enums.FormularType;
import uct.cs.dee.exceptions.*;
import uct.cs.dee.services.*;
import uct.cs.dee.utils.*;

public class RationalDirectReasoner  implements IDefeasibleReasoner 
{
	private ArrayList<PlBeliefSet> baseRank;
    private IRankBuilderService<ArrayList<PlBeliefSet>> constructor;
    private DefeasibleKnowledgeBase knowledge;

    /**
     * Default constructor
     */
    public RationalDirectReasoner()
    {
        this(new BaseRankBuilder());
    }

    /**
     * Parameterised constructor
     * @param constructor
     */
    public RationalDirectReasoner(IRankBuilderService<ArrayList<PlBeliefSet>> constructor)
    {
        this.constructor = constructor;
    }

    /**
     * Parameterised constructor
     * @param baseRank
     * @param knowledge
     */
    public RationalDirectReasoner(ArrayList<PlBeliefSet> baseRank, DefeasibleKnowledgeBase knowledge)
    {
        this.baseRank = baseRank;
        this.knowledge = knowledge;
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
        this.knowledge = knowledge;
    }

    /**
     * Answers defeasible query using RationalClosure algorithm
     * @param defeasibleImplication - defeasible query
     * @return entailment true/false answer
     */
    public boolean queryDefeasible(Implication defeasibleImplication) 
    {
        if(this.baseRank == null || this.knowledge == null) 
        	throw new MissingRankingException("Cannot perform query without both base rank and knowledge base.");
        
        SatSolver.setDefaultSolver(new Sat4jSolver());
        SatReasoner reasoner = new SatReasoner();
        PlBeliefSet R = new PlBeliefSet(this.knowledge.getKnowledgeBaseCopy(FormularType.Defeasible));
        Negation query_negated_antecedent = new Negation(defeasibleImplication.getFirstFormula());

        int i = 0;
        while (reasoner.query(DefeasibleKnowledgeBase.union(this.knowledge.getKnowledgeBaseCopy(FormularType.Propositional), R), query_negated_antecedent)
                && !R.isEmpty()) 
        {
            R.removeAll(this.baseRank.get(i));
            i++;
        }

        return reasoner.query(DefeasibleKnowledgeBase.union(knowledge.getKnowledgeBaseCopy(FormularType.Propositional), R), defeasibleImplication);
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
