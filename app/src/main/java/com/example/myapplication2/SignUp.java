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

public class SignUp extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        Button btn_signup2 = findViewById(R.id.button_signup2);
        EditText id_edit = (EditText) findViewById(R.id.id_Input);
        EditText name_edit = (EditText) findViewById(R.id.name_Input);
        EditText pw_edit= (EditText) findViewById(R.id.password_Input);
        EditText birthday_edit = (EditText) findViewById(R.id.birthday_Input);
        EditText age_edit = (EditText) findViewById(R.id.age_Input);

        btn_signup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),login.class);
                //startActivity(intent);
                signUp_func();
            }
            void signUp_func()
            {
                Log.w("signUp","회원가입하는중");
                try{
                    String id = id_edit.getText().toString();
                    String name =  name_edit.getText().toString();
                    String pw= pw_edit.getText().toString();
                    String birthday=birthday_edit.getText().toString();
                    String age = age_edit.getText().toString();

                    Log.w("앱에서 보낸 값", id+", "+name+", "+pw+", "+birthday+", "+age);
                    customTask task = new customTask();
                    String result = task.execute(id,name,pw,birthday,age).get();
                    Log.w("받은값",result);

                    if(result=="이미 가입된 아이디입니다")
                    {
                        //토스트 메시지 출력
                        Toast.makeText(getApplicationContext(),"이미 가입된 아이디입니다", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else if(result=="필수 입력 사항입니다")
                    {

                        Toast.makeText(getApplicationContext(),"필수 입력 사항입니다", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else if(result=="회원가입이 완료되었습니다") {
                        
                        Intent intent2 = new Intent(SignUp.this, login.class);
                        startActivity(intent2);
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
