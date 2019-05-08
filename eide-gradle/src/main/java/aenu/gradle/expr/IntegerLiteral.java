package aenu.gradle.expr;

public class IntegerLiteral extends Expression<Integer> {

    public IntegerLiteral(String code) {
        super(code);
    }

    @Override
    public Integer value() {
        return Integer.parseInt(super.code);
    }
}
