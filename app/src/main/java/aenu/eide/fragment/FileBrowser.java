
//license wtfpl 2.0

//by aenu 2018/10/22
//   email:202983447@qq.com

package aenu.eide.fragment;
import android.app.ListFragment;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import aenu.eide.R;
import android.widget.ListView;
import android.widget.BaseAdapter;
import java.io.FileFilter;
import java.io.File;
import java.util.Comparator;
import java.text.Collator;
import android.content.Context;
import java.util.Arrays;
import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Environment;
import aenu.eide.RequestListener;
import aenu.eide.E_MainActivity;
import aenu.eide.E_Application;

public class FileBrowser extends ListFragment
{
    private SharedPreferences pref;
    private File current_dir;
    private RequestListener listener;
    
    public FileBrowser(RequestListener l){
        listener=l;
    }
    
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        pref=PreferenceManager.getDefaultSharedPreferences(activity);
        String last_dir=pref.getString("last_dir",null);
        if(last_dir!=null)
            current_dir=new File(last_dir);
        else
            current_dir=E_Application.getProjectDir();
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        getListView().setFastScrollEnabled(true);
        getListView().setBackgroundColor(-1);  
        changeDir(current_dir);    
    }
   
    public void onDestroyView() {
        super.onDestroyView();
        pref.edit().putString("last_dir",current_dir.getAbsolutePath()).commit();
    }

    @Override
    public void onListItemClick(ListView l,View v,int position,long id){
        FileAdapter adapter=(FileBrowser.FileAdapter) l.getAdapter();
        File f=adapter.getFile(position);
        
        if(!f.canRead())
            return;
            
        if(f.isDirectory()){
            changeDir(f);
            return;
        }
        
        if(listener!=null){
            if(f.getName().equals("build.gradle"));
               // listener.onRequest(E_MainActivity.REQUEST_OPEN_PROJECT,f.getParentFile());
           // else
            //    listener.onRequest(E_MainActivity.REQUEST_OPEN_FILE,f);
            }
    }    
    
    private void changeDir(File newDir){
        current_dir=newDir;
        setListAdapter(new FileAdapter(getActivity(),newDir));
    }
    
    public static class FileAdapter extends BaseAdapter{

        /*private static final FileFilter filter_=new FileFilter(){
            private final String suffix[]={
            ".c",
            ".cc",
            ".h"
            };
            @Override
            public boolean accept(File p1){
                if(p1.isDirectory())
                    return true;
                final String name=p1.getName();
                for(String s:suffix)
                    if(name.endsWith(s))
                        return true;
                return false;
            }         
        };*/

        private static final Comparator<File> comparator_ = new Comparator<File>(){
            final Collator collator =Collator.getInstance(); 
            @Override
            public int compare(File p1,File p2){
                if(p1.isDirectory()&&p2.isFile())
                    return -1;
                else if(p1.isFile()&&p2.isDirectory())
                    return 1;
                return collator.compare(p1.getName(),p2.getName());
            }
        };

        private final File ROOT=new File("/");
        
        private File[] fileList_;
        private File parent_dir;
        private Context context_; 

        private FileAdapter(Context context,File file){
            context_=context;

            if(file!=null&&file.isDirectory()){
                
                File[] files=getFileList(file);
                parent_dir=file.getParentFile();
                
                if(parent_dir!=null){
                    
                    fileList_=new File[files.length+1];
                    fileList_[0]=parent_dir;
                    System.arraycopy(files,0,fileList_,1,files.length);
                    
                }else 
                    fileList_=files;
            }
            else{
                fileList_=getFileList(ROOT);
                parent_dir=null;
             }
        }

        private File[] getFileList(File f){
            File[] files=f.listFiles();//filter_);               
            Arrays.sort(files,comparator_);
            return files;
        }

        public File getFile(int pos){
            return fileList_[pos];
        }

        @Override
        public int getCount(){
            return fileList_.length;
        }

        @Override
        public Object getItem(int p1){
            return fileList_[p1];
        }

        @Override
        public long getItemId(int p1){
            return p1;
        }

        private LayoutInflater getLayoutInflater(){
            return (LayoutInflater)context_.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public static String getTimeStr(long time){
            return DateFormat.format("yyyy-MM-dd hh:mm:ss",time).toString();
        }

        public static String getSizeStr(long size){
            double s=(double)size;
            double u;
            String suffix;
            if(s>(u=1L<<40L)){
                suffix="tb";
            }
            else if(s>(u=1L<<30L)){
                suffix="gb";
            }
            else if(s>(u=1L<<20L)){
                suffix="mb";
            }
            else if(s>(u=1L<<10L)){
                suffix="kb";
            }
            else{
                u=1;
                suffix="b";
            }

            return String.format("%.2f ",s/u)+suffix;
        }

        @Override
        public View getView(int pos,View curView,ViewGroup p3){

            if(curView==null){
                curView=getLayoutInflater().inflate(R.layout.file_item,null);
            }

            File f=fileList_[pos];

            ImageView icon=(ImageView)curView.findViewById(android.R.id.icon);

            icon.setImageResource(
            f.isDirectory()
            ?R.drawable.ic_folder
            :R.drawable.ic_file);

            TextView name=(TextView)curView.findViewById(android.R.id.text1);
            
            if(f==parent_dir){
                name.setText("..");
                return curView;
            }
            
            name.setText(f.getName());

            TextView hint=(TextView)curView.findViewById(android.R.id.text2);
            String hihtStr=getTimeStr(f.lastModified());
            if(!f.isDirectory())
                hihtStr+=" "+getSizeStr(f.length());
            hint.setText(hihtStr);

            return curView;
        } 
    }//!FileAdapter
}
