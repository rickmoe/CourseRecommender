public class AtLeastNode extends LogicNode {

    private final int numRequired;
    private int numSatisfied;

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
        return (numRequired - numSatisfied != 1) ? "Pick " + (numRequired - numSatisfied) + " Of:" : "Any Of:";
    }

}
