
//license wtfpl 2.0

//by aenu 2019
//   email:202983447@qq.com


package aenu.eide;
import jackpal.androidterm.emulatorview.TermSession;
import android.os.ParcelFileDescriptor;
import java.io.IOException;
import dalvik.system.DexClassLoader;
import android.os.Handler;
import java.lang.reflect.Method;
import java.io.File;
import aenu.eide.util.OSUtils;
import java.io.PrintStream;
import jackpal.androidterm.TermExec;
import android.os.Message;

public class E_JavaTermSession extends TermSession
{
    private Thread watcher_thread;
    private int proc_id;
    private ParcelFileDescriptor term_fd;


    private Handler message_handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            if (!isRunning()) {
                return;
            }

            try{
                byte[] msg = ("\n\n exit code"+message.what).getBytes("UTF-8");
                appendToEmulator(msg, 0, msg.length);
                notifyUpdate();
            }
            catch (Exception e) {}
            finally{
                onProcessExit();
            }
        }
    };

    
    private final DexClassLoader dex;
    private final Class<?> mainClass;

    public E_JavaTermSession(DexClassLoader dex,Class<?> mainClass,String[] args) throws IOException, SecurityException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException{

        super.setDefaultUTF8Mode(true);

        this.dex=dex;
        this.mainClass=mainClass;
        
        final File fifo=new File(E_Application.getTmpDir(),
        "fifo-"+System.currentTimeMillis());

        if(fifo.exists()) fifo.delete();

        if(OSUtils.mkfifo(fifo.getAbsolutePath(),0700)!=0)
            throw new IOException("mkfifo failed: "+fifo.getAbsolutePath());

        term_fd=ParcelFileDescriptor.open(fifo,ParcelFileDescriptor.MODE_READ_WRITE);          
        setTermOut(new ParcelFileDescriptor.AutoCloseOutputStream(term_fd));
        setTermIn(new ParcelFileDescriptor.AutoCloseInputStream(term_fd));
        
        watcher_thread = new Thread() {
            @Override
            public void run() {
                int result = TermExec.waitFor(proc_id);
                message_handler.sendEmptyMessage(result);
            }
        };
    }

    @Override
    public void initializeEmulator(int columns, int rows) {
        super.initializeEmulator(columns, rows);
        
        if((proc_id=OSUtils.fork())==0){

            System.setOut(new PrintStream(getTermOut()));
            System.setErr(new PrintStream(getTermOut()));
            
            String[] args =new String[]{"a"};
            Object[] run_args=args==null?null:new Object[]{args};
        try{
            final Method main=mainClass.getMethod("main",String[].class);
            
            main.invoke(mainClass,run_args);
}catch(Exception e){
    System.err.println(e.toString());
}finally{
            System.exit(0);}
        }
        else{         
            watcher_thread.start();
        }        
    }

    @Override
    public void finish() {

        TermExec.sendSignal(-proc_id, 1);
        try {
            term_fd.close();
        } catch (IOException e) {
            // ok
        }

        super.finish();
    }
}
