/**
 * GraphView
 * Copyright 2016 Jonas Gehring
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 * GraphView
 * Copyright 2016 Jonas Gehring
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import java.util.ArrayList;


public class graph extends AppCompatActivity {

    public String getInformation(String ID) {
        Log.w("홈화면 초기 설정", "설정 정보 주는중");
        String result="null";
        try {
            String id = ID;

            Log.w("(초기)앱에서 보낸 값", id );//
            graph.getTask task = new graph.getTask();
            result = task.execute(id).get();
            Log.w("(초기)받은값", result);

        } catch (Exception e) {

        }
        return result;
    }

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
        Log.w("그래프 수면", "설정 정보 받는중");

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
        Log.w("그래프 운동", "설정 정보 받는중");

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
        String [] init_info = getInformation(ID).split(" ");
        int water_x=0;
        int sleep_x=0;
        int exercise_x=0;

        ArrayList<String> date_info = new ArrayList<>();
        for(int i=0;i<6;i++){
//            System.out.println("init_info : " + (i*4)+ " " + init_info[i*4]);
//            if(!init_info[i*4].equals("null") ) System.out.println("null!!!!!!!!!!!!");
            if(!init_info[i*4].equals("null")) {
                date_info.add(init_info[i*4]); date_info.add(init_info[(i*4)+1]); date_info.add(init_info[(i*4)+2]); date_info.add(init_info[(i*4)+3]);
            }
        }

        //period_graph
        int[] period_Date = Cal_periods(date_info);
        GraphView period = (GraphView) findViewById(R.id.graph_date);
        DataPoint [] data_period=new DataPoint[period_Date.length];
        for(int i=0;i<period_Date.length;i++)
        {
            data_period[i]=new DataPoint(i+1, period_Date[i]);
        }
        LineGraphSeries<DataPoint> series0 = new LineGraphSeries<DataPoint>(data_period);
        period.addSeries(series0);





        //water_graph

        for(int i=0;i<30;i++)
        {
            if(water_init[i].equals("null")) {
                break;
            }
            water_x = (i+1);
        }

        GraphView water = (GraphView) findViewById(R.id.graph_water);
        DataPoint [] data_water=new DataPoint[water_x];
        for(int i=0;i<water_x;i++)
        {
            data_water[i]=new DataPoint(i+1, Integer.parseInt(water_init[i]));
        }
        LineGraphSeries<DataPoint> series1 = new LineGraphSeries<DataPoint>(data_water);
        water.addSeries(series1);



        //exercise_graph

        double[] exercise_data  = new double[30];
        for(int i=0;i<30;i++)
        {
            if(exercise_init[i].equals("null")) {
                break;
            }
            else
            {
                double H,M;
                double hour;
                H=Double.parseDouble(exercise_init[i].split(":")[0]);
                M=Double.parseDouble(exercise_init[i].split(":")[1]);
                hour = H;
                if(M == 10) hour += 0.17;
                else if(M == 20) hour += 0.34;
                else if(M == 30) hour += 0.5;
                else if(M == 40) hour += 0.67;
                else if(M == 50) hour += 0.84;
                exercise_data[i] = hour;
            }
            exercise_x = (i+1);
        }

        GraphView exercise = (GraphView) findViewById(R.id.graph_exercise);
        DataPoint [] data_exercise=new DataPoint[exercise_x];
        for(int i=0;i<exercise_x;i++)
        {
            data_exercise[i]=new DataPoint(i+1,exercise_data[i]);
            System.out.println("운동 시간 계산 : " + exercise_data[i]);
        }
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(data_exercise);
        exercise.addSeries(series2);

        //sleep_graph

        double[] sleep_data  = new double[30];

        for(int i=0;i<30;i++)
        {
            if(sleep_init[i].equals("null")) {
                break;
            }
            else
            {
                double H,M;
                double hour;
                H=Double.parseDouble(sleep_init[i].split(":")[0]);
                M=Double.parseDouble(sleep_init[i].split(":")[1]);
                hour = H;
                if(M == 10) hour += 0.17;
                else if(M == 20) hour += 0.34;
                else if(M == 30) hour += 0.5;
                else if(M == 40) hour += 0.67;
                else if(M == 50) hour += 0.84;
                sleep_data[i] = hour;

            }
            sleep_x = (i+1);
        }

        GraphView sleep = (GraphView) findViewById(R.id.graph_sleep);

        DataPoint [] data_sleep=new DataPoint[sleep_x];
        for(int i=0;i<sleep_x;i++)
        {
            data_sleep[i]=new DataPoint(i+1, sleep_data[i]);
        }

        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<DataPoint>(data_sleep);
        sleep.addSeries(series3);


        //버튼 연결//
        Button btn_home = findViewById(R.id.home_btn);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),basic_information_page.class);
                intent.putExtra("Id", ID);
                startActivity(intent);
            }
        });

        Button fitness_btn = findViewById(R.id.fitness_btn);
        fitness_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Selftest_main.class);
                intent.putExtra("Id", ID);
                startActivity(intent);
            }
        });

        Button graph_btn = findViewById(R.id.graph_btn);
        graph_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),graph.class);
                intent.putExtra("Id", ID);
                startActivity(intent);
            }
        });

        Button calendar_btn = findViewById(R.id.calendar_btn);
        calendar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Calender_Main.class);

                intent.putExtra("Id", ID);
                startActivity(intent);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        });

        Button remind_btn = findViewById(R.id.remind_btn);
        remind_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Reminder_Main.class);
                intent.putExtra("Id", ID);
                startActivity(intent);
            }
        });
    }

    public int[] SelectedDate(String str){
        String[] result0 =  str.split("-");
        int [] result = new int[3];
        result[0] = Integer.parseInt(result0[0]);
        result[1] = Integer.parseInt(result0[1])-1;
        result[2] = Integer.parseInt(result0[2]);
        return result;
    }

    public int[] Cal_periods(ArrayList<String> init_info){
        int[] result  = new int[5];

        if(init_info.size() >= 8){
            result[0] = DateCount(init_info.get(0), init_info.get(4));
        }

        if(init_info.size() >= 12){
            result[1] = DateCount(init_info.get(4), init_info.get(8));
        }

        if(init_info.size() >= 16){
            result[2] = DateCount(init_info.get(8), init_info.get(12));
        }

        if(init_info.size() >= 20){
            result[3] = DateCount(init_info.get(12), init_info.get(16));
        }

        if(init_info.size() >= 24){
            result[4] = DateCount(init_info.get(16), init_info.get(20));
        }
        return result;
    }

    public int[] DatePlus(String date, int number){ // year month day
        int[] result_date = new int[3];
        int[] date1 =  SelectedDate(date);
        int date_day = date1[2];
        int date_month = date1[1];
        int date_year = date1[0];
        int[] mdays = {31,28,31,30,31,30,31,31,30,31,30,31};
        if(((date_year % 4 == 0) && (date_year % 100 != 0)) || (date_year % 400 == 0))
            mdays[1] = 29;

        int lastDayOfdate = mdays[date_month];


        for(int i=0;i<number;i++){
            date_day++;
            if(date_day>lastDayOfdate){   //달을 넘겨가며 생리가 이어질 경우 다음달로 초기화 해주기 위함
                date_day=1;
                date_month++; if(date_month == 12) { date_month = 0; date_year++; }
            }
        }
        result_date [0] = date_year; result_date[1] = date_month; result_date[2] = date_day;
        return result_date;
    }
    public int[] DateMinus(String date, int n){ // year month day
        int[] result_date = new int[3];
        int[] date1 =  SelectedDate(date);
        int date_day = date1[2];
        int date_month = date1[1];
        int date_year = date1[0];
        int[] mdays = {31,28,31,30,31,30,31,31,30,31,30,31};
        if(((date_year % 4 == 0) && (date_year % 100 != 0)) || (date_year % 400 == 0))
            mdays[1] = 29;
        int lastDayOfdate = mdays[date_month];
        for(int i=0;i<n;i++){
            date_day--;
            if(date_day == 0){   //달을 넘겨가며 생리가 이어질 경우 다음달로 초기화 해주기 위함
                date_month--; if(date_month < 0) { date_month = 11; date_year--;}
                date_day  = mdays[date_month];
            }
        }
        result_date [0] = date_year; result_date[1] = date_month; result_date[2] = date_day;
        return result_date;
    }
    public int DateCount(String to_date, String from_date){ // year month day // 생리기간 생리주기 계산가능
        if(DateCompare(to_date , from_date) < 0) {
            int[] dateto =  SelectedDate(from_date);
            int[] datefrom =  SelectedDate(to_date);
            int to_date_day = dateto[2];
            int to_date_month = dateto[1];
            int to_date_year = dateto[0];

            int from_date_day = datefrom[2];
            int from_date_month = datefrom[1];
            int from_date_year = datefrom[0];

            int cnt = 0;

            int[] mdays = {31,28,31,30,31,30,31,31,30,31,30,31};
            if(((to_date_year % 4 == 0) && (to_date_year % 100 != 0)) || (to_date_year % 400 == 0))
                mdays[1] = 29;
            int lastDayOfdate = mdays[to_date_month];
            for(int i=0;!(to_date_day == from_date_day && to_date_month == from_date_month && to_date_year == from_date_year);i++){
                to_date_day++; cnt++;
                if(to_date_day>lastDayOfdate){   //달을 넘겨가며 생리가 이어질 경우 다음달로 초기화 해주기 위함
                    to_date_day=1;
                    to_date_month++;if(to_date_month == 12) { to_date_month = 0; to_date_month++; }
                }
            }
            return cnt;
        }

        else{
            int[] dateto =  SelectedDate(to_date);
            int[] datefrom =  SelectedDate(from_date);
            int to_date_day = dateto[2];
            int to_date_month = dateto[1];
            int to_date_year = dateto[0];

            int from_date_day = datefrom[2];
            int from_date_month = datefrom[1];
            int from_date_year = datefrom[0];

            int cnt = 0;

            int[] mdays = {31,28,31,30,31,30,31,31,30,31,30,31};
            if(((to_date_year % 4 == 0) && (to_date_year % 100 != 0)) || (to_date_year % 400 == 0))
                mdays[1] = 29;


            int lastDayOfdate = mdays[to_date_month];
            for(int i=0;!(to_date_day == from_date_day && to_date_month == from_date_month && to_date_year == from_date_year);i++){
                to_date_day++; cnt++;
                if(to_date_day>lastDayOfdate){   //달을 넘겨가며 생리가 이어질 경우 다음달로 초기화 해주기 위함
                    to_date_day=1;
                    to_date_month++; if(to_date_month == 12) { to_date_month = 0; to_date_month++; }
                }
            }
            return cnt;
        }


    }

    String IntToDateString(int year, int month, int day){
        return year+"-"+month+"-"+day;
    }
    public int DateCompare(String str1, String str2) {
        int[] date1 =  SelectedDate(str1);
        int date_day = date1[2];
        int date_month = date1[1];
        int date_year = date1[0];

        int[] date2 =  SelectedDate(str2);
        int date_day2 = date2[2];
        int date_month2 = date2[1];
        int date_year2 = date2[0];

        if(date_year > date_year2) return -1;
        else if(date_year < date_year2) return 1;
        else{
            if(date_month > date_month2) return -1;
            else if(date_month < date_month2) return 1;
            else{
                if(date_day > date_day2) return -1;
                else if(date_day < date_day2) return 1;
                else return 0;
            }
        }
    }
    // 평균 주기 계산
    public int DateMenstrualCycle(String period1, String period2, String period3,String period4) {
        int result = (DateCount(period1, period2)+DateCount(period2,period3)+DateCount(period3,period4))/3;
        return result;
    }

    public int DateMenstrualCycle(String period1, String period2, String period3) {
        int result = (DateCount(period1, period2)+DateCount(period2,period3))/2;
        return result;
    }

    public int DateMenstrualCycle(String period1, String period2 ){
        int result = (DateCount(period1, period2));
        return result;
    }

    public int CalMenstrualCycle(ArrayList<String> info){
        if(info.size() >= 16) return DateMenstrualCycle(info.get(12), info.get(8), info.get(4), info.get(0));
        else if(info.size() >= 12) return DateMenstrualCycle(info.get(8), info.get(4), info.get(0));
        else if(info.size() >= 8) return DateMenstrualCycle(info.get(4), info.get(0));
        else return 28;
    }

    class getTask extends AsyncTask<String,Void,String> {
        String sendMsg,receiveMsg;
        protected String doInBackground(String... strings) {
            try {
                String str;

                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/getLimit3_view");  // 어떤 서버에 요청할지(localhost 안됨.)
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