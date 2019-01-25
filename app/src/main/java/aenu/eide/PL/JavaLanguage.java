
//license wtfpl 2.0

//by aenu 2018/10/22
//   email:202983447@qq.com

package aenu.eide.PL;
import com.myopicmobile.textwarrior.common.ILanguage;
import com.myopicmobile.textwarrior.common.ILexer;
import java.util.List;
//import com.myopicmobile.textwarrior.common.Hint;
import java.util.ArrayList;
import java.util.Map;
import java.io.File;
import java.util.HashMap;
import java.util.Set;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileReader;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFormatException;
import org.eclipse.jdt.internal.compiler.env.IBinaryField;
import org.eclipse.jdt.internal.compiler.env.IBinaryMethod;
import java.util.Enumeration;
import android.util.Log;
import java.util.Collection;
import org.eclipse.jdt.internal.compiler.classfmt.FieldInfo;
import org.eclipse.jdt.internal.compiler.classfmt.MethodInfo;

public class JavaLanguage implements ILanguage
{
    
    public static final String[] keywords = {
        "void", "boolean", "byte", "char", "short", "int", "long", "float", "double", "strictfp",
        "import", "package", "new", "class", "interface", "extends", "implements", "enum",
        "public", "private", "protected", "static", "abstract", "final", "native", "volatile",
        "assert", "try", "throw", "throws", "catch", "finally", "instanceof", "super", "this",
        "if", "else", "for", "do", "while", "switch", "case", "default",
        "continue", "break", "return", "synchronized", "transient",
        "true", "false", "null"
    };       
    
    public final List<ClassFileReader> BOOT_CLASS=new ArrayList<>();
    
    public final List<String> DEFAULT_IMPORTS=new ArrayList<>();
    
    private final JavaLexer lj=new JavaLexer();
    
    public JavaLanguage(File android_jar/*,File projects_dir*/){
        
        {//init all hints
        
            //android.jar
            {
                try{
                    ZipFile zipf=new ZipFile(android_jar);
                    Enumeration<? extends ZipEntry> entries=zipf.entries();//FIXME 该方法列出的条目可能不全
                    while(entries.hasMoreElements()){
                       
                        String name=entries.nextElement().getName();  
                        if(!name.endsWith(".class"))
                            continue;
                        ClassFileReader cf=null;
                        try{                   
                            cf=ClassFileReader.read(zipf,name);                        
                        }catch(Exception e2){
                            //skip                    
                        }
                        if(cf!=null){
                          
                          BOOT_CLASS.add(cf);
                            
                          if(name.startsWith("java/lang/")){                      
                            String short_name;{
                                int p=name.lastIndexOf('/');
                                if(p!=-1)
                                    short_name=name.substring(p+1,name.length()-6);//.class
                                else
                                    short_name=name.substring(0,name.length()-6);//.class
                                short_name=short_name.replace('$','.');
                            } 
                            DEFAULT_IMPORTS.add(short_name);
                          }
                        }
                        
                    }
                }catch(IOException e){
                    throw new RuntimeException(e); 
                }
            }
        }  
    }
    
    @Override
    public ILexer getLexer(){
        return lj;
    }
    
    @Override
    public boolean isSentenceTerminator(char c){
        return (c=='.');
    }

    @Override
    public boolean isWhitespace(char c){
        return (
            c==' '||
            c=='\n'||
            c=='\t'||
            c=='\r'||
            c=='\f'||
            c==EOF);
    }
}
