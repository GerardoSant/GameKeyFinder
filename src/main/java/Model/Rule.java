package Model;

import Model.Action.Action;
import Model.Condition.Condition;

import java.util.List;

public class Rule {

    //Para que la regla esté active, se tienen que cunmplir todas las condiciones.
    // Cuando está active, la regla llama a todos sus actuadores

    private String name;
    private boolean active;
    private List<Condition> conditions;
    private List<Action> actions;

    public Rule(String name, boolean active, List<Condition> conditions, List<Action> actions) {
        this.name = name;
        this.active = active;
        this.conditions = conditions;
        this.actions = actions;
    }

    public void addCondition(Condition condition) { this.conditions.add(condition);}

    public void addAction(Action action) {this.actions.add(action);}

    public void execute(){
        if (this.isTrueAllConditions()){
            this.actAllAction();
        }
    }

    public boolean isTrueAllConditions() {
        boolean result = true;
        for (Condition condition : conditions){
            if (!condition.check()){
                result = false;
                break;
            }
        }
        return result;
    }

    public void actAllAction() {
        for (Action action : actions ){
            action.actuate();
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public String getName() {
        return name;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public List<Action> getActions() {
        return actions;
    }
}
