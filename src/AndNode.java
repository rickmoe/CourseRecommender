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

    @Override
    public String toStringWithChildren(int indentLevel) {
        if (!satisfied) {
            int toSatisfy = 0;
            for (Node child : children) {
                if (!child.satisfied) {
                    toSatisfy++;
                }
            }
            if (toSatisfy == 0) {
                return "";
            } else if (toSatisfy == 1) {
                String output = "";
                for(Node child : children) output += child.toStringWithChildren(indentLevel);
                return output;
            } else {
                String output = " : ".repeat(indentLevel) + this.toString() + "\n";
                for(Node child : children) output += child.toStringWithChildren(indentLevel + 1);
                return output;
            }
        }
        return "";
    }
}