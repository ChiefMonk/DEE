package uct.cs.dee.builders;

import java.util.*;
import java.util.Map.Entry;

import org.tweetyproject.logics.pl.syntax.Implication;
import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import org.tweetyproject.logics.pl.syntax.PlFormula;
import org.tweetyproject.logics.pl.semantics.NicePossibleWorld;

import uct.cs.dee.enums.FormularType;
import uct.cs.dee.services.IRankBuilderService;
import uct.cs.dee.utils.DefeasibleKnowledgeBase;
import uct.cs.dee.utils.RankedInterpretation;

/**
 * Implementation of ModelRank algorithm in Rational Closure Model-Based Defeasible Reasoning
 */
public class ModelRankBuilder implements IRankBuilderService<RankedInterpretation> {

    /**
     * Constructs the ranked model of a given defeasible knowledge base that can be used to characterise its rational closure
     * @param knowledge - DefeasibleKnowledgeBase
     * @return RankedInterpretation - ranked model corresponding to rational closure
     */
    @Override
    public RankedInterpretation construct(DefeasibleKnowledgeBase knowledge) 
    {
        RankedInterpretation RankedModel = new RankedInterpretation(0);

        // Set of all possible worlds w.r.t. atoms
        Set<NicePossibleWorld> KB_U = NicePossibleWorld.getAllPossibleWorlds(
        		DefeasibleKnowledgeBase.union(knowledge).getMinimalSignature().toCollection());

        // Pre-emptively rank the worlds that don't satisfy the classical propositional
        // statements on the infinite rank
        Set<NicePossibleWorld> preliminaryInfiniteWorlds = new HashSet<>();
        for (NicePossibleWorld nw : KB_U) 
        {
            if (!nw.satisfies(knowledge.getKnowledgeBaseCopy(FormularType.Propositional))) 
            {
                preliminaryInfiniteWorlds.add(nw);
            }
        }

        // Remove the preliminary infinite worlds from all the possible worlds to rank
        Set<NicePossibleWorld> KB_U_remaining = new HashSet<>(KB_U);
        KB_U_remaining.removeAll(preliminaryInfiniteWorlds);

        // Remaining defeasible formulas to rank
        PlBeliefSet KB_D_remaining = knowledge.getKnowledgeBaseCopy(FormularType.Defeasible);

        // Mapping of defeasible formula antecedents to booleans to tick them off as
        // done (done when found a corresponding best world with finite rank)
        HashMap<PlFormula, Boolean> finished = new HashMap<>();
        // Initialise the entries in the hashmap
        for (PlFormula formula : KB_D_remaining) 
        {
            PlFormula antecedent = ((Implication) formula).getFirstFormula();
            finished.put(antecedent, false);
        }

        Set<NicePossibleWorld> currentRank;

        do {

            currentRank = new HashSet<>();

            for (NicePossibleWorld nw : KB_U_remaining) 
            {
                // if the world satisfies all the remaining defeasible formulas
                if (nw.satisfies(KB_D_remaining)) 
                {
                    currentRank.add(nw); // add the world to the current rank
                    // check whether the world is the best world for one of the formula antecedents
                    for (Entry<PlFormula, Boolean> entry : finished.entrySet()) 
                    {
                        if (!entry.getValue() && nw.satisfies(entry.getKey())) 
                        {
                            finished.replace(entry.getKey(), true);
                        }
                    }
                }
            }

            PlBeliefSet KB_D_remaining_previous = new PlBeliefSet(KB_D_remaining);
            // remove the formulas that have been checked off in the current rank
            for (PlFormula formula : KB_D_remaining_previous) 
            {
                PlFormula antecedent = ((Implication) formula).getFirstFormula();
                if (finished.get(antecedent)) 
                {
                    KB_D_remaining.remove(formula);
                }
            }

            if (!currentRank.isEmpty()) 
            {
                int rankIndex = RankedModel.addRank();
                RankedModel.addToRank(rankIndex, currentRank);
                KB_U_remaining.removeAll(currentRank);
            }

        } while (!currentRank.isEmpty());

        // Add the infinite rank
        Set<NicePossibleWorld> infiniteRank = new HashSet<>();
        infiniteRank.addAll(KB_U_remaining);
        infiniteRank.addAll(preliminaryInfiniteWorlds);
        RankedModel.addToInfiniteRank(infiniteRank);

        return RankedModel;
    }

}

