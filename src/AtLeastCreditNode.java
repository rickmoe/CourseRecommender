public class AtLeastCreditNode extends LogicNode {

    private final int creditsRequired;
    private int creditsSatisfied;

    public AtLeastCreditNode(int creditsRequired) {
        super();
        this.creditsRequired = creditsRequired;
        creditsSatisfied = 0;
    }

    @Override
    public void removePrerequisite(Node node) {
        super.removePrerequisite(node);
        if (node instanceof RequirementNode) creditsSatisfied += ((RequirementNode)node).getCredits();
        if (creditsSatisfied >= creditsRequired) setLogicallySatisfied();
    }

    @Override
    public String toString() {
        return "Needs " + (creditsRequired - creditsSatisfied) + " Credits From: ";
    }

}
