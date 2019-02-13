package aenu.eide;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.text.method.ScrollingMovementMethod;
import android.graphics.Color;

public class E_ErrorActivity extends Activity{
	
	public static final String EXTRA_ERROR_MESSAGE="extra.error_message";
	public static final String ACTION_ERROR="eide.intent.action.ERROR";
	public static final String PROCESS_NANE=E_Application.getAppPackageName()+":error";
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		TextView V=new TextView(this);
		setContentView(V);
		
		V.setMovementMethod(ScrollingMovementMethod.getInstance());
		V.setTextColor(Color.RED);
		V.setText(getErrorMessage());	
	}
	
	private String getErrorMessage(){
		return getIntent().getStringExtra(EXTRA_ERROR_MESSAGE);
	}
}
