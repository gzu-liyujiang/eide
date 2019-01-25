package aenu.gradle.expr;

public class StringLiteral extends Expression{


    public StringLiteral(String code){
        super(code);
    }

    @Override
    public String value(){
        return code.substring(1,code.length()-1);
    }
}
