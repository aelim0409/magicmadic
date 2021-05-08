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
    boolean autoClickForDateChange;

    @Override
    protected void onResume() {
        super.onResume();
        loadDB();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView todayText = findViewById(R.id.today_text);
        todayText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int y = cal.get(Calendar.YEAR);
                int m = cal.get(Calendar.MONTH);
                int d = cal.get(Calendar.DAY_OF_MONTH);
                markDate(y,m,d);
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

        params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 40);
        zeroParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);

        main_content_view = findViewById(R.id.main_memo_container);
        main_content_view.setOnTouchListener(new GestureListener());

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
        main_content_view.removeAllViews();
        fileGetter = getSharedPreferences("StateStorage", 0);
        state = fileGetter.getInt("state",0);
        state_str = getStateString(state);
        main_date_view.setText(state_str+" ("+year+"/"+month+"/"+day+")");

        fileGetter = getSharedPreferences(fileName, 0); // 클릭한 날짜에 해당되는 File의 DB 객체 생성
        final LayoutInflater layout_inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE); // inflater 생성

        // DB로부터 데이터를 불러오고 메모들을 초기화시킴
        totalNum = fileGetter.getInt("num", 0);

        // 메모 추가 안내 문구 설정
        TextView guideText = new TextView(this);
        guideText.setText("이곳을 눌러 추가하세요 +\n (좌우 스와이프로 날짜 이동 가능)");
        guideText.setTextColor(getResources().getColor(R.color.colorDarkGray));
        guideText.setTextSize(15);
        guideText.setGravity(Gravity.CENTER);
        if(totalNum==0) {
            if(state!=2) main_content_view.addView(guideText);

            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) main_content_view.getLayoutParams();
            main_content_view.setPadding(0,50,0,0);
            main_content_view.setLayoutParams(lp);
        }
        else {
            main_content_view.removeView(guideText);

            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) main_content_view.getLayoutParams();
            main_content_view.setPadding(0,0,0,0);
            main_content_view.setLayoutParams(lp);
        }

        for (int i = 0; i < totalNum; i++) {
            String str = fileGetter.getString("memo" + i, "오류가 발생했습니다");
            RelativeLayout memo = (RelativeLayout) layout_inflater.inflate(R.layout.activity_choose_date, null);
            sizeControl(memo, i);
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
            ((CheckBox)memo.getChildAt(1)).setOnCheckedChangeListener(new CheckBoxListener());
            ((CheckBox)memo.getChildAt(1)).setOnClickListener(new HidePaperByStateListener());

            main_content_view.addView(memo); // 메모지 추가
            EditText edittext = (EditText) memo.getChildAt(0);
            edittext.setText(str); // 메모지에 글자 추가
            edittext.setFocusable(false);
            memo.setOnTouchListener(new GestureListener());
            edittext.setOnTouchListener(new GestureListener());
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

    private CheckBox getCheckBoxAt(int index){
        return (CheckBox)((RelativeLayout)main_content_view.getChildAt(index)).getChildAt(1);
    }

    private void saveDBCheckBox(int index, boolean checked){
        SharedPreferences.Editor editor = getSharedPreferences(fileName, 0).edit();
        editor.putBoolean("check"+index, checked).commit();
    }

    private boolean loadDBCheckBox(int index){
        fileGetter = getSharedPreferences(fileName, 0);
        return fileGetter.getBoolean("check"+index,false);
    }

    private class HidePaperByStateListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            int index = main_content_view.indexOfChild((RelativeLayout)view.getParent());
            hidePaperByState(index);
            if(((CheckBox)view).isChecked())
                Toast.makeText(getBaseContext(),"완료한 일 목록으로 이동",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getBaseContext(),"해야할 일 목록으로 이동",Toast.LENGTH_SHORT).show();
        }
    }

    private void hidePaperByState(int index){
        if(state==0) return;

        // remove와 add를 통해 위로 한 칸씩 당겨주는 작업
        main_content_view.getChildAt(index).setLayoutParams(zeroParams);
        for(; index<totalNum-1; index++){
            RelativeLayout temp = (RelativeLayout)main_content_view.getChildAt(index);
            main_content_view.removeViewAt(index);
            main_content_view.addView(temp,index);
        }
    }

    private class CheckBoxListener implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton view, boolean checked) { // 체크박스를 누를 때 일어날 일
            checked = ((CheckBox) view).isChecked();
            EditText text = (EditText)((RelativeLayout)view.getParent()).getChildAt(0);
            int index = ((LinearLayout)view.getParent().getParent()).indexOfChild((RelativeLayout)view.getParent());
            if(checked==true){ // 체크 ON
                text.setPaintFlags(text.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG); // 취소선
                saveDBCheckBox(index, true);
            }
            else{ //체크 OFF
                text.setPaintFlags(0);
                saveDBCheckBox(index, false);
            }
        }
    }

    private void pageChange(){
        Intent intent = new Intent(MainActivity2.this, ModifyingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); // 빠른 연타 시 액티비티가 2개 생성 되는 것을 방지
        intent.putExtra("year", year);
        intent.putExtra("month", month);
        intent.putExtra("day", day);
        intent.putExtra("fileName",fileName);
        intent.putExtra("auto", autoClickForDateChange);
        startActivityForResult(intent,1);
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

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
                pageChange();
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                pageChange();
                return super.onDoubleTap(e);
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                pageChange();
                return super.onSingleTapConfirmed(e);
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
            switch(menuItem.getItemId()){
                case R.id.show_all: // '전체 목록'을 눌렀을 때
                    state = 0;
                    break;
                case R.id.show_not_done: // '해야할 일'을 눌렀을 때
                    state = 1;
                    break;
                case R.id.show_completed: // '완료한 일'을 눌렀을 때
                    state = 2;
                    break;
                case R.id.move_date: // '날짜 변경'을 눌렀을 때
                    autoClickForDateChange = true;
                    pageChange();
                    autoClickForDateChange = false;
                    break;
                default: // 그밖의 모든 메뉴를 눌렀을 때
                    return false;
            }
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
