package com.example.myapplication2;
//package 수상
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ChooseDateActivity extends Activity {
    TextView date_today;
    TextView main_date_view;
    ScrollView scroll_container;
    LinearLayout main_content_view;
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
    Button move_button;
    RelativeLayout moveLayout;
    RelativeLayout.LayoutParams moveParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        scroll_container = findViewById(R.id.scroll_container);

        params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 40);
        zeroParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);

        main_content_view = findViewById(R.id.main_memo_container);

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
                year = y; month = m+1; day = d;
                main_date_view.setText(state_str+" ("+year+"/"+month+"/"+day+")");
                fileName = year + "." + (month+1) + "." + day; // (업데이트 시 절대로 변경해선 안 됨!!)
                loadDB();

                Calendar currentPointing = Calendar.getInstance();
                currentPointing.set(y,m,d);
                pointingDate = currentPointing.getTimeInMillis();

                TextView move_text = findViewById(R.id.date_to_move_text);

                move_text.setText("("+year + "/" + month + "/" + day + ") 로 이동 → ");

                hideCheckBox();
            }
        });

        hideCheckBox();

        move_button = findViewById(R.id.move_button);
        move_button.setOnClickListener(new MoveDateListener());

        moveParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        moveParams.setMargins(20, 5, 20, 15);
        moveParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        moveLayout = findViewById(R.id.move_layout);
        moveLayout.setLayoutParams(moveParams);
    }

    class MoveDateListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            bundle.putInt("yearMoved",year);
            bundle.putInt("monthMoved",month);
            bundle.putInt("dayMoved",day);
            intent.putExtras(bundle);
            setResult(1, intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        setResult(0, intent);
        finish();
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
        main_content_view.removeAllViews();
        fileGetter = getSharedPreferences("StateStorage", 0);
        state = fileGetter.getInt("state",0);
        state_str = getStateString(state);
        main_date_view.setText(state_str+" ("+year+"/"+month+"/"+day+")");

        fileGetter = getSharedPreferences(fileName, 0); // 클릭한 날짜에 해당되는 File의 DB 객체 생성
        final LayoutInflater layout_inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE); // inflater 생성

        // DB로부터 데이터를 불러오고 메모들을 초기화시킴
        totalNum = fileGetter.getInt("num", 0);
        for (int i = 0; i < totalNum; i++) {
            String str = fileGetter.getString("memo" + i, "오류가 발생했습니다");
            RelativeLayout memo = (RelativeLayout) layout_inflater.inflate(R.layout.activity_choose_date, null);
            if(state==0) memo.setLayoutParams(params);
            else sizeControl(memo, i);
            ((GradientDrawable) memo.getBackground()).setColor(getResources().getColor(R.color.memoPaper));

            // 체크박스 초기화
            CheckBox checkBox = (CheckBox) memo.getChildAt(1);
            checkBox.setChecked(loadDBCheckBox(i));
            EditText text = (EditText)((RelativeLayout)checkBox.getParent()).getChildAt(0);
            if(checkBox.isChecked()==true){ // 체크 ON
                text.setPaintFlags(text.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            }
            else{ //체크 OFF
                text.setPaintFlags(0);
            }

            main_content_view.addView(memo); // 메모지 추가
            EditText edittext = (EditText) memo.getChildAt(0);
            edittext.setText(str); // 메모지에 글자 추가
            edittext.setFocusable(false);
        }
    }

    private void sizeControl(RelativeLayout memo, int i){ // 보기 방식에 따른 설정
        LinearLayout.LayoutParams zeroParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);
        fileGetter = getSharedPreferences(fileName, 0);
        boolean checked = fileGetter.getBoolean("check"+i,false);
        switch (state) {
            case 0:
                memo.setLayoutParams(params);
                break;
            case 1:
                if (checked) memo.setLayoutParams(zeroParams);
                else memo.setLayoutParams(params);
                break;
            case 2:
                if(!checked) memo.setLayoutParams(zeroParams);
                else memo.setLayoutParams(params);
                break;
            default:
                break;
        }
    }

    private boolean loadDBCheckBox(int index){
        fileGetter = getSharedPreferences(fileName, 0);
        return fileGetter.getBoolean("check"+index,false);
    }

    private void hideCheckBox(){ // 체크박스를 가림
        for(int i=0; i<main_content_view.getChildCount(); i++){
            ((RelativeLayout)main_content_view.getChildAt(i)).getChildAt(1).setVisibility(View.INVISIBLE);
        }
    }

}
