package StringExpression;

import CspProblem.StringVariable;

import java.util.Map;

/**
 * Created by Monis on 4/26/15.
 */
interface StringExpression {
    public String interpretString(Map<String, StringVariable> variables);
}
