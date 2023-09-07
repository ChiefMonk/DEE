/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uct.cs.dee.tool.services;

import java.util.List;

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
     * @throws java.lang.Exception 
     */
    public String validateQuery(String inputQuery) throws Exception;
    
    /**
     * 
     * @param kbStatementList
     * @return 
     * @throws java.lang.Exception 
     */
    public List<String> validateKnowledgeBase(List<String> kbStatementList) throws Exception;
}
