package uct.cs.dee.builders;

import java.util.*;

import org.tweetyproject.logics.pl.syntax.Implication;
import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import org.tweetyproject.logics.pl.syntax.PlFormula;
import org.tweetyproject.logics.pl.semantics.NicePossibleWorld;

import uct.cs.dee.enums.FormularType;
import uct.cs.dee.services.IRankBuilderService;
import uct.cs.dee.utils.DefeasibleKnowledgeBase;
import uct.cs.dee.utils.RankedInterpretation;


/**
 * Implementation of an algorithm based on the ModelRank algorithm (in Rational Closure Model-Based Defeasible Reasoning)
 * that uses the BaseRank algorithm as part of the construction process.
 */
public class ModelBaseRankBuilder implements IRankBuilderService<RankedInterpretation> {

    /**
     * Constructs the ranked model used to define Rational Closure for a given
     * knowledge base using BaseRank to facilitate its construction
     * 
     * @param knowledge - DefeasibleKnowledgeBase
     * @return RankedInterpretation
     */
    @Override
    public RankedInterpretation construct(DefeasibleKnowledgeBase knowledge) {

        // Apply BaseRank to the knowledge base
        ArrayList<PlBeliefSet> ranked_KB = new BaseRankBuilder().construct(knowledge);

        // HashMap of finite knowledge base formulas to their base rank
        HashMap<PlFormula, Integer> kB_BR_HashMap = new HashMap<>();

        for (int i = 0; i < ranked_KB.size() - 1; i++) {
            for (PlFormula formula : ranked_KB.get(i)) {
                kB_BR_HashMap.put(formula, i);
            }
        }

        // Set to contain all the infinite rank worlds
        Set<NicePossibleWorld> infinite_worlds = new HashSet<>();

        RankedInterpretation RankedModel = new RankedInterpretation(0);

        // Initialise the ranked model to have the minimum number of ranks - i.e. as
        // many ranks as there are base ranks
        for (int i = 0; i < ranked_KB.size(); i++) {
            RankedModel.addRank();
        }

        Set<NicePossibleWorld> kB_PossibleWorlds = NicePossibleWorld.getAllPossibleWorlds(DefeasibleKnowledgeBase.union(knowledge).getMinimalSignature().toCollection());

        for (NicePossibleWorld pWorld : kB_PossibleWorlds) {

            // If the current world does not satisfy the infinite base rank propositional
            // formulas then add to infinite rank
            if (!pWorld.satisfies(ranked_KB.get(ranked_KB.size() - 1))) {
                infinite_worlds.add(pWorld);
            } else {
                int tempMaxRank = 0;

                // Find the maximum rank of a knowledge base formula for which the current
                // world satisfies its antecedent
                for (PlFormula formula : knowledge.getKnowledgeBaseCopy(FormularType.Defeasible)) {
                    PlFormula antecedent = ((Implication) formula).getFirstFormula();
                    if (pWorld.satisfies(antecedent) && kB_BR_HashMap.get(formula) >= tempMaxRank) {
                        tempMaxRank = kB_BR_HashMap.get(formula);
                    }
                }

                // If the current world does not satisfy all the formulas on its max rank then
                // bump it up to the next rank
                if (!pWorld.satisfies(ranked_KB.get(tempMaxRank))) {
                    tempMaxRank++;
                }

                // If the rank of the world exceeds the number of ranks that exist, then create
                // a new rank and add the current world to it
                if (tempMaxRank >= RankedModel.getRankCount()) {
                    int rankIndex = RankedModel.addRank();
                    RankedModel.addToRank(rankIndex, pWorld); // Add rank at correct rank index
                } else {
                    // Otherwise, add the world to its correct rank in the ranked model
                    RankedModel.addToRank(tempMaxRank, pWorld);
                }
            }
        }

        // Lastly add the infinite rank worlds
        RankedModel.addToInfiniteRank(infinite_worlds);

        return RankedModel;

    }

}

