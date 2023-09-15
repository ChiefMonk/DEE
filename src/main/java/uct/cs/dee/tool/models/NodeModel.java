package uct.cs.dee.tool.models;

import uct.cs.dee.tool.helpers.UtilsHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import org.tweetyproject.logics.pl.syntax.PlFormula;

/**
 * <h1>NodeModel<\h1>
 * The Node Model.
 * 
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 * @version 1.0.1
 * @since 2023-07-03
 */
public class NodeModel 
{
    private final PlBeliefSet knowledgeBase;
    private List<PlFormula> justification;
    private Map<PlFormula, NodeModel> childrenNodes;

    public NodeModel(PlBeliefSet knowledgeBase)
    {
        this.knowledgeBase = knowledgeBase;
    }
    public NodeModel(PlBeliefSet knowledgeBase, List<PlFormula> justification)
    {
        this.knowledgeBase = knowledgeBase;
        this.justification = justification;
        this.childrenNodes = new HashMap<PlFormula, NodeModel>();
        for (PlFormula formula : justification )
        {
            childrenNodes.put(formula, new NodeModel(UtilsHelper.remove(knowledgeBase, formula)));
        }
    }
    
    public PlBeliefSet getKnowledgeBase()
    {
        return this.knowledgeBase;
    }

    public void setJustification(List<PlFormula> justification)
    {
        this.justification = justification;
        this.childrenNodes = new HashMap<PlFormula, NodeModel>();
        for (PlFormula formula : justification)
        {
            this.childrenNodes.put(formula, null);
        }
    }

    public List<PlFormula> getJustification()
    {
        return this.justification;
    }

    public void addChildNode(PlFormula formula, NodeModel node)
    {
        this.childrenNodes.put(formula, node);
    }
    
    public NodeModel getChildNode(PlFormula formula)
    {
        return this.childrenNodes.get(formula);
    }
    
    public void initialiseChildNodeMap()
    {
        for (PlFormula formula : justification )
        {
            childrenNodes.put(formula, null);
        }
    }
    
    public int getNumChildNodes()
    {
        return this.childrenNodes.size();
    }
    
    
    public List<List<PlFormula>> getAllJustifications()
    {
        List<List<PlFormula>> justifications = new ArrayList<List<PlFormula>>();
        
        if (this.childrenNodes != null && !this.childrenNodes.isEmpty())
        {
            for (PlFormula key : this.childrenNodes.keySet())
            {
                NodeModel childNode = this.childrenNodes.get(key);
                justifications.addAll(childNode.getAllJustifications());
            }
        }
        
        if (this.justification != null && !this.justification.isEmpty() && !justifications.contains(this.justification))
        {
            justifications.add(this.justification);
        }
        
        return justifications;
    }
    
    private String printKnowldegeBaseAsCSV()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (PlFormula formula : this.knowledgeBase)
        {
            stringBuilder.append(formula).append(", ");
        }
        String result = stringBuilder.toString();
        if (result != null && !result.isBlank() && result.length() > 2)
            return result.substring(0, result.length()-2);
        else
            return "NULL";
    }
    
    private String printJustificationAsCSV()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (PlFormula formula : justification)
        {
            stringBuilder.append(formula).append(", ");
        }
        String result = stringBuilder.toString();
        if (result != null && !result.isBlank() && result.length() > 2)
            return result.substring(0, result.length()-2);
        else
            return "NULL";
    }
    
    public String toString(String prefix)
    {
        StringBuilder stringBuilder = new StringBuilder(prefix);
        stringBuilder.append("<<Node>>\n")
                .append(prefix).append("<<KnowledegeBase == ").append(printKnowldegeBaseAsCSV()).append(">>\n")
                .append(prefix).append("<<Justification  == ").append(printJustificationAsCSV()).append(">>\n");
        if (this.childrenNodes == null || this.childrenNodes.isEmpty())
        {
            stringBuilder.append(prefix).append("No children nodes.\n");
        }
        else 
        {
            for (PlFormula key : this.childrenNodes.keySet())
            {
                stringBuilder.append(prefix).append(key).append(": \n").append(this.childrenNodes.get(key).toString(prefix+"\t")).append("\n");
            }
        }
        return stringBuilder.toString();
    }
    
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder("<<Node>>\n");
        stringBuilder.append("<<KnowledgeBase == ").append(printKnowldegeBaseAsCSV()).append(">>\n");
        stringBuilder.append("<<Justification == ").append(printJustificationAsCSV()).append(">>\n");
        if(this.childrenNodes == null || this.childrenNodes.isEmpty())
        {
            stringBuilder.append("No childern nodes.\n");
        }
        else
        {
            for (PlFormula key : this.childrenNodes.keySet())
            {
                stringBuilder.append(key).append(": \n").append(this.childrenNodes.get(key).toString("\t"));
            }
        }
        
        
        return stringBuilder.toString();
    }
    
}
