package com.example.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;



public class Selftest0 extends AppCompatActivity implements View.OnClickListener {
    int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selftest0);

        Button btn_back = (Button) findViewById(R.id.test0back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Selftest0.this, Selftest_main.class);
                startActivity(intent);
            }
        });

        Button btn_done = (Button) findViewById(R.id.test0done);
        btn_done.setOnClickListener(this);

        Intent intent1 = getIntent();

        findViewById(R.id.check1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });
        findViewById(R.id.check2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });
    }

    public int Checked(View v){
        CheckBox check1 = (CheckBox) findViewById(R.id.check1);
        CheckBox check2 = (CheckBox) findViewById(R.id.check2);
        CheckBox check3 = (CheckBox) findViewById(R.id.check3);
        CheckBox check4 = (CheckBox) findViewById(R.id.check4);
        CheckBox check5 = (CheckBox) findViewById(R.id.check5);
        CheckBox check6 = (CheckBox) findViewById(R.id.check6);
        CheckBox check7 = (CheckBox) findViewById(R.id.check7);
        CheckBox check8 = (CheckBox) findViewById(R.id.check8);

        int score = 0;
        if(check1.isChecked()) score++;
        if(check2.isChecked()) score++;
        if(check3.isChecked()) score++;
        if(check4.isChecked()) score++;
        if(check5.isChecked()) score++;
        if(check6.isChecked()) score++;
        if(check7.isChecked()) score++;
        if(check8.isChecked()) score++;

        return score;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.test0done){
            Intent intent = new Intent(this, SelftestResult.class);
            intent.putExtra("score", Checked(v));
            startActivity(intent);
        }
    }
}