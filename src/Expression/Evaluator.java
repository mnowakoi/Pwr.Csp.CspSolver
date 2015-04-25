package Expression;

/**
 * Created by Monis on 4/19/15.
 */

import CspProblem.Variable;

import java.util.*;

public class Evaluator implements Expression {
    private Expression syntaxTree;
    private ArrayList<String> syntaxVariables;

    public Evaluator(String expression) {
        this.syntaxVariables = new ArrayList<>();
        Stack<Expression> expressionStack = new Stack<>();
        for (String token : expression.split(" "))
        {
            if  (token.equals("+"))
            {
                Expression subExpression = new Plus(expressionStack.pop(), expressionStack.pop());
                expressionStack.push( subExpression );
            }
            else if (token.equals("-")) {
                Expression right = expressionStack.pop();
                Expression left = expressionStack.pop();
                Expression subExpression = new Minus(left, right);
                expressionStack.push( subExpression );
            }
            else if (token.equals("*"))
            {
                Expression subExpression = new Multiplication(expressionStack.pop(), expressionStack.pop());
                expressionStack.push(subExpression);
            }
            else if (isInt(token))
            {
                expressionStack.push(new Number(Integer.parseInt(token)));
            }
            else if (token.equals("||"))
            {
                Expression subExpression = new Absolute(expressionStack.pop());
                expressionStack.push(subExpression);
            }
            else if (token.equals("="))
            {
                Expression subExpression = new Equal(expressionStack.pop(), expressionStack.pop());
                expressionStack.push( subExpression );
            }
            else if (token.equals("<>"))
            {
                Expression subExpression = new Inequal(expressionStack.pop(), expressionStack.pop());
                expressionStack.push(subExpression);
            }
            else if (token.equals(">")) {
                Expression right = expressionStack.pop();
                Expression left = expressionStack.pop();
                Expression subExpression = new Greater(left, right);
                expressionStack.push( subExpression );
            }
            else if (token.equals("<")) {
                Expression right = expressionStack.pop();
                Expression left = expressionStack.pop();
                Expression subExpression = new Lower(left, right);
                expressionStack.push( subExpression );
            }
            else if (token.equals("rozne")) {
                  int count = expressionStack.pop().interpret(new HashMap<>());
                  ArrayList<Expression> variablesList = new ArrayList<>();
                  for(int i = 0; i < count; i++)
                  {
                      variablesList.add(expressionStack.pop());
                  }
                Expression subExpression = new AllDifferent(count, variablesList);
                expressionStack.push( subExpression );
            }
            else
            {
                VariableReference variableReference = new VariableReference(token);
                expressionStack.push(variableReference);
                this.syntaxVariables.add(token);
            }
        }
        syntaxTree = expressionStack.pop();
    }

    public int interpret(Map<String, Variable> context)
    {
        for(String variableName : syntaxVariables)
        {
            if(context.get(variableName).getValue() == null)
                return 1;
        }

        return syntaxTree.interpret(context);
    }

    public void addReferenceToVariable(Map<String, Variable> context)
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
}