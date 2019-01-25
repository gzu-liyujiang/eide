package aenu.gradle.expr;

public class BooleanLiteral extends Expression{


    public BooleanLiteral(String code){
        super(code);
    }

    @Override
    public Boolean value(){
        return new Boolean(super.code);
    }
}

