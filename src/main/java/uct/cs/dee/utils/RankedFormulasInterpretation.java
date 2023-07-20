package uct.cs.dee.utils;

import java.util.ArrayList;

import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import org.tweetyproject.logics.pl.syntax.PlFormula;
import org.tweetyproject.logics.pl.syntax.PlSignature;

import uct.cs.dee.exceptions.OutOfBoundsRankException;

/**
 * This class represents a ranked formulas interpretation, which we refer to within our respective papers. Each rank
 * now contains a representative formula instead of propositional worlds/valuations.
 */
public class RankedFormulasInterpretation 
{
    // The RankedFormulasInterpretation itself is stored in terms of an ArrayList of TweetyProject PlFormulas
    private ArrayList<PlFormula> ranks;

    /**
     * Default constructor
     */
    public RankedFormulasInterpretation() 
    {
        this(1);
    }

    /**
     * Constructor to allow specification of number of ranks - all of which default to null since no representative
     * formulas have yet been assigned
     * 
     * @param ranks The number of ranks
     */
    public RankedFormulasInterpretation(int ranks) 
    {
        this.ranks = new ArrayList<>();
        
        for (int index = 0; index <= ranks; ++index) 
        {
            this.ranks.add(null);
        }
    }

    /**
     * Constructor to allow the flexible assignment of the entire ArrayList of ranks as part of initialisation
     * 
     * @param ranks The list of ranks
     */
    public RankedFormulasInterpretation(ArrayList<PlFormula> ranks) 
    {
        this.ranks = new ArrayList<>(ranks);
    }

    /**
     * Get the number of finite ranks
     * 
     * @return The number of finite ranks
     */
    public int getRankCount() 
    {
        return this.ranks.size() - 1;
    }

    /**
     * Get the formula on the specified finite rank index
     * 
     * @param index The finite rank index
     * @return The rank
     */
    public PlFormula getRank(int index) 
    {
        if (index >= this.ranks.size() - 1 || index < 0) 
        {
            throw new OutOfBoundsRankException("Rank " + index + " does not exist.");
        }
        
        return this.ranks.get(index);
    }

    /**
     * Set the formula for the specified finite rank index
     * 
     * @param index The finite rank index
     * @param rankFormula The rank formula
     */
    public void setRank(int index, PlFormula rankFormula) 
    {
        if (index >= this.ranks.size() - 1 || index < 0) 
        {
            throw new OutOfBoundsRankException("Rank " + index + " is out of bounds.");
        }
        
        this.ranks.set(index, rankFormula);
    }

    /**
     * Get the formula on the infinite rank
     * 
     * @return The infinite rank
     */
    public PlFormula getInfiniteRank() 
    {
        return this.ranks.get(this.ranks.size() - 1);
    }

    /**
     * Set the formula for the infinite rank
     * 
     * @param rankFormula The formula
     */
    public void setInfiniteRank(PlFormula rankFormula) 
    {
        this.ranks.set(this.ranks.size() - 1, rankFormula);
    }

    /**
     * Add a new empty (null) finite rank below the infinite rank
     * 
     * @return The index of the empty rank
     */
    public int addRank() 
    {
        int position = this.getRankCount();
        this.ranks.add(position, null);
        
        return position;
    }

    /**
     * Add a new finite rank below the infinite rank containing the specified
     * formula
     * 
     * @param rankFormula The formula
     * @return The index at which the formula was inserted
     */
    public int addRank(PlFormula rankFormula) 
    {
        int position = this.getRankCount();
        this.ranks.add(position, rankFormula);
        
        return position;
    }

    /**
     * Add a new empty (null) finite rank at the specified index
     * 
     * @param index The index
     */
    public void addRank(int index) 
    {
        if (index >= this.ranks.size() - 1 || index < 0) 
        {
            throw new OutOfBoundsRankException("Rank " + index + " is out of bounds.");
        }
        
        this.ranks.add(index, null);
    }

    /**
     * Add new rank containing the specified formula at the specified index
     * 
     * @param index The index
     * @param rankFormula The formula
     */
    public void addRank(int index, PlFormula rankFormula) 
    {
        if (index >= this.ranks.size() - 1 || index < 0) 
        {
            throw new OutOfBoundsRankException("Rank " + index + " is out of bounds.");
        }
        
        this.ranks.add(index, rankFormula);
    }

    /**
     * Get all the formulas contained in the entire RankedFormulasInterpretation in the form of a TweetyProject
     * PlBeliefSet.
     * @return PlBeliefSet
     */
    public PlBeliefSet getAllFormulas()
    {
        return new PlBeliefSet(this.ranks);
    }

    /**
     * Generate the corresponding world-based RankedInterpretation for the given RankedFormulasInterpretation.
     * @return RankedInterpretation
     */
    public RankedInterpretation getRankedInterpretation()
    {
        RankedInterpretation rankedInterpretation = new RankedInterpretation(0);
        PlSignature signature = getAllFormulas().getMinimalSignature();
        
        for(int i = 0; i < this.ranks.size()-1; ++i)
        {
            rankedInterpretation.addRank(i, ExtensionUtils.getModels(this.ranks.get(i), signature));
        }
        
        rankedInterpretation.addToInfiniteRank(ExtensionUtils.getModels(this.ranks.get(this.ranks.size()-1), signature));
        
        return rankedInterpretation;
    }

    /**
     * Returns the string representation of the RankedFormulasInterpretation in the usual format - a number of discrete
     * levels or ranks.
     * @return The string representation
     */
    public String toString() 
    {
        String template = "%-3s:\t%s\n";
        String output = String.format(template, "∞", this.getInfiniteRank());
        
        for (int index = this.getRankCount() - 1; index >= 0; --index) 
        {
            output += String.format(template, index, this.getRank(index));
        }
        
        return output.trim();
    }
}