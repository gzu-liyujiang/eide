package aenu.gradle.expr;

public class IntegerLiteral extends Expression{
    
    
    public IntegerLiteral(String code){
        super(code);
    }
    
    @Override
    public Integer value(){
        return new Integer(super.code);
    }
}
