package io.parsers;

import java.util.Collection;

public class ParserStruct {

    private String NodeName;
    private Collection<String> SubNodes;

    public String getNodeName() {
        return NodeName;
    }

    public void setNodeName(String NodeName) {
        this.NodeName = NodeName;
    }

    public Collection<String> getSubNodes() {
        return SubNodes;
    }

    public void setSubNodes(Collection<String> SubNodes) {
        this.SubNodes = SubNodes;
    }
}
