public class NotNode extends Node {
    // Node is functionally a NAND but is intended for use with only one child
    public void updateSatisfied() {
        satisfied = true;
        for (Node child : this.children) {
            if (child.satisfied) {
                satisfied = false;
            }
        }
        for (Node parent : this.parents) {
            parent.updateSatisfied();
        }
    }
    @Override
    public String toString() {
        return "Not ";
    }

    @Override
    public String toStringWithChildren(int indentLevel) {
        if (!satisfied) {
            return " : ".repeat(indentLevel) + this.toString() + children.get(0).toString() + "\n";
        }
        return "";
    }
}
