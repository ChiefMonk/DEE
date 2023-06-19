package uct.cs.dee.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.tweetyproject.commons.ParserException;
import org.tweetyproject.logics.pl.syntax.Contradiction;
import org.tweetyproject.logics.pl.syntax.Implication;
import org.tweetyproject.logics.pl.syntax.Negation;
import org.tweetyproject.logics.pl.syntax.PlFormula;
import org.tweetyproject.logics.pl.semantics.NicePossibleWorld;
import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import org.tweetyproject.logics.pl.syntax.PlSignature;
import org.tweetyproject.logics.pl.parser.*;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import uct.cs.dee.enums.FormularType;

@Extension
public class ExtensionUtils 
{		
	public static boolean isEmptyOrNull(@This String inpuString)
	{
		if(inpuString != null && !inpuString.trim().isEmpty())
		{
			return false;
		}
		
		return true;
	}
	
	public static boolean isEmptyOrNullWithThrow(@This String inpuString) throws ParserException 
	{
		if(isEmptyOrNull(inpuString))
		{
			throw new ParserException(Constants.ErrorMessages.InValidFormula);
		}
		
		return true;
	}
	
	/**
     * Parse a collection of String formulas
     * @param formulas - collection containing all the formulas as Strings
     * @return DefeasibleFormulaCollection containing all the parsed formulas
     * @throws ParserException
     * @throws IOException
     */
    public static KnowledgeBaseFormulaSet parseFormulas(List<String> formulas) throws ParserException, IOException
    {
    	KnowledgeBaseFormulaSet collection = new KnowledgeBaseFormulaSet();
    	
        for(String rawFormula : formulas)
        {
            // Defeasible implication
            if(isDefeasible(rawFormula))
            {               
                collection.addFormula(FormularType.Defeasible, parseDefeasibleFormula(rawFormula));
            }
            // Propositional formula
            else
            {               
                collection.addFormula(FormularType.Propositional, parsePropositionalFormula(rawFormula));               
            }
        }
        
        return collection;
    }
    
    /**
     * Parse a single propositional formula
     * @param propositionalFormula
     * @return PlFormula
     * @throws ParserException
     * @throws IOException
     */
    public static PlFormula parsePropositionalFormula(String propositionalFormula) throws ParserException, IOException{
        PlParser parser = new PlParser();
        return parser.parseFormula(propositionalFormula);
    }
    
    /**
     * Parse a single defeasible formula (containing a twiddle connective)
     * @param defeasibleFormula
     * @return PlFormula - the parsed formula is a materialised defeasible implication
     * @throws ParserException
     * @throws IOException
     */
    public static PlFormula parseDefeasibleFormula(String defeasibleFormula) throws ParserException, IOException
    {
        PlParser parser = new PlParser();
        
        if(!isDefeasible(defeasibleFormula))
        {
            throw new ParserException("Expected a defeasible formula");
        }
        
        return parser.parseFormula(materialiseDefeasibleImplication(defeasibleFormula));
    }
    
    /**
     * Separates string of formulas into individual formulas.
     * Currently used for web interface.
     * @param data The string to parse
     * @return Array of string formulas
     */
    public static ArrayList<String> readFormulasFromString(String data) throws Exception 
    {
    	ArrayList<String> formulas = new ArrayList<String>();
    	Scanner reader = null;
    	try
    	{
    		reader = new Scanner(data);
    		while (reader.hasNext())
    		{
    			formulas.add(reader.nextLine());
    	    }
    	}
    	catch(Exception e)
    	{
    		throw e;
    	}
    	finally
    	{
    		if(reader != null)
    			reader.close();
    	}             
        
        return formulas;
    }
	
	/**
     * Determines whether a string formula is defeasible or not
     * @param formula
     * @return boolean indicating whether a formula is defeasible (true) or not (false)
	 * @throws Exception 
     */
	public static boolean isDefeasible(@This String formula) throws ParserException 
	{
		if(ExtensionUtils.isEmptyOrNull(formula))
		{
			throw new ParserException(Constants.ErrorMessages.InValidFormula);
		}
		
		return formula.contains(Constants.Symbols.Twiddle);		
	}
	
	 /**
     * Materialises given defeasible implication (changes twiddle to material implication)
     * 
     * @param defeasibleFormula
     * @return The materialized implication
     */
	public static String materialiseDefeasibleImplication(@This String defeasibleFormula) throws ParserException 
	{
		if(ExtensionUtils.isEmptyOrNull(defeasibleFormula))
		{
			throw new ParserException(Constants.ErrorMessages.InValidFormula);
		}
		
		return defeasibleFormula.replace(Constants.Symbols.Twiddle, Constants.Symbols.PropositionalImplication);
	}
	
	/**
     * Normalises a purely classical propositional as a defeasible implication
     * @param formula - classical propositional formula
     * @return defeasible implication
     */
    public static Implication normalizePropositionalFormula(PlFormula formula) throws ParserException 
    {
    	if(formula == null)
		{
			throw new ParserException(Constants.ErrorMessages.InValidFormula);
		}
    	
        return new Implication(new Negation(formula), new Contradiction());
    }
    
    /**
     * Combine ranked PlBeliefSets into single PlBeliefSet
     * 
     * @param rankSets The ranks to combine
     * @return The combined rank set
     */
    public static PlBeliefSet combineFormulaRanks(List<PlBeliefSet> rankSets) 
    {
        PlBeliefSet combinedSet = new PlBeliefSet();
        
        if(rankSets == null || rankSets.isEmpty())
        	return combinedSet;
        
        for (PlBeliefSet rank : rankSets) 
        {
        	combinedSet.addAll(rank);
        }
        
        return combinedSet;
    }
    
    /**
     * Combine ranked PlBeliefSets into single PlBeliefSet
     * 
     * @param rankSets The ranks to combine
     * @return The combined rank set
     */
    public static PlBeliefSet combineFormulaRanks(PlBeliefSet[] rankSets) 
    {       
        if(rankSets == null || rankSets.length == 0)
        	return  new PlBeliefSet();
        
        return combineFormulaRanks(Arrays.asList(rankSets));              
    }
    
    
    
    /**
     * Generates models of a formula
     * 
     * @param formula The formula
     * @param signature The atom signature
     * @return The models
     */
    public static Set<NicePossibleWorld> getModels(PlFormula formula, PlSignature signature)
    {
        HashSet<NicePossibleWorld> result = new HashSet<NicePossibleWorld>();
        
        for(NicePossibleWorld world: NicePossibleWorld.getAllPossibleWorlds(signature.toCollection()))
        {
            if(world.satisfies(formula))
            {
                result.add(world);
            }
        }
        
        return result;
    }
    
    /**
     * Reads in and parses the specified knowledge base text file
     * @param kbFileName - path to the knowledge base file
     * @return DefeasibleKnowledgeBase instance
     * @throws FileNotFoundException
     * @throws ParserException
     * @throws IOException
     */   
    public static DefeasibleKnowledgeBase parseInputKnowledgeBase(String kbFileName) throws FileNotFoundException, ParserException, IOException 
    {
    	Scanner reader = new Scanner( new File(kbFileName));            
        ArrayList<String> formularList = new ArrayList<String>();
             
        while (reader.hasNext()) 
        {
        	formularList.add(reader.nextLine());
        }
            
        reader.close();
        
        KnowledgeBaseFormulaSet parsedFormulas = parseFormulas(formularList);
        
        return new DefeasibleKnowledgeBase(parsedFormulas);    	      
    }
}
