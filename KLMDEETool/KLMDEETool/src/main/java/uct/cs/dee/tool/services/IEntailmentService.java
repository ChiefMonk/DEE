package uct.cs.dee.tool.services;

import uct.cs.dee.tool.models.MinimalRankedFormulas;
import uct.cs.dee.tool.models.RationalClosureResults;
import uct.cs.dee.tool.models.ValidationResult;

/**
 * <h1>IEntailmentService <\h1>
 * The IEntailmentService interface has methods that should be implemented for entailment determination.
 * 
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 * @version 1.0.1
 * @since 2013-06-01
 */
public interface IEntailmentService {      
     /**
    * This method returns an indicator showing if the knowledge base entails the query. 
    * 
    * @return ValidationResult.
    */
    public ValidationResult<String> computeEntailment();
     
    /**
    * This method returns the IKnowledgeBaseService. 
    * 
    * @return an IKnowledgeBaseService.
    */
    public IKnowledgeBaseService getKnowledgeBaseService();
       
    /**
    * This method returns an indicator showing if the knowledge base entails the query. 
    * 
    * @return a Boolean.
    */
    public boolean doesKbEntailQuery();
    
    /**
    * This method returns the number of discarded ranks during entailment determination. 
    * 
    * @return an Integer.
    */
    public int getNumberOfDiscardedRanks();  
    
     /**
     *
     * @return
     */
    public  MinimalRankedFormulas getBaseRankingFormulas();
    
    /**
     *
     * @return
    */
    public  RationalClosureResults getEntailmentResults();
    
    /**
     *
     * @return
     */
    public  String getBaseRankingFormulasMessage();
    
    /**
     *
     * @return
     */
    public String getDiscardedFormulaListMessage();
    
    /**
     *
     * @param addEndline
     * @return
     */
    public String getRemainingFormulaListMessage(boolean addEndline);
    
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
