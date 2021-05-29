package com.example.myapplication2;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class basic_information_page extends AppCompatActivity {

    public String getInformation(String ID) {
        Log.w("홈화면 초기 설정", "설정 정보 주는중");
        String result="null";
        try {
            String id = ID;

            Log.w("(초기)앱에서 보낸 값", id );//
            basic_information_page.getTask task = new basic_information_page.getTask();
            result = task.execute(id).get();
            Log.w("(초기)받은값", result);

        } catch (Exception e) {

        }
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_information_page);

        //id 가져옴 (func에 넣어줘야함 이 위치 아님)
        Intent Intent = getIntent();
        String ID = Intent.getStringExtra("Id");
        TextView name= findViewById(R.id.PersonName);
        name.setText(ID);

        String info = getInformation(ID);
        String[] init_info = info.split(" ");
        Log.w("result 확인: info ", info);
        System.out.println("info : " + info);

        TextView tw_expectedDay = (TextView) findViewById(R.id.tw_expectedDay);                 //생리 예정일
        TextView tw_ovulationDay = (TextView) findViewById(R.id.tw_ovulationDay);               //배란예정일
        TextView tw_lastphysicalDay = (TextView) findViewById(R.id.tw_lastphysicalDay);         //마지막 생리일
        TextView tw_start = (TextView)findViewById(R.id.tw_start);                              //가임기 시작
        TextView tw_cycle = (TextView)findViewById(R.id.tw_cycle);                            //평균생리주기

        String today = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        ArrayList<String> date_info = new ArrayList<>();

        System.out.println("init_info : " + init_info[0]);

        for(int i=0;i<6;i++){
//            System.out.println("init_info : " + (i*4)+ " " + init_info[i*4]);
//            if(!init_info[i*4].equals("null") ) System.out.println("null!!!!!!!!!!!!");
            if(!init_info[i*4].equals("null")) {
                date_info.add(init_info[i*4]); date_info.add(init_info[(i*4)+1]); date_info.add(init_info[(i*4)+2]); date_info.add(init_info[(i*4)+3]);
            }
        }

        System.out.println("date_info" + date_info);

        System.out.println("date_info : " + date_info);

        int num_cycle = CalMenstrualCycle(date_info);


       int[] expectedDay = DatePlus(date_info.get(0), num_cycle);
       String expectedDay_string = expectedDay[0] + "-" + (expectedDay[1]+1) + "-" + expectedDay[2]; // date_info.get(3);
       int num_expetedDay = DateCount(today, expectedDay_string); // 예상일이 지나면 계산 안됨.


       int[] ovulationDay = DateMinus(expectedDay_string, num_cycle/2);
       String ovulationDay_string = ovulationDay[0] + "-" + (ovulationDay[1] + 1) + "-" + ovulationDay[2];
       int num_ovulationDay = DateCount(today, ovulationDay_string);


       int[] startDay = DateMinus(ovulationDay_string, 4);
       String startDay_string =  startDay[0] + "-" + (startDay[1] + 1) + "-" + startDay[2];
       int num_startDay = DateCount(today, startDay_string);

       TextView state_remind = (TextView)findViewById(R.id.state_remind);

       int[] period = new int[3];
       String period_end_string;
       if(date_info.get(1) == null) {
           period = DatePlus(date_info.get(0),7);
           period_end_string = period[0] + "-" + (period[1]+1) + "-" + period[2];
       } else period_end_string = date_info.get(1);

       int[] period2 = DatePlus(startDay_string, 5);
       String period2_end_string = period2[0] + "-" + (period2[1]+1) + "-" + period2[2];

        // 평균생리주기
        tw_cycle.setText(Integer.toString(num_cycle));

        // 마지막 생리일일
        if(date_info.size() > 4) {
            tw_lastphysicalDay.setText(date_info.get(1));
            if(num_expetedDay == 0){
                tw_expectedDay.setText("오늘 입니다.");
            }
            else {
                if(num_expetedDay > 0)
                    tw_expectedDay.setText(Integer.toString(num_expetedDay) +" 일 후 ");
                else
                    tw_expectedDay.setText(Integer.toString(num_expetedDay) +" 일 전 ");
            }

            if(DateCompare(period_end_string, startDay_string) == 1){// date_info.get(1) 없을수도 있을때는? 해결
                if(num_ovulationDay == 0){
                    tw_ovulationDay.setText("오늘 입니다.");
                }
                else {tw_ovulationDay.setText(Integer.toString(num_ovulationDay) +" 일 후 ");}

                if(num_startDay == 0){
                    tw_start.setText("오늘 입니다.");
                }
                else {tw_start.setText(Integer.toString(num_startDay) +" 일 후 ");}
            }
            else{
                tw_ovulationDay.setText("배란 가능성 낮음");
                tw_start.setText("배란 가능성 낮음");
            }
        }
        else{
            tw_lastphysicalDay.setText("사용자 정보가 없습니다.");
            tw_expectedDay.setText("사용자 정보가 없습니다.");
            tw_ovulationDay.setText("사용자 정보가 없습니다.");
            tw_start.setText("사용자 정보가 없습니다.");
        }

        if(DateCompare(expectedDay_string, today) > 0){
            state_remind.setText("예상 생리일정이 지났습니다.");
        } else if (DateCompare(ovulationDay_string, today) == 0){
            state_remind.setText("예상 배란일 입니다.");
        } else if (DateCompare(today , date_info.get(0)) >= 0 && DateCompare(today, period_end_string) <= 0){
            state_remind.setText("생리 중 입니다.");
        } else if(DateCompare(today , startDay_string) >= 0 && DateCompare(today, period2_end_string) <= 0){
            state_remind.setText("가임기 입니다.");
        } else state_remind.setText("비가임기 입니다.");

        System.out.println( " 생리 예정일 : "+ expectedDay_string + " 예상 배란일  : "+ ovulationDay_string +  " 가임기 예상 : "+startDay_string +  " 생리 최근 마지막에 끝난날 : "+period_end_string +  " 가임기 끝나는날 : "+period2_end_string);

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
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);

                intent.putExtra("Id", ID);
                startActivity(intent);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        });

        Button remind_btn = findViewById(R.id.remind_btn);
        remind_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity3.class);
                intent.putExtra("Id", ID);
                startActivity(intent);
            }
        });

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


    public int[] SelectedDate(String str){
        String[] result0 =  str.split("-");
        int [] result = new int[3];
        result[0] = Integer.parseInt(result0[0]);
        result[1] = Integer.parseInt(result0[1])-1;
        result[2] = Integer.parseInt(result0[2]);
        return result;
    }

    public int[] DatePlus(String date, int number){ // year month day
        int[] result_date = new int[3];
        int[] date1 =  SelectedDate(date);
        int date_day = date1[2];
        int date_month = date1[1];
        int date_year = date1[0];
        int[] mdays = {31,28,31,30,31,30,31,31,30,31,30,31};
        int lastDayOfdate = mdays[date_month];

        for(int i=0;i<number;i++){
            date_day++;
            if(date_day>lastDayOfdate){   //달을 넘겨가며 생리가 이어질 경우 다음달로 초기화 해주기 위함
                date_day=1;
                date_month++;
            }
        }
        result_date [0] = date_year; result_date[1] = date_month; result_date[2] = date_day;
        return result_date;
    }
    int[] DatePlus(int date_year, int date_month, int date_day, int number) { // year month day
        int[] result_date = new int[3];
        int[] mdays = {31,28,31,30,31,30,31,31,30,31,30,31};
        int lastDayOfdate = mdays[date_month];
        for(int i=0;i<number;i++){
            date_day++;
            if(date_day>lastDayOfdate){   //달을 넘겨가며 생리가 이어질 경우 다음달로 초기화 해주기 위함
                date_day=1;
                date_month++;
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
        int lastDayOfdate = mdays[date_month];
        for(int i=0;i<n;i++){
            date_day--;
            if(date_day == 0){   //달을 넘겨가며 생리가 이어질 경우 다음달로 초기화 해주기 위함
                date_month--;
                date_day  = mdays[date_month];
            }
        }
        result_date [0] = date_year; result_date[1] = date_month; result_date[2] = date_day;
        return result_date;
    }
    int[] DateMinus(int date_year, int date_month, int date_day, int n){ // year month day
        int[] result_date = new int[3];
        int[] mdays = {31,28,31,30,31,30,31,31,30,31,30,31};
        int lastDayOfdate = mdays[date_month];
        for(int i=0;i<n;i++){
            date_day--;
            if(date_day == 0){   //달을 넘겨가며 생리가 이어질 경우 다음달로 초기화 해주기 위함
                date_month--;
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
            int lastDayOfdate = mdays[to_date_month];
            for(int i=0;!(to_date_day == from_date_day && to_date_month == from_date_month && to_date_year == from_date_year);i++){
                to_date_day++; cnt++;
                if(to_date_day>lastDayOfdate){   //달을 넘겨가며 생리가 이어질 경우 다음달로 초기화 해주기 위함
                    to_date_day=1;
                    to_date_month++;
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
            int lastDayOfdate = mdays[to_date_month];
            for(int i=0;!(to_date_day == from_date_day && to_date_month == from_date_month && to_date_year == from_date_year);i++){
                to_date_day++; cnt++;
                if(to_date_day>lastDayOfdate){   //달을 넘겨가며 생리가 이어질 경우 다음달로 초기화 해주기 위함
                    to_date_day=1;
                    to_date_month++;
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
    //배란일 가임기 표시
    String DateOvulationDay(String date, int cycle) throws ParseException { //최근 생리 시작 날짜
        int[] week_after = DatePlus(date, 7);
        int[] result_OvulationDay = new int[3];
        result_OvulationDay = DatePlus(date, cycle-14);
        int[] calculate_date = new int[3];
        calculate_date = DateMinus(result_OvulationDay[0]+"-"+(result_OvulationDay[1]+1)+"-"+result_OvulationDay[2],4);

        String A = week_after[0]+"-"+(week_after[1]+1)+"-"+week_after[2];
        String B = calculate_date[0]+"-"+(calculate_date[1]+1)+"-"+calculate_date[2];

        if(DateCompare(A, B) > 0)
        {
            //배란일
//            cal_view.addDecorators(new MainActivity2.EventDecorator(Color.GREEN, Collections.singleton(CalendarDay.from(result_OvulationDay[0],result_OvulationDay[1],result_OvulationDay[2]))));
//            //가임기
//            cal_view.addDecorators(new MainActivity2.EventDecorator(Color.BLUE, Collections.singleton(CalendarDay.from(calculate_date[0],calculate_date[1],calculate_date[2]))));
//            calculate_date = DateMinus(result_OvulationDay[0]+"-"+(result_OvulationDay[1]+1)+"-"+result_OvulationDay[2],3);
//            cal_view.addDecorators(new MainActivity2.EventDecorator(Color.BLUE, Collections.singleton(CalendarDay.from(calculate_date[0],calculate_date[1],calculate_date[2]))));
//            calculate_date = DateMinus(result_OvulationDay[0]+"-"+(result_OvulationDay[1]+1)+"-"+result_OvulationDay[2],2);
//            cal_view.addDecorators(new MainActivity2.EventDecorator(Color.BLUE, Collections.singleton(CalendarDay.from(calculate_date[0],calculate_date[1],calculate_date[2]))));
//            calculate_date = DateMinus(result_OvulationDay[0]+"-"+(result_OvulationDay[1]+1)+"-"+result_OvulationDay[2],1);
//            cal_view.addDecorators(new MainActivity2.EventDecorator(Color.BLUE, Collections.singleton(CalendarDay.from(calculate_date[0],calculate_date[1],calculate_date[2]))));
//            calculate_date = DatePlus(result_OvulationDay[0]+"-"+(result_OvulationDay[1]+1)+"-"+result_OvulationDay[2],1);
//            cal_view.addDecorators(new MainActivity2.EventDecorator(Color.BLUE, Collections.singleton(CalendarDay.from(calculate_date[0],calculate_date[1],calculate_date[2]))));
            String result;
            result = result_OvulationDay[0] + "-" + result_OvulationDay[1] + "-" + result_OvulationDay[2];
            return result;
        }
        else return "0000-00-00";
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
}
