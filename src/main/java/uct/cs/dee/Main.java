package uct.cs.dee;

import org.tweetyproject.logics.pl.syntax.*;

import java.io.*;
import java.util.Scanner;

import org.tweetyproject.logics.pl.parser.PlParser;
import org.tweetyproject.commons.ParserException;

/*
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */

public class Main 
{
    public static void main(String[] args) throws IOException, ParserException, Exception 
    {
        PlBeliefSet beliefSet = new PlBeliefSet();
        PlParser parser = new PlParser();
        PlBeliefSet classicalSet = new PlBeliefSet();
        
        PlFormula formulaWereCheckingFor = null;

               
        try 
        {
        	if(args == null || args.length != 2)
        	{
        		throw new Exception("Please specify the knowledge base file and the query string e.g kb.txt 'p ~> w'");
        	}
        	
        	String kbFileName = args[0];
            String formulaToCheckFor = args[1];
             
            File kbFile = new File(kbFileName);
            Scanner reader = new Scanner(kbFile);
                      
            
            if (formulaToCheckFor.contains("¬")) 
            {
                formulaToCheckFor = formulaToCheckFor.replaceAll("¬", "!");
            }
            
            formulaWereCheckingFor = (PlFormula) parser.parseFormula(reformatDefeasibleImplication(formulaToCheckFor));
            
            while (reader.hasNextLine()) 
            {
                String stringFormula = verifyInputFormula(reader.nextLine());
                
                if (stringFormula.contains("¬")) 
                {
                    stringFormula = stringFormula.replaceAll("¬", "!");
                }
                if (stringFormula.contains("~>")) 
                {
                    stringFormula = reformatDefeasibleImplication(stringFormula);
                } 
                else 
                {
                    classicalSet.add((PlFormula) parser.parseFormula(stringFormula));
                }
                
                beliefSet.add((PlFormula) parser.parseFormula(stringFormula));
            }
            
            reader.close();
            
            //RationalReasoner reasoner = new RationalReasoner(beliefSet, formulaWereCheckingFor, classicalSet, "regular");
        } 
        catch (IOException e) 
        {
        	System.out.println();
        	System.out.println(e);
        	System.out.println();
        }         
        catch (Exception e) 
        {
        	System.out.println();
            System.out.println("Input Knowledge Base file is not in correct format. Please use the following rules:");
            System.out.println("1. Each formula must be on a new line");
            System.out.println("2. All formulas must use the following syntax:");           
            System.out.println("   a. Implication            : =>");
            System.out.println("   b. Defeasible Implication : ~>");
            System.out.println("   c. Conjunction symbol     : && ");
            System.out.println("   d. Disjunction symbol     : ||");
            System.out.println("   e. Equivalence symbol     : <=>");
            System.out.println("   f. Negation symbol        : !");
            
            System.out.println();
        	System.out.println(e);
        	System.out.println();
        }
               
    }
    
    private static String verifyInputFormula(String stringFormula) 
    {
    	 if (stringFormula.contains("¬")) 
         {
             stringFormula = stringFormula.replaceAll("¬", "!");
         }
         if (stringFormula.contains("~>")) 
         {
             stringFormula = reformatDefeasibleImplication(stringFormula);
         } 
                 
        return stringFormula;
    }

    private static String reformatDefeasibleImplication(String formula) 
    {
        int index = formula.indexOf("~>");
        formula = "(" + formula.substring(0, index) + ") => (" + formula.substring(index + 2, formula.length()) + ")";
        
        return formula;
    }
}