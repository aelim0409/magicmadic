package com.example.myapplication2;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;

import java.util.Calendar;

public class AlarmActivity_physiology extends AppCompatActivity {
    Calendar calendar;
    SwipeButton swipeButton;
    TextView timeText;
    MediaPlayer mediaPlayer;
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_alarm_physiology);
            Log.w("알람 페이지", "실행");
            calendar = Calendar.getInstance();
            swipeButton = (SwipeButton) findViewById(R.id.swipe_btn_physiology);
            timeText = (TextView) findViewById(R.id.time_phy);

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
            // 잠금화면 위로 띄어줌

            // mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.beep);
            // mediaPlayer.setLooping(true);
            // mediaPlayer.start();


            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (flag == true) {
                        try {
                            calendar = Calendar.getInstance();
                            if (calendar.get(Calendar.HOUR_OF_DAY) > 0 && calendar.get(Calendar.HOUR_OF_DAY) < 12) {
                                timeText.setText("AM" + calendar.get(Calendar.HOUR_OF_DAY) + "시 " + calendar.get(Calendar.MINUTE) + "분 ");

                            } else if (calendar.get(Calendar.HOUR_OF_DAY) == 12) {
                                timeText.setText("PM" + calendar.get(Calendar.HOUR_OF_DAY) + "시 " + calendar.get(Calendar.MINUTE) + "분 ");

                            } else if (calendar.get(Calendar.HOUR_OF_DAY) > 12 && calendar.get(Calendar.HOUR_OF_DAY) < 24) {
                                timeText.setText("PM" + calendar.get(Calendar.HOUR_OF_DAY) + "시 " + calendar.get(Calendar.MINUTE) + "분 ");

                            } else if (calendar.get(Calendar.HOUR_OF_DAY) == 0 || calendar.get(Calendar.HOUR_OF_DAY) == 24) {
                                timeText.setText("AM 0시 " + calendar.get(Calendar.MINUTE) + "분 ");

                            }
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }).start(); //실시간 시계 출력

            swipeButton.setOnStateChangeListener(new OnStateChangeListener() {
                @Override
                public void onStateChange(boolean active) {
                    // mediaPlayer.stop();
                    flag = false;
                    Toast.makeText(getApplicationContext(),"종료하였습니다",Toast.LENGTH_LONG).show();
                    finish();
                }
            });//스와이프 밀어서 해제

        }

    }
}
