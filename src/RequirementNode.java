public class RequirementNode extends Node {

    private final String requirement;
    private final int credits;

    public RequirementNode(String requirement, int credits) {
        super();
        this.requirement = requirement;
        this.credits = credits;
    }

    public RequirementNode(String requirement) {
        this(requirement, 0);
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return requirement;
    }
}