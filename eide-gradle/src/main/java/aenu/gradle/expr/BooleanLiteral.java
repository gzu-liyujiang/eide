package aenu.gradle.expr;

public class BooleanLiteral extends Expression<Boolean> {

    public BooleanLiteral(String code) {
        super(code);
    }

    @Override
    public Boolean value() {
        return Boolean.parseBoolean(super.code);
    }
}

