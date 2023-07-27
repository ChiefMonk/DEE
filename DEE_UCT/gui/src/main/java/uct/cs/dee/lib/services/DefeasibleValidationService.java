/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uct.cs.dee.lib.services;

import org.tweetyproject.commons.ParserException;
import org.tweetyproject.logics.pl.parser.PlParser;
import org.tweetyproject.logics.pl.syntax.PlFormula;
import uct.cs.dee.lib.interfaces.IValidationService;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class DefeasibleValidationService implements IValidationService {

    // representation of the propositional logic and defeasible connectives
    private final static String NEGATION = "~";
    private final static String CONJUNCTION = "&&";
    private final static String DISJUNCTION = "||";
    private final static String IMPLICATION = "->";
    private final static String BI_IMPLICATION = "<->";    
    private final static String DEFEASIBLE_IMPLICATION_CONNECTIVE_1 = "|~";
    private final static String DEFEASIBLE_IMPLICATION_CONNECTIVE_2 = "~>";
    
    //@Inject private 
    private PlParser _plParser;
            
    public DefeasibleValidationService(){
        _plParser = new PlParser();
    }
    
    @Override
    public boolean IsDefeasibleStatement(String statement) {        
         if (statement == null || statement.isEmpty())
             return false;
         
         return (statement.contains(DEFEASIBLE_IMPLICATION_CONNECTIVE_1) || statement.contains(DEFEASIBLE_IMPLICATION_CONNECTIVE_2));
    }
    
    @Override
    public boolean hasValidConnective(String statement) {        
         if (statement == null || statement.isEmpty())
             return false;
         
         return (statement.contains(DEFEASIBLE_IMPLICATION_CONNECTIVE_1) ||
                 statement.contains(DEFEASIBLE_IMPLICATION_CONNECTIVE_2) || 
                 statement.contains(BI_IMPLICATION) || 
                 statement.contains(IMPLICATION) || 
                 statement.contains(DISJUNCTION) || 
                 statement.contains(CONJUNCTION) || 
                 statement.contains(NEGATION));
    }

    @Override
    public boolean isInputQueryValid(String statement) throws ParserException, Exception {         
         if(!IsDefeasibleStatement(statement))
             return false;
         
         PlParser parser = new PlParser();
         
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isInputKnowledgeBaseValid(String knowledgeBase) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // <editor-fold defaultstate="collapsed" desc="PRIVATE METHODS">
    /**
     * 
     * @param statement
     * @return org.tweetyproject.logics.pl.parser.PlParser
     */
    private PlFormula parseDefeasibleStatement(String statement) throws ParserException, Exception {
        return null;
    }
    // </editor-fold>
    
}
