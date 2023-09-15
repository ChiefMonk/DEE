package uct.cs.dee.tool.services;

import uct.cs.dee.tool.models.ValidationResultModel;

/**
 * <h1>IJustificationService<\h1>
 * The IJustificationService interface has methods that should be implemented for entailment justification.
 * 
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 * @version 1.0.1
 * @since 2023-07-03
 */
public interface IJustificationService {
   
     /**    
     * compute justification
     * @return A ValidationResult<String>    
     */
    public ValidationResultModel<String> computeJustification();        
    
    /**
    * Get EntailmentService
    * @return
    */
    public IEntailmentService getEntailmentService();
    
    /**
     * Get display message
     * @return a display message.
    */
    public String getDisplayMessage(); 
    
     /**
     * Get explanation message    
     * @return an explanation message.
     */
    public String getExplanationMessage();  
}

