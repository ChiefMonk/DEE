package uct.cs.dee.tool.models;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>IExplanationService<\h1>
 * The IExplanationService interface has methods that should be implemented for a full entailment explanation.
 * 
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 * @version 1.0.1
 * @since 2023-07-03
 */
public class HittingSetTree 
{
    private final List<Node> _nodeList;
    private final Node _rootNode;
    
    public HittingSetTree(Node rootNode)
    {
        _rootNode = rootNode;
        _nodeList = new ArrayList<Node>();
    }
    
    public void addNode(Node node)
    {
        _nodeList.add(node);
    }  
}
