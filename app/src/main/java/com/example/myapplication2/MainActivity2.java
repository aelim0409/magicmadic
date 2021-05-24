package com.example.myapplication2;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class MainActivity2 extends Activity implements OnDateSelectedListener {
    TextView date_today;
    TextView main_date_view;
    MaterialCalendarView cal_view;
    Button symptoms1,symptoms2,symptoms3,symptoms4,symptoms5,symptoms6,symptoms7,symptoms8,symptoms9,symptoms10,symptoms11,symptoms12;
    Button mucus1,mucus2,mucus3, mucus4,mucus5,mucus6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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

        //save_button=findViewById(R.id.save_button);

        //save_button.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
//
         //   }
       // });
        symptoms1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        symptoms2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        symptoms3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        symptoms4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        symptoms5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        symptoms6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        symptoms7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        symptoms8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        symptoms9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });symptoms10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        symptoms11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        symptoms12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mucus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mucus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mucus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mucus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mucus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mucus6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ImageButton waterplus, waterminus, exerciseplus, exerciseminus, sleepplus, sleepminus;
        TextView water, exerciseH, exerciseM, sleepH, sleepM;


        waterminus = (ImageButton)findViewById(R.id.waterminus);
        waterplus = (ImageButton)findViewById(R.id.waterplus);
        water = (TextView)findViewById(R.id.water_record);
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

        exerciseplus = (ImageButton)findViewById(R.id.exerciseplus);
        exerciseminus = (ImageButton)findViewById(R.id.exercisemnus);
        exerciseH = (TextView)findViewById(R.id.exercise_recordH);
        exerciseM = (TextView)findViewById(R.id.exercise_recordM);

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
//                Intent intent = new Intent(getApplicationContext(),graph.class);
//                startActivity(intent);
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

        sleepplus = (ImageButton)findViewById(R.id.sleepplus);
        sleepminus = (ImageButton)findViewById(R.id.sleepminus);
        sleepH = (TextView)findViewById(R.id.sleep_recordH);
        sleepM = (TextView)findViewById(R.id.sleep_recordM);

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

        cal_view.setSelectedDate(CalendarDay.today());


//        int n=30; //시작일자 서버로 받아오기 or 시작 버튼 눌리면 넘겨서 날짜 받기
//        int month1=5; //시작버튼을 누른 달 받아오기
//        int duration=5; // 예측 기간 서버로 받아오기 & default 5
//        Calendar c = Calendar.getInstance();
//        int lastDay = c.getActualMaximum(5);    //선택된 달의 마지막 일자
//        for(int i=0;i<duration;i++){
//            cal_view.addDecorators(new EventDecorator(Color.RED, Collections.singleton(CalendarDay.from(2021,month1,n++))));
//            if(n==lastDay){   //달을 넘겨가며 생리가 이어질 경우 다음달로 초기화 해주기 위함
//                n=1;
//                month1++;
//            }
//        }


        Button start_button = (Button)findViewById(R.id.start_button);
        Button end_button = (Button)findViewById(R.id.end_button);

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date =  main_date_view.getText().toString();
                try {
                    Date current = SelectedDate(date);
                    int day = CalendarDay.from(current).getDay();
                    int month = CalendarDay.from(current).getMonth();
                    int year = CalendarDay.from(current).getYear();
                    CalendarDay.from(current).getDay();
                    Calendar cc = Calendar.getInstance();
                    int lastDay = cc.getActualMaximum(month);
                    for(int i=0;i<5;i++){
                        cal_view.addDecorators(new EventDecorator(Color.RED, Collections.singleton(CalendarDay.from(2021,month,day++))));
                        if(day==lastDay){   //달을 넘겨가며 생리가 이어질 경우 다음달로 초기화 해주기 위함
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

            }
        });



    }

    public Date SelectedDate(String str) throws ParseException {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy년MM월dd일");
        Date to = transFormat.parse(str);
        return to;
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        main_date_view.setText(date.getYear() + "년" + (date.getMonth()+1) + "월" + date.getDay() + "일");
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


}
