package com.example.myapplication2;

import android.content.Intent;
import android.os.AsyncTask;
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

public class login extends AppCompatActivity {
    int fail =0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button btn_login = findViewById(R.id.button_login2);
        EditText id_edit = (EditText) findViewById(R.id.id_Input);
        EditText pw_edit = (EditText) findViewById(R.id.password_Input);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                //startActivity(intent);
                login_func();
            }
            void login_func() {

                    Log.w("login", "로그인 하는중");

                    try {
                        String id = id_edit.getText().toString();
                        String pw = pw_edit.getText().toString();
                        Log.w("앱에서 보낸값", id + ", " + pw);

                        CustomTask task = new CustomTask();
                        String result = task.execute(id, pw).get();
                        Log.w("받은값", result);

                        if(result=="아이디 혹은 패스워드가 다릅니다"){
                            Toast.makeText(getApplicationContext(),"아이디 혹은 패스워드가 다릅니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else if(result=="회원이 존재하지 않습니다"){
                            Toast.makeText(getApplicationContext(),"회원이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else if(result=="필수 입력 사항입니다"){
                            Toast.makeText(getApplicationContext(),"필수 입력 사항이 빠졌습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }


                        else {//오류 없을 시
                            Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
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
                URL url = new URL("http://192.168.0.65:8080/android");  // 어떤 서버에 요청할지(localhost 안됨.)
                // ex) http://123.456.789.10:8080/hello/android
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //데이터를 POST 방식으로 전송합니다.
                conn.setDoOutput(true);


                // 서버에 보낼 값 포함해 요청함.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&pw="+strings[1]; // GET방식으로 작성해 POST로 보냄 ex) "id=admin&pwd=1234";
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