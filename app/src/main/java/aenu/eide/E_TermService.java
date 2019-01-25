
//license wtfpl 2.0

//by aenu 2018/10/24
//   email:202983447@qq.com

package aenu.eide;
import android.app.Service;
import android.os.IBinder;
import android.content.Intent;
import android.os.Binder;
import android.app.NotificationManager;
import android.content.Context;
import android.app.Notification;
import android.app.PendingIntent;

public class E_TermService extends Service
{
    class TBinder extends Binder{
        public final E_TermService service=E_TermService.this;
    }
    
    private final TBinder binder=new TBinder();
    private final int NOTIFICATION_ID=android.os.Process.myPid();
    
    @Override
    public IBinder onBind(Intent p1){
        return binder;
    }  
    
    @Override
    public int onStartCommand(Intent intent, int i, int i2) {
        return START_STICKY;
    }

    @Override
    public void onCreate(){
        NotificationManager nm=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(this)
          .setContentTitle("终端")
          .setContentText("会话正在运行")
          .setSmallIcon(android.R.drawable.ic_media_play)
          .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this,E_TermActivity.class), 0))
          .build();
        notification.flags|=Notification.FLAG_NO_CLEAR|Notification.FLAG_ONGOING_EVENT;
        nm.notify(NOTIFICATION_ID,notification);       
    }

    @Override
    public void onDestroy(){
        NotificationManager nm=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancel(NOTIFICATION_ID);
    }
}
