/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uct.cs.dee.tool.services;

import org.tweetyproject.commons.ParserException;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
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