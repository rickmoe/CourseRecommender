import javax.imageio.metadata.IIOMetadataFormatImpl;

public class OrNode extends LogicNode {

    @Override
    public void removePrerequisite(Node node) {
        super.removePrerequisite(node);
        setLogicallySatisfied();
    }

    @Override
    public String toString() {
        return "Any Of:\n";
    }
}
