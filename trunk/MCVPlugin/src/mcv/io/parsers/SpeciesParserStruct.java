package mcv.io.parsers;

import java.util.Collection;

public class SpeciesParserStruct {

    private String nodeName;
    private Collection<String> subNodes;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String NodeName) {
        this.nodeName = NodeName;
    }

    public Collection<String> getSubNodes() {
        return subNodes;
    }

    public void setSubNodes(Collection<String> SubNodes) {
        this.subNodes = SubNodes;
    }
}
