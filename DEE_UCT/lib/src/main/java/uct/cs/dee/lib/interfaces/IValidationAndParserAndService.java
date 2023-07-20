/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uct.cs.dee.lib.interfaces;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public interface IValidationAndParserAndService {
    /**
     *      
     * @param statement
     * @return 
     */
    boolean boolIsDefeasibleStatement(String statement);    
    
    /**
     * 
     * @param inputQuery
     * @return 
     */
    boolean validateInputQuery(String inputQuery);   
    
    /**
     * 
     * @param inputKnowledgeBase
     * @return 
     */
    boolean validateInputKnowledgeBase(String inputKnowledgeBase);
    
    /**
     * 
     * @param inputQuery
     * @return 
     */
    boolean parseInputQuery(String inputQuery);
    
    /**
     * 
     * @param inputKnowledgeBase
     * @return 
     */
    boolean parseInputKnowledgeBase(String inputKnowledgeBase);
}
