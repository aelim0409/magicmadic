package com.example.myapplication2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReciver_sleep  extends BroadcastReceiver {
    public static final String ACTION_RESTART_SERVICE="Restart";
    @Override
    public void onReceive(Context context, Intent intent){
        if(intent.getAction().equals(ACTION_RESTART_SERVICE)){
            Log.d("alarm리시버","리시버 실행");
            Intent in =new Intent(context,AlarmService_sleep.class);
            context.startService(in);
        }
    }

}
