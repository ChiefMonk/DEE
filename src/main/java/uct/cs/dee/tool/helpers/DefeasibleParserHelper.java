package uct.cs.dee.tool.helpers;

import org.tweetyproject.logics.pl.parser.*;
import org.tweetyproject.logics.pl.syntax.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import uct.cs.dee.tool.impl.DefeasibleKnowledgeBaseService;
import uct.cs.dee.tool.models.*;


/**
 * <h1>DefeasibleParserHelper<\h1>
 * The Defeasible Parser Helper.
 * 
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 * @version 1.0.1
 * @since 2023-07-03
 */
public class DefeasibleParserHelper {
    
    PlParser _plParser;
    
    /**
     *
     * @param plParser
     */
    public DefeasibleParserHelper(PlParser plParser)
    {
        _plParser = plParser;
    }
    
      /**
     * parse Formula
     * @param formula    
     * @return PlFormula
     * @throws java.lang.Exception
     */
    public PlFormula parseFormula(String formula) throws Exception
    {
        Stack<Object> stack = new Stack<>();
        for (int i = 0; i < formula.length(); i++)
        {
            consumeToken(stack, formula.charAt(i));
        }
        return this.parseDefeasibleImplication(stack);
    }
    
      /**
     * computes Justification
     * @param knowledgeBase
     * @param query
     * @throws Exception
     * @return void
     */
    private void consumeToken(Stack<Object> stack, char c) throws Exception
    {
        String token =  "" + c;
        if (token.equals(" "))
        {
            // Do nothing
        }
        else if (token.equals(")"))
        {
            if (!stack.contains("("))
                throw new Exception("Parse Exception: unmatching brackets.");
            List<Object> subFormula = new ArrayList<>();
            for (Object o = stack.pop(); !((o instanceof String) && ((String) o).equals("(")); o = stack.pop())
		subFormula.add(0, o);
            PlFormula x = null;
            if (subFormula.contains(DefeasibleKnowledgeBaseService.SYMBOL_DEFEASIBLE_IMPLICATION))
                x = parseDefeasibleImplication(subFormula);
            else
                x = _plParser.parseFormula(constructString(subFormula));
            stack.push(x);
        }
        else if (token.equals(">") && stack.lastElement().equals("~"))
        {
            stack.pop();
            stack.push(DefeasibleKnowledgeBaseService.SYMBOL_DEFEASIBLE_IMPLICATION);
        }
        else
        {
            stack.push(token);
        }
    }
    
      /**
     * parse Defeasible Implication
     * @param formula   
     * @throws Exception
     * @return PlFormula
     */
    private PlFormula parseDefeasibleImplication(List<Object> formula) throws Exception
    {
        if (formula.isEmpty())
            throw new Exception("Empty parentheses.");
        if (!hasDefeasibleImplication(formula))
            return _plParser.parseFormula(constructString(formula));
        if (formula.size() == 1 && formula.get(0) instanceof DefeasibleImplicationModel)
            return (DefeasibleImplicationModel)formula.get(0);
        
        
        List<Object> left = new ArrayList<>();
        List<Object> right = new ArrayList<>();
        boolean isRightFormula = false;
        for (Object o : formula)
        {
            if ((o instanceof String) && ((String)o).equals(DefeasibleKnowledgeBaseService.SYMBOL_DEFEASIBLE_IMPLICATION))
                isRightFormula = true;
            else if (isRightFormula)
                right.add(o);
            else 
                left.add(o);
        }
        
        return new DefeasibleImplicationModel(parseDefeasibleImplication(left), parseDefeasibleImplication(right));
        
    }
    
      /**
     * construct String
     * @param list   
     * @return
     */
    private String constructString(List<Object> list)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object o : list)
        {
            stringBuilder.append(o.toString());
        }
        return stringBuilder.toString();
    }
    
      /**
     * has Defeasible Implication
     * @param list     
     * @return
     */
    private boolean hasDefeasibleImplication(List<Object> list)
    {
        if (list.contains(DefeasibleKnowledgeBaseService.SYMBOL_DEFEASIBLE_IMPLICATION))
            return true;
        
        for (Object o : list)
        {
            if (o instanceof DefeasibleImplicationModel)
                return true;
        }
        return false;
    }
}

