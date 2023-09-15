/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uct.cs.dee.tool.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tweetyproject.logics.pl.parser.PlParser;
import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import org.tweetyproject.logics.pl.syntax.PlFormula;
import uct.cs.dee.tool.models.ValidationResult;
import uct.cs.dee.tool.services.*;
import uct.cs.dee.tool.helpers.DefeasibleLogicParser;
import uct.cs.dee.tool.helpers.Utils;

/**
 * <h1>IExplanationService<\h1>
 * The IExplanationService interface has methods that should be implemented for a full entailment explanation.
 * 
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 * @version 1.0.1
 * @since 2023-07-03
 */
public class DefeasibleKnowledgeBaseService implements IKnowledgeBaseService {
    
    private static final String SYMBOL_NEGATION = "!"; 
    private static final String SYMBOL_OR = "||";
    private static final String SYMBOL_AND = "&&";
    private static final String SYMBOL_IMPLICATION = "=>";
    private static final String SYMBOL_BI_IMPLICATION = "<=>";
    public static final String SYMBOL_DEFEASIBLE_IMPLICATION = "~>";
    public static final String SYMBOL_DEFEASIBLE_IMPLICATION_ERROR = "~";
    
    private PlFormula _queryFormula = null;
    private PlBeliefSet _knowledgeBaseSet = null;  
    private List<String> _knowledgeBaseList = null;
    
    public DefeasibleKnowledgeBaseService()
    {
        _knowledgeBaseList = new ArrayList<String>();
    }
    
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
     * @return
     */
    @Override
    public List<PlFormula> getClassicalKbFormulas()
    {
       return  Utils.getClassicalFormulas(getKnowledgeBase()); 
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
            
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(DefeasibleKnowledgeBaseService.class.getName()).log(Level.SEVERE, "Error validating input query", ex);
            return new ValidationResult<>(String.format("Please defeasible query (%s) is not invalidy.\nIt must contain a defeasible implication connective (%s)", inputQuery, SYMBOL_DEFEASIBLE_IMPLICATION)); 
        } 
        
        return new ValidationResult<>(true,_queryFormula.toString());
    }
    
    /**
     * 
     * @param filePath     
     * @return ValidationResult
     */
    @Override
    public ValidationResult<String> validateKnowledgeBaseFile(String filePath) 
    {   
        String error = "Please select a valid file with a predefined defeasible knowledge base, K.";
        
        if (filePath == null || filePath.isEmpty())            
            return new ValidationResult(error); 
         
        try
        {   
            Path path = Paths.get(filePath);
           
            if(!Files.exists(path))
                return new ValidationResult(error); 
           
           List<String> fileLines = Files.readAllLines(path, StandardCharsets.UTF_8);

           return validateKnowledgeBase(fileLines);
        }
        catch (IOException ex)
        {
           Logger.getLogger(DefeasibleKnowledgeBaseService.class.getName()).log(Level.SEVERE, "Error validating input knowledge base file", ex);
           return new ValidationResult<>(String.format("%s\n%s",error, ex.getMessage()));          
        }                          
    }
    
     /**
     * 
     * @param kbString
     * @return ValidationResult
     */
    @Override
    public ValidationResult<String> validateKnowledgeBase(String kbString) 
    {                
        if (kbString == null || kbString.isEmpty())            
            return new ValidationResult("Please define a valid defeasible knowledge base, K."); 
            
        List<String> kbStatementList = new ArrayList<>();             
                       
        for (String line : kbString.split("\n"))
        {                 
            if (line == null || line.trim().isEmpty())
                continue;
                
            kbStatementList.add(line.trim());              
        }
                   
       return validateKnowledgeBase(kbStatementList);
    }
    
    /**
     * 
     * @param kbStatements
     * @return ValidationResult
     */
    @Override
    public ValidationResult<String> validateKnowledgeBase(List<String> kbStatements) 
    {
         if (kbStatements == null || kbStatements.isEmpty())            
            return new ValidationResult("Please define a valid defeasible knowledge base, K."); 
         
        _knowledgeBaseSet = new PlBeliefSet();
        PlParser classicalParser = new PlParser();
        DefeasibleLogicParser defeasibleParser = new DefeasibleLogicParser(classicalParser);
        
        int lineNumber = 1;
        String lineStatement = "";
               
        try 
        {
            for (String statement : kbStatements) 
            {
                if (statement == null || statement.isEmpty())
                    continue;

                lineStatement = statement;            
                statement = translateFormula(statement);
                
                if(statement.contains(SYMBOL_DEFEASIBLE_IMPLICATION_ERROR) && !statement.contains(SYMBOL_DEFEASIBLE_IMPLICATION))
                    throw new Exception("Invalid defeasible implication sympol");
                
                PlFormula formula = isDefeasibleFormula(statement) ? defeasibleParser.parseFormula(statement) : classicalParser.parseFormula(statement);
                
                _knowledgeBaseSet.add(formula);  
                _knowledgeBaseList.add(formula.toString());
              
                lineNumber++;
            }  
        } 
        catch (Exception ex) 
        {
           Logger.getLogger(DefeasibleKnowledgeBaseService.class.getName()).log(Level.SEVERE, "Error validating input knowledge base", ex);
           return new ValidationResult<>(String.format("The Knowledgebase statement on line %s (%s) is not valid.\nPlease use the following symbols.\n%s\n%s",lineNumber, lineStatement, getConnectiveList(),ex.getMessage()));            
        }
                   
        return new ValidationResult<>(true, null);
    }
        
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
    
    /**
     *
     * @return
     */
    @Override
     public String getValidatedKnowledgeBaseMessage() 
     {   
        StringBuilder sb = new StringBuilder();  
              
        int lineNumber = 1;        
        for (var formula : _knowledgeBaseList) 
        {   
            String lineStatement = String.format("0%s",lineNumber); 
            if(lineNumber >= 10)
                 lineStatement = String.format("%s",lineNumber); 
            
            if(lineNumber >= _knowledgeBaseSet.size())
                sb.append(String.format("%s : %s",lineStatement, formula)); 
            else
                sb.append(String.format("%s : %s\n",lineStatement, formula));
            
            lineNumber++;
        } 
        sb.append(String.format("\n\nK = %s",_knowledgeBaseSet)); 
        return sb.toString();
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
    
    
    /**
     *     
     * @return a display message.
    */
    public String getDisplayMessage()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Knowledge Base, K = %s\n", _knowledgeBaseSet.toString()));
        sb.append(String.format("Query, α = %s", _queryFormula.toString()));
        return sb.toString();        
    }    
    /**
     *     
     * @return an explanation message.
    */
    public String getExplanationMessage()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Knowledge Base, K : %s\n", _knowledgeBaseSet.toString()));
        sb.append(String.format("Query, α : %s", _queryFormula.toString()));
        return sb.toString();
    }    
}
