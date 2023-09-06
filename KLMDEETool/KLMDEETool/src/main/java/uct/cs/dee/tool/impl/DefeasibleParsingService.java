/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uct.cs.dee.tool.impl;

import org.tweetyproject.commons.ParserException;
import org.tweetyproject.logics.pl.parser.PlParser;
import org.tweetyproject.logics.pl.syntax.PlFormula;
import uct.cs.dee.tool.services.*;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public final class DefeasibleParsingService implements IParsingService {

    private PlParser _plParser;
            
    public DefeasibleParsingService(){
        _plParser = new PlParser();
    }
    
    @Override
    public boolean isInputQueryValid(String inputQuery) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isInputKnowledgeBaseValid(String inputKnowledgeBase) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     /**
     * 
     * @param formula
     * @return org.tweetyproject.logics.pl.syntax.PlFormula
     * @throws org.tweetyproject.commons.ParserException
     * @throws java.lang.Exception 
     */
    @Override
    public PlFormula parseFormula(String formula) throws ParserException, Exception {        
        if (formula == null || formula.isEmpty())
             return null;
        
        return null;
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
    
    /**
     * 
     * @param statement
     * @return org.tweetyproject.logics.pl.parser.PlParser
     */
    private PlFormula parseClassicalStatement(String statement) throws ParserException, Exception {
        return null;
    }
    // </editor-fold>
    
}
