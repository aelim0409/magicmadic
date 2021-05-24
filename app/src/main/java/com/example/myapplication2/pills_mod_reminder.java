package com.example.myapplication2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class pills_mod_reminder extends AppCompatActivity {

    public String getInformation(String ID) {
        Log.w("피임약 초기 설정", "설정 정보 주는중");
        String result="null";
        try {
            String id = ID;

            Log.w("(초기)앱에서 보낸 값", id );//+water
            pills_mod_reminder.getTask task = new pills_mod_reminder.getTask();
            result = task.execute(id).get();
            Log.w("(초기)받은값", result);

            return result;

        } catch (Exception e) {

        }
        return result;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pills_mod_reminder);

        Intent Intent = getIntent();
        String ID = Intent.getStringExtra("Id");

        Button button_move;
        EditText pills_hour1 = (EditText) findViewById(R.id.pills_hour);
        EditText pills_minute1 = (EditText) findViewById(R.id.pills_minute);
        EditText pills_startMonth1 = (EditText) findViewById(R.id.pills_startMonth);
        EditText pills_startDay1 = (EditText) findViewById(R.id.pills_startDay);
        EditText pills_days1 = (EditText) findViewById(R.id.pills_days);//없음

        String info=getInformation(ID);
        String [] init_info= info.split(" ");
        String [] get_pills_time=init_info[0].split(":");
        String [] get_pills_date= init_info[1].split("-");
        String get_pills_hour=get_pills_time[0];
        String get_pills_minute=get_pills_time[1];
        String get_pills_month=get_pills_date[1];
        String get_pills_day=get_pills_date[2];

        pills_hour1.setText(get_pills_hour);
        pills_minute1.setText(get_pills_minute);
        pills_startMonth1.setText(get_pills_month);
        pills_startDay1.setText(get_pills_day);
        pills_days1.setText(init_info[2]);


        button_move = findViewById(R.id.button_move);
        button_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move_exercise_func();
            }

            void move_exercise_func() {



                Log.w("remember", "피임약 정보 저장 하는중");
                try {

                    SimpleDateFormat yearFormat=new SimpleDateFormat("yyyy", Locale.getDefault());
                    String id= ID;
                    String pills_hour = pills_hour1.getText().toString();
                    String pills_minute = pills_minute1.getText().toString();
                    String pills_time=pills_hour+":"+pills_minute+":"+"00";
                    String pills_startMonth = pills_startMonth1.getText().toString();
                    String pills_startDay = pills_startDay1.getText().toString();
                    String pills_date=yearFormat+"-"+pills_startMonth+"-"+pills_startDay;
                    String pills_days = pills_days1.getText().toString();//before(기간)

                    if(pills_hour.length()==0 && pills_minute.length()==0)
                    {
                        pills_time="null";
                    }
                    if(pills_startDay.length()==0 && pills_startMonth.length()==0)
                    {
                        pills_date="null";
                    }

                    Log.w("앱에서 보낸값", id+", "+pills_time + ", " + pills_date + ", " + pills_days );


                    pills_mod_reminder.CustomTask task = new pills_mod_reminder.CustomTask();
                    String result = task.execute(id,pills_time, pills_date, pills_days).get();
                    Log.w("받은값", result);


                    Intent intent = new Intent(pills_mod_reminder.this, MainActivity3.class);
                    intent.putExtra("Id",ID);
                    startActivity(intent);

                    finish();


                } catch (Exception e) {
                }
            }

        });
    }




    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;

        @Override
        // doInBackground의 매개변수 값이 여러개일 경우를 위해 배열로
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
                sendMsg = "id="+strings[0]+"&pills_time=" + strings[1]+"pills_date=" + strings[2]+"pills_days="
                        + strings[3]; // GET방식으로 작성해 POST로 보냄 ex) "id=admin&pwd=1234";
                osw.write(sendMsg);                           // OutputStreamWriter에 담아 전송
                osw.flush();

                // jsp와 통신이 잘 되고, 서버에서 보낸 값 받음.
                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                } else {    // 통신이 실패한 이유를 찍기위한 로그
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
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
    class getTask extends AsyncTask<String,Void,String> {
        String sendMsg,receiveMsg;
        protected String doInBackground(String... strings) {
            try {
                String str;

                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/getUserReminderList_view");  // 어떤 서버에 요청할지(localhost 안됨.)
                // ex) http://123.456.789.10:8080/hello/android
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //데이터를 POST 방식으로 전송합니다.
                conn.setDoOutput(true);

                // 서버에 보낼 값 포함해 요청함.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0];
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



