package aenu.gradle.expr;

import java.util.List;
import java.util.ArrayList;

public class Array extends Expression<List<Expression>> {
    List<Expression> list;

    Array(String code) {
        super(code);
        list = split(code);
    }

    private List<Expression> split(String code) {
        List<Expression> list = new ArrayList<>();
        code = code.replace(" ", "");
        code = code.substring(1, code.length() - 1);//[]
        String[] arr = code.split(",");
        if (arr[0].startsWith("'") || arr[0].charAt(0) == '"')
            for (String a : arr)
                list.add(new StringLiteral(a));
        else if (arr[0].equals("true") || arr[0].equals("false"))
            for (String a : arr)
                list.add(new BooleanLiteral(a));
        else if (arr[0].contains("."))//FIXME
            for (String a : arr)
                list.add(new FloatLiteral(a));
        else//FIXME
            for (String a : arr)
                list.add(new IntegerLiteral(a));

        return list;
    }

    @Override
    public List<Expression> value() {
        return this.list;
    }
}
