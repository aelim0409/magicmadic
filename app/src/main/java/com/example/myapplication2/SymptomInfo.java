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
import java.text.SimpleDateFormat;
import java.util.Date;

public class SymptomInfo extends AppCompatActivity {


    CheckBox symptoms1,symptoms2,symptoms3,symptoms4,symptoms5,symptoms6,symptoms7,symptoms8,symptoms9,symptoms10,symptoms11,symptoms12;


    String id,setSelectedDate;
    public void give_symptom(String ID,String date, String []a)
    {
        Log.w("symptom 후기 설정", "설정 정보 주는중");
        try {
            String id = ID;

            String none="true";
            //얜무조건 트루



            String cramps=a[0];
            String breastTenderness=a[1];
            String headache=a[2];
            String acne=a[3];
            String lumbago=a[4];
            String nausea = a[5];
            String fatigue=a[6];
            String abdominalBloating=a[7];
            String desires=a[8];
            String insomnia=a[9];
            String constipation=a[10];
            String diarrhea=a[11];

            /*
            id, date, none, cramps, breastTenderness,
            headache, acne, lumbago, nausea,
            fatigue, abdominalBloating, desires,
            insomnia, constipation, diarrhea
             */
            Log.w("(저장시)앱에서 보낸 값", id +", "+date+", "+none+", "
                    +cramps+", "+breastTenderness+", "+headache+", "+acne+", "+lumbago+", "
                    +", "+nausea+", "+fatigue+", "+abdominalBloating+", "+
                    desires+", "+insomnia+", "+constipation+", "+diarrhea);//+water
            SymptomInfo.setSymptom task = new SymptomInfo.setSymptom();
            String result = task.execute(id,date, none,cramps, breastTenderness,
                    headache, acne, lumbago, nausea,
                    fatigue, abdominalBloating, desires,
                    insomnia, constipation, diarrhea).get();
            Log.w("(저장시)받은값", result);

            //return result;

        } catch (Exception e) {

        }
    }

    public String get_symptom_info(String ID,String date) {
        Log.w("symptom 초기 설정", "설정 정보 주는중");

        String result="null";

        try {
            String id = ID;

            Log.w("(초기)앱에서 보낸 값", id +", "+date);//+water
            SymptomInfo.getSymptom task = new SymptomInfo.getSymptom();
            result = task.execute(id,date).get();
            Log.w("(초기)받은값", result);

            //return result;

        } catch (Exception e) {

        }return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_info);

        Intent Intent = getIntent();
        String [] get_info = Intent.getStringArrayExtra("info");
        id=get_info[0];
        setSelectedDate=get_info[1];
        if(setSelectedDate.equals("null"))
        {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String today_date = sdf.format(date);
            setSelectedDate=today_date;
        }
        String [] init_info = get_symptom_info(id,setSelectedDate).split(" ");

        Button symptom=findViewById(R.id.symptom_btn);
        symptoms1=(CheckBox)findViewById(R.id.symptom1);
        symptoms2=(CheckBox)findViewById(R.id.symptom2);
        symptoms3=(CheckBox)findViewById(R.id.symptom3);
        symptoms4=(CheckBox)findViewById(R.id.symptom4);
        symptoms5=(CheckBox)findViewById(R.id.symptom5);
        symptoms6=(CheckBox)findViewById(R.id.symptom6);
        symptoms7=(CheckBox)findViewById(R.id.symptom7);
        symptoms8=(CheckBox)findViewById(R.id.symptom8);
        symptoms9=(CheckBox)findViewById(R.id.symptom9);
        symptoms10=(CheckBox)findViewById(R.id.symptom10);
        symptoms11=(CheckBox)findViewById(R.id.symptom11);
        symptoms12=(CheckBox)findViewById(R.id.symptom12);

        symptoms1.setButtonDrawable(R.drawable.my_selector);
        symptoms2.setButtonDrawable(R.drawable.my_selector);
        symptoms3.setButtonDrawable(R.drawable.my_selector);
        symptoms4.setButtonDrawable(R.drawable.my_selector);
        symptoms6.setButtonDrawable(R.drawable.my_selector);
        symptoms5.setButtonDrawable(R.drawable.my_selector);
        symptoms7.setButtonDrawable(R.drawable.my_selector);
        symptoms8.setButtonDrawable(R.drawable.my_selector);
        symptoms9.setButtonDrawable(R.drawable.my_selector);
        symptoms10.setButtonDrawable(R.drawable.my_selector);
        symptoms11.setButtonDrawable(R.drawable.my_selector);
        symptoms12.setButtonDrawable(R.drawable.my_selector);

        symptoms1.setChecked(Boolean.parseBoolean(init_info[1]));
        symptoms2.setChecked(Boolean.parseBoolean(init_info[2]));
        symptoms3.setChecked(Boolean.parseBoolean(init_info[3]));
        symptoms4.setChecked(Boolean.parseBoolean(init_info[4]));
        symptoms5.setChecked(Boolean.parseBoolean(init_info[5]));
        symptoms6.setChecked(Boolean.parseBoolean(init_info[6]));
        symptoms7.setChecked(Boolean.parseBoolean(init_info[7]));

        symptoms8.setChecked(Boolean.parseBoolean(init_info[8]));
        symptoms9.setChecked(Boolean.parseBoolean(init_info[9]));
        symptoms10.setChecked(Boolean.parseBoolean(init_info[10]));
        symptoms11.setChecked(Boolean.parseBoolean(init_info[11]));
        symptoms12.setChecked(Boolean.parseBoolean(init_info[12]));


        String [] symptom_Check={"false","false","false","false","false","false","false","false","false","false","false","false"};
        symptoms1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[0]="true";
                } else {
                    symptom_Check[0]="false";
                }


            }
        });
        symptoms2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[1]="true";
                } else {
                    symptom_Check[1]="false";
                }
            }
        });
        symptoms3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[2]="true";
                } else {
                    symptom_Check[2]="false";
                }
            }
        });
        symptoms4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[3]="true";
                } else {
                    symptom_Check[3]="false";
                }
            }
        });
        symptoms5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[4]="true";
                } else {
                    symptom_Check[4]="false";
                }
            }
        });
        symptoms6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[5]="true";
                } else {
                    symptom_Check[5]="false";
                }
            }
        });
        symptoms7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[6]="true";
                } else {
                    symptom_Check[6]="false";
                }
            }
        });
        symptoms8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[7]="true";
                } else {
                    symptom_Check[7]="false";
                }

            }
        });
        symptoms9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[8]="true";
                } else {
                    symptom_Check[8]="false";
                }
            }
        });symptoms10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[9]="true";
                } else {
                    symptom_Check[9]="false";
                }
            }
        });
        symptoms11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[10]="true";
                } else {
                    symptom_Check[10]="false";
                }
            }
        });
        symptoms12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[11]="true";
                } else {
                    symptom_Check[11]="false";
                }
            }
        });

        for(int i=0;i<12;i++)
        {
            if ((symptom_Check[i].equals( "false") )&& (init_info[i+1].equals("true")))
                symptom_Check[i] = "true";
            else if ((symptom_Check[i] .equals("true")) && (init_info[i+1].equals("false")))
                symptom_Check[i] = "true";
            else if ((symptom_Check[i].equals( "false")) && (init_info[i+1].equals("false")))
                symptom_Check[i] = "false";
            else if ((symptom_Check[i].equals( "true")) && (init_info[i+1].equals("true")))
                symptom_Check[i] = "false";

        }

        symptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                give_symptom(id,setSelectedDate,symptom_Check);
            }
        });



    }

    class getSymptom extends AsyncTask<String,Void,String> {
        String sendMsg,receiveMsg;
        protected String doInBackground(String... strings) {
            try {
                String str;

                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/getSymptom_view");  // 어떤 서버에 요청할지(localhost 안됨.)
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

    class setSymptom extends AsyncTask<String,Void,String> {
        String sendMsg,receiveMsg;
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/sendSymptom_view");  // 어떤 서버에 요청할지(localhost 안됨.)
                // ex) http://123.456.789.10:8080/hello/android
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //데이터를 POST 방식으로 전송합니다.
                conn.setDoOutput(true);

                /*
                id, date, none, cramps, breastTenderness, headache,
                 acne, lumbago, nausea,
                fatigue, abdominalBloating, desires, insomnia, constipation, diarrhea
                 */
                // 서버에 보낼 값 포함해 요청함.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&date="+strings[1]+"&none="+strings[2]+
                        "&cramps="+strings[3]+"&breastTenderness="+strings[4]+"&headache="+strings[5]
                        +"&acne="+strings[6]+"&lumbago="+strings[7]+"&nausea="+strings[8]+"&fatigue="+strings[9]
                        +"&abdominalBloating="+strings[10]+"&desires="+strings[11]+"&insomnia="
                        +strings[12]+"&constipation="+strings[13]+"&diarrhea="+strings[14];
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