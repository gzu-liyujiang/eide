package aenu.gradle.expr;

public abstract class Expression{
    public final String code;
    public Expression(String code){
        this.code=code;
    }
    
    public abstract Object value();
}
