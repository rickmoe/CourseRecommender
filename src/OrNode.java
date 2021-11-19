public class OrNode extends Node {

    public void updateSatisfied() {
        satisfied = false;
        for (Node child : this.children) {
            if (child.satisfied) {
                satisfied = true;
            }
        }
        for (Node parent : this.parents) {
            parent.updateSatisfied();
        }
    }

    @Override
    public String toString() {
        return "Any Of:";
    }
}