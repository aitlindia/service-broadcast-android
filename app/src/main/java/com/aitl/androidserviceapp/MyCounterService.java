package com.aitl.androidserviceapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyCounterService extends Service {

    String TAG = "MyCounterService";


    boolean startstopflag = false;


    int counter = 0;

    public MyCounterService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "Activity on Start Command...");

        startstopflag = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(startstopflag){
                    counter++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent mybroatcastIntent = new Intent();
                    mybroatcastIntent.setAction(MainActivity.mBroadcastIntegerAction);
                    mybroatcastIntent.putExtra("count",counter);
                    sendBroadcast(mybroatcastIntent);
                }
            }
        }).start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        startstopflag = false;

        Log.d(TAG, "Activity on Destroy...");
    }


}
