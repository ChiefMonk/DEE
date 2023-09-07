/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uct.cs.dee.tool.services;

import java.util.List;
import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import org.tweetyproject.logics.pl.syntax.PlFormula;
import uct.cs.dee.tool.models.ValidationResult;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public interface IKnowledgeBaseService  {       
    /**
     * 
     * @param formula
     * @return 
    */    
    public String translateFormula(String formula);      
    
     /**
     * 
     * @param inputQuery
     * @return 
     */
    public ValidationResult<String> validateQuery(String inputQuery);
    
    /**
     * 
     * @param kbStatementList
     * @return 
     */
    public ValidationResult<List<String>> validateKnowledgeBase(List<String> kbStatementList);
    
     /**
     * 
     * @return 
    */
    public PlBeliefSet getKnowledgeBase();
    
    /**
     * 
     * @return 
    */
    public PlFormula getQuery();
}
