package uct.cs.dee.tool.models;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>HittingLinkedListModel<\h1>
 * The HittingLinkedList Model.
 * 
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 * @version 1.0.1
 * @since 2023-07-03
 */
public class HittingLinkedListModel 
{
    private final List<NodeModel> _nodeList;
    private final NodeModel _rootNode;
    
    public HittingLinkedListModel(NodeModel rootNode)
    {
        _rootNode = rootNode;
        _nodeList = new ArrayList<NodeModel>();
    }
    
    public void addNode(NodeModel node)
    {
        _nodeList.add(node);
    }  
}
