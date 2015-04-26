package StringExpression;

import CspProblem.StringVariable;

import java.util.*;

/**
 * Created by Monis on 4/26/15.
 */
public class StringEvaluator implements StringExpression {
    private StringExpression syntaxTree;
    private ArrayList<String> syntaxVariables;

    public StringEvaluator(String expression, List<String> variableNames) {
        this.syntaxVariables = new ArrayList<>();
        Stack<StringExpression> expressionStack = new Stack<>();
        for (String token : expression.split(" "))
        {
            if (isInt(token))
            {
                expressionStack.push(new Number(Integer.parseInt(token)));
            }
            else if (token.equals("="))
            {
               StringExpression subExpression = new StringEqual(expressionStack.pop(), expressionStack.pop());
               expressionStack.push( subExpression );
            }
            else if (token.equals("<>"))
            {
                StringExpression subExpression = new StringInequal(expressionStack.pop(), expressionStack.pop());
                expressionStack.push(subExpression);
            }
            else if (token.equals("[]"))
            {
                String index = expressionStack.pop().interpretString(new HashMap<>());
                StringExpression subExpression = new Extract(expressionStack.pop(), index);
                expressionStack.push( subExpression );
            }
            else if (token.equals("rozne")) {
                String countString = expressionStack.pop().interpretString(new HashMap<>());
                int count = Integer.parseInt(countString);
                ArrayList<StringExpression> variablesList = new ArrayList<>();
                for(int i = 0; i < count; i++)
                {
                    variablesList.add(expressionStack.pop());
                }
                StringExpression subExpression = new AllStringDifferent(count, variablesList);
                expressionStack.push(subExpression);
            }
            else if (variableNames.contains(token))
            {
                StringVariableReference variableReference = new StringVariableReference(token);
                expressionStack.push(variableReference);
                this.syntaxVariables.add(token);
            }
            else
            {
                expressionStack.push(new Text(token));
            }
        }
        syntaxTree = expressionStack.pop();
    }

    public void addReferenceToVariable(Map<String, StringVariable> context)
    {
        for(String variableName : syntaxVariables)
        {
            context.get(variableName).addEvaluator(this);
        }
    }

    private boolean isInt(String value)
    {
        try
        {
            Integer.parseInt(value);
            return true;
        } catch(NumberFormatException nfe)
        {
            return false;
        }
    }

    @Override
    public String interpretString(Map<String, StringVariable> variables) {
        for(String variableName : syntaxVariables)
        {
            if(variables.get(variableName).getValue() == null)
                return "true";
        }

        return syntaxTree.interpretString(variables);
    }
}
