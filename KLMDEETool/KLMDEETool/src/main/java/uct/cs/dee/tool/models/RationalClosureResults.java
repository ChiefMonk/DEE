package uct.cs.dee.tool.models;

import org.tweetyproject.logics.pl.syntax.PlBeliefSet;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class RationalClosureResults 
{
    private final boolean entailment;
    private final int ranksRemoved;
    private final MinimalRankedFormulas rankedFormulas;
    private final PlBeliefSet remainingFormulas;
    
    public RationalClosureResults(boolean entailment, int ranksRemoved, MinimalRankedFormulas rankedFormulas, PlBeliefSet remainingFormulas)
    {
        this.entailment = entailment;
        this.ranksRemoved = ranksRemoved;
        this.rankedFormulas = rankedFormulas;
        this.remainingFormulas = remainingFormulas;
    }
    
    public boolean entailmentsHolds()
    {
        return this.entailment;
    }
    
    public int getRanksRemoved()
    {
        return this.ranksRemoved;
    }
    
    /**
     *
     * @return
     */
    public MinimalRankedFormulas getMinimalRanking()
    {
        return this.rankedFormulas;
    }
    
    /**
     *
     * @return
     */
    public PlBeliefSet getRemainingFormulas()
    {
        return this.remainingFormulas;
    }
    
    /**
     *
     * @return
     */
    public MinimalRankedFormulas getDiscardedFormulas()
    {
        return this.rankedFormulas;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<< Rational Closure Result - BEGIN>>\n")
                .append("Entailment holds :\t").append(this.entailment).append("\n")
                .append("Ranked removed :\t").append(this.ranksRemoved).append("\n")
                .append("Minimal rank:\n").append(this.rankedFormulas)
                .append("<< Rational Closure Result - END >>");
        return stringBuilder.toString();
    }
    
}
