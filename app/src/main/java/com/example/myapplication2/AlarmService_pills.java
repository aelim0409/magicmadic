package com.example.myapplication2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class AlarmService_pills extends Service {

    String TAG = "TAG+Service";
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags,int startId)
    {
        Log.d(TAG,"AlarmService");
        Intent alarmIntent= new Intent(getApplicationContext(),AlarmActivity_pills.class);
        startActivity(alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        return super.onStartCommand(intent,flags,startId);
    }
}
