package com.example.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity2 extends Activity {
    TextView date_today;
    TextView main_date_view;
    ScrollView scroll_container;
    //LinearLayout main_content_view;
    String fileName; // (업데이트 시 절대로 변경해선 안 됨!!)
    RelativeLayout.LayoutParams params;
    LinearLayout.LayoutParams zeroParams;
    GestureDetector detector;
    CalendarView cal_view;
    long pointingDate;
    int state;
    SharedPreferences fileGetter;
    String state_str;
    int totalNum;
    int year, month, day; // 업데이트 시 절대로 변경해선 안 됨!! (사용자들이 이미 쓰고 있는 DB 파일 이름 때문)
    boolean autoClickForDateChange;

    Button []symptoms;
    Button []mucus;

    @Override
    protected void onResume() {
        super.onResume();
        loadDB();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView todayText = findViewById(R.id.today_text);

        symptoms[0]=findViewById(R.id.symptom1);
        symptoms[1]=findViewById(R.id.symptom2);
        symptoms[2]=findViewById(R.id.symptom3);
        symptoms[3]=findViewById(R.id.symptom4);
        symptoms[4]=findViewById(R.id.symptom5);
        symptoms[5]=findViewById(R.id.symptom6);
        symptoms[6]=findViewById(R.id.symptom7);
        symptoms[7]=findViewById(R.id.symptom8);
        symptoms[8]=findViewById(R.id.symptom9);
        symptoms[9]=findViewById(R.id.symptom10);
        symptoms[10]=findViewById(R.id.symptom11);
        symptoms[11]=findViewById(R.id.symptom12);

        mucus[0]=findViewById(R.id.mucus1);
        mucus[1]=findViewById(R.id.mucus2);
        mucus[2]=findViewById(R.id.mucus3);
        mucus[3]=findViewById(R.id.mucus4);
        mucus[4]=findViewById(R.id.mucus5);
        mucus[5]=findViewById(R.id.mucus6);

        symptoms[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        symptoms[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        symptoms[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        symptoms[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        symptoms[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        symptoms[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        symptoms[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        symptoms[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        symptoms[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        symptoms[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });symptoms[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        symptoms[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        symptoms[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mucus[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mucus[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mucus[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mucus[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mucus[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mucus[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mucus[6].setOnClickListener(new View.OnClickListener() {
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
                markDate(y,m,d);

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


            }
        });

        date_today = findViewById(R.id.date_today);
        date_today.setText(new SimpleDateFormat("yyyy년 M월 d일", Locale.getDefault()).format(new Date())); // 오늘 날짜 초기화

        main_date_view = findViewById(R.id.date_text);

        fileGetter = getSharedPreferences("StateStorage", 0);
        state = fileGetter.getInt("state",0);
        state_str = getStateString(state);

        String tempDate = new SimpleDateFormat("yyyy/M/d", Locale.getDefault()).format(new Date());
        String[] parseDate = tempDate.split("/");
        year = Integer.parseInt(parseDate[0]);
        month = Integer.parseInt(parseDate[1]);
        day = Integer.parseInt(parseDate[2]);

        main_date_view.setText(state_str+" ("+year+"/"+month+"/"+day+")");

        ImageButton menuButton = findViewById(R.id.sub_menu_button);
        menuButton.setOnClickListener(new MenuListener());

        scroll_container = findViewById(R.id.scroll_container);


        cal_view = findViewById(R.id.calendar);
        pointingDate = cal_view.getDate();

        fileName =year+"."+(month+1)+"."+day;// 오늘 날짜로 화면 초기화 (업데이트 시 절대로 변경해선 안 됨!!)
        loadDB();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            cal_view.setSelectedWeekBackgroundColor(getResources().getColor(R.color.brown));
        }
        cal_view.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int y, int m, int d) {
                markDate(y,m,d);
            }
        });
    }

    private void markDate(int y, int m, int d){
        year = y; month = m+1; day = d;
        main_date_view.setText(state_str+" ("+year+"/"+month+"/"+day+")");
        fileName = year + "." + (month+1) + "." + day; // (업데이트 시 절대로 변경해선 안 됨!!)
        loadDB();

        Calendar currentPointing = Calendar.getInstance();
        currentPointing.set(y,m,d);
        pointingDate = currentPointing.getTimeInMillis();
        cal_view.setDate(pointingDate);
    }

    private String getStateString(int state){
        switch (state){
            case 0:
                return "전체 목록";
            case 1:
                return "해야할 일";
            case 2:
                return "완료한 일";
            default:
                return null;
        }
    }

    private void loadDB(){
        fileGetter = getSharedPreferences("StateStorage", 0);
        state = fileGetter.getInt("state",0);
        state_str = getStateString(state);
        main_date_view.setText(state_str+" ("+year+"/"+month+"/"+day+")");

        fileGetter = getSharedPreferences(fileName, 0); // 클릭한 날짜에 해당되는 File의 DB 객체 생성
        final LayoutInflater layout_inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE); // inflater 생성

    }




    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==1){
            int y = data.getIntExtra("y",0);
            int m = data.getIntExtra("m",0)-1;
            int d = data.getIntExtra("d",0);
            markDate(y,m,d);
        }
    }

    class GestureListener implements View.OnTouchListener {
        private GestureDetector gestureDetector = new GestureDetector(MainActivity2.this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
                // 상하 제스처는 무시
                if(Math.abs(event1.getY()-event2.getY())>200){
                    return true;
                }

                long date = pointingDate; // long 타입
                Calendar rightNow = Calendar.getInstance(); // long 타입
                rightNow.setTimeInMillis(date);
                if(event1.getX()-event2.getX()<-1){//오른쪽
                    rightNow.add(Calendar.DATE, -1); // yesterday
                }
                else if(event1.getX()-event2.getX()>1){//왼쪽
                    rightNow.add(Calendar.DATE, 1); // tomorrow
                }
                pointingDate = rightNow.getTimeInMillis();
                cal_view.setDate(pointingDate);

                int y = rightNow.get(Calendar.YEAR);
                int m = rightNow.get(Calendar.MONTH);
                int d = rightNow.get(Calendar.DAY_OF_MONTH);
                year = y; month = m+1; day = d;

                main_date_view.setText(state_str+" ("+year+"/"+month+"/"+day+")");
                fileName = year + "." + (month+1) + "." + day; // (업데이트 시 절대로 변경해선 안 됨!!)
                loadDB();
                return true;
            }
        });

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            gestureDetector.onTouchEvent(event);
            return true;
        }
    }

    private class MenuListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            PopupMenu menu = new PopupMenu(getBaseContext(), view);
            getMenuInflater().inflate(R.menu.sub_menu, menu.getMenu());
            menu.setOnMenuItemClickListener(new MenuItemListener());
            menu.getMenu().getItem(0).getSubMenu().getItem(state).setChecked(true);
            menu.show();
        }
    }

    private class MenuItemListener implements PopupMenu.OnMenuItemClickListener{
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            saveState();
            state_str = getStateString(state);
            main_date_view.setText(state_str+" ("+year+"/"+month+"/"+day+")");
            loadDB();

            menuItem.setChecked(!menuItem.isChecked());
            return true;
        }
    }

    private void saveState(){
        fileGetter = getSharedPreferences("StateStorage", 0);
        SharedPreferences.Editor editor = fileGetter.edit();
        editor.putInt("state", state).commit();
    }
}
