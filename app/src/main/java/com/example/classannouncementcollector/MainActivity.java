package com.example.classannouncementcollector;

import static android.icu.number.NumberRangeFormatter.with;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.Manifest;
import android.os.Handler;

import com.example.classannouncementcollector.Database.MessageCategoryDataBase;
import com.example.classannouncementcollector.Database.MessageDataBase;
import com.example.classannouncementcollector.dao.MessageCategoryDao;
import com.example.classannouncementcollector.dao.MessageDao;
import com.example.classannouncementcollector.entity.Message;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import com.example.classannouncementcollector.databinding.ActivityMainBinding;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static MessageDao messageDao;
    public static MessageCategoryDao messageCategoryDao;

    @SuppressLint("StaticFieldLeak")
    public static NotificationCompat.Builder builder;

    public static NotificationManager notificationManager;

    public static String DDL_notification=" ";
    StringBuffer strBuffer = new StringBuffer();


    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MessageDataBase messageDataBase = Room.databaseBuilder(
                getApplicationContext(),
                MessageDataBase.class,
                "message"
        ).build();


        MessageCategoryDataBase messageCategoryDataBase=Room.databaseBuilder(
                getApplicationContext(),
                MessageCategoryDataBase.class,
                "messageCategory"
        ).build();

        messageDao =messageDataBase.mMessageDao();
        messageCategoryDao=messageCategoryDataBase.messageCategoryDao();

        com.example.classannouncementcollector.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        //渠道
        createNotificationChannel();

        //申请通知权限
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
               List<Message> all_messages= MainActivity.messageDao.getALLMessage();
                for(Message message:all_messages){
                    String message_dates =message.getDeadline();
                    int date_year= Integer.parseInt(message_dates.substring(0,4));
                    int date_month=Integer.parseInt(message_dates.substring(4,6));
                    int date_day=Integer.parseInt(message_dates.substring(6));

                    LocalDate currentDate = LocalDate.now(); // 当前日期
                    LocalDate endDate = LocalDate.of(date_year, date_month, date_day);
                    long daysBetween = ChronoUnit.DAYS.between(currentDate, endDate);
                    if(daysBetween<=3){
//                        strBuffer.append(message_dates);
//                        strBuffer.append("\n");

                        //创建通知
                        builder = new NotificationCompat.Builder(MainActivity.this, "001")
                                .setSmallIcon(R.drawable.baseline_error_24)
                                .setAutoCancel(true)
                                .setContentTitle("有通知即将到期！")
                                .setContentText("有通知即将到期，请及时查看相关信息！")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                        notificationManager.notify(Integer.parseInt("001"),builder.build());
                        break;
                    }
                }

            }
        }).start();

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "APP";
            String description = "MessageDDL";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("001", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this.
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}