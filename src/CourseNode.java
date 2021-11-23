public class CourseNode extends RequirementNode{

    protected final int credits;

    public CourseNode(String requirement) {
        super(requirement);
        this.credits = 0;
    }

    public CourseNode(String requirement, int credits) {
        super(requirement);
        this.credits = credits;
    }

    @Override
    public void updateSatisfied() {}

    public void setSatisfied() {
        satisfied = true;
        for (Node parent : this.parents) {
            parent.updateSatisfied();
        }
    }

}
