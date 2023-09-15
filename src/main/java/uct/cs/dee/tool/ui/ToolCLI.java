/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uct.cs.dee.tool.ui;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import uct.cs.dee.tool.models.ValidationResult;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class ToolCLI {
     public static void main(String[] args)
     {      
       try 
        {            
            if(args == null || args.length != 2)
            {
        	throw new Exception("Please specify the knowledge base file path and the query string e.g kb.txt 's ~> w'");
            }
        	
            String knowledgeBaseFilePath = args[0];
            String queryString = args[1];
            
            Path path = Paths.get(knowledgeBaseFilePath); 
            List<String> fileLines = Files.readAllLines(path, StandardCharsets.UTF_8);
                      
            ValidationResult<String> result = UIManager.computeEntailmentAndExplanation(fileLines, queryString);

            if(!result.isValid())
            {
                consoleWriteLine("ERROR", result.getMessage());
                return;
            }
        
            // validated knowledge base
            consoleWriteLine("THE KNOWLEDGE BASE AND DEFEASIBLE QUERY", UIManager.KnowledgeBaseService().getValidatedKnowledgeBaseMessage());

            // base ranking
            consoleWriteLine("BASE-RANKINGS OF STATEMENTS", UIManager.EntailmentService().getBaseRankingFormulasMessage());

            // discarded formulas       
            consoleWriteLine("DISCARDED BASE-RANKINGS", UIManager.EntailmentService().getDiscardedFormulaListMessage());

            // entailment       
            consoleWriteLine("DEFEASIBLE ENTAILMENT", UIManager.EntailmentService().getDisplayMessage());

            // justification       
            consoleWriteLine("DEFEASIBLE JUSTIFICATION", UIManager.JustificationService().getDisplayMessage());

            // explanation      
            consoleWriteLine("EXPLANATION", UIManager.ExplanationService().getExplanationMessage());           
        } 
        catch (IOException e) 
        {
        	System.out.println();
        	System.out.println(String.format("An error occured: %s", e));
        	System.out.println();
        }         
        catch (Exception e) 
        {
        	System.out.println();
        	System.out.println(String.format("An error occured: %s", e));
        	System.out.println();
        }
    }
     
    private static void consoleWriteLine(String heading, String message)
    {     
        System.out.println();
        System.out.println(String.format("===== %s =====", heading));
        System.out.println(message);
    }
}
