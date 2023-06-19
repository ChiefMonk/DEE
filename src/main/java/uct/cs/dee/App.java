package uct.cs.dee;

import java.io.*;
import java.util.*;

import org.tweetyproject.logics.pl.parser.PlParser;
import org.tweetyproject.commons.ParserException;
import org.tweetyproject.logics.pl.syntax.Implication;
import org.tweetyproject.logics.pl.syntax.PlBeliefSet;

import uct.cs.dee.builders.*;
import uct.cs.dee.utils.*;
import uct.cs.dee.reasoners.*;

public class App 
{
	
	public static void main(String[] args) throws FileNotFoundException, ParserException, IOException, Exception 
	{		         
         try 
         {
        	if(args == null || args.length != 2)
         	{
         		throw new IOException("Please specify the knowledge base file and the query string e.g kb.txt 'p ~> w'");
         	}
        	 
        	 String fileName = args[0];
             String rawQuery = args[1];
             
            // KnowledgeBaseReader reader = new KnowledgeBaseReader(KNOWLEDGE_BASE_DIR);
             DefeasibleKnowledgeBase knowledgeBase = ExtensionUtils.parseInputKnowledgeBase(fileName);

             System.out.println("----------------------------");
             System.out.println(knowledgeBase);
             System.out.println("----------------------------");

             ArrayList<PlBeliefSet> ranked_KB = new BaseRankBuilder().construct(knowledgeBase);

             System.out.println("\nBaseRank:");
             System.out.println("---------------------------------------");             
             Object[] ranked_KB_Arr = ranked_KB.toArray();
             System.out.println("∞" + " :\t" + ranked_KB_Arr[ranked_KB_Arr.length - 1]);
             
             for (int rank_Index = ranked_KB_Arr.length - 2; rank_Index >= 0; rank_Index--) 
             {
            	 System.out.println(rank_Index + " :\t" + ranked_KB_Arr[rank_Index]);
             }

             System.out.println("---------------------------------------");
             System.out.println("\nTesting query:\t" + rawQuery);

             PlParser parser = new PlParser();
             Implication query = (Implication) parser.parseFormula(ExtensionUtils.materialiseDefeasibleImplication(rawQuery));

             System.out.println("----------------------------");

             // Construct backend representations
             RankedInterpretation rationalClosureModel = new ModelRankBuilder().construct(knowledgeBase);
             RankedInterpretation rationalClosureModelBaseRank = new ModelBaseRankBuilder().construct(knowledgeBase);          
             RankedFormulasInterpretation rationalClosureFormulaModel = new FormulaRankBuilder().construct(knowledgeBase);
             RankedFormulasInterpretation rationalClosureCumulativeFormulaModel = new CumulativeFormulaRankBuilder().construct(knowledgeBase);
           

             System.out.println("\nRational Closure Ranked Model:\n" + rationalClosureModel);
             System.out.println("----------------------------");
             
             System.out.println("\nRational Closure Ranked Model using BaseRank:\n" + rationalClosureModelBaseRank);
             System.out.println("----------------------------");
             
             System.out.println("\nRational Closure Formula Model:\n" + rationalClosureFormulaModel);
             System.out.println("----------------------------");
             
             System.out.println("\nRational Closure Formula Model Ranked Interpretation:\n" + rationalClosureFormulaModel.getRankedInterpretation());
             System.out.println("----------------------------");
             
             System.out.println("\nRational Closure Cumulative Formula Model:\n" + rationalClosureCumulativeFormulaModel);
             System.out.println("----------------------------");
             
             System.out.println("\nRational Closure Cumulative Formula Model Ranked Interpretation:\n" + rationalClosureCumulativeFormulaModel.getRankedInterpretation());            
             System.out.println("----------------------------");
             
             System.out.println("\nQuery Results:");

             // Create reasoners
             IDefeasibleReasoner[] checkers = 
            	 {
                             new RationalDirectReasoner(ranked_KB, knowledgeBase),
                             new RationalRegularReasoner(ranked_KB),
                             new RationalIndexingReasoner(ranked_KB),
                             new RationalBinaryReasoner(ranked_KB),
                             new RationalBinaryIndexingReasoner(ranked_KB),
                             new MinimalRankedEntailmentReasoner(rationalClosureModel),
                             new MinimalRankedEntailmentFormulaReasoner(rationalClosureFormulaModel),
                             new MinimalRankedEntailmentCumulativeFormulaReasoner(rationalClosureCumulativeFormulaModel)
             };

             // Query each reasoner
             for (IDefeasibleReasoner checker : checkers) 
             {
                     String template = "%-50s%s";
                     System.out.println(String.format(template, checker.getClass().getSimpleName(), checker.queryDefeasible(query)));
             }
             System.out.println("----------------------------");

	     } 
	     catch (FileNotFoundException e) 
	     {
	    	 System.out.println("Could not find knowledge base file!");
	         e.printStackTrace();
	     } 
	     catch (ParserException e) 
	     {
	          System.out.println("Invalid formula in knowledge base!");
	         e.printStackTrace();
	     } 
	     catch (IOException e) 
	     {
	    	 System.out.println("IO issue during formula parsing!");
	         e.printStackTrace();
	     }
         catch (Exception e) 
	     {
	    	 System.out.println("General issue during formula parsing!");
	         e.printStackTrace();
	     }
	}
}
