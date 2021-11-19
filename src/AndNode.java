public class AndNode extends Node {

    public void updateSatisfied() {
        satisfied = true;
        for (Node child : this.children) {
            if (!child.satisfied) {
                satisfied = false;
            }
        }
        for (Node parent : this.parents) {
            parent.updateSatisfied();
        }
    }

    @Override
    public String toString() {
        return "All Of:";
    }
}