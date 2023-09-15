package uct.cs.dee.tool.services;

import java.util.List;
import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import org.tweetyproject.logics.pl.syntax.PlFormula;
import uct.cs.dee.tool.models.ValidationResult;

/**
 * <h1>IJustificationService<\h1>
 * The IJustificationService interface has methods that should be implemented for entailment justification.
 * 
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 * @version 1.0.1
 * @since 2023-07-03
 */
public interface IKnowledgeBaseService  {          
    
     /**
     * 
     * @param inputQuery
     * @return 
     */
    public ValidationResult<String> validateQuery(String inputQuery);
    
    /**
     * 
     * @param kbString
     * @return 
     */
    public ValidationResult<String> validateKnowledgeBase(String kbString);
    
     /**
     * 
     * @param kbStatements
     * @return 
     */
    public ValidationResult<String> validateKnowledgeBase(List<String> kbStatements);
    
     /**
     * 
     * @param filePath
     * @return 
     */
    public ValidationResult<String> validateKnowledgeBaseFile(String filePath);         
    
     /**
     * 
     * @return 
    */
    public PlBeliefSet getKnowledgeBase();
    
   /**
    * This method returns an indicator showing if the knowledge base entails the query. 
    * 
    * @return the query formula.
    */
    public PlFormula getQuery();   
    
    /**
     *
     * @return
     */
    public List<PlFormula> getClassicalKbFormulas();
    
    /**
     * 
     * @return 
    */
    public String getValidatedKnowledgeBaseMessage() ;
    
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
