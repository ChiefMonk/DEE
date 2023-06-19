package uct.cs.dee.builders;

import java.util.*;

import org.tweetyproject.logics.pl.syntax.*;

import uct.cs.dee.enums.FormularType;
import uct.cs.dee.services.IRankBuilderService;
import uct.cs.dee.utils.DefeasibleKnowledgeBase;

import org.tweetyproject.logics.pl.reasoner.SatReasoner;
import org.tweetyproject.logics.pl.sat.Sat4jSolver;
import org.tweetyproject.logics.pl.sat.SatSolver;

/**
 * This is an implementation of the BaseRank algorithm.
 */
public class BaseRankBuilder implements IRankBuilderService<ArrayList<PlBeliefSet>>
{
    /**
     * Constructs the base ranking of a given defeasible knowledge base.
     * @param knowledge - defeasible knowledge base
     * @return base ranking of the knowledge base formulas in the form of an ArrayList of TweetProject PlBeliefSets
     */
    public ArrayList<PlBeliefSet> construct(DefeasibleKnowledgeBase knowledge) 
    {
        SatSolver.setDefaultSolver(new Sat4jSolver());
        SatReasoner reasoner = new SatReasoner();
        ArrayList<PlBeliefSet> rankedKB = new ArrayList<PlBeliefSet>();

        PlBeliefSet previousKB = new PlBeliefSet();
        PlBeliefSet currentKB = new PlBeliefSet();

        currentKB.addAll(knowledge.getKnowledgeBaseCopy(FormularType.Defeasible));

        while (!currentKB.equals(previousKB) && !currentKB.isEmpty()) 
        {
            previousKB = currentKB;
            currentKB = new PlBeliefSet();

            PlBeliefSet KB_C_U_previousKB = DefeasibleKnowledgeBase.union(knowledge.getKnowledgeBaseCopy(FormularType.Propositional), previousKB);

            for (PlFormula formula : previousKB) 
            {
                Negation negatedAntecedent = new Negation(((Implication) formula).getFirstFormula());

                if (reasoner.query(KB_C_U_previousKB, negatedAntecedent)) 
                {
                    currentKB.add(formula);
                }
            }

            PlBeliefSet currentRank = new PlBeliefSet();
            currentRank.addAll(previousKB);
            currentRank.removeAll(currentKB);

            if (!currentRank.isEmpty()) 
            {
                rankedKB.add(currentRank);
            }
        }

        if (!currentKB.isEmpty()) 
        {
            rankedKB.add(DefeasibleKnowledgeBase.union(knowledge.getKnowledgeBaseCopy(FormularType.Propositional), currentKB));
        } 
        else 
        {
            rankedKB.add(knowledge.getKnowledgeBaseCopy(FormularType.Propositional)); // Add all classical statements - infinite rank
        }

        return rankedKB;
    }
}
