public class RequirementNode extends Node {

    protected final String requirement;

    public RequirementNode(String requirement) {
        super();
        this.requirement = requirement;
    }

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
        return requirement;
    }
}