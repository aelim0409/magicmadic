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

        Intent Intent = getIntent();
        String ID = Intent.getStringExtra("Id");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button water = findViewById(R.id.water_setting_btn);
        Button pills= findViewById(R.id.pills_setting_btn);
        Button sleep = findViewById(R.id.sleep_setting_btn);
        Button hospital = findViewById(R.id.hospital_setting_btn);
        Button physiology= findViewById(R.id.phyiology_setting_btn);
        Button exercise = findViewById(R.id.exercise_setting_btn);

        Button reminder_btn=findViewById(R.id.reminder_btn);
        Switch pills_switch=findViewById(R.id.switch1);
        Switch physilogy_switch=findViewById(R.id.switch2);
        Switch hospital_switch=findViewById(R.id.switch3);
        Switch water_switch=findViewById(R.id.switch4);
        Switch exercise_switch=findViewById(R.id.switch5);
        Switch sleep_switch=findViewById(R.id.switch6);

        String[] booleans = {"0","0","0","0","0","0"};


        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), water_mod_reminder.class);
                startActivity(intent1);
            }
        });
        pills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), pills_mod_reminder.class);
                startActivity(intent2);
            }
        });
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), HospitalModReminderActivity.class);
                startActivity(intent3);
            }
        });

        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), sleep_mod_reminder.class);
                startActivity(intent4);
            }
        });

        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(getApplicationContext(), exercise_mode_reminder.class);
                startActivity(intent5);
            }
        });
        //생리 버튼 좀 생각정리 필요


        class switch1Listener implements CompoundButton.OnCheckedChangeListener {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    booleans[0] = "0";

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

                    booleans[5] = "0";
                }
                else
                    booleans[5] = "1";
            }
        }

        pills_switch.setOnCheckedChangeListener(new switch1Listener());
        physilogy_switch.setOnCheckedChangeListener(new switch2Listener());
        hospital_switch.setOnCheckedChangeListener(new switch3Listener());
        water_switch.setOnCheckedChangeListener(new switch4Listener());
        exercise_switch.setOnCheckedChangeListener(new switch5Listener());
        sleep_switch.setOnCheckedChangeListener(new switch6Listener());

        reminder_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
            //startActivity(intent);
            RemindUp_func();
        }

        void RemindUp_func() {
            Log.w("리마인더 온오프 설정", "설정 정보 주는중");

            //앞에 페이지에서 정보 가져오기


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
                String water = booleans[3];
                String exerciseTimeGoal = booleans[4];
                String hospital = booleans[2];
                //null 초기화 부분
                String beforeBirthControlPills="0";
                String birthControlPillsTime ="08:00:00";
                String beforePhysiology="0";
                String hospitalDate ="0";

                // 서버에 없음

                Log.w("앱에서 보낸 값", id + ", "+birthControlPills + ", " +physiology +", "+ sleepTimeGoal + ", " + exerciseTimeGoal + ", " + hospital+", "+water);//+water
                MainActivity3.customTask task = new MainActivity3.customTask();
                String result = task.execute(id,birthControlPills, physiology, sleepTimeGoal, exerciseTimeGoal, hospital,water).get();
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
                intent.putExtra("Id", ID);
                startActivity(intent);

            }
        });

        Button fitness_btn = findViewById(R.id.fitness_btn);
        fitness_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),basic_information_page.class);
                intent.putExtra("Id", ID);
                startActivity(intent);
            }
        });


        Button calendar_btn = findViewById(R.id.calendar_btn);
        calendar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),basic_information_page.class);
                intent.putExtra("Id", ID);
                startActivity(intent);
            }
        });

        Button remind_btn = findViewById(R.id.remind_btn);
        remind_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),basic_information_page.class);
                intent.putExtra("Id", ID);
                startActivity(intent);
            }
        });
    }
    class customTask extends AsyncTask<String,Void,String> {
        String sendMsg,receiveMsg;
        protected String doInBackground(String... strings) {
            try {
                String str;

                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/setUserReminderList_view");  // 어떤 서버에 요청할지(localhost 안됨.)
                // ex) http://123.456.789.10:8080/hello/android
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //데이터를 POST 방식으로 전송합니다.
                conn.setDoOutput(true);

                // 서버에 보낼 값 포함해 요청함.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&birthControlPills="+strings[1]+
                        "&physiology="+strings[2]+"&sleepTimeGoal="+strings[3]+"&exerciseTimeGoal="+strings[4]
                        +"&hospital="+strings[5]+"&water="+strings[6];
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
