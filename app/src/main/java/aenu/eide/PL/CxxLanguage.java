
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
        ,"-I/data/data/aenu.eide/eide-ndk/toolchains/arm-linux-androideabi-4.9/prebuilt/linux-arm/lib/gcc/arm-linux-androideabi/4.9.x/include"
        ,"-std=gnu11"
                 };
    }
    
    public static final String[] defaultCppFlag(){
        return new String[]{
        "-I/data/data/aenu.eide/eide-ndk/sysroot/usr/include"
        ,"-I/data/data/aenu.eide/eide-ndk/sysroot/usr/include/arm-linux-androideabi"
        ,"-I/data/data/aenu.eide/eide-ndk/toolchains/arm-linux-androideabi-4.9/prebuilt/linux-arm/lib/gcc/arm-linux-androideabi/4.9.x/include"
        ,"-std=g++11"
        ,"-I/data/data/aenu.eide/eide-ndk/sources/cxx-stl/gnu-libstdc++/4.9/include"
        
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
