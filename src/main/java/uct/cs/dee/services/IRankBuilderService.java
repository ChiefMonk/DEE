package uct.cs.dee.services;

import uct.cs.dee.utils.DefeasibleKnowledgeBase;

/**
 * Interface for constructing abstract ranked representations for reasoning
 */
public interface IRankBuilderService <T>
{

	/**
     * Constructs ranking 
     * 
     * @param knowledge The knowledge base
     * @return The ranking
     */
    public T construct(DefeasibleKnowledgeBase knowledge);
}