package StringExpression;

import CspProblem.StringVariable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by Monis on 4/26/15.
 */
public class AllStringDifferent implements StringExpression {
    private int count;
    private List<StringExpression> argsList;

    public AllStringDifferent(int count, ArrayList<StringExpression> argsList)
    {
        this.count = count;
        this.argsList = argsList;
    }

    public String interpretString(Map<String, StringVariable> variables)  {
        HashSet<String> values = new HashSet<>();
        for(StringExpression expression : argsList)
        {
            values.add(expression.interpretString(variables));
        }
        return values.size() == count ? "true" : "false";
    }
}