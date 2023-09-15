package uct.cs.dee.tool.ui;

import java.util.List;
import uct.cs.dee.tool.impl.*;
import uct.cs.dee.tool.models.ValidationResult;
import uct.cs.dee.tool.services.*;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class UIManager { 
    
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
    public static ValidationResult<String> validateKnowledgeBase(String kbString) {            
       
        initialiseServices();
       
       return _knowledgeBaseService.validateKnowledgeBase(kbString);
    }
    
    public static ValidationResult<String> validateKnowledgeBase(List<String> kbStatements) {        

        initialiseServices();
        
       return _knowledgeBaseService.validateKnowledgeBase(kbStatements);
    }
    
    public static ValidationResult<String> validateKnowledgeBaseFile(String filePath) {            
       
        initialiseServices();
       
       return _knowledgeBaseService.validateKnowledgeBaseFile(filePath);
    }
    
     public static ValidationResult<String> validateQuery(String query) {               
       return _knowledgeBaseService.validateQuery(query);
    }
    
    public static ValidationResult<String> computeEntailmentAndExplanation(String kbString, String query) { 
        
         initialiseServices();
         
        // validate the knowledge base string
        ValidationResult<String> validateResult = validateKnowledgeBase(kbString);        
        if(!validateResult.isValid())
            return validateResult;             
        
        return executeEntailmentAndExplanation(query);
    }
    
     public static ValidationResult<String> computeEntailmentAndExplanation(List<String> kbStatements, String query) { 
        
         initialiseServices();
         
        // validate the knowledge base string
        ValidationResult<String> validateResult = validateKnowledgeBase(kbStatements);        
        if(!validateResult.isValid())
            return validateResult;             
        
        return executeEntailmentAndExplanation(query);
    }
    
    private static ValidationResult<String> executeEntailmentAndExplanation(String query) 
    { 
     // validate the query string
       ValidationResult<String>  validateResult = validateQuery(query);        
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
    
    public static IKnowledgeBaseService KnowledgeBaseService() {
        return _knowledgeBaseService;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="EntailmentService">
    public static IEntailmentService EntailmentService() {
        return _entailmentService;
    }
    // </editor-fold>
     
    // <editor-fold defaultstate="collapsed" desc="IJustificationService">
    public static IJustificationService JustificationService() {
        return _justificationService;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="IExplanationService">
    public static IExplanationService ExplanationService() {
        return _explanationService;
    }
    // </editor-fold>
}
