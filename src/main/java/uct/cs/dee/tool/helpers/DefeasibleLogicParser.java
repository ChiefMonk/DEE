package uct.cs.dee.tool.helpers;

import org.tweetyproject.logics.pl.parser.*;
import org.tweetyproject.logics.pl.syntax.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import uct.cs.dee.tool.impl.DefeasibleKnowledgeBaseService;
import uct.cs.dee.tool.models.*;


/**
 * <h1>IExplanationService<\h1>
 * The IExplanationService interface has methods that should be implemented for a full entailment explanation.
 * 
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 * @version 1.0.1
 * @since 2023-07-03
 */
public class DefeasibleLogicParser {
    
    PlParser _plParser;
    
    public DefeasibleLogicParser(PlParser plParser)
    {
        _plParser = plParser;
    }
    
    public PlFormula parseFormula(String formula) throws Exception
    {
        Stack<Object> stack = new Stack<>();
        for (int i = 0; i < formula.length(); i++)
        {
            consumeToken(stack, formula.charAt(i));
        }
        return this.parseDefeasibleImplication(stack);
    }
    
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
    
    private PlFormula parseDefeasibleImplication(List<Object> formula) throws Exception
    {
        if (formula.isEmpty())
            throw new Exception("Empty parentheses.");
        if (!hasDefeasibleImplication(formula))
            return _plParser.parseFormula(constructString(formula));
        if (formula.size() == 1 && formula.get(0) instanceof DefeasibleImplication)
            return (DefeasibleImplication)formula.get(0);
        
        
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
        
        return new DefeasibleImplication(parseDefeasibleImplication(left), parseDefeasibleImplication(right));
        
    }
    
    private String constructString(List<Object> list)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object o : list)
        {
            stringBuilder.append(o.toString());
        }
        return stringBuilder.toString();
    }
    
    private boolean hasDefeasibleImplication(List<Object> list)
    {
        if (list.contains(DefeasibleKnowledgeBaseService.SYMBOL_DEFEASIBLE_IMPLICATION))
            return true;
        
        for (Object o : list)
        {
            if (o instanceof DefeasibleImplication)
                return true;
        }
        return false;
    }
}
