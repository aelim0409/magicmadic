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
    Button symptoms1,symptoms2,symptoms3,symptoms4,symptoms5,symptoms6,symptoms7,symptoms8,symptoms9,symptoms10,symptoms11,symptoms12;
    Button mucus1,mucus2,mucus3, mucus4,mucus5,mucus6;
    int selectedYear, selectedMonth, selectedDay;
    String setSelectedDate;
    String ID_selected;

    public void give_symptom(String ID,String none, String []a)
    {
        Log.w("symptom 후기 설정", "설정 정보 주는중");
        try {
            String id = ID;

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
            String today_date = sdf.format(date);


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
            Log.w("(초기)앱에서 보낸 값", id +", "+today_date+", "+none+", "
                    +cramps+", "+breastTenderness+", "+headache+", "+acne+", "+lumbago+", "
                    +", "+nausea+", "+fatigue+", "+abdominalBloating+", "+
                    desires+", "+insomnia+", "+constipation+", "+diarrhea);//+water
            MainActivity2.setSymptom task = new MainActivity2.setSymptom();
            String result = task.execute(id,today_date,none, cramps, breastTenderness,
                    headache, acne, lumbago, nausea,
                    fatigue, abdominalBloating, desires,
                    insomnia, constipation, diarrhea).get();
            Log.w("(초기)받은값", result);

            //return result;

        } catch (Exception e) {

        }
    }

    public void give_mucus(String ID,String none,String []b)
    {

        Log.w("symptom 후기 설정", "설정 정보 주는중");

        try {
            String id = ID;

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
            String today_date = sdf.format(date);

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
            Log.w("(초기)앱에서 보낸 값", id +", "+today_date+", "+none+", "
                    +mottled+", "+sticky+", "+creamy+", "+likeEggWhite+", "+watery+", "
                    +", "+abnormal);//+water
            MainActivity2.setMucus task = new MainActivity2.setMucus();
            String result = task.execute(id,today_date,none, mottled, sticky,
                    creamy, likeEggWhite, watery, abnormal).get();
            Log.w("(초기)받은값", result);

            //return result;

        } catch (Exception e) {

        }

    }

    public String giveSelectedInformation(String ID, String setSelectedDate){
        Log.w("선택된 날짜 정보", "설정 정보 주는중");

        String result="null";

        try {
            String id = ID;

            //Date date = new Date();
           // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

            Log.w("(초기)앱에서 보낸 값", id +", "+setSelectedDate);//+water
            MainActivity2.setDate task = new MainActivity2.setDate();
            result = task.execute(id,setSelectedDate).get();
            Log.w("(초기)받은값", result);

            //return result;

        } catch (Exception e) {

        }return result;
    }
    public String getSelectedInformation(String ID, String setSelectedDate){
        Log.w("선택한 날짜 정보 받기", "설정 정보 받는중");

        String result="null";

        try {
            String id = ID;
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

            Log.w("(초기)앱에서 보낸 값", id +", "+setSelectedDate);//+water
            MainActivity2.getDate task = new MainActivity2.getDate();
            result = task.execute(id,setSelectedDate).get();
            Log.w("(초기)받은값", result);

            //return result;

        } catch (Exception e) {

        }return result;
    }
    public String getInformation(String ID) {

        Log.w("리마인더 온오프 설정", "설정 정보 주는중");

        String result="null";

        try {
            String id = ID;

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
            String today_date = sdf.format(date);

            Log.w("(초기)앱에서 보낸 값", id +", "+today_date);//+water
            MainActivity2.getTask task = new MainActivity2.getTask();
            result = task.execute(id,today_date).get();
            Log.w("(초기)받은값", result);

            //return result;

        } catch (Exception e) {

        }return result;
    }

    public String get_symptom_info(String ID) {
        Log.w("리마인더 온오프 설정", "설정 정보 주는중");

        String result="null";

        try {
            String id = ID;

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
            String today_date = sdf.format(date);

            Log.w("(초기)앱에서 보낸 값", id +", "+today_date);//+water
            MainActivity2.getSymptom task = new MainActivity2.getSymptom();
            result = task.execute(id,today_date).get();
            Log.w("(초기)받은값", result);

            //return result;

        } catch (Exception e) {

        }return result;
    }

    public String get_mucus_info(String ID) {
        Log.w("리마인더 온오프 설정", "설정 정보 주는중");

        String result="null";

        try {
            String id = ID;

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
            String today_date = sdf.format(date);

            Log.w("(초기)앱에서 보낸 값", id +", "+today_date);//+water
            MainActivity2.getMucus task = new MainActivity2.getMucus();
            result = task.execute(id,today_date).get();
            Log.w("(초기)받은값", result);

            //return result;

        } catch (Exception e) {

        }return result;
    }

    String start_day_input="null";
    String end_day_input="null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent Intent = getIntent();

        String ID = Intent.getStringExtra("Id");
        ID_selected=ID;
        String [] init_info = getInformation(ID).split(" ");
        String [] selected_info = getSelectedInformation(ID,setSelectedDate).split(" ");

        String sleep_time= init_info[1];
        String exercise_time = init_info[2];
        String water_intake= init_info[3];
        String start_day= init_info[4];
        String end_day=init_info[5];
        String symptom_init=init_info[6];
        String mucus_init=init_info[7];

        String selected_sleep_time= selected_info[1];
        String selected_exercise_time = selected_info[2];
        String selected_water_intake= selected_info[3];
        String selected_start_day= selected_info[4];
        String selected_end_day=selected_info[5];
        String selected_symptom_init=selected_info[6];
        String selected_mucus_init=selected_info[7];

        String mucus_none= "false";
        String symptom_none="false";
        String [] init_symptoms ={"false","false","false","false","false","false","false","false","false","false","false","false"};
        String [] mucus_symptoms={"false","false","false","false","false","false"};


        ImageButton waterplus, waterminus, exerciseplus, exerciseminus, sleepplus, sleepminus;
        TextView water, exerciseH, exerciseM, sleepH, sleepM;

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

        if(symptom_init.equals("true"))
        {
            String [] symptom_init_info = get_symptom_info(ID).split(" ");
            //index 1 부터가 symptom "true"/"fasle"

            for(int i=0;i<12;i++)
            {
                init_symptoms[i]=symptom_init_info[i+1];
            }
            /*
            cramps=symptom_init_info[1];
            breastTenderness=symptom_init_info[2];
             headache = symptom_init_info[3];
             acne= symptom_init_info[4];
             lumbago = symptom_init_info[5];
             nausea = symptom_init_info[6];
             fatigue = symptom_init_info[7];
             abdominalBloating =symptom_init_info[8];
              desires = symptom_init_info[9];
             insomnia =symptom_init_info[10];
             constipation= symptom_init_info[11];
             diarrhea = symptom_init_info[12];
             //초기화해줄 값
            String symptom_1=symptom_init_info[1];
            String symptom_2=symptom_init_info[2];
            String symptom_3 = symptom_init_info[3];
            String symptom_4= symptom_init_info[4];
            String symptom_5 = symptom_init_info[5];
            String symptom_6 = symptom_init_info[6];
            String symptom_7 = symptom_init_info[7];
            String symptom_8 =symptom_init_info[8];
            String symptom_9 = symptom_init_info[9];
            String symptom_10 =symptom_init_info[10];
            String symptom_11= symptom_init_info[11];
            String symptom_12 = symptom_init_info[12];
             */
        }
        if(mucus_init.equals("true"))
        {
            String [] mucus_init_info = get_mucus_info(ID).split(" ");
            for(int i=0;i<12;i++)
            {
                mucus_symptoms[i]=mucus_init_info[i+1];
            }
            String mucus_1=mucus_init_info[1];
            String mucus_2=mucus_init_info[2];
            String mucus_3= mucus_init_info[3];
            String mucus_4 = mucus_init_info[4];
            String mucus_5=mucus_init_info[5];
            String mucus_6=mucus_init_info[6];
        }

        TextView todayText = findViewById(R.id.today_text);

        symptoms1=findViewById(R.id.symptom1);
        symptoms2=findViewById(R.id.symptom2);
        symptoms3=findViewById(R.id.symptom3);
        symptoms4=findViewById(R.id.symptom4);
        symptoms5=findViewById(R.id.symptom5);
        symptoms6=findViewById(R.id.symptom6);
        symptoms7=findViewById(R.id.symptom7);
        symptoms8=findViewById(R.id.symptom8);
        symptoms9=findViewById(R.id.symptom9);
        symptoms10=findViewById(R.id.symptom10);
        symptoms11=findViewById(R.id.symptom11);
        symptoms12=findViewById(R.id.symptom12);

        mucus1=findViewById(R.id.mucus1);
        mucus2=findViewById(R.id.mucus2);
        mucus3=findViewById(R.id.mucus3);
        mucus4=findViewById(R.id.mucus4);
        mucus5=findViewById(R.id.mucus5);
        mucus6=findViewById(R.id.mucus6);


        String [] symptom_Check={"false","false","false","false","false","false","false","false","false","false","false","false"};
        symptoms1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                symptom_Check[0]="true";
            }
        });
        symptoms2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                symptom_Check[1]="true";
            }
        });
        symptoms3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                symptom_Check[2]="true";
            }
        });
        symptoms4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                symptom_Check[3]="true";
            }
        });
        symptoms5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                symptom_Check[4]="true";
            }
        });
        symptoms6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                symptom_Check[5]="true";
            }
        });
        symptoms7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                symptom_Check[6]="true";
            }
        });
        symptoms8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                symptom_Check[7]="true";

            }
        });
        symptoms9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                symptom_Check[8]="true";
            }
        });
        symptoms10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                symptom_Check[9]="true";
            }
        });
        symptoms11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                symptom_Check[10]="true";
            }
        });
        symptoms12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                symptom_Check[11]="true";
            }
        });


        for(int i=0;i<12;i++)
        {
                if ((symptom_Check[i].equals( "false") )&& (init_symptoms[i].equals("true")))
                    symptom_Check[i] = "true";
                else if ((symptom_Check[i] .equals("true")) && (init_symptoms[i].equals("false")))
                    symptom_Check[i] = "true";
                else if ((symptom_Check[i].equals( "false")) && (init_symptoms[i].equals("false")))
                    symptom_Check[i] = "false";
                else if ((symptom_Check[i].equals( "true")) && (init_symptoms[i].equals("true")))
                    symptom_Check[i] = "false";

        }


        for(int i =0;i<12;i++)
        {
            if(symptom_Check[i].equals("true")) {
                symptom_none="true";
            }
        }

        String symptom_none2=symptom_none;

        String cramps = symptom_Check[0];
        String breastTenderness=symptom_Check[1];
        String headache=symptom_Check[2];
        String acne=symptom_Check[3];
        String lumbago=symptom_Check[4];
        String nausea =symptom_Check[5];
        String fatigue=symptom_Check[6];
        String abdominalBloating=symptom_Check[7];
        String desires=symptom_Check[8];
        String insomnia=symptom_Check[9];
        String constipation=symptom_Check[10];
        String diarrhea=symptom_Check[11];

        String [] mucus_Check={"false","false","false","false","false","false"};

        mucus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mucus_Check[0]="true";
            }
        });
        mucus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mucus_Check[1]="true";
            }
        });
        mucus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mucus_Check[2]="true";
            }
        });
        mucus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mucus_Check[3]="true";
            }
        });
        mucus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mucus_Check[4]="true";
            }
        });
        mucus6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mucus_Check[5]="true";
            }
        });


        for(int i=0;i<6;i++)
        {
            if ((mucus_Check[i].equals( "false") )&& (mucus_symptoms[i].equals("true")))
                mucus_Check[i] = "true";
            else if ((mucus_Check[i] .equals("true")) && (mucus_symptoms[i].equals("false")))
                mucus_Check[i] = "true";
            else if ((mucus_Check[i].equals( "false")) && (mucus_symptoms[i].equals("false")))
                mucus_Check[i] = "false";
            else if ((mucus_Check[i].equals( "true")) && (mucus_symptoms[i].equals("true")))
                mucus_Check[i] = "false";
        }

        for(int i =0;i<6;i++)
        {
            if(mucus_Check[i].equals("true"))
                mucus_none="true";
        }

        String mucus_none2= mucus_none;


        String mottled=mucus_Check[0];
        String sticky= mucus_Check[1];
        String creamy=mucus_Check[2];
        String likeEggWhite=mucus_Check[3];
        String watery=mucus_Check[4];
        String abnormal = mucus_Check[5];



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
        date_today.setText(new SimpleDateFormat("yyyy년 M월 d일", Locale.getDefault()).format(new Date())); // 오늘 날짜 초기화

        main_date_view = findViewById(R.id.date_text);
        main_date_view.setText(new SimpleDateFormat("yyyy년 M월 d일", Locale.getDefault()).format(new Date())); // 오늘 날짜 초기화
        cal_view = (MaterialCalendarView)findViewById(R.id.calendar);

        cal_view.setOnDateChangedListener(this);
        cal_view.setOnMonthChangedListener((OnMonthChangedListener) this);
        cal_view.setSelectedDate(CalendarDay.today());

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
                    String sleepTime=sleepH+":"+sleepM+":"+"00";
                    String exerciseTime=exerciseH+":"+exerciseM+":"+"00";
                    String waterIntake = water.getText().toString();
                    String startDay=start_day_input;
                    String endDay=end_day_input;
                    String symptom = symptom_none2;
                    String mucus=mucus_none2;
                    String [] symptom_Check2= new String[12];
                    String [] mucus_Check2=new String [6];

                    for(int i=0;i<12;i++)
                    {
                        symptom_Check2[i]=symptom_Check[i];
                    }

                    for(int i=0;i<6;i++)
                    {
                        mucus_Check2[i]=mucus_Check[i];
                    }

                    if(symptom.equals("true"))
                    {
                        //symptom table 보내주기
                        give_symptom(ID,symptom,symptom_Check2);
                    }

                    if(mucus.equals("true"))
                    {
                        //mucus table 보내주기
                        give_mucus(ID,mucus,mucus_Check2);
                    }

                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
                    String today_date = sdf.format(date);

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

        Button start_button = (Button)findViewById(R.id.start_button);
        Button end_button = (Button)findViewById(R.id.end_button);
        //String Startdate="null";
        final Date[] start = new Date[1];
        final Date[] end = new Date[1];

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date =  main_date_view.getText().toString();
                try {
                    start[0] = SelectedDate(date);
                    start_day_input= start[0].toString();
                    int day = CalendarDay.from(start[0]).getDay();
                    int month = CalendarDay.from(start[0]).getMonth();
                    int year = CalendarDay.from(start[0]).getYear();
                    int period=5;
                    CalendarDay.from(start[0]).getDay();
                    Calendar cc = Calendar.getInstance();
                    System.out.println(setSelectedDate);

                    int[] mdays = {31,28,31,30,31,30,31,31,30,31,30,31};
                    int lastDay = mdays[month];

                    System.out.println(lastDay);
                    for(int i=0;i<period;i++){
                        cal_view.addDecorators(new EventDecorator(Color.RED, Collections.singleton(CalendarDay.from(year,month,day))));
                        day++;
                        if(day>lastDay){   //달을 넘겨가며 생리가 이어질 경우 다음달로 초기화 해주기 위함
                            day=1;
                            month++;
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });


        end_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date =  main_date_view.getText().toString();
                try {
                    end[0] = SelectedDate(date);

                    int day = CalendarDay.from(end[0]).getDay();
                    int month = CalendarDay.from(end[0]).getMonth();
                    int year = CalendarDay.from(end[0]).getYear();
                    CalendarDay.from(end[0]).getDay();
                    Calendar cc = Calendar.getInstance();
                    int[] mdays = {31,28,31,30,31,30,31,31,30,31,30,31};
                    int lastDay = mdays[month];
                    for(int i=0;i<5;i++){
                        cal_view.addDecorators(new EventDecorator(Color.parseColor("#EDFAFF"), Collections.singleton(CalendarDay.from(year,month,day))));
                        day+=1;
                        if(day>lastDay){   //달을 넘겨가며 생리가 이어질 경우 다음달로 초기화 해주기 위함
                            day=1;
                            month++;
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        end_day_input= end[0].toString();

    }

    public Date SelectedDate(String str) throws ParseException {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy년MM월dd일");
        Date to = transFormat.parse(str);
        return to;
    }

    //날짜 선택되었을 때
    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        main_date_view.setText(date.getYear() + "년" + (date.getMonth()+1) + "월" + date.getDay() + "일");
        selectedYear=date.getYear();
        selectedMonth=date.getMonth()+1;
        selectedDay=date.getDay();
        setSelectedDate=selectedYear+"-"+selectedMonth+"-"+selectedDay;

        giveSelectedInformation(ID_selected, setSelectedDate);
        getSelectedInformation(ID_selected,setSelectedDate);

    }

    //달 바뀌었을 때
    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

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




    //getTask, customTask
    class customTask extends AsyncTask<String,Void,String> {
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

                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/getUserCalender_view");  // 어떤 서버에 요청할지(localhost 안됨.)
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

    class setSymptom extends AsyncTask<String,Void,String> {
        String sendMsg,receiveMsg;
        protected String doInBackground(String... strings) {
            try {
                String str;

                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/setSymptom_view");  // 어떤 서버에 요청할지(localhost 안됨.)
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
                sendMsg = "id="+strings[0]+"&today_date="+strings[1]+"&none="+strings[2]+
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

    class setMucus extends AsyncTask<String,Void,String> {
        String sendMsg,receiveMsg;
        protected String doInBackground(String... strings) {
            try {
                String str;

                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/setMucus_view");  // 어떤 서버에 요청할지(localhost 안됨.)
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
                sendMsg = "id="+strings[0]+"&today_date="+strings[1]+"&none="+strings[2]+
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

    class setDate extends AsyncTask<String,Void,String> {
        String sendMsg,receiveMsg;
        protected String doInBackground(String... strings) {
            try {
                String str;

                URL url = new URL("http://3.36.134.232:8080/MedicMagic_SPRING/setDate_view");  // 어떤 서버에 요청할지(localhost 안됨.)
                // ex) http://123.456.789.10:8080/hello/android
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //데이터를 POST 방식으로 전송합니다.
                conn.setDoOutput(true);

                // 서버에 보낼 값 포함해 요청함.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&selected_date="+setSelectedDate;
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
                sendMsg = "id="+strings[0]+"&selected_date="+setSelectedDate;
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
