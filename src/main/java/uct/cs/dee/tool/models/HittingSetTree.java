package uct.cs.dee.tool.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
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
