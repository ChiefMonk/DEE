package uct.cs.dee.tool.impl;

import uct.cs.dee.tool.helpers.UtilsHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tweetyproject.logics.pl.reasoner.SatReasoner;
import org.tweetyproject.logics.pl.sat.Sat4jSolver;
import org.tweetyproject.logics.pl.sat.SatSolver;
import org.tweetyproject.logics.pl.syntax.*;
import uct.cs.dee.tool.models.*;
import uct.cs.dee.tool.services.*;

/**
 * <h1>DefeasibleEntailmentService<\h1>
 * The DefeasibleEntailmentService implements IEntailmentService for Defeasible Reasoning.
 * 
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 * @version 1.0.1
 * @since 2023-07-03
 */
public final class DefeasibleEntailmentService implements IEntailmentService {
     
    private final IKnowledgeBaseService _knowledgeBaseService;    
    private EntailmentResultModel _rationalClosure;
       
    public DefeasibleEntailmentService(IKnowledgeBaseService knowledgeBaseService) {    
        _knowledgeBaseService = knowledgeBaseService;       
    }
    
    @Override
     public EntailmentResultModel getEntailmentResults() {
        return _rationalClosure;
    }
     
     /**
    * This method returns the IKnowledgeBaseService. 
    * 
    * @return an IKnowledgeBaseService.
    */
    @Override
    public IKnowledgeBaseService getKnowledgeBaseService()
    {
        return _knowledgeBaseService;
    }
      
    
    /**
    *     
    * @return Boolean
    */
    @Override
    public boolean doesKbEntailQuery() {
        return _rationalClosure.doesEntailmentHold();
    }
    
    /**
    *     
    * @return integer
    */
    @Override
    public int getNumberOfDiscardedRanks() {
        return _rationalClosure.getNumberOfDiscardedRanks();
    }
    
    @Override
    public ValidationResultModel<String> computeEntailment() 
    {           
        try
        { 
            SatSolver.setDefaultSolver(new Sat4jSolver());
            SatReasoner reasoner = new SatReasoner();

            MinimalRankedFormulaModel minimalRankedFormulas = computeMinimalRanking(_knowledgeBaseService.getKnowledgeBase(), reasoner);
           // System.out.println("=====Minimal Ranked Formulas=====");
           // System.out.println(minimalRankedFormulas);

            PlBeliefSet R = new PlBeliefSet(UtilsHelper.materialise(minimalRankedFormulas.getAllFormulas()));
           // System.out.println("=====R=====");
           // System.out.println(R);

            int i = 0;

            while(reasoner.query(R, new Negation(((DefeasibleImplicationModel) _knowledgeBaseService.getQuery()).getFirstFormula())) && !R.isEmpty())
            {
                R = removeRankedFormulas(minimalRankedFormulas.getFinitlyRankedFormula(i), R);
                i ++;
            }

            _rationalClosure = new EntailmentResultModel(reasoner.query(R, UtilsHelper.materialise((DefeasibleImplicationModel)_knowledgeBaseService.getQuery())), i, minimalRankedFormulas, R);                       
            
            return new ValidationResultModel<>(true,_rationalClosure.getEntailmentMessage());
        }
        catch (Exception ex) 
        {
            Logger.getLogger(DefeasibleEntailmentService.class.getName()).log(Level.SEVERE, "Error computing defeasible entailment", ex);
            return new ValidationResultModel<>(String.format("An error occurred when computing the defeasible entailment. Plaese correct and try again.\n%s)", ex.getMessage())); 
        }       
    }  
    
    
     /**
     *
     * @return
     */
    @Override
    public MinimalRankedFormulaModel getBaseRankingFormulas()
    {
        return _rationalClosure.getMinimalRanking();        
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getBaseRankingFormulasMessage()
    {
        return getBaseRankingFormulas().toString();        
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getDiscardedFormulaListMessage()
    {
        return _rationalClosure.getDiscardedFormulaListMessage();        
    }
    
     /**
     *
     * @return
     */
    @Override
    public String getRemainingFormulaListMessage(boolean addEndline)
    {
          return _rationalClosure.getRemainingFormulaListMessage(addEndline); 
    }
    
    /**
     *     
     * @return a display message.
    */
    @Override
    public String getExplanationMessage()
    {
        StringBuilder sb = new StringBuilder();                
                            
        int counter = 1;
        
        sb.append("BaseRaking : ");
        int size = _rationalClosure.getMinimalRanking().getAllFormulasList().size();
        for (String formula : _rationalClosure.getMinimalRanking().getAllFormulasList())
        {
            sb.append(formula);
            if(counter < size)
                 sb.append(", ");
            counter++;            
        }
        sb.append("\n");
        
        counter = 1;
        sb.append("Discarded Rakings : ");
        size = _rationalClosure.getDiscardedFormulaList().size();
        if(size == 0)
        {
             sb.append("Discarded Rakings : None");
        }
        else
        {
            for (String formula : _rationalClosure.getDiscardedFormulaList())
            {
                sb.append(formula);
                if(counter < size)
                     sb.append(", ");
                counter++;            
            }
        }
        sb.append("\n");
        
        counter = 1;
        sb.append("Remaining Rakings : ");
        size = _rationalClosure.getRemainingFormulaList().size();
        if(size == 0)
        {
             sb.append("Remaining Rakings : None, all have been discarded");
        }
        else
        {
            for (String formula : _rationalClosure.getRemainingFormulaList())
            {
                sb.append(formula);
                if(counter < size)
                     sb.append(", ");
                counter++;            
            }
        }
        sb.append("\n");
                
        if(doesKbEntailQuery())
        {
            sb.append("Does K entail α? : Yes");          
        }
        else
        {
            sb.append("Does K entail α? : No\n");
            sb.append("Because there are no remaining base ranked formulas, all have been discarded: J = { empty}\n");           
            sb.append(String.format("Therefore K = %s does not entail %s", getKnowledgeBaseService().getKnowledgeBase(), getKnowledgeBaseService().getQuery()));  
        }                          
             
        return sb.toString();         
    }
    
    /**
     *     
     * @return an explanation message.
    */
    @Override
    public String getDisplayMessage()
    {
        StringBuilder sb = new StringBuilder();   
        
        if(doesKbEntailQuery())
        {
            sb.append(String.format("%s entails %s \n", _rationalClosure.getRemainingFormulaList().get(0), getKnowledgeBaseService().getQuery()));
            sb.append(String.format("K = %s entails %s\n", getKnowledgeBaseService().getKnowledgeBase(), getKnowledgeBaseService().getQuery()));          
            sb.append("Does K entail α? : Yes");
        }
        else
        {
            sb.append("No remaining baseranked formulas, all have been discarded\n");
            sb.append(String.format("K = %s does not entail %s\n", getKnowledgeBaseService().getKnowledgeBase(), getKnowledgeBaseService().getQuery()));  
            sb.append("Does K entail α? : No");
        }                          
             
        return sb.toString();    
    }  
          
    private static MinimalRankedFormulaModel computeMinimalRanking(PlBeliefSet knowledgeBase, SatReasoner reasoner) throws Exception
    {        
        List<PlFormula> classicalFormulas = UtilsHelper.getClassicalFormulas(knowledgeBase);       
        
        List<PlFormula> currentFormulas = getDefeasibleFormulas(knowledgeBase, classicalFormulas);
        List<PlFormula> prevFormulas = new ArrayList<>();
        List<List<PlFormula>> rankedFormulas = new ArrayList<>();
        int i = 0;
        
        while(!prevFormulas.equals(currentFormulas))
        {          
            prevFormulas = currentFormulas;
            currentFormulas = computeExceptionalFormulas(currentFormulas, classicalFormulas, reasoner);                      
            List<PlFormula> currentRank = remove(prevFormulas, currentFormulas);           
            rankedFormulas.add(currentRank);          
            i++;
        }
        
        List<PlFormula> infinitRank = rankedFormulas.remove(i-1);
        MinimalRankedFormulaModel result = new MinimalRankedFormulaModel(classicalFormulas, rankedFormulas);
        result.addInfinitlyRankedFormula(infinitRank);
        
        return result;
    }
    
    // This method compute the following set: E_(i+1) := { alpha => beta in E_{i} | materialsied(E_{i}) Union C \entails not alpha }
    private static List<PlFormula> computeExceptionalFormulas(List<PlFormula> formulaList, List<PlFormula> classicalFormulas, SatReasoner reasoner ) throws Exception
    {
        List<PlFormula> exceptionals = new ArrayList<PlFormula>();
        List<PlFormula> materialised = UtilsHelper.materialise(formulaList);
        List<PlFormula> unionedList = UtilsHelper.union(materialised, classicalFormulas);
        PlBeliefSet beliefSet = new PlBeliefSet(unionedList);
        
        
        for (PlFormula formula : formulaList)
        {
            if (formula instanceof DefeasibleImplicationModel)
            {
                PlFormula alpha = ((DefeasibleImplicationModel) formula).getFirstFormula();
                if (reasoner.query(beliefSet, new Negation(alpha)))
                {
                    exceptionals.add(formula);
                }
            }
            else
            {
                throw new Exception("Only defeasible implications can be ranked");
            }
        }
        return exceptionals;
    }
    
    // This method computes the following set: R_{i} := E_{i} \ E_{i+1}
    private static List<PlFormula> remove(List<PlFormula> from, List<PlFormula> removeFormulas) throws Exception
    {
        from.removeAll(removeFormulas);
        return from;
    }
    
    private static PlBeliefSet removeRankedFormulas(List<PlFormula> removeList, PlBeliefSet R) throws Exception
    {
        R.removeAll(UtilsHelper.materialise(removeList));
        return R;
    }
    
    private static List<PlFormula> getDefeasibleFormulas(PlBeliefSet knowledgeBase, List<PlFormula> classicalFormulas)
    {
        List<PlFormula> defeasibleFormulas = new ArrayList<PlFormula>();
        for (PlFormula plFormula: knowledgeBase)
        {
            if (!classicalFormulas.contains(plFormula))
                defeasibleFormulas.add(plFormula);
        }
        return defeasibleFormulas;
    }
    
    private static boolean equals(PlFormula x, PlFormula y) throws Exception
    {
        if (!(x instanceof DefeasibleImplicationModel ) || !(y instanceof DefeasibleImplicationModel) )
            throw new Exception("This equals method only compared defeasible implications");
        
        if (((DefeasibleImplicationModel)x).getFormula().equals(((DefeasibleImplicationModel)y).getFormula()))
            return true;
        else
            return false;        
    }
}
