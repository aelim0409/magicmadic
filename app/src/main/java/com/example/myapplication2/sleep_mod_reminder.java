package com.example.myapplication2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class sleep_mod_reminder extends AppCompatActivity {

    EditText id_edit;
    EditText sleep_goal;
    Button button_move;

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.water_mod_reminder);
        button_move=findViewById(R.id.button_move);
        id_edit = (EditText) findViewById(R.id.id_Input);
        sleep_goal=(EditText) findViewById(R.id.water_text);

        button_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),login.class);
                //startActivity(intent);
                water_mod_func();
            }

            void water_mod_func()
            {
                Log.w("signUp","회원가입하는중");
                try{

                    String id = id_edit.getText().toString();
                    String water=sleep_goal.getText().toString();

                    Log.w("앱에서 보낸 값", id+", "+water);
                    sleep_mod_reminder.customTask task = new sleep_mod_reminder.customTask();
                    String result = task.execute(id,water).get();
                    Log.w("받은값",result);

                    if(result=="양수를 입력해주세요")
                    {
                        //토스트 메시지 출력
                        Toast.makeText(getApplicationContext(),"양수를 입력해주세요.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else{
                        sleep_goal.setText(water);
                        Toast.makeText(getApplicationContext(),"목표 정보를 저장했습니다.", Toast.LENGTH_SHORT).show();
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
                URL url = new URL("http://192.168.0.65:8080/android");  // 어떤 서버에 요청할지(localhost 안됨.)
                // ex) http://123.456.789.10:8080/hello/android
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //데이터를 POST 방식으로 전송합니다.
                conn.setDoOutput(true);

                // 서버에 보낼 값 포함해 요청함.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&name="+strings[1]+"&pw"+strings[2]+"&birthday"+strings[3]+"&age"+strings[4]; // GET방식으로 작성해 POST로 보냄 ex) "id=admin&pwd=1234";
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