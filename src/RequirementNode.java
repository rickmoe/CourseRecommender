public class RequirementNode extends Node {

    protected final String requirement;
    protected final int credits;

    public RequirementNode(String requirement, int credits) {
        super();
        this.requirement = requirement;
        this.credits = credits;
    }

    public RequirementNode(String requirement) {
        this(requirement, 0);
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