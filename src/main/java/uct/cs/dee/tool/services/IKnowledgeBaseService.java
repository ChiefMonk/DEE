package uct.cs.dee.tool.services;

import java.util.List;
import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import org.tweetyproject.logics.pl.syntax.PlFormula;
import uct.cs.dee.tool.models.ValidationResultModel;

/**
 * <h1>IKnowledgeBaseService<\h1>
 * The IKnowledgeBaseService interface has methods that should be implemented for KnowledgeBase management.
 * 
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 * @version 1.0.1
 * @since 2023-07-03
 */
public interface IKnowledgeBaseService  {          
    
     /**
     * * Validate the input Query
     * @param inputQuery
     * @return 
     */
    public ValidationResultModel<String> validateQuery(String inputQuery);
    
    /**
     * Validate the input knowledge base string
     * @param kbString
     * @return 
     */
    public ValidationResultModel<String> validateKnowledgeBase(String kbString);
    
     /**
    * Validate the input knowledge base list
     * @param kbStatements
     * @return 
     */
    public ValidationResultModel<String> validateKnowledgeBase(List<String> kbStatements);
    
     /**
     * Validate the input knowledge base file
     * @param filePath
     * @return 
     */
    public ValidationResultModel<String> validateKnowledgeBaseFile(String filePath);         
    
     /**
     * Get the knowledge base
     * @return 
    */
    public PlBeliefSet getKnowledgeBase();
    
   /**
    *  Get the qyery
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
