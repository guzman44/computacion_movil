package co.movil.computacion.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import co.movil.Helper.RetrofitClientInstance;
import co.movil.computacion.R;
import co.movil.computacion.controller.Home;
import co.movil.computacion.dtos.NotificacionesDTO;
import co.movil.computacion.interfaces.IAuthentication;
import co.movil.computacion.model.UserTokenViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationService extends Service {

    public static final long NOTIFY_INTERVAL = 30 * 1000; // 30 seconds
    private Handler mHandler = new Handler();
    private Timer mTimer = null;
    private String token;
    private UserTokenViewModel vc;
    private String username;
    private String password;
    private int idLogin;

    public static String CHANNEL_ID = "MyApp EventPlus";
    int notificationId = 0;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Bundle extras = intent.getExtras();
        vc = (UserTokenViewModel) extras.get("userToken");
        token = (String) extras.get("token");
        username = (String) extras.get("username");
        password = (String) extras.get("password");
        idLogin = (int) extras.get("idLogin");
        token = "Bearer " + token;

        if (mTimer != null)
            mTimer.cancel();
        mTimer = new Timer();

        // schedule task
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask (token, username, password), 0, NOTIFY_INTERVAL);
        return super.onStartCommand(intent, flags, startId);
    }

    class TimeDisplayTimerTask extends TimerTask {
        private UserTokenViewModel vc;
        private String token;
        private String username;
        private String password;

        public TimeDisplayTimerTask(String token, String username, String password) {
            this.token = token;
            this.password = password;
            this.username = username;
        }

        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    callEventPlus();
                }
            });
        }

        /**
         * Metodo de invocacion
         */
        private void callEventPlus(){
            IAuthentication service = RetrofitClientInstance.getRetrofitInstance().create(IAuthentication.class);
            Call<List<NotificacionesDTO>> call = service.listadoTodasNotificaconesxIdLogin("application/json",token, idLogin);

            call.enqueue(new Callback<List<NotificacionesDTO>>() {

                @Override
                public void onResponse(Call<List<NotificacionesDTO>> call, Response<List<NotificacionesDTO>> response) {
                    if(response.errorBody()!= null){
                    }else{
                        for(NotificacionesDTO list: response.body()){
                            createNotification(list.getTitulo(),list.getMensaje());
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<NotificacionesDTO>> call, Throwable t) {
                }
            });
        }

        /**
         * Creacio de la notificacion
         *
         * @param title
         * @param text
         */
        public void createNotification(String title, String text) {
            createNotificationChannel();
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(NotificationService.this);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationService.this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.calendar_select)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(text))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            final Bundle bundle = new Bundle();
            bundle.putSerializable("USER",vc);
            bundle.putString("userName",username);
            bundle.putString("password",password);

            Intent notificationIntent = new Intent(NotificationService.this, Home.class).putExtras(bundle);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            PendingIntent intent = PendingIntent.getActivity(NotificationService.this, 0,
                    notificationIntent, 0);
            builder.setContentIntent(intent);
            builder.setAutoCancel(true);
            notificationManager.notify(notificationId, builder.build());
        }

        /**
         * Creacion del canal
         */
        private void createNotificationChannel() {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = "channel";
                String description = "channel EventPlus";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                //IMPORTANCE_MAX MUESTRA LA NOTIFICACIÓN ANIMADA
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                channel.setDescription(description);
                // Register the channel with the system; you can't change the importance
                // or other notification behaviors after this
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}
