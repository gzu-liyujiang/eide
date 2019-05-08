package aenu.gradle.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FunctionInvoke extends Expression<Object> {

    private String func_name;
    private List<Expression> args = new ArrayList<>();
    private Map<String, Expression> arg_map;

    public FunctionInvoke(String code) {
        super(code);
        analysisCode(code.replace(" ", ""));
    }

    private void analysisCode(String code) {
        int func_name_start = 0;
        int func_name_end = code.indexOf('(');

        func_name = code.substring(func_name_start, func_name_end);

        code = code.substring(func_name_end + 1, code.length() - 1);

        String[] _args = code.split(",");

        boolean DescriptorExist = true;

        if (_args[0].charAt(0) == '"')
            DescriptorExist = false;
        if (_args[0].charAt(0) == '\'')
            DescriptorExist = false;
        if (_args[0].charAt(0) >= '0' && _args[0].charAt(0) <= '9')
            DescriptorExist = false;
        if (_args[0].charAt(0) == '[')
            DescriptorExist = false;
        if (_args[0].equals("false"))
            DescriptorExist = false;
        if (_args[0].equals("true"))
            DescriptorExist = false;

        if (DescriptorExist) {
            this.arg_map = new HashMap<>();
            for (int i = 0; i < _args.length; i++) {

                String[] _arg = new String[2];

                {//split
                    int p = _args[i].indexOf(':');
                    _arg[0] = _args[i].substring(0, p);
                    _arg[1] = _args[i].substring(p + 1);
                }

                Expression expr;

                switch (_arg[1].charAt(0)) {
                    case '\'':
                    case '"':
                        expr = new StringLiteral(_arg[1]);
                        break;
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        if (_arg[1].contains("."))
                            expr = new FloatLiteral(_arg[1]);
                        else
                            expr = new IntegerLiteral(_arg[1]);
                        break;
                    case '[':
                        expr = new Array(_arg[1]);
                        break;
                    case 't':
                    case 'f':
                        if ((_arg[1].equals("true")) || _arg[1].equals("false")) {
                            expr = new BooleanLiteral(_arg[1]);
                            break;
                        }
                    default:

                        android.util.Log.e("eide", "err FI:" + _args[i] + ":" + _arg[1]);
                        throw new RuntimeException();
                }

                this.arg_map.put(_arg[0], expr);
                this.args.add(expr);
            }
        } else {
            for (int i = 0; i < _args.length; i++) {

                Expression expr;

                switch (_args[i].charAt(0)) {
                    case '\'':
                    case '"':
                        expr = new StringLiteral(_args[i]);
                        break;
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        if (_args[i].contains("."))
                            expr = new FloatLiteral(_args[i]);
                        else
                            expr = new IntegerLiteral(_args[i]);
                        break;
                    case '[':
                        expr = new Array(_args[i]);
                        break;
                    case 't':
                    case 'f':
                        if ((_args[i].equals("true")) || _args[i].equals("false")) {
                            expr = new BooleanLiteral(_args[i]);
                            break;
                        }
                    default:
                        android.util.Log.e("eide", "err FI:" + _args[i]);

                        throw new RuntimeException();
                }

                this.args.add(expr);
            }
        }
    }

    public Expression getArg(String k) {
        if (arg_map == null)
            return null;
        return arg_map.get(k);
    }

    public List<Expression> getArgs() {
        return this.args;
    }

    public String getFuncName() {
        return func_name;
    }

    public Set<String> getArgsDescriptor() {
        if (arg_map == null)
            return null;
        return arg_map.keySet();
    }

    @Override
    public Object value() {
        throw new RuntimeException();
    }
}
