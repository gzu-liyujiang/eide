package aenu.eide.view;
import android.support.v4.view.ViewPager;
import android.content.Context;
import android.util.AttributeSet;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerAdapter;
import com.unnamed.b.atv.model.TreeNode;
import java.util.Map;
import android.view.View;
import java.util.HashMap;
import java.io.File;
import aenu.eide.RequestListener;
import android.widget.TextView;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.FrameLayout;
import com.unnamed.b.atv.view.AndroidTreeView;
import java.util.List;
import java.util.ArrayList;
import aenu.eide.diagnostic.DiagnosticMessage;
import java.util.Set;
import android.widget.ArrayAdapter;
import android.graphics.Color;
import android.view.ViewGroup;
import aenu.eide.E_MainActivity;
import android.widget.ImageView;
import aenu.eide.R;
import android.view.LayoutInflater;
import aenu.eide.E_FileActivity;

public class ProjectView extends ViewPager
{
    final PagerTabStrip tab;
    File project_dir;
    
    public ProjectView(Context context){
        super(context);
        this.tab=new PagerTabStrip(context);
        initialize();
    }

    public ProjectView(Context context,AttributeSet attrs){
        super(context,attrs);
        this.tab=new PagerTabStrip(context,attrs);
        initialize();
    }
    
    private void initialize(){
        this.addView(this.tab);
        openProject(null,null);
    }
    
    public void setErrors(Map<File,List<DiagnosticMessage>> errs){
        ProjectAdapter A=(ProjectView.ProjectAdapter) getAdapter();
        A.setErrors(errs);
    }  
    
    public void setWarnings(Map<File,List<DiagnosticMessage>> wars){
        ProjectAdapter A=(ProjectView.ProjectAdapter) getAdapter();
        A.setWarnings(wars);
    }  
    
    public void openProject(File project_dir,RequestListener listener){
        this.project_dir=project_dir;
        setAdapter(new ProjectAdapter(getContext(),project_dir,listener));
    }
    
    public static class ProjectAdapter extends PagerAdapter implements TreeNode.TreeNodeClickListener{

        private final Map<String,View> views=new HashMap<>();
        private final Context context;
        private final File project_dir;
        private final RequestListener listener;

        public ProjectAdapter(Context context,File projectDir,RequestListener l){
            this.context=context;
            this.project_dir=projectDir;
            this.listener=l;
            initialize();
        }

        private void initialize(){

            views.put("错误",newListView("无错误"));
            views.put("警告",newListView("无警告"));
            views.put("项目",
            project_dir==null?newTextView("未打开项目"):newTreeView());
        }

        private TextView newTextView(String text){
            final TextView v=new TextView(context);
            v.setGravity(Gravity.CENTER);
            v.setText(text);
            v.setBackgroundColor(-1);
            return v;
        }

        private View newListView(String empty_message){
            final FrameLayout v=new FrameLayout(context);
            final ListView l=new ListView(context);
            final TextView e=newTextView(empty_message);
            v.setBackgroundColor(-1);
            v.addView(e);
            v.addView(l);
            l.setId(android.R.id.list);
            l.setEmptyView(e);
            return v;
        }

        private View newTreeView(){
            TreeNode root=TreeNode.root();
            tree_addChildNode(project_dir,root);
            AndroidTreeView treeView=new AndroidTreeView(context,root);
            treeView.setDefaultViewHolder(ProjectTreeHolder.class);
            treeView.setDefaultNodeClickListener(this);
            final View rv=treeView.getView();
            rv.setBackgroundColor(-1);
            return rv;
        }

        private void tree_addChildNode(File dir,TreeNode parent){
            File[] files=dir.listFiles();
            if(files==null)
                return;
            for(File f:files){
                TreeNode node=new TreeNode(f);
                parent.addChild(node);

                if(f.isDirectory())
                    tree_addChildNode(f,node);

            }
        }

        private List<String> to_string_list(Map<File,List<DiagnosticMessage>> map){
            final ArrayList<String> list=new ArrayList<>();
            if(map==null) return list;
            final Set<Map.Entry<File,List<DiagnosticMessage>>> entries=map.entrySet();
            if(entries==null)return list;
            for(Map.Entry<File,List<DiagnosticMessage>> e:entries){
                String fn=e.getKey().getAbsolutePath();
                List<DiagnosticMessage> infos=e.getValue();
                for(DiagnosticMessage info:infos){
                    list.add(fn+":"+info.line+":"+info.column+":"+info.text);               
                }
            }
            return list;
        }

        public void setErrors(Map<File,List<DiagnosticMessage>> errs){
            final ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,-1){
                public View getView(int position,View convertView,ViewGroup parent) {
                    TextView v=convertView!=null?(TextView)convertView:new TextView(context);
                    v.setTextColor(Color.RED);
                    v.setText(getItem(position));
                    return v;
                }          
            };
            adapter.addAll(to_string_list(errs));

            final ListView v=(ListView)views.get("错误").findViewById(android.R.id.list);
            v.setAdapter(adapter);
        }

        public void setWarnings(Map<File,List<DiagnosticMessage>> wars){
            final ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,-1){
                public View getView(int position,View convertView,ViewGroup parent) {
                    TextView v=convertView!=null?(TextView)convertView:new TextView(context);
                    v.setTextColor(Color.YELLOW);
                    v.setText(getItem(position));
                    return v;
                }          
            };
            adapter.addAll(to_string_list(wars));

            final ListView v=(ListView)views.get("警告").findViewById(android.R.id.list);
            v.setAdapter(adapter);
        }

        @Override
        public void onClick(TreeNode node,Object value){
            File f=(File) value;
            if(f.isFile())
                if(listener!=null);
                 //   listener.onRequest(E_MainActivity.REQUEST_OPEN_FILE,f);
        }

        @Override
        public int getCount(){
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View v, Object o){
            return v==views.get(o);
        }

        @Override
        public void destroyItem(ViewGroup container, int position,Object object) {
            final View v=views.get(object);
            container.removeView(v);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final Map.Entry<String,View> e=new ArrayList<Map.Entry<String,View>>(views.entrySet()).get(position);    
            container.addView(e.getValue());
            return e.getKey();
        }

        @Override
        public CharSequence getPageTitle(int positon){
            final String v= new ArrayList<String>(views.keySet()).get(positon);
            return v;
        }
    }
    
    private static class ProjectTreeHolder extends TreeNode.BaseNodeViewHolder{

        private View node_view;

        @Override
        public View createNodeView(TreeNode node,Object value){
            return null;
        }

        @Override
        public View getNodeView(){
            File f=(File) mNode.getValue();
            ImageView icon=(ImageView)node_view.findViewById(android.R.id.icon);
            TextView text=(TextView)node_view.findViewById(android.R.id.text1);
            TextView hint=(TextView)node_view.findViewById(android.R.id.text2);

            icon.setImageResource(f.isDirectory()?R.drawable.ic_folder:R.drawable.ic_file);
            text.setText(f.getName());
            hint.setText(E_FileActivity.FileAdapter.getTimeStr(f.lastModified()));
            if(!f.isDirectory())
                hint.setText(hint.getText()+" "+E_FileActivity.FileAdapter.getSizeStr(f.length()));

            node_view.setPadding(getLeftPadding(mNode,2),2,2,2);
            return node_view;
        }

        private int getLeftPadding(TreeNode node,int padding){
            if(node.isRoot())
                return padding;
            return getLeftPadding(node.getParent(),padding+16);
        }

        public ProjectTreeHolder(Context context){
            super(context);
            node_view=LayoutInflater.from(context).inflate(R.layout.file_item,null);  
            node_view.setId(com.unnamed.b.atv.R.id.tree_items);

        }
    }
}
