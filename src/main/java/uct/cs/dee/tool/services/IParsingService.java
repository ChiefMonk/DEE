package uct.cs.dee.tool.services;

import org.tweetyproject.commons.ParserException;
import org.tweetyproject.logics.pl.syntax.PlFormula;

/**
 * <h1>IParsingService<\h1>
 * The IParsingService interface has methods that should be implemented for parsing.
 * 
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 * @version 1.0.1
 * @since 2023-07-03
 */
public interface IParsingService {
    
    /**
     * 
     * @param inputQuery
     * @return 
     */
    public boolean isInputQueryValid(String inputQuery);
    
    /**
     * 
     * @param inputKnowledgeBase
     * @return 
     */
    public boolean isInputKnowledgeBaseValid(String inputKnowledgeBase);
    
    /**
     * 
     * @param formula
     * @return org.tweetyproject.logics.pl.syntax.PlFormula
     * @throws org.tweetyproject.commons.ParserException
     * @throws java.lang.Exception 
     */
    public PlFormula parseFormula(String formula) throws ParserException, Exception;
}
