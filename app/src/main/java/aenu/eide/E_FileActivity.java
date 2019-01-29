package aenu.eide;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import java.util.Comparator;
import java.io.File;
import android.widget.BaseAdapter;
import java.text.Collator;
import android.content.Context;
import java.util.Arrays;
import android.view.LayoutInflater;
import android.text.format.DateFormat;
import java.io.FileFilter;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.Adapter;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import aenu.eide.view.NewProjectDialog;
import android.widget.Toast;
import aenu.eide.util.IOUtils;
import java.io.InputStream;
import java.io.IOException;

public class E_FileActivity extends AppCompatActivity implements NewProjectDialog.CreateProjectListener
{
    private SharedPreferences pref;
    private File current_dir;
    private ListView list;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file);
        
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        
        list=(ListView)findViewById(android.R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4){
                onListItemClick(list,p2,p3,p4);
            }
        });
        
        pref=PreferenceManager.getDefaultSharedPreferences(this);
        String last_dir=pref.getString("last_dir",null);
        if(last_dir!=null)
            current_dir=new File(last_dir);
        else
            current_dir=E_Application.getProjectDir();
        if(!current_dir.exists())
            current_dir=E_Application.getProjectDir();
        changeDir(current_dir);
    }

    @Override
    protected void onDestroy(){
        pref.edit().putString("last_dir",current_dir.getAbsolutePath())
            .commit();
        super.onDestroy();
    }
    
    private void onListItemClick(ListView l,View v,int position,long id){
        FileAdapter adapter=(FileAdapter) l.getAdapter();
        File f=adapter.getFile(position);

        if(!f.canRead())
            return;

        if(f.isDirectory()){
            changeDir(f);
            return;
        }
        
        final File pDir=f.getParentFile();
        
        setResult(RESULT_OK,new Intent().setData(Uri.fromFile(pDir)));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.file,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menu_goto_parent:
            if(current_dir.getParentFile()!=null)
                changeDir(current_dir.getParentFile());
                break;
            case R.id.menu_new_project:
                new NewProjectDialog(this)
                    .setCreateProjectListener(this)
                    .show();
                break;
        }
        return true;
    }    
    
    @Override
    public void createProject(int p_ty, String name){
        
        {//是否存在
            File f=new File(current_dir,name);
            if(f.exists()){
                Toast.makeText(this,"项目存在！>> "+name,1).show();
                return;
            }
        }
        
        String templete=null;
        switch(p_ty){
            case NewProjectDialog.PROJECT_ANDROID:
            templete="templete-android.zip";        
            break;
            case NewProjectDialog.PROJECT_JAVA:
            templete="templete-java.zip";
            break;
            case NewProjectDialog.PROJECT_CX:
            templete="templete-cx.zip";
            break;
        }      
        
        {//解压模板   
            final File newP_dir=new File(current_dir,name);          
            try{
                InputStream in=getAssets().open(templete);
                IOUtils.zip_uncompress(in,newP_dir);
                in.close();
            }catch(IOException e){
                Toast.makeText(this,"创建项目失败！>> "+e,1).show();
            } 
            finally{
                changeDir(newP_dir);
            }
        }
    }

    private void changeDir(File newDir){
        current_dir=newDir;
        getSupportActionBar().setTitle(newDir.getName());
        list.setAdapter(new FileAdapter(this,newDir));
    }
    
    public static class FileAdapter extends BaseAdapter{

        private static final FileFilter filter_=new FileFilter(){
            
            @Override
            public boolean accept(File p1){
                if(p1.isDirectory())
                    return true;
                final String name=p1.getName();
                if(name.equals("build.gradle"))
                    return true;
                return false;
            }         
        };

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
                fileList_=files;
            }
            else{
                fileList_=getFileList(ROOT);
                parent_dir=null;
            }
        }

        private File[] getFileList(File f){
            File[] files=f.listFiles(filter_);               
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
