package aenu.eide.view;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import aenu.eide.R;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Adapter;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

public class NewProjectDialog extends Dialog
{
    public static final int PROJECT_ANDROID=0;
    public static final int PROJECT_JAVA=1;
    public static final int PROJECT_CX=2;
    
    public static interface CreateProjectListener{
        void createProject(int p_ty,String name);
    }
    
    private static class Item{
        final Bitmap icon;
        final String text;
        final String hint;
        Item(Bitmap icon,String text,String hint){
            this.icon=icon;
            this.text=text;
            this.hint=hint;
        }
    }
    
    private final Item[] items=new Item[]{
        new Item(null,"Android","java/c/c++/xml"),
        new Item(null,"Java","java"),
        new Item(null,"C/C++","c/c++"),
    };
    
    private ListView list;
    private CreateProjectListener listener;
    
    public NewProjectDialog(Context context){
        super(context);
        setTitle("新建项目");
        list=new ListView(context);
        setContentView(list);
        
        list.setAdapter(adapter());
        list.setOnItemClickListener(listener());
    }
    
    private final ArrayAdapter adapter(){
        final int layout=R.layout.file_item;
        final ArrayAdapter<Item> adapter=new ArrayAdapter<Item>(getContext(),layout){
            public View getView(int position,View convertView,ViewGroup parent) {
                View v=null;
                if(convertView==null)
                    v=getLayoutInflater().inflate(layout,null);
                else
                    v=convertView;
                TextView text1=(TextView) v.findViewById(android.R.id.text1);
                TextView text2=(TextView) v.findViewById(android.R.id.text2);      
                text1.setText(getItem(position).text);
                text2.setText(getItem(position).hint);
                return v;
            }          
        };
        adapter.addAll(items);
        return adapter;
    }
    
    private final AdapterView.OnItemClickListener listener(){
        final AdapterView.OnItemClickListener l=new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4){
                NewProjectDialog.this.hide();
                editProjectNameDialog(p3).show();
            }           
        };
        return l;
    }
    
    private final Dialog editProjectNameDialog(final int type){
        final EditText et = new EditText(getContext());
        et.setHint("请输入项目名");
        
        return new AlertDialog.Builder(getContext()).setTitle(null)
        .setView(et)
        .setPositiveButton("确定", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String input = et.getText().toString();
                if (input.equals("")) {
                    Toast.makeText(getContext(), "项目名不能为空！" + input, Toast.LENGTH_LONG).show();
                }
                else {
                    if(listener!=null)
                        listener.createProject(type,input);
                }
            }
        })
        .setNegativeButton("取消", null)
        .create();  
    }
    
    public NewProjectDialog setCreateProjectListener(CreateProjectListener l){
        this.listener=l;
        return this;
    }
}
