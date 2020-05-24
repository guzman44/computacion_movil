package co.movil.computacion.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import androidx.core.app.NotificationCompat;
import co.movil.Helper.RetrofitClientInstance;
import co.movil.computacion.controller.Splash;
import co.movil.computacion.interfaces.IAuthentication;
import co.movil.computacion.model.UserTokenViewModel;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationService extends Service {

    public static final long NOTIFY_INTERVAL = 10 * 1000; // 10 seconds
    private Handler mHandler = new Handler();
    private Timer mTimer = null;
    private String token;
    private UserTokenViewModel vc;
    private String username;
    private String password;

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.w("Intento !!", "hola intento !!");
        Bundle extras = intent.getExtras();
        token = (String) extras.get("token");
        username = (String) extras.get("username");
        password = (String) extras.get("password");
        token = "Bearer " + token;

        if (mTimer != null) {
            mTimer.cancel();
        }

        mTimer = new Timer();

        // schedule task
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask (token, username, password), 0, NOTIFY_INTERVAL);
        return super.onStartCommand(intent, flags, startId);

    }


    class TimeDisplayTimerTask extends TimerTask {
        private UserTokenViewModel vc;
        private String token;
        private int cantidadEmergencia = 999999900;
        private int cantidadPqr= 999999900;
        private int cantidadEmergenciaBase= 999999900;
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
                    //callEventPlus();
                }

            });
        }

        private String getDateTime() {
            // get date time in custom format
            SimpleDateFormat sdf = new SimpleDateFormat("[yyyy/MM/dd - HH:mm:ss]");
            return sdf.format(new Date());
        }

        public IAuthentication prepareRetrofit() {
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(RetrofitClientInstance.HostApi)
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            return retrofit.create(IAuthentication.class);
        }


        public void createNotification(String title, String text) {
            String NOTIFICATION_CHANNEL_ID = "channel_id";
            createNotificationChannel("chanel8",NOTIFICATION_CHANNEL_ID,"hola test");
            //Notification Channel ID passed as a parameter here will be ignored for all the Android versions below 8.0

            NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationService.this, NOTIFICATION_CHANNEL_ID);
            builder.setContentTitle(title);
            builder.setContentText(text);
            //builder.setSmallIcon(R.drawable.logo);
            builder.setChannelId("test");

            Intent notificationIntent = new Intent(NotificationService.this, Splash.class);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            PendingIntent intent = PendingIntent.getActivity(NotificationService.this, 0,
                    notificationIntent, 0);

            builder.setContentIntent(intent);
            builder.setAutoCancel(true);

            Notification notification = builder.build();
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            mNotificationManager.notify(1231203981, notification);
        }

        private void createNotificationChannel(String channel_name,String CHANNEL_ID,String channel_description) {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = channel_name;
                String description = channel_description;
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
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
