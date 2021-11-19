public class AtLeastNode extends Node {

    protected final int numRequired;
    protected int numToSatisfy;

    public AtLeastNode(int numRequired) {
        super();
        this.numRequired = numRequired;
        numToSatisfy = numRequired;
    }

    @Override
    public void updateSatisfied() {
        numToSatisfy = numRequired;
        for (Node child : this.children) {
            if (child.satisfied) {
                numToSatisfy--;
            }
        }
        this.satisfied = (numToSatisfy <= 0);
        for (Node parent : this.parents) {
            parent.updateSatisfied();
        }
    }

    @Override
    public String toString() {
        return (numToSatisfy != 1) ? "Pick " + (numToSatisfy) + " Of:" : "Any Of:";
    }

}
