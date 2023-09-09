/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uct.cs.dee.tool.services;

import java.util.List;
import org.tweetyproject.commons.ParserException;
import org.tweetyproject.logics.pl.syntax.PlFormula;

/**
 * <h1> IJustificationService <\h1>
 * The IJustificationService interface has methods that should be implemented for entailment justification.
 * 
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 * @version 1.0.1
 * @since 2013-06-01
 */
public interface IJustificationService {
   
     /**    
     * 
     * @return boolean
     * @throws org.tweetyproject.commons.ParserException
     * @throws java.lang.Exception 
     */
    public boolean computeJustification() throws ParserException, Exception;
    
    /**
     *     
     * @return boolean
     */
    public boolean isQueryEntailed();
    
    /**
     * 
     *
     * @return java.util.List<org.tweetyproject.logics.pl.syntax.PlFormula>    
     */
    public List<PlFormula> getJustification();
    
     /**
     *     
     * @return java.util.List<java.util.List<org.tweetyproject.logics.pl.syntax.PlFormula>>    
     */
    public  List<List<PlFormula>> getDematerialisedJustification();
    
     /**
     *     
     * @return String
     */
    public String getDisplayMessage();
    
    /**
     * 
     * @param formula
     * @return org.tweetyproject.logics.pl.syntax.PlFormula
     * @throws org.tweetyproject.commons.ParserException
     * @throws java.lang.Exception 
     * @see java.lang.Exception
     */
    //public PlFormula parseFormula(String formula) throws ParserException, Exception;
}

