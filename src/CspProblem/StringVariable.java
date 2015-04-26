package CspProblem;

import StringExpression.StringEvaluator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Monis on 4/17/15.
 */
public class StringVariable extends IVariable {
    private String name;
    private List<String> domain;
    private String value;
    private ArrayList<StringEvaluator> evaluators;

    public StringVariable(String name)
    {
        this.name = name;
        this.evaluators = new ArrayList<>();
    }

    public void setDomain(List<String> domain)
    {
        this.domain = domain;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean hasNextDomainValue()
    {
        return (this.value==null && this.domain.size() > 0) ||
                (this.value != null && this.domain.indexOf(this.value)<this.domain.size()-1);
    }

    public void setNextDomainValue()
    {
        this.value = this.domain.get(this.domain.indexOf(value)+1);
    }

    public void deleteFromDomain(String elemIndex) { this.domain.remove(elemIndex); }

    public void addToDomain(String value) { this.domain.add(value); }

    public void addEvaluator(StringEvaluator evaluator)
    {
        this.evaluators.add(evaluator);
    }

    public boolean isConflictingVariable(HashMap<String, StringVariable> variablesMap)
    {
        for(StringEvaluator evaluator : evaluators)
        {
            if (evaluator.interpretString(variablesMap).equals("false")) return false;
        }
        return true;
    }
}
