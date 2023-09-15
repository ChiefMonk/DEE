package uct.cs.dee.tool.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.tweetyproject.commons.ParserException;
import uct.cs.dee.tool.models.ValidationResult;
import uct.cs.dee.tool.services.*;

/**
 * <h1>IExplanationService<\h1>
 * The IExplanationService interface has methods that should be implemented for a full entailment explanation.
 * 
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 * @version 1.0.1
 * @since 2023-07-03
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
        String justification = _justificationService.getExplanationMessage();
        String entailment = _justificationService.getEntailmentService().getExplanationMessage();
        String knowledgeBase = _justificationService.getEntailmentService().getKnowledgeBaseService().getExplanationMessage();                 
        
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s\n", knowledgeBase));
        sb.append(String.format("%s\n", entailment));
        sb.append(String.format("%s", justification));
                 
       return sb.toString();                       
    }
    
}
