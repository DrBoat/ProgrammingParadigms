package expression;

public class Add extends AbstractExpression {

    public Add(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    protected double operation(double a, double b) {
        return a + b;
    }

    protected int operation(int a, int b) {
        return a + b;
    }

}
