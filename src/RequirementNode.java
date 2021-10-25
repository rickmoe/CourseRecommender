public class RequirementNode extends Node {

    String requirement;

    public RequirementNode(String requirement) {
        super();
        this.requirement = requirement;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    @Override
    public String toString() {
        return requirement + "\n";
    }
}