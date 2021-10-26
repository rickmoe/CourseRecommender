public class RequirementNode extends Node {

    String requirement;

    public RequirementNode(String requirement) {
        super();
        this.requirement = requirement;
    }

    @Override
    public String toString() {
        return requirement;
    }
}