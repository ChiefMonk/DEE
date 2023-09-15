package uct.cs.dee.tool.services;

import uct.cs.dee.tool.models.ValidationResult;

/**
 * <h1>IExplanationService<\h1>
 * The IExplanationService interface has methods that should be implemented for a full entailment explanation.
 * 
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 * @version 1.0.1
 * @since 2023-07-03
 */
public interface IExplanationService {      
    
    /**    
     * 
     * @return A ValidationResult
     */
    public ValidationResult<String> computeExplanation();        
    
     /**
     *     
     * @return an explanation message.
     */
    public String getExplanationMessage();
}
