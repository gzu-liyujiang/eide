package aenu.gradle.expr;

public class FloatLiteral extends Expression{


    public FloatLiteral(String code){
        super(code);
    }

    @Override
    public Double value(){
        return new Double(super.code);
    }
}
