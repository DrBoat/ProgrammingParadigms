package expression;

public class Const implements CommonExpression {

    private Number val;

    public Const(int x){
        this.val = x;
    }

    public Const(double x) {
        this.val = x;
    }

    public int evaluate(int x) {
        return val.intValue();
    }

    public double evaluate(double x) {
        return val.doubleValue();
    }
}
