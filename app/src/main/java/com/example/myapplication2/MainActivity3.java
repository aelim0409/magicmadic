package com.example.myapplication2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity3 extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button reminder_btn=findViewById(R.id.reminder_btn);
        Switch switch1=findViewById(R.id.switch1);
        Switch switch2=findViewById(R.id.switch2);
        Switch switch3=findViewById(R.id.switch3);
        Switch switch4=findViewById(R.id.switch4);
        Switch switch5=findViewById(R.id.switch5);
        Switch switch6=findViewById(R.id.switch6);

        String[] booleans = {"0","0","0","0","0","0"};


        class switch1Listener implements CompoundButton.OnCheckedChangeListener {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    booleans[0] = "0";
                    Intent intent = new Intent(getApplicationContext(), pills_mod_reminder.class);
                    startActivity(intent);


                }
                else
                {
                    booleans[0] = "1";
                }
            }

        }

        class switch2Listener implements CompoundButton.OnCheckedChangeListener {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {

                    booleans[1] = "0";

                }
                else
                {
                    booleans[1] = "1";
                }
            }
        }
        class switch3Listener implements CompoundButton.OnCheckedChangeListener {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Intent intent = new Intent(getApplicationContext(), HospitalModReminderActivity.class);
                    startActivity(intent);
                    booleans[2] = "0";
                }
                else
                {
                    booleans[2] = "1";
                }
            }
        }
        class switch4Listener implements CompoundButton.OnCheckedChangeListener {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Intent intent = new Intent(getApplicationContext(), water_mod_reminder.class);
                    startActivity(intent);
                    booleans[3] = "0";
                }
                else
                    booleans[3] = "1";
            }
        }
        class switch5Listener implements CompoundButton.OnCheckedChangeListener {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Intent intent = new Intent(getApplicationContext(), exercise_mode_reminder.class);
                    startActivity(intent);
                    booleans[4] = "0";
                }
                else
                    booleans[4] = "1";
            }
        }
        class switch6Listener implements CompoundButton.OnCheckedChangeListener {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Intent intent = new Intent(getApplicationContext(), sleep_mod_reminder.class);
                    startActivity(intent);
                    booleans[5] = "0";
                }
                else
                    booleans[5] = "1";
            }
        }

        switch1.setOnCheckedChangeListener(new switch1Listener());
        switch2.setOnCheckedChangeListener(new switch2Listener());
        switch3.setOnCheckedChangeListener(new switch3Listener());
        switch4.setOnCheckedChangeListener(new switch4Listener());
        switch5.setOnCheckedChangeListener(new switch5Listener());
        switch6.setOnCheckedChangeListener(new switch6Listener());

        reminder_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
            //startActivity(intent);
            RemindUp_func();
        }

        void RemindUp_func() {
            Log.w("리마인더 설정", "설정 정보 주는중");
            Intent intent2 = getIntent();
            String ID = intent2.getStringExtra("Id");
            try {
                String id = ID;
//                String birthControlPills = String.valueOf(booleans[0]);
//                String physiology = String.valueOf(booleans[1]);
//                String sleepTimeGoal = String.valueOf(booleans[5]);
//                String exerciseTimeGoal = String.valueOf(booleans[3]);
//                String hospital = String.valueOf(booleans[2]);

               // String birthControlPills = booleans[0];
                String birthControlPills = booleans[0];
                String physiology = booleans[1];
                String sleepTimeGoal = booleans[5];
                String exerciseTimeGoal = booleans[3];
                String hospital = booleans[2];
                //null 초기화 부분
                String beforeBirthControlPills="0";
                String birthControlPillsTime ="08:00:00";
                String beforePhysiology="0";
                String hospitalDate ="0";

               // String water = String.valueOf(booleans[3]); // 서버에 없음

                Log.w("앱에서 보낸 값", id + ", "+birthControlPills + ", "+beforeBirthControlPills+", " + birthControlPillsTime+ ", "
                        +physiology +", "+ beforePhysiology+", " + sleepTimeGoal + ", " + exerciseTimeGoal + ", " + hospital+", "+hospitalDate);//+water
                MainActivity3.customTask task = new MainActivity3.customTask();
                String result = task.execute(id,birthControlPills, beforeBirthControlPills,birthControlPillsTime,physiology, beforePhysiology,sleepTimeGoal, exerciseTimeGoal, hospital,hospitalDate).get();
                Log.w("받은값", result);


            } catch (Exception e) {

            }
        }
    });





        Button btn_home = findViewById(R.id.home_btn);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),basic_information_page.class);
                startActivity(intent);

            }
        });

        Button fitness_btn = findViewById(R.id.fitness_btn);
        fitness_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),basic_information_page.class);
                startActivity(intent);
            }
        });


        Button calendar_btn = findViewById(R.id.calendar_btn);
        calendar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),basic_information_page.class);
                startActivity(intent);
            }
        });

        Button remind_btn = findViewById(R.id.remind_btn);
        remind_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),basic_information_page.class);
                startActivity(intent);
            }
        });
    }
    class customTask extends AsyncTask<String,Void,String> {
        String sendMsg,receiveMsg;
        protected String doInBackground(String... strings) {
            try {
                String str;

                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/setReminder_view");  // 어떤 서버에 요청할지(localhost 안됨.)
                // ex) http://123.456.789.10:8080/hello/android
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //데이터를 POST 방식으로 전송합니다.
                conn.setDoOutput(true);

                // 서버에 보낼 값 포함해 요청함.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&birthControlPills="+strings[1]+"&beforeBirthControlPills="+strings[2]+"&birthControlPillsTime="+strings[3]+
                        "&physiology="+strings[4]+"&beforePhysiology="+strings[5]+"&sleepTimeGoal="+strings[6]+"&exerciseTimeGoal="+strings[7]
                        +"&hospital="+strings[8]+"&hospitalDate="+strings[9];
                 // GET방식으로 작성해 POST로 보냄 ex) "id=admin&pwd=1234";
                osw.write(sendMsg);                           // OutputStreamWriter에 담아 전송
                osw.flush();


                // jsp와 통신이 잘 되고, 서버에서 보낸 값 받음.
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                } else {    // 통신이 실패한 이유를 찍기위한 로그
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 서버에서 보낸 값을 리턴합니다.
            return receiveMsg;
        }
    }
}
