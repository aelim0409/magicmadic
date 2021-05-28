package com.example.myapplication2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;





public class graph extends AppCompatActivity {

    public String getWaterInformation(String ID){
        Log.w("그래프 수분", "설정 정보 받는중");

        String result="null";

        try {
            String id = ID;
            Log.w("(초기)앱에서 보낸 값", id );//+water
            graph.getWater task = new graph.getWater();
            result = task.execute(id).get();
            Log.w("(초기)받은값", result);

            //return result;

        } catch (Exception e) {

        }return result;
    }

    public String getSleepInformation(String ID){
        Log.w("그래프 수분", "설정 정보 받는중");

        String result="null";

        try {
            String id = ID;
            Log.w("(초기)앱에서 보낸 값", id );//+water
            graph.getSleep task = new graph.getSleep();
            result = task.execute(id).get();
            Log.w("(초기)받은값", result);

            //return result;

        } catch (Exception e) {

        }return result;
    }

    public String getExerciseInformation(String ID){
        Log.w("그래프 수분", "설정 정보 받는중");

        String result="null";

        try {
            String id = ID;
            Log.w("(초기)앱에서 보낸 값", id );//+water
            graph.getExercise task = new graph.getExercise();
            result = task.execute(id).get();
            Log.w("(초기)받은값", result);

            //return result;

        } catch (Exception e) {

        }return result;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);
        Intent Intent = getIntent();
        String ID = Intent.getStringExtra("Id");

        String [] water_init = getWaterInformation(ID).split(" ");
        String [] sleep_init = getSleepInformation(ID).split(" ");
        String [] exercise_init = getExerciseInformation(ID).split(" ");
        int water_x=0;
        int sleep_x=0;
        int exercise_x=0;

        //water_graph

        for(int i=0;i<30;i++)
        {
            if(water_init[i].equals("null")) {
                water_x = i;
                break;
            }
        }

        GraphView water = (GraphView) findViewById(R.id.graph_water);
        DataPoint [] data_water=new DataPoint[water_x];
        for(int i=0;i<water_x;i++)
        {
            data_water[i]=new DataPoint(i, Double.parseDouble(water_init[i]));
        }
        LineGraphSeries<DataPoint> series1 = new LineGraphSeries<DataPoint>(data_water);
        water.addSeries(series1);



        //exercise_graph

        for(int i=0;i<30;i++)
        {
            if(exercise_init[i].equals("null")) {
                exercise_x = i;
                break;
            }
            else
            {
                int H,M;
                H=Integer.parseInt(exercise_init[i].split(":")[0]);
                M=Integer.parseInt(exercise_init[i].split(":")[1]);

                exercise_init[i]= Double.toString((H*60+M)/60);

            }
        }

        GraphView exercise = (GraphView) findViewById(R.id.graph_exercise);
        DataPoint [] data_exercise=new DataPoint[exercise_x];
        for(int i=0;i<exercise_x;i++)
        {
            data_exercise[i]=new DataPoint(i, Double.parseDouble(exercise_init[i]));
        }
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(data_exercise);
        exercise.addSeries(series2);

        //sleep_graph

        for(int i=0;i<30;i++)
        {
            if(sleep_init[i].equals("null")) {
                sleep_x = i;
                break;
            }
            else
            {
                int H,M;
                H=Integer.parseInt(sleep_init[i].split(":")[0]);
                M=Integer.parseInt(sleep_init[i].split(":")[1]);

                sleep_init[i]= Double.toString((H*60+M)/60);

            }
        }

        GraphView sleep = (GraphView) findViewById(R.id.graph_sleep);

        DataPoint [] data_sleep=new DataPoint[sleep_x];
        for(int i=0;i<sleep_x;i++)
        {
            data_sleep[i]=new DataPoint(i, Double.parseDouble(sleep_init[i]));
        }

        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<DataPoint>(data_sleep);
        sleep.addSeries(series3);


        //버튼 연결//
        Button btn_home = findViewById(R.id.home_btn);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),basic_information_page.class);
                startActivity(intent);
            }
        });

        Button fitness_btn = findViewById(R.id.fitness_btn);
        fitness_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Selftest_main.class);
                startActivity(intent);
            }
        });

        Button graph_btn = findViewById(R.id.graph_btn);
        graph_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),graph.class);
                startActivity(intent);
            }
        });

        Button calendar_btn = findViewById(R.id.calendar_btn);
        calendar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);
            }
        });

        Button remind_btn = findViewById(R.id.remind_btn);
        remind_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity3.class);
                startActivity(intent);
            }
        });
    }



    class getWater extends AsyncTask<String,Void,String> {
        String sendMsg,receiveMsg;
        protected String doInBackground(String... strings) {
            try {
                String str;

                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/getWater_view");  // 어떤 서버에 요청할지(localhost 안됨.)
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
    class getSleep extends AsyncTask<String,Void,String> {
        String sendMsg,receiveMsg;
        protected String doInBackground(String... strings) {
            try {
                String str;

                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/getSleep_view");  // 어떤 서버에 요청할지(localhost 안됨.)
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
    class getExercise extends AsyncTask<String,Void,String> {
        String sendMsg,receiveMsg;
        protected String doInBackground(String... strings) {
            try {
                String str;

                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/getExercise_view");  // 어떤 서버에 요청할지(localhost 안됨.)
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