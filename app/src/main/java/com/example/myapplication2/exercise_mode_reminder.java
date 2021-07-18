package com.example.myapplication2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

public class exercise_mode_reminder extends AppCompatActivity {
    Calendar alarm;
    int alarm_hour=18;
    int alarm_minute=0 ;
    //알람 설정
    public void setAlarm(int hour, int minute)
    {
        alarm = Calendar.getInstance();
        alarm.setTimeInMillis(System.currentTimeMillis());
        alarm.set(Calendar.HOUR_OF_DAY,hour);
        alarm.set(Calendar.MINUTE,minute);
        alarm.set(Calendar.SECOND,0);

        // if(alarm.before(Calendar.getInstance())) alarm.add(Calendar.DATE,1);

        Intent alarmIntent = new Intent(getApplicationContext(),AlarmReciver_pills.class);
        AlarmManager alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmIntent.setAction(AlarmReciver_pills.ACTION_RESTART_SERVICE);
        PendingIntent alarmCallpendingIntent = PendingIntent.getBroadcast(exercise_mode_reminder.this,0,alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,alarm.getTimeInMillis(),alarmCallpendingIntent);
        else if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,alarm.getTimeInMillis(),alarmCallpendingIntent);
    }

    public String getInformation(String ID) {
        Log.w("운동 초기 설정", "설정 정보 주는중");
        String result="null";
        try {
            String id = ID;

            Log.w("(초기)앱에서 보낸 값", id );//+water
            exercise_mode_reminder.getTask task = new exercise_mode_reminder.getTask();
            result = task.execute(id).get();
            Log.w("(초기)받은값", result);


        } catch (Exception e) {

        }
       return result;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_mode_reminder);


        Intent Intent = getIntent();
        String ID = Intent.getStringExtra("Id");

        Button button_move;
        EditText exercise_time = (EditText) findViewById(R.id.exercise_hour);

        String info =getInformation(ID);
        String [] init_info = info.split(" ");


        exercise_time.setText(init_info[1]);


        button_move = findViewById(R.id.button_move);
        button_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = move_exercise_func();
                if(result.equals("양수를 입력해주세요"))
                {
                    //토스트 메시지 출력
                    Toast.makeText(getApplicationContext(),"양수를 입력해주세요", Toast.LENGTH_LONG).show();
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"목표 운동시간 저장하였습니다", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(exercise_mode_reminder.this, Reminder_Main.class);
                    intent.putExtra("Id",ID);
                    startActivity(intent);

                    finish();
                }

            }

            String move_exercise_func() {

                String result="null";
                Log.w("remember", "운동 정보 저장 하는중");
                try {
                    String exercise_hour = exercise_time.getText().toString();
                    String id= ID;

                    Log.w("앱에서 보낸값", id+ ", "+exercise_hour);

                    exercise_mode_reminder.CustomTask task = new exercise_mode_reminder.CustomTask();
                    result = task.execute(id,exercise_hour).get();
                    Log.w("받은값", result);


                } catch (Exception e) {
                }
                return result;
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
                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/setUserExercise_view");  // 어떤 서버에 요청할지(localhost 안됨.)
                // ex) http://123.456.789.10:8080/hello/android
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //데이터를 POST 방식으로 전송합니다.
                conn.setDoOutput(true);


                // 서버에 보낼 값 포함해 요청함.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&exercise_hour=" + strings[1]; // GET방식으로 작성해 POST로 보냄 ex) "id=admin&pwd=1234";
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

                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/getUserExercise_view");  // 어떤 서버에 요청할지(localhost 안됨.)
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



