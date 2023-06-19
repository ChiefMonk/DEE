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


public class MinimalRankedEntailmentCumulativeFormulaReasoner extends MinimalRankedEntailmentFormulaReasoner 
{

    /**
     * Constructor to produce reasoner with a pre-existing RankedFormulasInterpretation model
     * @param model
     */
    public MinimalRankedEntailmentCumulativeFormulaReasoner(RankedFormulasInterpretation model) {
        super(model);
    }

    /**
     * Constructor to produce reasoner with a RankConstructor capable of generating the necessary RankedFormulasInterpretation
     * model needed for reasoning
     * @param constructor - RankConstructor<RankedFormulasInterpretation>
     */
    public MinimalRankedEntailmentCumulativeFormulaReasoner(IRankBuilderService<RankedFormulasInterpretation> constructor) {
        super(constructor);
    }

}
