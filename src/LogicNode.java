public abstract class LogicNode extends Node {

    protected boolean logicallySatisfied;

    protected void setLogicallySatisfied() {
        logicallySatisfied = true;
        remove();
    }

    @Override
    public boolean canBeRemoved() {
        return logicallySatisfied;
    }

}
