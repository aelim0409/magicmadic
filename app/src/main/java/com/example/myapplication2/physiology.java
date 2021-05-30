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

public class physiology extends AppCompatActivity {

    //알람 설정
    public void setAlarm(int hour, int minute)
    {
        alarm = Calendar.getInstance();
        alarm.setTimeInMillis(System.currentTimeMillis());
        alarm.set(Calendar.HOUR_OF_DAY,hour);
        alarm.set(Calendar.MINUTE,minute);
        alarm.set(Calendar.SECOND,0);

        if(alarm.before(Calendar.getInstance())) alarm.add(Calendar.DATE,1);

        Intent alarmIntent = new Intent(getApplicationContext(),AlarmReciver_pills.class);
        AlarmManager alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmIntent.setAction(AlarmReciver_pills.ACTION_RESTART_SERVICE);
        PendingIntent alarmCallpendingIntent = PendingIntent.getBroadcast(physiology.this,0,alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,alarm.getTimeInMillis(),alarmCallpendingIntent);
        else if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,alarm.getTimeInMillis(),alarmCallpendingIntent);

        System.out.println("알람알람알람ㅇ람마 되라도리다릳로다릳라라디");
    }

    Calendar alarm;
    int alarm_hour;
    int alarm_minute ;
    public String getInformation(String ID) {
        Log.w("생리알람 초기 설정", "설정 정보 주는중");
        String result="null";
        try {
            String id = ID;

            Log.w("(초기)앱에서 보낸 값", id );//+water
            physiology.getTask task = new physiology.getTask();
            result = task.execute(id).get();
            Log.w("(초기)받은값", result);

        } catch (Exception e) {

        }
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physiology);

        Intent Intent = getIntent();
        String ID = Intent.getStringExtra("Id");


        Button button_move;

        button_move=findViewById(R.id.button_move);

        EditText physiology_H=(EditText) findViewById(R.id.physiology_hour);
        EditText physiology_M=(EditText) findViewById(R.id.physiology_minute);

        String info=getInformation(ID);
        String [] init_info = info.split(" ");

        physiology_H.setText(init_info[1].split(":")[0]);
        physiology_M.setText(init_info[1].split(":")[1]);

        alarm_hour = Integer.parseInt(physiology_H.getText().toString());
        alarm_minute = Integer.parseInt(physiology_M.getText().toString());

        button_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                water_mod_func();
                setAlarm(alarm_hour,alarm_minute);
            }

            void water_mod_func()
            {
                Log.w("physiologyTime","생리 알람 설정중");
                try{

                    String id = ID;
                    String physiologyTime=physiology_H.getText().toString()+":"+physiology_M.getText().toString()+":"+"00";


                    Log.w("앱에서 보낸 값", id+", "+physiologyTime);
                    physiology.customTask task = new physiology.customTask();
                    String result = task.execute(id,physiologyTime).get();
                    Log.w("받은값",result);

                    if(result.equals("양수를 입력해주세요"))
                    {
                        //토스트 메시지 출력
                        Toast.makeText(getApplicationContext(),"양수를 입력해주세요.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    else{
                        //water_text.setText(water_goal);
                        Toast.makeText(getApplicationContext(),"목표 정보를 저장했습니다.", Toast.LENGTH_LONG).show();
                        android.content.Intent intent = new Intent(getApplicationContext(),MainActivity3.class);
                        intent.putExtra("Id",ID);
                        startActivity(intent);
                        finish();
                    }


                }catch(Exception e){

                }
            }
        });
    }

    class customTask extends AsyncTask<String,Void,String> {
        String sendMsg,receiveMsg;
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/setUserReminderPhysiology_view");  // 어떤 서버에 요청할지(localhost 안됨.)
                // ex) http://123.456.789.10:8080/hello/android
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //데이터를 POST 방식으로 전송합니다.
                conn.setDoOutput(true);

                // 서버에 보낼 값 포함해 요청함.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&physiologyTime="+strings[1]; // GET방식으로 작성해 POST로 보냄 ex) "id=admin&pwd=1234";
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
    class getTask extends AsyncTask<String,Void,String> {
        String sendMsg,receiveMsg;
        protected String doInBackground(String... strings) {
            try {
                String str;

                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/getUserReminderPhysiology_view");  // 어떤 서버에 요청할지(localhost 안됨.)
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