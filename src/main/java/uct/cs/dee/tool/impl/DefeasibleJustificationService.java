package uct.cs.dee.tool.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tweetyproject.commons.ParserException;
import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import org.tweetyproject.logics.pl.syntax.PlFormula;
import uct.cs.dee.tool.models.*;
import uct.cs.dee.tool.services.*;
import uct.cs.dee.tool.helpers.*;

/**
 * <h1>DefeasibleJustificationService<\h1>
 *  The DefeasibleJustificationService implements IJustificationService for Defeasible Reasoning.
 * 
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 * @version 1.0.1
 * @since 2023-07-03
 */
public class DefeasibleJustificationService implements IJustificationService {

    private final IEntailmentService _entailmentService;   
    private final List<List<PlFormula>> _dematerialisedJustificationFormulas;
    private final List<String> _removeKbFormulas;
              
    public DefeasibleJustificationService(IEntailmentService entailmentService ) {
        _entailmentService = entailmentService;     
        _dematerialisedJustificationFormulas = new ArrayList<List<PlFormula>>();   
        _removeKbFormulas = new  ArrayList<String>();   
    }
    
    /**
     *
     * @return
     */
    @Override
    public IEntailmentService getEntailmentService()
    {
        return _entailmentService;
    }
    
    @Override
    public ValidationResultModel<String> computeJustification() {
        try 
        { 
            if (!_entailmentService.doesKbEntailQuery())            
                return new ValidationResultModel<>(true, null);
                    
           
            if (_entailmentService.getNumberOfDiscardedRanks() == 0)
            {         
                List<PlFormula> classicalFormulas = UtilsHelper.getClassicalFormulas(_entailmentService.getKnowledgeBaseService().getKnowledgeBase());                        
                NodeModel rootNode = ClassicalJustificationHelper.computeJustification(UtilsHelper.materialise(_entailmentService.getKnowledgeBaseService().getKnowledgeBase()), UtilsHelper.materialise(_entailmentService.getKnowledgeBaseService().getQuery()));                
                                       
                for (List<PlFormula> j : rootNode.getAllJustifications())                
                    _dematerialisedJustificationFormulas.add(UtilsHelper.dematerialise(j, classicalFormulas));                                                            
            }
            else                
            {  
                int i = 0;
               
                PlBeliefSet knowledgeBase = UtilsHelper.clone(_entailmentService.getKnowledgeBaseService().getKnowledgeBase());
        
                while (i < _entailmentService.getNumberOfDiscardedRanks())
                {
                    knowledgeBase = UtilsHelper.remove(knowledgeBase, _entailmentService.getBaseRankingFormulas().getFinitlyRankedFormula(i));
                    _removeKbFormulas.add(knowledgeBase.toString());                   
                    i ++;
                }
                
                List<PlFormula> classicalFormulas = UtilsHelper.getClassicalFormulas(knowledgeBase);                        
                NodeModel rootNode = ClassicalJustificationHelper.computeJustification(UtilsHelper.materialise(knowledgeBase), UtilsHelper.materialise(_entailmentService.getKnowledgeBaseService().getQuery()));                
                
                 for (List<PlFormula> j : rootNode.getAllJustifications())                
                    _dematerialisedJustificationFormulas.add(UtilsHelper.dematerialise(j, classicalFormulas));                                               
            }
        } 
        catch (ParserException ex) 
        {
            Logger.getLogger(DefeasibleJustificationService.class.getName()).log(Level.SEVERE, "Error computing justification", ex);
            return new ValidationResultModel<>(String.format("Computing justification failed: %s", ex.getMessage()));
        }
        catch (Exception ex) {
            Logger.getLogger(DefeasibleJustificationService.class.getName()).log(Level.SEVERE, "Error computing justification", ex);
            return new ValidationResultModel<>(String.format("Computing justification failed: %s", ex.getMessage()));
        }   
        
        return new ValidationResultModel<>(true, null);
    }
    
    public List<List<PlFormula>> getDematerialisedJustificationFormulas()
    {
        return _dematerialisedJustificationFormulas;
    }
    
     /**
     *     
     * @return a display message.
    */
    @Override
    public String getDisplayMessage()
    {
        StringBuilder sb = new StringBuilder();
        
        if(!_entailmentService.doesKbEntailQuery())
        {
            sb.append("J = { empty }");
            return sb.toString();
        }
                              
        int justSize = _dematerialisedJustificationFormulas.size();
        int justCounter = 0;
        
         sb.append("J = { ");
        for (List<PlFormula> just : _dematerialisedJustificationFormulas)
        {
            sb.append(UtilsHelper.printJustificationAsCSV(just));
            justCounter++;
            if(justCounter < justSize)
                sb.append(", ");
        }
        sb.append(" }");                 
        
        return sb.toString();        
    }
           

    /**
     *     
     * @return an explanation message.
    */
    @Override
    public String getExplanationMessage()
    {                     
        if(!_entailmentService.doesKbEntailQuery())                  
            return "";
        
        StringBuilder sb = new StringBuilder();                            
        sb.append(String.format("Bacause %s is a subset of remaining %s\n", getDisplayMessage(), _entailmentService.getEntailmentResults().getRemainingFormulaList().get(0)));                 
        sb.append(String.format("Therefore %s entails %s \n", _entailmentService.getEntailmentResults().getRemainingFormulaList().get(0), _entailmentService.getKnowledgeBaseService().getQuery()));       
        sb.append(String.format("It follows that K = %s entails %s", _entailmentService.getKnowledgeBaseService().getKnowledgeBase(), _entailmentService.getKnowledgeBaseService().getQuery()));                   
          
        return sb.toString();
    }  
           
}
