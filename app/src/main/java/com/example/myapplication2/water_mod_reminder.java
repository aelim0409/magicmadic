package com.example.myapplication2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class water_mod_reminder extends AppCompatActivity {

    Calendar alarm;
    int alarm_hour=18;
    int alarm_minute =0;

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
        PendingIntent alarmCallpendingIntent = PendingIntent.getBroadcast(water_mod_reminder.this,0,alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,alarm.getTimeInMillis(),alarmCallpendingIntent);
        else if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,alarm.getTimeInMillis(),alarmCallpendingIntent);
    }

    public String getInformation(String ID) {
        Log.w("???????????? ?????? ??????", "?????? ?????? ?????????");
        String result="null";
        try {
            String id = ID;

            Log.w("(??????)????????? ?????? ???", id );//+water
            water_mod_reminder.getTask task = new water_mod_reminder.getTask();
            result = task.execute(id).get();
            Log.w("(??????)?????????", result);

        } catch (Exception e) {
        }
        return result;
    }


    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.water_mod_reminder);

        Intent Intent = getIntent();
        String ID = Intent.getStringExtra("Id");


        Button button_move;

        button_move=findViewById(R.id.button_move);

        EditText water_text=(EditText) findViewById(R.id.water_text);


        String info=getInformation(ID);
        String [] init_info = info.split(" ");

        water_text.setText(init_info[1]);

        alarm_hour = 18;
        alarm_minute = 0;

        button_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String result = water_mod_func();
                if(result.equals("????????? ??????????????????"))
                {
                    //????????? ????????? ??????
                    Toast.makeText(getApplicationContext(),"????????? ??????????????????.", Toast.LENGTH_LONG).show();
                    finish();
                }
                else{
                    //water_text.setText(water_goal);
                    Toast.makeText(getApplicationContext(),"?????? ????????? ??????????????????.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), Reminder_Main.class);
                    intent.putExtra("Id",ID);
                    startActivity(intent);
                    finish();
                }
               // setAlarm(alarm_hour,alarm_minute);
            }

            String water_mod_func()
            {
                String result= "null";
                Log.w("water_mod","??????????????????");
                try{

                    String id = ID;
                    String water_goal=water_text.getText().toString();

                    String water_time="18:00:00";
                    Log.w("????????? ?????? ???", id+", "+water_goal+", "+water_time);
                    water_mod_reminder.customTask task = new water_mod_reminder.customTask();
                    result = task.execute(id,water_goal,water_time).get();
                    Log.w("?????????",result);
                }catch(Exception e){
                }
                return result;
            }
        });
    }

    class customTask extends AsyncTask<String,Void,String> {
        String sendMsg,receiveMsg;
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/setUserWaterIntake_view");  // ?????? ????????? ????????????(localhost ??????.)
                // ex) http://123.456.789.10:8080/hello/android
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //???????????? POST ???????????? ???????????????.
                conn.setDoOutput(true);

                // ????????? ?????? ??? ????????? ?????????.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&water_goal="+strings[1]+"&water_time="+strings[2]; // GET???????????? ????????? POST??? ?????? ex) "id=admin&pwd=1234";
                osw.write(sendMsg);                           // OutputStreamWriter??? ?????? ??????
                osw.flush();


                // jsp??? ????????? ??? ??????, ???????????? ?????? ??? ??????.
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                } else {    // ????????? ????????? ????????? ???????????? ??????
                    Log.i("?????? ??????", conn.getResponseCode()+"??????");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // ???????????? ?????? ?????? ???????????????.
            return receiveMsg;
        }
    }
    class getTask extends AsyncTask<String,Void,String> {
        String sendMsg,receiveMsg;
        protected String doInBackground(String... strings) {
            try {
                String str;

                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/getUserWaterIntake_view");  // ?????? ????????? ????????????(localhost ??????.)
                // ex) http://123.456.789.10:8080/hello/android
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //???????????? POST ???????????? ???????????????.
                conn.setDoOutput(true);

                // ????????? ?????? ??? ????????? ?????????.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0];
                // GET???????????? ????????? POST??? ?????? ex) "id=admin&pwd=1234";
                osw.write(sendMsg);                           // OutputStreamWriter??? ?????? ??????
                osw.flush();


                // jsp??? ????????? ??? ??????, ???????????? ?????? ??? ??????.
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                } else {    // ????????? ????????? ????????? ???????????? ??????
                    Log.i("?????? ??????", conn.getResponseCode()+"??????");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // ???????????? ?????? ?????? ???????????????.
            return receiveMsg;
        }
    }

}
