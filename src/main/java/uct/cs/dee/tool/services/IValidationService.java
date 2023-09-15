package uct.cs.dee.tool.services;

import org.tweetyproject.commons.ParserException;

/**
 * <h1>IValidationService<\h1>
 * The IValidationService interface has methods that should be implemented for validation.
 * 
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 * @version 1.0.1
 * @since 2023-07-03
 */
public interface IValidationService {
    
    /**
     *      
     * @param statement
     * @return A Boolean indicating if the statement is defeasible or not   
     */
    public boolean IsDefeasibleStatement(String statement);   
    
    /**
     * 
     * @param statement
     * @return 
     */
    public boolean hasValidConnective(String statement);
    
    /**
     * 
     * @param statement
     * @return A Boolean indicating if the input query statement is valid or not   
     * @throws org.tweetyproject.commons.ParserException
     * @throws java.lang.Exception 
     */
    public boolean isInputQueryValid(String statement) throws ParserException, Exception;   
    
    /**
     * 
     * @param knowledgeBase
     * @return A Boolean indicating if the knowledge base is valid or not   
     * @throws org.tweetyproject.commons.ParserException
     * @throws java.lang.Exception 
     */
    public boolean isInputKnowledgeBaseValid(String knowledgeBase) throws ParserException, Exception;
    
}
