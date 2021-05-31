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
                //startActivity(intent);
                String result = login_func();
                if(result.equals("아이디 혹은 패스워드가 다릅니다")){
                    Toast.makeText(getApplicationContext(),"아이디 혹은 패스워드가 다릅니다.", Toast.LENGTH_SHORT).show();
                    // System.out.println(result=="아이디 혹은 패스워드가 다릅니다");

                    //finish();
                }
                else if(result.equals("회원이 존재하지 않습니다")){
                    Toast.makeText(getApplicationContext(),"회원이 존재하지 않습니다.", Toast.LENGTH_LONG).show();
                    //finish();
                    //System.out.println(result=="회원이 존재하지 않습니다");
                }
                else if(result.equals("필수 입력 사항입니다")){
                    Toast.makeText(getApplicationContext(),"필수 입력 사항이 빠졌습니다.", Toast.LENGTH_LONG).show();
                    //finish();
                }
                else{
                    //result=aelim aelim 2000-04-04 aelim 22
                    String [] save = result.split(" ");
                    String ID= save[0];

                    Intent intent = new Intent(getApplicationContext(),basic_information_page.class);
                    intent.putExtra("Id", save[0]);
                    startActivity(intent);
                }
            }
            String login_func(){
                String result="null";
                Log.w("login","로그인 하는중");
                try {
                    String id = id_edit.getText().toString();
                    String pw = pw_edit.getText().toString();

                    if (id_edit.getText().length() == 0) {
                        id = "null";
                    }
                    if (pw_edit.getText().length() == 0) {
                        pw = "null";
                    }
                    Log.w("앱에서 보낸값", id + ", " + pw);

                    CustomTask task = new CustomTask();
                    //서버에서 받ㅇ느 값
                    result = task.execute(id, pw).get();
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
                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/signIn_view");  // 어떤 서버에 요청할지(localhost 안됨.)
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