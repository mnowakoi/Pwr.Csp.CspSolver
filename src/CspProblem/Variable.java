package CspProblem;

import Expression.Evaluator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Monis on 4/17/15.
 */
public class Variable extends IVariable {
    private String name;
    private List<Integer> domain;
    private Integer value;
    private ArrayList<Evaluator> evaluators;

    public Variable(String name)
    {
        this.name = name;
        this.evaluators = new ArrayList<>();
    }

    public void setDomain(List<Integer> domain)
    {
        this.domain = domain;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public boolean hasNextDomainValue()
    {
        return (this.value==null && this.domain.size() > 0) ||
                (this.value != null && this.value != this.domain.get(this.domain.size()-1));
    }

    public void setNextDomainValue()
    {
        this.value = this.domain.get(this.domain.indexOf(value)+1);
    }

    public void deleteFromDomain(Integer elemIndex) { this.domain.remove(elemIndex); }

    public void addToDomain(int value) { this.domain.add(value); }

    public void addEvaluator(Evaluator evaluator)
    {
        this.evaluators.add(evaluator);
    }

    public boolean isConflictingVariable(HashMap<String, Variable> variablesMap)
    {
        for(Evaluator evaluator : evaluators)
        {
            if (evaluator.interpret(variablesMap)==0) return false;
        }
        return true;
    }
}
