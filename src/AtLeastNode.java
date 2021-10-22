public class AtLeastNode extends LogicNode {

    private int numSatisfied = 0;
    private int numRequired;

    public AtLeastNode(int numRequired) {
        this.numRequired = numRequired;
    }

    @Override
    public void removePrerequisite(Node node) {
        super.removePrerequisite(node);
        numSatisfied++;
        if (numSatisfied >= numRequired) setLogicallySatisfied();
    }

}
