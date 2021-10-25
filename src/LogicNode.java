public abstract class LogicNode extends Node {

    protected boolean logicallySatisfied;

    public LogicNode() {
        super();
        logicallySatisfied = false;
    }

    protected void setLogicallySatisfied() {
        logicallySatisfied = true;
        remove();
    }

    @Override
    public boolean canBeRemoved() {
        return logicallySatisfied;
    }

}
