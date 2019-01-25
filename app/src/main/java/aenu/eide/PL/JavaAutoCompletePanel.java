
//license wtfpl 2.0

//by aenu 2018/11/26
//   email:202983447@qq.com

package aenu.eide.PL;
import com.myopicmobile.textwarrior.android.FreeScrollingTextField;
import org.eclipse.jdt.internal.compiler.parser.Parser;
import org.eclipse.jdt.internal.compiler.problem.ProblemReporter;
import org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;
import org.eclipse.jdt.internal.compiler.problem.DefaultProblemFactory;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.eclipse.jdt.internal.compiler.ast.ImportReference;
import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;
import org.eclipse.jdt.internal.compiler.batch.CompilationUnit;
import org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration;
import org.eclipse.jdt.internal.compiler.CompilationResult;
import org.eclipse.jdt.internal.compiler.ast.MethodDeclaration;
import org.eclipse.jdt.internal.compiler.ast.AbstractMethodDeclaration;
import org.eclipse.jdt.internal.compiler.ast.Statement;
import org.eclipse.jdt.internal.compiler.ast.LocalDeclaration;
import java.util.List;
import java.util.ArrayList;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import aenu.eide.R;
import org.eclipse.jdt.core.compiler.CategorizedProblem;

public class JavaAutoCompletePanel extends AutoCompletePanel
{
    private final Bitmap ICON_CLASS=BitmapFactory.decodeResource(_context.getResources(),R.drawable.objects);
    private final Bitmap ICON_FIELD=BitmapFactory.decodeResource(_context.getResources(),R.drawable.box_light_blue);
    private final Bitmap ICON_METHOD=BitmapFactory.decodeResource(_context.getResources(),R.drawable.box_light_red);
      
    private JavaLanguage _lang;
    private int _replaceTextLength;
    public static final Parser _parser=new Parser(
    new ProblemReporter(
    new IErrorHandlingPolicy(){
        @Override
        public boolean ignoreAllErrors(){
            return false;
        }
        @Override
        public boolean proceedOnErrors(){
            return true;
        }
        @Override
        public boolean stopOnFirstError(){
            return false;
        }
    },
    new CompilerOptions(),
    new DefaultProblemFactory()),
    true);
    
    @Override
    public void select(int pos){
        if(!isShowing())
            return;
            
        ListItem item=_adapter.getItem(pos);
        String text=item.text;

        _textField.replaceText(_textField.getCaretPosition() - _replaceTextLength, _replaceTextLength, text);
        
        dismiss();
    }

    @Override
    public void complete(CharSequence text, int pos){
        if(pos==0||text.length()==0)return;

        dismiss();
        
        int curr=pos;
        boolean is_invoke=false;
        for(;curr>=1;curr--){
            char c=text.charAt(curr-1);
            
            if(c=='.')
                is_invoke=true;
                
            if((c==' ')
            ||(c=='.')
            ||(c=='\n')
            ||(c=='{')
            ||(c=='}')
            ||(c=='(')
            ||(c==')')
            ||(c=='+')
            ||(c=='-')
            ||(c=='*')
            ||(c=='/')
            ||(c=='=')
            ||(c=='|')
            ||(c=='&')
            ||(c=='^')
            ||(c=='?')
            ||(c==':')
            ||(c=='>')
            ||(c=='<')
            ||(c=='!')
            ||(c=='@')
            ||(c=='~')
            ||(c=='%')
            ||(c=='[')
            ||(c==']')
            ||(c==',')
            ||(c==';'))
                break;
        }

        _replaceTextLength=pos-curr;

        if(_replaceTextLength==0) return;
        

        ICompilationUnit uint=new CompilationUnit(text.toString().toCharArray(),"","utf-8");
        CompilationResult result=new CompilationResult(uint,0,1,100);
        CompilationUnitDeclaration decl=_parser.parse(uint,result);

        TypeDeclaration class_decl = decl.types[0];

        if(class_decl==null){
            dismiss();
            return;
        }

        final String input=text.subSequence(curr,_replaceTextLength).toString().toLowerCase();

        final ImportReference[] imports=decl.imports;
        final List<String> default_import_class=_lang.DEFAULT_IMPORTS;
        final FieldDeclaration[] current_class_fields=class_decl.fields;
        final AbstractMethodDeclaration[] current_class_methods=class_decl.methods;
        final List<LocalDeclaration> local_vars=new ArrayList<>();

        MethodDeclaration parse_method=null;
        
        if(class_decl.methods!=null){

            for(AbstractMethodDeclaration m:class_decl.methods){
                if(m.bodyStart<pos&&m.bodyEnd>pos){
                    parse_method=(MethodDeclaration)m;
                    break;
                }
            }

            if(parse_method!=null){
                _parser.parse(parse_method,decl);
                Statement[] statements=parse_method.statements;
                if(statements!=null)
                    for(Statement s:statements)
                        if(s instanceof LocalDeclaration)
                            local_vars.add((LocalDeclaration)s);          
            }         
        }
        
        List<ListItem>list=new ArrayList<>();

        if(is_invoke){
            if(parse_method!=null){
                _parser.parse(parse_method,decl);
                Statement[] statements=parse_method.statements;
                if(statements!=null)
                    for(Statement s:statements)
                        if(s.sourceEnd==curr-1){
                            
                }
            }         
        }
        else{
            {//filter keyword
                for(String str:JavaLanguage.keywords)
                    if(str.startsWith(input))
                        list.add(new ListItem(null,str,null));

            }

            {//filter class
                for(String str:default_import_class)
                    if(str.toLowerCase().startsWith(input))
                        list.add(new ListItem(ICON_CLASS,str,null));

                String cls;
                if(imports!=null)
                    for(ImportReference ir:imports)
                        if( (cls=new String(ir.tokens[ir.tokens.length-1]))
                        .toLowerCase().startsWith(input))
                            list.add(new ListItem(ICON_CLASS,cls,null));

            }


            {//filter field
                String fld;
                if(current_class_fields!=null)
                    for(FieldDeclaration f:current_class_fields)
                        if( (fld=new String(f.name))
                        .toLowerCase().startsWith(input))
                            list.add(new ListItem(ICON_FIELD,fld,new String(f.type.getLastToken())));
            }

            {//filter method
                String mtd;
                if(current_class_methods!=null)
                    for(AbstractMethodDeclaration m:current_class_methods)
                        if( (mtd=new String(m.selector))
                        .toLowerCase().startsWith(input))
                            list.add(new ListItem(ICON_METHOD,mtd,null));

            }

            {//filter local var
                String var;
                if(local_vars.size()!=0)
                    for(LocalDeclaration v:local_vars)
                        if( (var=new String(v.name))
                        .toLowerCase().startsWith(input))
                            list.add(new ListItem(ICON_FIELD,var,new String(v.type.getLastToken())));

            }
        }
        
        if(list.size()!=0){
            _adapter.setItems(list);

            setWidth(_textField.getWidth()-PADDING*2);
            setHorizontalOffset(PADDING);

            setHeight(list.size()>1?getItemHeight()*2:getItemHeight());
            setVerticalOffset(_textField.getCaretY()-_textField.getScrollY()-_textField.getHeight());

            if(!isShowing())
                show();
        }
        else
            dismiss();
    }
    
    public JavaAutoCompletePanel(FreeScrollingTextField fstf,JavaLanguage l){
        super(fstf);
        _lang=l;
    }
}
