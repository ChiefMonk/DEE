package uct.cs.dee.tool.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tweetyproject.commons.ParserException;
import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import org.tweetyproject.logics.pl.syntax.PlFormula;
import uct.cs.dee.tool.models.Node;
import uct.cs.dee.tool.models.ValidationResult;
import uct.cs.dee.tool.services.*;
import uct.cs.dee.tool.helpers.ClassicJust;
import uct.cs.dee.tool.helpers.Utils;

/**
 * <h1>IExplanationService<\h1>
 * The IExplanationService interface has methods that should be implemented for a full entailment explanation.
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
    public ValidationResult<String> computeJustification() {
        try 
        { 
            if (!_entailmentService.doesKbEntailQuery())            
                return new ValidationResult<>(true, null);
                    
           
            if (_entailmentService.getNumberOfDiscardedRanks() == 0)
            {         
                List<PlFormula> classicalFormulas = Utils.getClassicalFormulas(_entailmentService.getKnowledgeBaseService().getKnowledgeBase());                        
                Node rootNode = ClassicJust.computeJustification(Utils.materialise(_entailmentService.getKnowledgeBaseService().getKnowledgeBase()), Utils.materialise(_entailmentService.getKnowledgeBaseService().getQuery()));                
                                       
                for (List<PlFormula> j : rootNode.getAllJustifications())                
                    _dematerialisedJustificationFormulas.add(Utils.dematerialise(j, classicalFormulas));                                                            
            }
            else                
            {  
                int i = 0;
               
                PlBeliefSet knowledgeBase = Utils.clone(_entailmentService.getKnowledgeBaseService().getKnowledgeBase());
        
                while (i < _entailmentService.getNumberOfDiscardedRanks())
                {
                    knowledgeBase = Utils.remove(knowledgeBase, _entailmentService.getBaseRankingFormulas().getFinitlyRankedFormula(i));
                    _removeKbFormulas.add(knowledgeBase.toString());                   
                    i ++;
                }
                
                List<PlFormula> classicalFormulas = Utils.getClassicalFormulas(knowledgeBase);                        
                Node rootNode = ClassicJust.computeJustification(Utils.materialise(knowledgeBase), Utils.materialise(_entailmentService.getKnowledgeBaseService().getQuery()));                
                
                 for (List<PlFormula> j : rootNode.getAllJustifications())                
                    _dematerialisedJustificationFormulas.add(Utils.dematerialise(j, classicalFormulas));                                               
            }
        } 
        catch (ParserException ex) 
        {
            Logger.getLogger(DefeasibleJustificationService.class.getName()).log(Level.SEVERE, "Error computing justification", ex);
            return new ValidationResult<>(String.format("Computing justification failed: %s", ex.getMessage()));
        }
        catch (Exception ex) {
            Logger.getLogger(DefeasibleJustificationService.class.getName()).log(Level.SEVERE, "Error computing justification", ex);
            return new ValidationResult<>(String.format("Computing justification failed: %s", ex.getMessage()));
        }   
        
        return new ValidationResult<>(true, null);
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
            sb.append(Utils.printJustificationAsCSV(just));
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
