package aenu.gradle.expr;

public abstract class Expression<T>{
    public final String code;
    public Expression(String code){
        this.code=code;
    }
    
    public abstract T value();
}
