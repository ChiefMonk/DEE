package uct.cs.dee.tool.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.tweetyproject.commons.ParserException;
import uct.cs.dee.tool.models.ValidationResult;
import uct.cs.dee.tool.services.*;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class DefeasibleExplanationService implements IExplanationService {

    protected final IJustificationService _justificationService;   
     
     public DefeasibleExplanationService(IJustificationService justificationService ) {
        _justificationService = justificationService;             
    }   
    
    @Override
    public ValidationResult<String> computeExplanation() 
    {
        try 
        { 
           
        } 
        catch (ParserException ex) {
            Logger.getLogger(DefeasibleJustificationService.class.getName()).log(Level.SEVERE, null, ex);
             return new ValidationResult<>(String.format("Computing justification failed: %s", ex.getMessage()));
        }
        catch (Exception ex) {
            Logger.getLogger(DefeasibleJustificationService.class.getName()).log(Level.SEVERE, null, ex);
            return new ValidationResult<>(String.format("Computing justification failed: %s", ex.getMessage()));
        }   
        
        return new ValidationResult<>(true, null);
    }

    /**
     *
     * @return
     */
    @Override
    public String getExplanationMessage() 
    {        
        String justification = _justificationService.getDisplayMessage();
        String entailment = _justificationService.getEntailmentService().getDisplayMessage();
        String knowledgeBase = _justificationService.getEntailmentService().getKnowledgeBaseService().getDisplayMessage();                 
        
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s\n", knowledgeBase));
        sb.append(String.format("%s\n", entailment));
        sb.append(String.format("%s", justification));
          
       return sb.toString();                       
    }
    
}
