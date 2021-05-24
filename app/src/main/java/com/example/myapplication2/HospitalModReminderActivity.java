package com.example.myapplication2;

import android.content.Intent;
import android.os.AsyncTask;
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
import java.text.SimpleDateFormat;
import java.util.Locale;

public class HospitalModReminderActivity extends AppCompatActivity {

    public String getInformation(String ID) {
        Log.w("병원 초기 설정", "설정 정보 주는중");
        String result="null";
        try {
            String id = ID;

            Log.w("(초기)앱에서 보낸 값", id );//+water
            HospitalModReminderActivity.getTask task = new HospitalModReminderActivity.getTask();
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
        setContentView(R.layout.hospital_mod_reminder);

        Intent Intent = getIntent();
        String ID = Intent.getStringExtra("Id");

        Button button_move;
        EditText hospital_month = (EditText) findViewById(R.id.hospital_month);
        EditText hospital_date = (EditText) findViewById(R.id.hospital_date);

        String info=getInformation(ID);
        String [] init_info = info.split(" ");

        hospital_month.setText(init_info[1]);
        hospital_date.setText(init_info[2]);

        button_move = findViewById(R.id.button_move);
        button_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                func();
            }
            void func() {

                Log.w("remember", "병원정보 저장 하는중");
                try {
                    String id = ID;
                    String hospitalMonth = hospital_month.getText().toString();
                    String hospitalDate = hospital_date.getText().toString();

                    SimpleDateFormat yearFormat=new SimpleDateFormat("yyyy", Locale.getDefault());
                    String hospital_date= yearFormat+"-"+hospitalMonth+"-"+hospitalDate;

                    if(hospitalMonth.length()==0 && hospitalDate.length()==0)
                    {
                        hospital_date="null";
                    }
                    Log.w("앱에서 보낸값", id+", "+ hospitalMonth);

                    HospitalModReminderActivity.CustomTask task = new HospitalModReminderActivity.CustomTask();
                    String result = task.execute(id,hospital_date).get();
                    Log.w("받은값", result);

                    if(result.equals("양수를 입력해주세요"))
                    {
                        //토스트 메시지 출력
                        Toast.makeText(getApplicationContext(),"양수를 입력해주세요", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    else {
                        Intent intent = new Intent(HospitalModReminderActivity.this, MainActivity3.class);
                        intent.putExtra("Id",ID);
                        startActivity(intent);

                        finish();
                    }

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
                sendMsg = "id="+strings[0]+"&hospital_date=" + strings[1]; // GET방식으로 작성해 POST로 보냄 ex) "id=admin&pwd=1234";
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



