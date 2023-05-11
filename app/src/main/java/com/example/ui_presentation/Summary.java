package com.example.ui_presentation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class Summary extends AppCompatActivity {


    TextView sched,name,desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        String summary_sched =  getIntent().getStringExtra("schedule");
        String summary_name = getIntent().getStringExtra("Meal_name");
        String summary_desc = getIntent().getStringExtra("meal_desc");
        int summary_image = getIntent().getIntExtra("meal_image", 0);
        sched = findViewById(R.id.schedule);
        name = findViewById(R.id.meal_name);
        desc= findViewById(R.id.meal_desc);
        ImageView mealpic = findViewById(R.id.image);
        Button btn_Remind = findViewById(R.id.btn_alarm);


        sched.setText(summary_sched);
        name.setText(summary_name);
        desc.setText(summary_desc);
        mealpic.setImageResource(summary_image);
        createNotificationChannel();


    btn_Remind.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            Intent i = new Intent(Summary.this,ReminderBroadcast.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(Summary.this,0,i,0);
//
//            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            long timeatButtonClick = System.currentTimeMillis();

            long tenSecondMillis =  1;

//            alarmManager.set(AlarmManager.RTC_WAKEUP,timeatButtonClick +tenSecondMillis, pendingIntent);

            FirebaseFirestore firestore = FirebaseFirestore.getInstance();



            String uid = FirebaseAuth.getInstance().getCurrentUser().getEmail();

            DocumentReference docRef = firestore.collection("users").document(uid);

            if (summary_sched.equals("Breakfast")) {
                docRef.update("breakfast", summary_name).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Summary.this, "Data added to breakfast", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else if (summary_sched.equals("Lunch")){
                docRef.update("lunch", summary_name).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Summary.this, "Data added to lunch", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else if (summary_sched.equals("Dinner")){
                docRef.update("dinner", summary_name).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Summary.this, "Data added to dinner", Toast.LENGTH_SHORT).show();
                    }
                });
            }



            startActivity(new Intent(Summary.this,Mainmenu.class));



        }
    });


    }
    private  void createNotificationChannel(){

        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
            CharSequence name = "Channel";
            String desc = "Channel for my app";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notify", name,importance);
            channel.setDescription(desc);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


        }
    }
}