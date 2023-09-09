/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uct.cs.dee.tool.services;

import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import org.tweetyproject.logics.pl.syntax.PlFormula;
import uct.cs.dee.tool.models.RationalClosureResults;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public interface IEntailmentService {
    
   public void computeEntailment(PlBeliefSet knowledgeBase, PlFormula query) throws Exception;   
   
   public RationalClosureResults computeRationalClosure(PlBeliefSet knowledgeBase, PlFormula query ) throws Exception;
   
   /**
     *     
     * @return Boolean
     */
    public boolean doesKbEntailQuery();  
}
