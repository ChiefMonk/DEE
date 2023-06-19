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
	
	public static void main(String[] args) 
	{
		 String fileName = args.length >= 1 ? args[0] : "penguins.txt";
         String rawQuery = args.length == 2 ? args[1] : "p|~w";
         
         try 
         {
            // KnowledgeBaseReader reader = new KnowledgeBaseReader(KNOWLEDGE_BASE_DIR);
             DefeasibleKnowledgeBase knowledgeBase = ExtensionUtils.parseInputKnowledgeBase(fileName);

             System.out.println("----------------------------");
             System.out.println(knowledgeBase);
             System.out.println("----------------------------");

             ArrayList<PlBeliefSet> ranked_KB = new BaseRankBuilder().construct(knowledgeBase);

             System.out.println("BaseRank:");
             System.out.println("----------------------------");
             Object[] ranked_KB_Arr = ranked_KB.toArray();
             System.out.println("∞" + " :\t" + ranked_KB_Arr[ranked_KB_Arr.length - 1]);
             
             for (int rank_Index = ranked_KB_Arr.length - 2; rank_Index >= 0; rank_Index--) 
             {
                     System.out.println(rank_Index + " :\t" + ranked_KB_Arr[rank_Index]);
             }

             System.out.println("----------------------------");
             System.out.println("Testing query:\t" + rawQuery);

             PlParser parser = new PlParser();
             Implication query = (Implication) parser.parseFormula(ExtensionUtils.materialiseDefeasibleImplication(rawQuery));

             System.out.println("----------------------------");

             // Construct backend representations
             RankedInterpretation rationalClosureModel = new ModelRankBuilder().construct(knowledgeBase);
             RankedInterpretation rationalClosureModelBaseRank = new ModelBaseRankBuilder().construct(knowledgeBase);          
             RankedFormulasInterpretation rationalClosureFormulaModel = new FormulaRankBuilder().construct(knowledgeBase);
             RankedFormulasInterpretation rationalClosureCumulativeFormulaModel = new CumulativeFormulaRankBuilder().construct(knowledgeBase);
           

             System.out.println("Rational Closure Ranked Model:\n" + rationalClosureModel);
             System.out.println("----------------------------");
             System.out.println("Rational Closure Ranked Model using BaseRank:\n"
                             + rationalClosureModelBaseRank);
             System.out.println("----------------------------");
             System.out.println("Rational Closure Formula Model:\n" + rationalClosureFormulaModel);
             System.out.println("----------------------------");
             System.out.println("Rational Closure Formula Model Ranked Interpretation:\n" + rationalClosureFormulaModel.getRankedInterpretation());

             System.out.println("----------------------------");
             System.out.println("Rational Closure Cumulative Formula Model:\n" + rationalClosureCumulativeFormulaModel);
             System.out.println("----------------------------");
             System.out.println("Rational Closure Cumulative Formula Model Ranked Interpretation:\n"
                             + rationalClosureCumulativeFormulaModel.getRankedInterpretation());
            
             System.out.println("Query Results:");

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
                     String template = "%-40s%s";
                     System.out.println(String.format(template, checker.getClass().getSimpleName(), checker.queryDefeasible(query)));
             }

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
	}
}
