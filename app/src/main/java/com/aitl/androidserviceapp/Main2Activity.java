package com.aitl.androidserviceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView tv = (TextView) findViewById(R.id.tv);

        Intent mi = getIntent();

        String name = mi.getStringExtra("name_key");

        tv.setText(name);

    }


}
