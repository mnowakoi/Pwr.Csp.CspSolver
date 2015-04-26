package Expression;

import CspProblem.Variable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by Monis on 4/19/15.
 */
public class AllDifferent implements Expression {
    private int count;
    private List<Expression> argsList;

    public AllDifferent(int count, ArrayList<Expression> argsList) {
        this.count = count;
        this.argsList = argsList;
    }

    public Integer interpret(Map<String, Variable> variables) {
        int countNotNull = count;
        HashSet<Integer> values = new HashSet<>();
        for (Expression expression : argsList) {
            Integer leftValue = expression.interpret(variables);
            if (leftValue != null) {
                values.add(leftValue);
            } else {
                countNotNull--;
            }
        }
        return values.size() == countNotNull ? 1 : 0;
    }
}
