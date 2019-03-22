package expression;

public class Variable implements CommonExpression {

    private String v;

    public Variable(String s) {
        this.v = s;
    }

    public int evaluate(int x) {
        return x;
    }

    public double evaluate(double x) {
        return x;
    }
}
