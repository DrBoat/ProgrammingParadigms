package expression;

public class Subtract extends AbstractExpression {

    public Subtract(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    protected int operation(int a, int b) {
        return a - b;
    }

    protected double operation(double a, double b) {
        return a - b;
    }
}
