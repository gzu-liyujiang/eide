package aenu.gradle.expr;

public class FloatLiteral extends Expression<Double> {

    public FloatLiteral(String code) {
        super(code);
    }

    @Override
    public Double value() {
        return Double.parseDouble(super.code);
    }
}
