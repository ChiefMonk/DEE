package za.ac.uct.cs.dee.common.services;

import za.ac.uct.cs.dee.common.structures.DefeasibleKnowledgeBase;

/**
 * Interface for constructing abstract ranked representations 
 * for reasoning
 */
public interface RankConstructor<T> {
    
    /**
     * Constructs ranking 
     * 
     * @param knowledge The knowledge base
     * @return The ranking
     */
    public T construct(DefeasibleKnowledgeBase knowledge);

}
