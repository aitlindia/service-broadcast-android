package com.aitl.androidserviceapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";

    public static final String mBroadcastStringAction = "com.truiton.broadcast.string";
    public static final String mBroadcastIntegerAction = "com.truiton.broadcast.integer";
    public static final String mBroadcastArrayListAction = "com.truiton.broadcast.arraylist";


    private TextView mTextView;
    private IntentFilter mIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.counterTV);

        mIntentFilter = new IntentFilter();

        mIntentFilter.addAction(mBroadcastStringAction);
        mIntentFilter.addAction(mBroadcastIntegerAction);
        mIntentFilter.addAction(mBroadcastArrayListAction);

    }

    public void StartServiceOnClick(View view) {

        Intent intent = new Intent(this,MyCounterService.class);
        startService(intent);
    }

    public void StopServiceOnclick(View view) {
        Intent intent = new Intent(this,MyCounterService.class);
        stopService(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(mReceiver,mIntentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(mReceiver);
        super.onPause();
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {

            //mTextView.setText(mTextView.getText()+ "Broadcast From Service: \n");

            int count = intent.getIntExtra("count", 0);
            String strcnt = String.valueOf(count);
            Log.d(TAG,"Data Count: "+ strcnt);
            mTextView.setText("Count Value: "+strcnt);
            Log.d(TAG,"Action Type: "+intent.getAction());


            if (intent.getAction().equals(mBroadcastStringAction)) {
                mTextView.setText(mTextView.getText()
                        + intent.getStringExtra("Data") + "\n\n");
            } else if (intent.getAction().equals(mBroadcastIntegerAction)) {

            }
            /**/
        }
    };


    public void StartActivity(View view) {

        Intent mintent = new Intent(this,Main2Activity.class);
        mintent.putExtra("name_key","Advance Integrated Tech Lab");
        startActivity(mintent);
    }
}
