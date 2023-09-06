/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uct.cs.dee.tool.services;

import org.tweetyproject.commons.ParserException;
import org.tweetyproject.logics.pl.syntax.PlFormula;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
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
