package uct.cs.dee.tool.ui;

import java.util.List;
import uct.cs.dee.tool.impl.*;
import uct.cs.dee.tool.models.ValidationResultModel;
import uct.cs.dee.tool.services.*;

/**
 * <h1>UIManager<\h1>
 * The UIManager manager all user interactions with the GUI and CLI.
 * 
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 * @version 1.0.1
 * @since 2023-07-03
 */
public class UIManager { 
    
    // static variables
    private static IKnowledgeBaseService _knowledgeBaseService;
    private static IEntailmentService _entailmentService;
    private static IJustificationService _justificationService;
    private static IExplanationService _explanationService;
    
    private static void initialiseServices() {       
        _knowledgeBaseService = new DefeasibleKnowledgeBaseService();                
        _entailmentService = new DefeasibleEntailmentService(_knowledgeBaseService);                
        _justificationService = new DefeasibleJustificationService(_entailmentService);                
        _explanationService = new DefeasibleExplanationService(_justificationService);
    }
    
    // <editor-fold defaultstate="collapsed" desc="IKnowledgeBaseService">
    
     /**
      * Validate the KnowledgeBase string
     * @param kbString
     * @return ValidationResultModel
     */
    public static ValidationResultModel<String> validateKnowledgeBase(String kbString) {            
       
        initialiseServices();
       
       return _knowledgeBaseService.validateKnowledgeBase(kbString);
    }
    
      /**
     * Validate the KnowledgeBase List
     * @param kbStatements
     * @return ValidationResultModel
     */
    public static ValidationResultModel<String> validateKnowledgeBase(List<String> kbStatements) {        

        initialiseServices();
        
       return _knowledgeBaseService.validateKnowledgeBase(kbStatements);
    }
    
      /**
     * * Validate the KnowledgeBase File
     * @param filePath
     * @return ValidationResultModel
     */
    public static ValidationResultModel<String> validateKnowledgeBaseFile(String filePath) {            
       
        initialiseServices();
       
       return _knowledgeBaseService.validateKnowledgeBaseFile(filePath);
    }
    
      /**
     * * Validate the input Query
     * @param query
     * @return ValidationResultModel
     */
    public static ValidationResultModel<String> validateQuery(String query) {               
       return _knowledgeBaseService.validateQuery(query);
    }
    
      /**
     * execute Entailment And Explanation
     * @param kbString
     * @param query
     * @return ValidationResultModel
     */
    public static ValidationResultModel<String> computeEntailmentAndExplanation(String kbString, String query) { 
        
         initialiseServices();
         
        // validate the knowledge base string
        ValidationResultModel<String> validateResult = validateKnowledgeBase(kbString);        
        if(!validateResult.isValid())
            return validateResult;             
        
        return executeEntailmentAndExplanation(query);
    }
    
      /**
     * execute Entailment And Explanation
     * @param kbStatements
     * @param query
     * @return ValidationResultModel
     */
     public static ValidationResultModel<String> computeEntailmentAndExplanation(List<String> kbStatements, String query) { 
        
         initialiseServices();
         
        // validate the knowledge base string
        ValidationResultModel<String> validateResult = validateKnowledgeBase(kbStatements);        
        if(!validateResult.isValid())
            return validateResult;             
        
        return executeEntailmentAndExplanation(query);
    }
    
     /**
     * execute Entailment And Explanation
     * @param query
     * @return ValidationResultModel
     */
    private static ValidationResultModel<String> executeEntailmentAndExplanation(String query) 
    { 
     // validate the query string
       ValidationResultModel<String>  validateResult = validateQuery(query);        
        if(!validateResult.isValid())
            return validateResult;
                
        // compute entailment
        validateResult = EntailmentService().computeEntailment();        
        if(!validateResult.isValid())
            return validateResult; 
        
        // compute justification
        validateResult = JustificationService().computeJustification();
        if(!validateResult.isValid())
            return validateResult;
        
        return ExplanationService().computeExplanation();
    }
    
    /**
     * get the KnowledgeBaseService    
     * @return IKnowledgeBaseService
    */
    public static IKnowledgeBaseService KnowledgeBaseService() {
        return _knowledgeBaseService;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="EntailmentService">
     /**
     * get the EntailmentService    
     * @return IEntailmentService
    */
    public static IEntailmentService EntailmentService() {
        return _entailmentService;
    }
    // </editor-fold>
     
    // <editor-fold defaultstate="collapsed" desc="IJustificationService">
     /**
     * get the JustificationService    
     * @return IJustificationService
    */
    public static IJustificationService JustificationService() {
        return _justificationService;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="IExplanationService">
     /**
     * get the ExplanationService    
     * @return IExplanationService
    */
    public static IExplanationService ExplanationService() {
        return _explanationService;
    }
    // </editor-fold>
}
