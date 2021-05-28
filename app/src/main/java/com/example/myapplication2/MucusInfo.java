package com.example.myapplication2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MucusInfo extends AppCompatActivity {
    CheckBox mucus1,mucus2,mucus3, mucus4,mucus5,mucus6;
    
    String id,setSelectedDate;

    public void give_mucus(String ID,String date,String []b)
    {
        Log.w("mucus 후기 설정", "설정 정보 주는중");

        try {
            id = ID;

            String none="true";
            //얜 무조건 트루
            String mottled=b[0];
            String sticky=b[1];
            String creamy=b[2];
            String likeEggWhite=b[3];
            String watery=b[4];
            String abnormal = b[5];

            /*
            id, date, none, mottled, sticky,
            creamy, likeEggWhite, watery, abnormal
            */
            Log.w("(저장시)앱에서 보낸 값", id +", "+date+", "+none+", "
                    +mottled+", "+sticky+", "+creamy+", "+likeEggWhite+", "+watery+", "
                    +abnormal);//+water
            MucusInfo.setMucus task = new MucusInfo.setMucus();
            String result = task.execute(id,date, none, mottled, sticky,
                    creamy, likeEggWhite, watery, abnormal).get();
            Log.w("(저장시)받은값", result);

            //return result;

        } catch (Exception e) {

        }

    }

    public String get_mucus_info(String ID,String date) {
        Log.w("mucus 초기 설정", "설정 정보 주는중");

        String result="null";

        try {
            String id = ID;


            Log.w("(초기)앱에서 보낸 값", id +", "+date);//+water
            MucusInfo.getMucus task = new MucusInfo.getMucus();
            result = task.execute(id,date).get();
            Log.w("(초기)받은값", result);

            //return result;

        } catch (Exception e) {

        }return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mucus_info);

        Intent Intent = getIntent();
        String [] get_info = Intent.getStringArrayExtra("info");
        id=get_info[0];
        setSelectedDate= get_info[1];
        /*
        if(setSelectedDate.equals("null"))
        {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String today_date = sdf.format(date);
            setSelectedDate=today_date;
        }

         */
        Button mucus =findViewById(R.id.mucus_btn);
        String [] mucus_Check= {"false","false","false","false","false","false"};
        String [] init_info=get_mucus_info(id,setSelectedDate).split(" ");



        //String [] init_info = getInformation(ID).split(" ");

        mucus1=(CheckBox)findViewById(R.id.mucus1);
        mucus2=(CheckBox)findViewById(R.id.mucus2);
        mucus3=(CheckBox)findViewById(R.id.mucus3);
        mucus4=(CheckBox)findViewById(R.id.mucus4);
        mucus5=(CheckBox)findViewById(R.id.mucus5);
        mucus6=(CheckBox)findViewById(R.id.mucus6);
        mucus1.setButtonDrawable(R.drawable.my_selector);
        mucus2.setButtonDrawable(R.drawable.my_selector);
        mucus3.setButtonDrawable(R.drawable.my_selector);
        mucus4.setButtonDrawable(R.drawable.my_selector);
        mucus5.setButtonDrawable(R.drawable.my_selector);
        mucus6.setButtonDrawable(R.drawable.my_selector);

        mucus1.setChecked(Boolean.parseBoolean(init_info[1]));
        mucus2.setChecked(Boolean.parseBoolean(init_info[2]));
        mucus3.setChecked(Boolean.parseBoolean(init_info[3]));
        mucus4.setChecked(Boolean.parseBoolean(init_info[4]));
        mucus5.setChecked(Boolean.parseBoolean(init_info[5]));
        mucus6.setChecked(Boolean.parseBoolean(init_info[6]));


        mucus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    mucus_Check[0]="true";
                } else {
                    mucus_Check[0]="false";
                }

            }
        });

        mucus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    mucus_Check[1]="true";
                } else {
                    mucus_Check[1]="false";
                }
            }
        });

        mucus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    mucus_Check[2]="true";
                } else {
                    mucus_Check[2]="false";
                }
            }
        });
        mucus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    mucus_Check[3]="true";
                } else {
                    mucus_Check[3]="false";
                }
            }
        });
        mucus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    mucus_Check[4]="true";
                } else {
                    mucus_Check[4]="false";
                }
            }
        });
        mucus6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    mucus_Check[5]="true";
                } else {
                    mucus_Check[5]="false";
                }
            }
        });


        for(int i=0;i<6;i++)
        {
            if ((mucus_Check[i].equals( "false") )&& (init_info[i+1].equals("true")))
                mucus_Check[i] = "true";
            else if ((mucus_Check[i] .equals("true")) && (init_info[i+1].equals("false")))
                mucus_Check[i] = "true";
            else if ((mucus_Check[i].equals( "false")) && (init_info[i+1].equals("false")))
                mucus_Check[i] = "false";
            else if ((mucus_Check[i].equals( "true")) && (init_info[i+1].equals("true")))
                mucus_Check[i] = "false";
        }


        mucus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                give_mucus(id,setSelectedDate,mucus_Check);
            }
        });


    }

    class getMucus extends AsyncTask<String,Void,String> {
        String sendMsg,receiveMsg;
        protected String doInBackground(String... strings) {
            try {
                String str;

                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/getMucus_view");  // 어떤 서버에 요청할지(localhost 안됨.)
                // ex) http://123.456.789.10:8080/hello/android
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //데이터를 POST 방식으로 전송합니다.
                conn.setDoOutput(true);

                // 서버에 보낼 값 포함해 요청함.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&date="+strings[1];
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

    class setMucus extends AsyncTask<String,Void,String> {
        String sendMsg,receiveMsg;
        protected String doInBackground(String... strings) {
            try {
                String str;

                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/sendMucus_view");  // 어떤 서버에 요청할지(localhost 안됨.)
                // ex) http://123.456.789.10:8080/hello/android
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //데이터를 POST 방식으로 전송합니다.
                conn.setDoOutput(true);

                /*
                id, date, none, mottled, sticky,
                creamy, likeEggWhite, watery, abnormal
                 */
                // 서버에 보낼 값 포함해 요청함.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&date="+strings[1]+"&none="+strings[2]+
                        "&mottled="+strings[3]+"&sticky="+strings[4]+"&creamy="+strings[5]+"&likeEggWhite="+strings[6]
                        +"&watery="+strings[7]+"&abnormal="+strings[8];
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