package expression;

public abstract class AbstractExpression implements CommonExpression {

    private CommonExpression left, right;

    public AbstractExpression(CommonExpression left, CommonExpression right) {
        this.left = left;
        this.right = right;
    }

    public double evaluate(double x) {
        return operation(left.evaluate(x), right.evaluate(x));
    }

    public int evaluate(int x) {
        return operation(left.evaluate(x), right.evaluate(x));
    }

    protected abstract int operation(int a, int b);

    protected abstract double operation(double a, double b);
}
