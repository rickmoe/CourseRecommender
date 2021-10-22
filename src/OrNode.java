public class OrNode extends LogicNode {

    @Override
    public void removePrerequisite(Node node) {
        super.removePrerequisite(node);
        setLogicallySatisfied();
    }

}
