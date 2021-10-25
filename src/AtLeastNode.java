public class AtLeastNode extends LogicNode {

    private int numSatisfied;
    private int numRequired;

    public AtLeastNode(int numRequired) {
        super();
        this.numRequired = numRequired;
        numSatisfied = 0;
    }

    @Override
    public void removePrerequisite(Node node) {
        super.removePrerequisite(node);
        numSatisfied++;
        if (numSatisfied >= numRequired) setLogicallySatisfied();
    }

    @Override
    public String toString() {
        return (numRequired - numSatisfied != 1) ? "Pick " + (numRequired - numSatisfied) + " Of:\n" : "Any Of:\n";
    }

}
