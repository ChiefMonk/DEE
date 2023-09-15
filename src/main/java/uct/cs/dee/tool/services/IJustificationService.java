package uct.cs.dee.tool.services;

import uct.cs.dee.tool.models.ValidationResult;

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
     * 
     * @return A ValidationResult<String>    
     */
    public ValidationResult<String> computeJustification();        
    
    /**
    *
    * @return
    */
    public IEntailmentService getEntailmentService();
    
    /**
     *     
     * @return a display message.
    */
    public String getDisplayMessage(); 
    
     /**
     *     
     * @return an explanation message.
     */
    public String getExplanationMessage();  
}

