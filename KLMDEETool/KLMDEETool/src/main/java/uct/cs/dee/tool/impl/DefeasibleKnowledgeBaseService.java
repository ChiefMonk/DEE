/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uct.cs.dee.tool.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tweetyproject.logics.pl.parser.PlParser;
import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import org.tweetyproject.logics.pl.syntax.PlFormula;
import uct.cs.dee.tool.models.ValidationResult;
import uct.cs.dee.tool.services.*;
import uct.cs.dee.tool.utils.DefeasibleLogicParser;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class DefeasibleKnowledgeBaseService implements IKnowledgeBaseService {
    
    private static final String SYMBOL_NEGATION = "!"; 
    private static final String SYMBOL_OR = "||";
    private static final String SYMBOL_AND = "&&";
    private static final String SYMBOL_IMPLICATION = "=>";
    private static final String SYMBOL_BI_IMPLICATION = "<=>";
    public static final String SYMBOL_DEFEASIBLE_IMPLICATION = "~>";
    
    private PlFormula _queryFormula = null;
    private PlBeliefSet _knowledgeBaseSet = null;  
    
    /**
     * 
     * @return 
    */
    @Override
    public PlBeliefSet getKnowledgeBase() {
        return _knowledgeBaseSet;
    }
    
    /**
     * 
     * @return 
    */
    @Override
    public PlFormula getQuery() {
        return _queryFormula;
    }
    
     /**
     * 
     * @param inputQuery
     * @return 
     */
    @Override
    public ValidationResult<String> validateQuery(String inputQuery) {  
        try {
            if (inputQuery == null || inputQuery.isEmpty()) {
                return new ValidationResult<>(String.format("Please enter a valid defeasible query.\nIt must contain a defeasible implication connective (%s)", SYMBOL_DEFEASIBLE_IMPLICATION));     
            }   
            
            if (!isDefeasibleFormula(inputQuery)) {
                return new ValidationResult<>(String.format("Please defeasible query (%s) is not invalidy.\nIt must contain a defeasible implication connective (%s)", inputQuery, SYMBOL_DEFEASIBLE_IMPLICATION)); 
            }
            
            DefeasibleLogicParser defeasibleParser = new DefeasibleLogicParser(new PlParser());
            _queryFormula = defeasibleParser.parseFormula(translateFormula(inputQuery)); 
            
        } catch (Exception ex) {
            Logger.getLogger(DefeasibleKnowledgeBaseService.class.getName()).log(Level.SEVERE, "Error validating input query", ex);
            return new ValidationResult<>(String.format("Please defeasible query (%s) is not invalidy.\nIt must contain a defeasible implication connective (%s)", inputQuery, SYMBOL_DEFEASIBLE_IMPLICATION)); 
        } 
        
        return new ValidationResult<>(true,_queryFormula.toString());
    }
    
    /**
     * 
     * @param kbStatementList
     * @return 
     */
    @Override
    public ValidationResult<List<String>> validateKnowledgeBase(List<String> kbStatementList) {
        _knowledgeBaseSet = new PlBeliefSet();
        PlParser classicalParser = new PlParser();
        DefeasibleLogicParser defeasibleParser = new DefeasibleLogicParser(classicalParser);
        
        int lineNumber = 1;
        String lineStatement = "";
               
        try {
            for (String statement : kbStatementList) {
                if (statement == null || statement.isEmpty())
                    continue;

                lineStatement = statement;            
                statement = translateFormula(statement);

                if (isDefeasibleFormula(statement))
                    _knowledgeBaseSet.add(defeasibleParser.parseFormula(statement));
                else
                    _knowledgeBaseSet.add(classicalParser.parseFormula(statement));  

                lineNumber++;
            }  
        } catch (Exception ex) {
            Logger.getLogger(DefeasibleKnowledgeBaseService.class.getName()).log(Level.SEVERE, "Error validating input knowledge base", ex);
           return new ValidationResult<>(String.format("The Knowledgebase statement on line %s (%s) is invalid.\nPlease use the following symbols.\n%s",lineNumber, lineStatement, getConnectiveList()));            
        } 
        
        List<String> formulaList = new ArrayList<String>(); 
        lineNumber = 1;
        for (PlFormula plFormula : _knowledgeBaseSet) {   
            lineStatement = String.format("0%s",lineNumber); 
            if(lineNumber >= 10)
                 lineStatement = String.format("%s",lineNumber); 
            formulaList.add(String.format("%s : %s",lineStatement, plFormula));                              
        } 
        
        return new ValidationResult<>(true,formulaList);
    }
    
    @Override
    public String translateFormula(String formula) {       
        return reformatDefeasibleImplication(formula);       
    }
  
    private static String reformatDefeasibleImplication(String formula) {
        if(formula == null || formula.isEmpty())
            return formula;
        
        if(formula.contains(SYMBOL_DEFEASIBLE_IMPLICATION)){
            int index = formula.indexOf(SYMBOL_DEFEASIBLE_IMPLICATION);
            formula = "(" + formula.substring(0, index) + ") " + SYMBOL_DEFEASIBLE_IMPLICATION + " (" + formula.substring(index + 2, formula.length()) + ")";
        }
        return formula;
    }
    
    private static boolean isDefeasibleFormula(String formula) {
         if(formula == null || formula.isEmpty())
            return false;
         
        return formula.contains(SYMBOL_DEFEASIBLE_IMPLICATION);
    }
    
    private static String getConnectiveList() {
        StringBuilder sb = new StringBuilder();
        sb.append( String.format("Negation : %s\n",SYMBOL_NEGATION));
        sb.append( String.format("Conjunction : %s\n",SYMBOL_OR));
        sb.append( String.format("Disjunction : %s\n",SYMBOL_AND));
        sb.append( String.format("Implication : %s\n",SYMBOL_IMPLICATION));
        sb.append( String.format("Bi-Implication : %s\n",SYMBOL_BI_IMPLICATION));
        sb.append( String.format("Defeasible Implication : %s\n",SYMBOL_DEFEASIBLE_IMPLICATION));       
        
        return sb.toString();
    }
    
}
