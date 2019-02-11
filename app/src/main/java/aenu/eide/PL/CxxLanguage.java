
//license wtfpl 2.0

//by aenu 2018-2019
//   email:202983447@qq.com

package aenu.eide.PL;
import com.myopicmobile.textwarrior.common.ILanguage;
import com.myopicmobile.textwarrior.common.ILexer;

public class CxxLanguage implements ILanguage{
    private CxxLexer lexer;
    
    public static final String[] defaultCFlag(){
        return new String[]{
        "-I/data/data/aenu.eide/eide-ndk/sysroot/usr/include"
        ,"-I/data/data/aenu.eide/eide-ndk/sysroot/usr/include/arm-linux-androideabi"
			,"-I/data/data/aenu.eide/eide-ndk/toolchains/llvm/prebuilt/linux-arm/lib/clang/7.0.0/include"
        ,"-std=c99"
                 };
    }
    
    public static final String[] defaultCppFlag(){
        return new String[]{
        "-I/data/data/aenu.eide/eide-ndk/sysroot/usr/include"
        ,"-I/data/data/aenu.eide/eide-ndk/sysroot/usr/include/arm-linux-androideabi"
			,"-I/data/data/aenu.eide/eide-ndk/sources/cxx-stl/llvm-libc++/include"
			
			,"-I/data/data/aenu.eide/eide-ndk/toolchains/llvm/prebuilt/linux-arm/lib/clang/7.0.0/include"
			,"-std=c++11"
			
        };
    }
    
    
    public CxxLanguage(CxxLexer cl){
        this.lexer=cl;
    }
    @Override
    public ILexer getLexer(){
        return lexer;
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
