package uct.cs.dee.lib.models;

import org.tweetyproject.logics.pl.syntax.PlBeliefSet;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class RationalClosureResults 
{
    private boolean entailment;
    private int ranksRemoved;
    private MinimalRankedFormulas rankedFormulas;
    private PlBeliefSet remainingFormulas;
    
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
    
    public MinimalRankedFormulas getMinimalRanking()
    {
        return this.rankedFormulas;
    }
    
    public PlBeliefSet getRemainingFormulas()
    {
        return this.remainingFormulas;
    }
    
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
