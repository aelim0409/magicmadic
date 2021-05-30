package com.example.myapplication2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;

public class MainActivity2 extends Activity implements OnDateSelectedListener, OnMonthChangedListener {

    TextView date_today;
    TextView main_date_view;
    MaterialCalendarView cal_view;

    int selectedYear, selectedMonth, selectedDay;
    String setSelectedDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    String ID_selected;
    ImageButton waterplus, waterminus, exerciseplus, exerciseminus, sleepplus, sleepminus;
    TextView water, exerciseH, exerciseM, sleepH, sleepM;

    Button start_button, end_button;

    String START="null";
    String END ="null";

    String start_day_input="null";
    String end_day_input="null";

    public String giveChangingMonth(String ID, String month){
        Log.w("달 바꾸기", "바뀐 달 주는중");

        String result="null";
        try {
            String id = ID;

            Log.w("앱에서 보낸 값", id +", "+month);
            MainActivity2.getMonth task = new MainActivity2.getMonth();
            result = task.execute(id,month).get();
            Log.w("받은값", result);

        } catch (Exception e) {

        }return result;
    }
    public String getSelectedInformation(String ID, String setSelectedDate){
        Log.w("선택한 날짜 정보 받기", "설정 정보 받는중");

        String result="null";

        try {
            String id = ID;
            Log.w("(초기)앱에서 보낸 값", id +", "+setSelectedDate);//+water
            MainActivity2.getDate task = new MainActivity2.getDate();
            result = task.execute(id,setSelectedDate).get();
            Log.w("(초기)받은값", result);

            //return result;

        } catch (Exception e) {

        }return result;
    }
    public String getInformation(String ID) {
        Log.w("캘린더 초기 설정", "설정 정보 주는중");
        String result="null";

        try {
            String id = ID;
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String today_date = sdf.format(date);
            Log.w("(초기)앱에서 보낸 값", id +", "+today_date);//+water
            MainActivity2.getTask task = new MainActivity2.getTask();
            result = task.execute(id,today_date).get();
            Log.w("(초기)받은값", result);
            //return result;

        } catch (Exception e) {

        }return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent Intent = getIntent();
        String ID = Intent.getStringExtra("Id");
        ID_selected=ID;

        String [] init_info = getInformation(ID).split(" ");
        setSelectedDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Button symptom , mucus;
        symptom  = (Button)findViewById(R.id.symptom_button);
        mucus = (Button)findViewById(R.id.mucus_button);
        start_button = (Button)findViewById(R.id.start_button);
        end_button = (Button)findViewById(R.id.end_button);
        symptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SymptomInfo.class);
                String [] intent_str =  new String[2];
                intent_str[0] = ID;
                intent_str[1] = setSelectedDate;
                intent.putExtra("info", intent_str);
                startActivity(intent);
            }
        });

        mucus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MucusInfo.class);
                String [] intent_str={ID,setSelectedDate};
                intent.putExtra("info", intent_str);
                startActivity(intent);
            }
        });

        //init_info[0]=sleeptime init_info[1]=exercisetime init_info[2]=waterintake
        // init_info[3]=startday1 init_info[4]=endday1 init_info[5]=null init_info[6]=null init_info[7]=null init_info[8]=null
        String sleep_time= init_info[0];
        String exercise_time = init_info[1];
        String water_intake = init_info[2];

        //getinforamation 한 시작과 끝 날짜

        waterminus = (ImageButton)findViewById(R.id.waterminus);
        waterplus = (ImageButton)findViewById(R.id.waterplus);
        water = (TextView)findViewById(R.id.water_record);
        exerciseplus = (ImageButton)findViewById(R.id.exerciseplus);
        exerciseminus = (ImageButton)findViewById(R.id.exercisemnus);
        exerciseH = (TextView)findViewById(R.id.exercise_recordH);
        exerciseM = (TextView)findViewById(R.id.exercise_recordM);
        sleepplus = (ImageButton)findViewById(R.id.sleepplus);
        sleepminus = (ImageButton)findViewById(R.id.sleepminus);
        sleepH = (TextView)findViewById(R.id.sleep_recordH);
        sleepM = (TextView)findViewById(R.id.sleep_recordM);

        System.out.print(exercise_time.split(":")[0]);
        water.setText(water_intake);
        exerciseH.setText(exercise_time.split(":")[0]);

        exerciseM.setText(exercise_time.split(":")[1]);
        sleepH.setText(sleep_time.split(":")[0]);
        sleepM.setText(sleep_time.split(":")[1]);

        TextView todayText = findViewById(R.id.today_text);

        // String mucus_none2= mucus_none;

        waterplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = water.getText().toString();
                int n = Integer.parseInt(str);
                n++;
                str = Integer.toString(n);
                water.setText(str);
            }
        });
        waterminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = water.getText().toString();
                int n = Integer.parseInt(str);
                n--;
                if(n< 0) n =0;
                str = Integer.toString(n);
                water.setText(str);
            }
        });
        exerciseplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = exerciseM.getText().toString();
                String str1 = exerciseH.getText().toString();
                int n = Integer.parseInt(str);
                int n1 = Integer.parseInt(str1);
                n += 10;
                if(n >= 60){
                    n1++;
                    n = 0;
                }
                str = Integer.toString(n);
                str1 = Integer.toString(n1);
                exerciseM.setText(str);
                exerciseH.setText(str1);
            }
        });
        exerciseminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = exerciseM.getText().toString();
                String str1 = exerciseH.getText().toString();
                int n = Integer.parseInt(str);
                int n1 = Integer.parseInt(str1);
                n -= 10;
                if(n < 0 ) {
                    n = 50;
                    if( n1 == 0) n = 0;
                    else n1--;
                    if(n1 < 0) n1 = 0;
                }
                str = Integer.toString(n);
                str1 = Integer.toString(n1);
                exerciseM.setText(str);
                exerciseH.setText(str1);
            }
        });

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

        sleepplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = sleepM.getText().toString();
                String str1 = sleepH.getText().toString();
                int n = Integer.parseInt(str);
                int n1 = Integer.parseInt(str1);
                n += 10;
                if(n >= 60){
                    n1++;
                    n = 0;
                }
                str = Integer.toString(n);
                str1 = Integer.toString(n1);
                sleepM.setText(str);
                sleepH.setText(str1);
            }
        });
        sleepminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = sleepM.getText().toString();
                String str1 = sleepH.getText().toString();
                int n = Integer.parseInt(str);
                int n1 = Integer.parseInt(str1);
                n -= 10;
                if(n < 0 ) {
                    n = 50;
                    if( n1 == 0) n = 0;
                    else n1--;
                    if(n1 < 0) n1 = 0;
                }
                str = Integer.toString(n);
                str1 = Integer.toString(n1);
                sleepM.setText(str);
                sleepH.setText(str1);
            }
        });

        todayText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int y = cal.get(Calendar.YEAR);
                int m = cal.get(Calendar.MONTH);
                int d = cal.get(Calendar.DAY_OF_MONTH);
            }
        });

        date_today = findViewById(R.id.date_today);
        date_today.setText(new SimpleDateFormat("yyyy년 MM월 d일", Locale.getDefault()).format(new Date())); // 오늘 날짜 초기화

        main_date_view = findViewById(R.id.date_text);
        main_date_view.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date())); // 오늘 날짜 초기화
        cal_view = (MaterialCalendarView)findViewById(R.id.calendar);

        cal_view.setOnDateChangedListener(this);
        cal_view.setOnMonthChangedListener((OnMonthChangedListener) this);
        cal_view.setSelectedDate(CalendarDay.today());

        int year;
        int day;
        int period=0;
        String [] start_d_day;
        String [] end_d_day;

        //init_info[0]=sleeptime init_info[1]=exercisetime init_info[2]=waterintake
        // init_info[3]=startday1 init_info[4]=endday1 init_info[5]=null init_info[6]=null init_info[7]=null init_info[8]=null

        for(int i=0;i<5;i+=2){
            if(!init_info[i+5].equals("null")){
                try {
                    start_d_day = init_info[i+5].split("-");
                    end_d_day = init_info[i + 5].split("-");

                    year = Integer.parseInt(start_d_day[0]);
                    int month_itr = Integer.parseInt(start_d_day[1]) - 1;
                    day = Integer.parseInt(start_d_day[2]);

                    int[] mdays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                    int lastDay = mdays[month_itr];

                    if(init_info[i+6].equals("null")){
                        period=5;
                        int day_end=Integer.parseInt(start_d_day[2])+4;
                        //end_d_day[2]=start_d_day[2];
                        if(day_end > lastDay){
                            if(Integer.parseInt(start_d_day[1])+1<10){
                                end_d_day[1]="0"+Integer.toString(Integer.parseInt(start_d_day[1])+1);
                            }
                            else{
                                end_d_day[1]=Integer.toString(Integer.parseInt(start_d_day[1])+1);
                            }
                            end_d_day[0]= start_d_day[0];
                            if(day_end-lastDay<10){
                                end_d_day[2]="0"+Integer.toString(day_end-lastDay);
                            }
                            else{
                                end_d_day[2]= Integer.toString(day_end-lastDay);
                            }
                        }
                        else{
                            end_d_day[0]=start_d_day[0];
                            end_d_day[1]=start_d_day[1];
                            end_d_day[2]=Integer.toString(day_end);
                        }
                        System.out.println("첫 화면에서 end 정보가 없는 경우"+end_d_day[0]+"년 "+end_d_day[1]+"월 "+end_d_day[2]+"일일");
                    }
                    else{
                        end_d_day = init_info[i + 6].split("-");
                        int day_end = Integer.parseInt(end_d_day[2]);
                        period = day_end - day + 1;
                        System.out.println("첫 화면에서 end 정보가 있는 경우"+end_d_day[0]+"년 "+end_d_day[1]+"월 "+end_d_day[2]+"일일");
                    }

                    if (period < 0) {
                        period += mdays[month_itr];
                    }

                    for (int j = 0; j < period; j++) {

                        cal_view.addDecorators(new EventDecorator(Color.RED, Collections.singleton(CalendarDay.from(year, month_itr, day))));
                        System.out.println("year = " + year + "month_itr = " + month_itr + " day= " + day);
                        day++;
                        if (day > lastDay) {   //달을 넘겨가며 생리가 이어질 경우 다음달로 초기화 해주기 위함
                            day = 1;
                            month_itr++;
                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

        Button save_button=findViewById(R.id.save_button);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                give_info(ID);
            }

            void give_info(String ID)
            {
                Log.w("캘린더 전체 ", "설정 정보 주는중");

                try {
                    String id = ID;
                    String sleepTime=sleepH.getText().toString()+":"+sleepM.getText().toString()+":"+"00";
                    String exerciseTime=exerciseH.getText().toString()+":"+exerciseM.getText().toString()+":"+"00";
                    String waterIntake = water.getText().toString();
                    String startDay=start_day_input;
                    String endDay=end_day_input;
                    String symptom = "false";
                    String mucus="false";

                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String today_date = sdf.format(date);

                    if(!today_date.equals(setSelectedDate))
                        today_date=setSelectedDate;
                    else
                        today_date=sdf.format(date);

                    /*
                    if(startDay.equals(START))
                        startDay=START;
                    if(endDay.equals(END))
                        endDay=END;
*/
                    if(startDay=="null" && START!="null")
                    {
                        startDay=START;
                    }
                    else if(endDay=="null"&&END!="null")
                    {
                        endDay=END;
                    }

                    Log.w("(초기)앱에서 보낸 값", id +", "+today_date+", "+sleepTime+", "+exerciseTime+", "+waterIntake+", "+startDay
                            +", "+endDay+", "+symptom+", "+mucus);//+water
                    MainActivity2.customTask task = new MainActivity2.customTask();
                    String result = task.execute(id,today_date,sleepTime,exerciseTime,waterIntake,startDay,endDay,symptom,mucus).get();
                    Log.w("(초기)받은값", result);

                    //return result;

                } catch (Exception e) {

                }
            }
        });
        //String Startdate="null";
        final Date[] start = new Date[1];
        final Date[] end = new Date[1];

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date =  main_date_view.getText().toString();

                int day = 1, month = 1, year = 2021, period = 5, lastDay = 31;

                try {
                    end_day_input = "null";
                    start[0] = SelectedDate(date);
                    day = CalendarDay.from(start[0]).getDay();
                    month = CalendarDay.from(start[0]).getMonth();
                    year = CalendarDay.from(start[0]).getYear();
                    CalendarDay.from(start[0]).getDay();
                    Calendar cc = Calendar.getInstance();
                    System.out.println(setSelectedDate);
                    int[] mdays = {31,28,31,30,31,30,31,31,30,31,30,31};
                    lastDay = mdays[month];
                } catch(ParseException e) {
                    e.printStackTrace();
                }
                finally {
                    if(!START.equals("null")) {
                        start_button.setBackgroundColor(Color.rgb(255,255,255));
                        START = "null";
                        start_day_input = "null";
                        for(int i=0;i<period;i++){
                            cal_view.addDecorators(new EventDecorator(Color.rgb(237, 250, 255), Collections.singleton(CalendarDay.from(year,month,day))));
                            day++;
                            if(day>lastDay){   //달을 넘겨가며 생리가 이어질 경우 다음달로 초기화 해주기 위함
                                day=1;
                                month++;
                            }
                        }
                    } else {
                        start_button.setBackgroundColor(Color.rgb(255, 255, 182));
                        start_day_input= setSelectedDate;
                        START = start_day_input;
                        for(int i=0;i<period;i++){
                            cal_view.addDecorators(new EventDecorator(Color.RED, Collections.singleton(CalendarDay.from(year,month,day))));
                            day++;
                            if(day>lastDay){   //달을 넘겨가며 생리가 이어질 경우 다음달로 초기화 해주기 위함
                                day=1;
                                month++;
                            }
                        }
                    }
                }
            }
        });


        end_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date =  main_date_view.getText().toString();

                int day = 1, month = 1, year = 2021, period = 5, lastDay = 31;

                try {
                    end[0] = SelectedDate(date);
                    start_day_input="null";

                    day = CalendarDay.from(end[0]).getDay()+1;
                    month = CalendarDay.from(end[0]).getMonth();
                    year = CalendarDay.from(end[0]).getYear();
                    CalendarDay.from(end[0]).getDay();
                    Calendar cc = Calendar.getInstance();
                    int[] mdays = {31,28,31,30,31,30,31,31,30,31,30,31};
                    lastDay = mdays[month];
                } catch (ParseException e) {
                    e.printStackTrace();
                } finally {
                    if(!END.equals("null")) {
                        end_button.setBackgroundColor(Color.rgb(255, 255, 255));
                        END = "null";
                        end_day_input = "null";
                    } else {
                        end_button.setBackgroundColor(Color.rgb(255, 255, 182));
                        end_day_input = setSelectedDate;
                        END = end_day_input;
                        for(int i=0;i<5;i++){
                            cal_view.addDecorators(new EventDecorator(Color.parseColor("#EDFAFF"), Collections.singleton(CalendarDay.from(year,month,day))));
                            day+=1;
                            if(day>lastDay){   //달을 넘겨가며 생리가 이어질 경우 다음달로 초기화 해주기 위함
                                day=1;
                                month++;
                            }
                        }
                    }
                }
            }
        });
    }

    public Date SelectedDate(String str) throws ParseException {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date to = transFormat.parse(str);
        return to;
    }

    //날짜 선택되었을 때
    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        main_date_view.setText(date.getYear() + "-" + (date.getMonth()+1) + "-" + date.getDay());
        selectedYear=date.getYear();
        String month="null";
        selectedMonth=date.getMonth()+1;
        if(selectedMonth<10)
            month="0"+Integer.toString(selectedMonth);
        else
            month=Integer.toString(selectedMonth);
        selectedDay=date.getDay();
        String day="null";
        if(selectedDay<10)
            day="0"+Integer.toString(selectedDay);
        else
            day=Integer.toString(selectedDay);

        setSelectedDate=selectedYear+"-"+month+"-"+day;

        String [] selected_info = getSelectedInformation(ID_selected,setSelectedDate).split(" ");

        String Exercise[]=selected_info[1].split(":");
        String Sleep[]=selected_info[0].split(":");
        water = (TextView)findViewById(R.id.water_record);
        exerciseH = (TextView)findViewById(R.id.exercise_recordH);
        exerciseM = (TextView)findViewById(R.id.exercise_recordM);
        sleepH = (TextView)findViewById(R.id.sleep_recordH);
        sleepM = (TextView)findViewById(R.id.sleep_recordM);

        water.setText(selected_info[2]);
        exerciseH.setText(Exercise[0]);
        exerciseM.setText(Exercise[1]);
        sleepH.setText(Sleep[0]);
        sleepM.setText(Sleep[1]);

        START=selected_info[3];
        if(!START.equals("null")) {
            start_button.setBackgroundColor(Color.rgb(255, 255, 182));
        } else {
            start_button.setBackgroundColor(Color.rgb(255, 255, 255));
        }
        END=selected_info[4];
        if(!END.equals("null")) {
            end_button.setBackgroundColor(Color.rgb(255, 255, 182));
        } else {
            end_button.setBackgroundColor(Color.rgb(255, 255, 255));
        }
        //시작날짜와 끝날짜 정보도 받음 캘린더 가시화 시켜야함.
    }

    //달 바뀌었을 때
    @Override
    public void onMonthChanged (MaterialCalendarView widget, CalendarDay date) {

        int changed_month=date.getMonth()+1;
        String month="null";

        if(changed_month<10){
            month="0"+Integer.toString(changed_month);
        }
        else{
            month=Integer.toString(changed_month);
        }

        String [] month_info= giveChangingMonth(ID_selected, month).split(" ");
        //month_info[0]=2021-04-01, month_info[1]=2021-04-06, month_info[2]="null", month_info[3]="null", month_info[4]="null", month_info[5]="null"

        int year;
        int day;
        int period=0;
        String [] start_d_day;
        String [] end_d_day;
        for(int i=0;i<5;i+=2){
            if(!month_info[i].equals("null")){
                try {

                    start_d_day = month_info[i].split("-");  // start_d_day[0]=년 start_d_day[1]=월 start_d_day[2]=일
                    end_d_day= month_info[i].split("-");

                    year = Integer.parseInt(start_d_day[0]);
                    int month_itr = Integer.parseInt(start_d_day[1]) - 1;
                    day = Integer.parseInt(start_d_day[2]);

                    int[] mdays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                    int lastDay = mdays[month_itr];

                    if(month_info[i+1].equals("null")){
                        period=5;
                        int day_end=Integer.parseInt(start_d_day[2])+4;
                        //end_d_day[2]=start_d_day[2];
                        if(day_end > lastDay){
                            if(Integer.parseInt(start_d_day[1])+1<10){
                                end_d_day[1]="0"+ Integer.toString(month_itr+1);
                            }
                            else{
                                end_d_day[1]= Integer.toString(month_itr+1);
                            }
                            if(day_end-lastDay<10){
                                end_d_day[2]="0"+Integer.toString((day_end-lastDay));
                            }
                            else{
                                end_d_day[2]= Integer.toString((day_end-lastDay));
                            }
                        }
                        else{
                            end_d_day[0]=start_d_day[0];
                            end_d_day[1]=start_d_day[1];
                            end_d_day[2]=Integer.toString(day_end);
                        }
                        System.out.println("달 바꼈을 때 end 정보가 없는 경우"+end_d_day[0]+"년 "+end_d_day[1]+"월 "+end_d_day[2]+"일일");
                    }
                    else{
                        end_d_day = month_info[i + 1].split("-");
                        int day_end = Integer.parseInt(end_d_day[2]);
                        period = day_end - day + 1;
                        System.out.println("달 바꼈을 때 end 정보가 있는 경우 "+end_d_day[0]+end_d_day[1]+ end_d_day[2]);
                    }

                    //start_d_day[0]=2021 start_d_day[1]=06 start_d_day[2]=13
                    if (period < 0) {
                        period += mdays[month_itr];
                    }
                    for (int j = 0; j < period; j++) {
                        cal_view.addDecorators(new EventDecorator(Color.RED, Collections.singleton(CalendarDay.from(year, month_itr, day))));
                        System.out.println("year = " + year + "month_itr = " + month_itr + " day= " + day);
                        day++;
                        if (day > lastDay) {   //달을 넘겨가며 생리가 이어질 경우 다음달로 초기화 해주기 위함
                            day = 1;
                            month_itr++;
                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public int[] DatePlus(String date, int number) throws ParseException { // year month day
        int[] result_date = new int[3];
        Date date1 =  SelectedDate(date);
        int date_day = CalendarDay.from(date1).getDay();
        int date_month = CalendarDay.from(date1).getMonth();
        int date_year = CalendarDay.from(date1).getYear();
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
    int[] DatePlus(int date_year, int date_month, int date_day, int number) throws ParseException { // year month day
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
    public int[] DateMinus(String date, int n) throws ParseException { // year month day
        int[] result_date = new int[3];
        Date date1 =  SelectedDate(date);
        int date_day = CalendarDay.from(date1).getDay();
        int date_month = CalendarDay.from(date1).getMonth();
        int date_year = CalendarDay.from(date1).getYear();
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
    int[] DateMinus(int date_year, int date_month, int date_day, int n) throws ParseException { // year month day
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
    public int DateCount(String to_date, String from_date) throws ParseException { // year month day // 생리기간 생리주기 계산가능
        int[] result_todate = new int[3];
        int[] result_fromdate = new int[3];
        Date dateto =  SelectedDate(to_date);
        Date datefrom =  SelectedDate(from_date);
        int to_date_day = CalendarDay.from(dateto).getDay();
        int to_date_month = CalendarDay.from(dateto).getMonth();
        int to_date_year = CalendarDay.from(dateto).getYear();
        int from_date_day = CalendarDay.from(datefrom).getDay();
        int from_date_month = CalendarDay.from(datefrom).getMonth();
        int from_date_year = CalendarDay.from(datefrom).getYear();

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

    boolean DateBetweenAandB(String A, String B, String btween){
        if(A.compareTo(btween) > 0  && B.compareTo(btween) < 0) return true;
        else return false;
    }

    String IntToDateString(int year, int month, int day){
        return year+"-"+month+"-"+day;
    }
    public int DateCompare(String str1, String str2) throws ParseException {
        Date date1 =  SelectedDate(str1);
        int date_day = CalendarDay.from(date1).getDay();
        int date_month = CalendarDay.from(date1).getMonth();
        int date_year = CalendarDay.from(date1).getYear();

        Date date2 =  SelectedDate(str2);
        int date_day2 = CalendarDay.from(date2).getDay();
        int date_month2 = CalendarDay.from(date2).getMonth();
        int date_year2 = CalendarDay.from(date2).getYear();

        if(date_year > date_year2) return -1; else if(date_year < date_year2) return 1;
        else{
            if(date_month > date_month2) return -1; else if(date_month < date_month2) return 1;
            else{
                if(date_day > date_day2) return -1; else if(date_day < date_day2) return 1; else return 0;
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
            cal_view.addDecorators(new EventDecorator(Color.GREEN, Collections.singleton(CalendarDay.from(result_OvulationDay[0],result_OvulationDay[1],result_OvulationDay[2]))));
            //가임기
            cal_view.addDecorators(new EventDecorator(Color.BLUE, Collections.singleton(CalendarDay.from(calculate_date[0],calculate_date[1],calculate_date[2]))));
            calculate_date = DateMinus(result_OvulationDay[0]+"-"+(result_OvulationDay[1]+1)+"-"+result_OvulationDay[2],3);
            cal_view.addDecorators(new EventDecorator(Color.BLUE, Collections.singleton(CalendarDay.from(calculate_date[0],calculate_date[1],calculate_date[2]))));
            calculate_date = DateMinus(result_OvulationDay[0]+"-"+(result_OvulationDay[1]+1)+"-"+result_OvulationDay[2],2);
            cal_view.addDecorators(new EventDecorator(Color.BLUE, Collections.singleton(CalendarDay.from(calculate_date[0],calculate_date[1],calculate_date[2]))));
            calculate_date = DateMinus(result_OvulationDay[0]+"-"+(result_OvulationDay[1]+1)+"-"+result_OvulationDay[2],1);
            cal_view.addDecorators(new EventDecorator(Color.BLUE, Collections.singleton(CalendarDay.from(calculate_date[0],calculate_date[1],calculate_date[2]))));
            calculate_date = DatePlus(result_OvulationDay[0]+"-"+(result_OvulationDay[1]+1)+"-"+result_OvulationDay[2],1);
            cal_view.addDecorators(new EventDecorator(Color.BLUE, Collections.singleton(CalendarDay.from(calculate_date[0],calculate_date[1],calculate_date[2]))));
            String result;
            result = result_OvulationDay[0] + "-" + result_OvulationDay[1] + "-" + result_OvulationDay[2];
            return result;
        }
        else return "0000-00-00";
    }

    // 평균 주기 계산
    public int DateMenstrualCycle(String period1, String period2, String period3) throws ParseException {
        int result = (DateCount(period1, period2)+(DateCount(period2,period3)))/2;
        return result;
    }

    class MySelectorDecorator implements DayViewDecorator  {
        private final Drawable drawable;

        MySelectorDecorator(Drawable drawable) {
            this.drawable = drawable;
        }

        public MySelectorDecorator(Activity context){
            drawable = context.getResources().getDrawable(R.drawable.my_selector);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return true;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setSelectionDrawable(drawable);
        }
    }

    class EventDecorator implements DayViewDecorator{
        private final int color;
        private final HashSet<CalendarDay> dates;
        public EventDecorator(int color, Collection<CalendarDay> dates){
            this.color = color;
            this.dates = new HashSet<>(dates);
        }
        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new DotSpan(5,color));
        }
    }


    class customTask extends AsyncTask<String,Void,String> {
        String sendMsg,receiveMsg;
        protected String doInBackground(String... strings) {
            try {
                String str;

                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/getUserCalender_view");  // 어떤 서버에 요청할지(localhost 안됨.)
                // ex) http://123.456.789.10:8080/hello/android
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //데이터를 POST 방식으로 전송합니다.
                conn.setDoOutput(true);

                /*
                Log.w("(초기)앱에서 보낸 값", id +", "+today_date+", "+sleepTime+", "+exerciseTime+", "+waterIntake+", "+startDay
                        +", "+endDay+", "+symptom+", "+mucus);//+water
                        */

                // 서버에 보낼 값 포함해 요청함.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&today_date="+strings[1]+
                        "&sleepTime="+strings[2]+"&exerciseTime="+strings[3]+"&waterIntake="+strings[4]+"&startDay="+strings[5]+
                        "&endDay="+strings[6]+"&symptom="+strings[7]+"&mucus="+strings[8];
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

    class getTask extends AsyncTask<String,Void,String> {
        String sendMsg,receiveMsg;
        protected String doInBackground(String... strings) {
            try {
                String str;

                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/setUserCalender_view");  // 어떤 서버에 요청할지(localhost 안됨.)
                // ex) http://123.456.789.10:8080/hello/android
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //데이터를 POST 방식으로 전송합니다.
                conn.setDoOutput(true);

                // 서버에 보낼 값 포함해 요청함.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&today_date="+strings[1];
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


    class getDate extends AsyncTask<String,Void,String> {
        String sendMsg,receiveMsg;
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/getDate_view");  // 어떤 서버에 요청할지(localhost 안됨.)
                // ex) http://123.456.789.10:8080/hello/android
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //데이터를 POST 방식으로 전송합니다.
                conn.setDoOutput(true);

                // 서버에 보낼 값 포함해 요청함.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&selected_date="+strings[1];
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
    class getMonth extends AsyncTask<String,Void,String> {
        String sendMsg,receiveMsg;
        protected String doInBackground(String... strings) {
            try {
                String str;

                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/getMonth_view");  // 어떤 서버에 요청할지(localhost 안됨.)
                // ex) http://123.456.789.10:8080/hello/android
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //데이터를 POST 방식으로 전송합니다.
                conn.setDoOutput(true);

                // 서버에 보낼 값 포함해 요청함.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&month="+strings[1];
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