public class AtLeastCreditNode extends Node {

    protected final int numRequired;
    protected int numToSatisfy;

    public AtLeastCreditNode(int numRequired) {
        super();
        this.numRequired = numRequired;
        numToSatisfy = numRequired;
    }

    @Override
    public void updateSatisfied() {
        numToSatisfy = numRequired;
        for (Node child : this.children) {
            if (child instanceof RequirementNode && child.satisfied) {
                numToSatisfy -= ((RequirementNode)child).credits;
            }
        }
        this.satisfied = (numToSatisfy <= 0);
        for (Node parent : this.parents) {
            parent.updateSatisfied();
        }
    }

    @Override
    public String toString() {
        return "Needs " + (numToSatisfy) + " Credits From:";
    }

}
