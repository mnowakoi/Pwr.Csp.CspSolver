package StringExpression;

import CspProblem.StringVariable;

import java.util.Map;

/**
 * Created by Monis on 4/26/15.
 */
public class Extract implements StringExpression {
    StringExpression leftOperand;
    int index;

    public Extract(StringExpression left, String index) {
        leftOperand = left;
        this.index = Integer.parseInt(index);
    }

    public String interpretString(Map<String, StringVariable> variables)  {
        String left = leftOperand.interpretString(variables);
        return left == null ? null : Character.toString(left.charAt(index));
    }
}