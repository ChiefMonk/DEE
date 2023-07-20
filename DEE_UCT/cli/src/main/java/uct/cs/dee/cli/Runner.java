/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package uct.cs.dee.cli;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import uct.cs.dee.lib.*;
import uct.cs.dee.lib.models.*;
import org.tweetyproject.logics.pl.parser.PlParser;
import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import org.tweetyproject.logics.pl.syntax.PlFormula;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class Runner {

 public static void main(String[] args) 
    {
        try 
        {
            PlBeliefSet knowledgeBase = new PlBeliefSet();
            PlFormula query = null;
            PlParser classicalParser = new PlParser();
            DefeasibleLogicParser defeasibleParser = new DefeasibleLogicParser(classicalParser);
            
            // args[0] = Defeasible Knowledge Base
            //String inputKnowledgeBasePath = "src/main/resources/example/input/SpecialPenguinExample.txt";       //Default Knowledge Base
            String inputKnowledgeBasePath = "kb.txt";  
            //if (args == null || args[0] != null)
            //    inputKnowledgeBasePath = args[0];
            Scanner scanner = new Scanner(new File(inputKnowledgeBasePath));
            
            while (scanner.hasNextLine())
            {
                String inputFormula = scanner.nextLine();
                if (inputFormula.contains("~>"))
                    knowledgeBase.add(defeasibleParser.parseFormula(inputFormula));
                else
                    knowledgeBase.add(classicalParser.parseFormula(inputFormula));
            }
            
            System.out.println("Knowledge Base:\n" + knowledgeBase);

            // args[1] = Query
            //if (args[1] == null)
            //    throw new Exception("Invalid query.");
            //else
            //   query = defeasibleParser.parseFormula(args[1]);
            
            //query = defeasibleParser.parseFormula("Robin~>Wings");
            //query = defeasibleParser.parseFormula("Penguin~>Wings");
            //query = defeasibleParser.parseFormula("SpecialPenguin~>Fly");
            query = defeasibleParser.parseFormula("Penguin~>Robin");
            
            System.out.println("Query:\n" + query.toString());
            
            computeDefeasibleExplanation(knowledgeBase, query);
            
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void computeDefeasibleExplanation(PlBeliefSet knowledgeBase, PlFormula query) throws Exception
    {
        List<PlFormula> classicalFormulas = Utils.getClassicalFormulas(knowledgeBase);
        
        RationalClosureResults rationalClosure= RationalClosure.computeRationalClosure(knowledgeBase, query);
        System.out.println(rationalClosure);
        
        if (!rationalClosure.entailmentsHolds())
        {
            System.out.println("Entailment does not hold.");
            System.out.println("Following remaining formulas does not entail the query: \n" + rationalClosure.getRemainingFormulas());
            return;
        }
        
        int ranksRemoved = rationalClosure.getRanksRemoved();
        
        if (ranksRemoved == 0)
        {
            Node rootNode = ClassicJust.computeJustification(Utils.materialise(knowledgeBase), Utils.materialise(query));
            List<List<PlFormula>> justifiactions = rootNode.getAllJustifications();
            List<List<PlFormula>> dematerialisedJustification = new ArrayList<List<PlFormula>>();
            for (List<PlFormula> justification : justifiactions)
            {
                dematerialisedJustification.add(Utils.dematerialise(justification, classicalFormulas));
            }
            System.out.println("<<Final Justification>>");
            for (List<PlFormula> newJust : dematerialisedJustification)
            {
                System.out.println(Utils.printJustificationAsCSV(newJust));
            }
            return;
        }
        
        int i = 0;
        
        while (i < ranksRemoved)
        {
            knowledgeBase = Utils.remove(knowledgeBase, rationalClosure.getMinimalRanking().getFinitlyRankedFormula(i));
            System.out.println("Removing rank " + i + " =====");
            System.out.println(knowledgeBase);
            i ++;
        }
        
        Node rootNode = ClassicJust.computeJustification(Utils.materialise(knowledgeBase), Utils.materialise(query));
        List<List<PlFormula>> justifiactions = rootNode.getAllJustifications();
        List<List<PlFormula>> dematerialisedJustification = new ArrayList<List<PlFormula>>();
        for (List<PlFormula> justification : justifiactions)
        {
            dematerialisedJustification.add(Utils.dematerialise(justification, classicalFormulas));
        }
        
        System.out.println("<<Final Justification>>");
        for (List<PlFormula> newJust : dematerialisedJustification)
        {
            System.out.println(Utils.printJustificationAsCSV(newJust));
        }
        
    }
    
}
