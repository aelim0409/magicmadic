package com.example.myapplication2;


/*
public class AlarmReciver_pills extends Thread{
    Handler handler;
    boolean isRun = true;

    public AlarmReciver_pills(Handler handler){
        this.handler = handler;
    }

    public void stopForever(){
        synchronized (this) {
            this.isRun = false;
        }
    }

    public void run(){
        //반복적으로 수행할 작업을 한다.
        while(isRun){
            handler.sendEmptyMessage(0);//쓰레드에 있는 핸들러에게 메세지를 보냄
            try{
                Thread.sleep(10000); //10초씩 쉰다.
            }catch (Exception e) {}
        }
    }
}

*/


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReciver_pills extends BroadcastReceiver {
    public static final String ACTION_RESTART_SERVICE="Restart";
    @Override
    public void onReceive(Context context, Intent intent){
        if(intent.getAction().equals(ACTION_RESTART_SERVICE)){
            Log.d("alarm리시버","리시버 실행");
            Intent in =new Intent(context,AlarmService_pills.class);
            context.startService(in);
        }
    }

}


